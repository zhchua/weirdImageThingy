package utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import exceptions.NullConstructorException;

public class BaseObject {

	/** Default constructor.
	 * 
	 */
	public BaseObject(){
	}
	
	/** Create a deep copy of given BaseObject. <br>
	 * Overwrite this for ALL child classes for deepcopying.
	 * 
	 * @throws NullConstructorException if param is NULL.
	 * @param Object given to deepcopy from.
	 */
	public BaseObject(Object givenObject){	
		if(givenObject == null){
			throw new NullConstructorException();
		}
	}
	
	/** Generates hashcode from a string of this object's fields.
	 * @return Integer hashcode of this object.
	 */ 
	@Override
	public int hashCode(){
		return Objects.hash(this.getObjectDetails());
	}
	
	/** Checks if given object is the same class as this object.
	 * 
	 * @param Object given
	 * @return Boolean whether given object is the same class as this.
	 */
	private boolean isSameClass(Object givenObject){
		return (givenObject.getClass().getName().equals(this.getClass().getName()));
	}
	
	private boolean isEqualPrimitiveValue(Field field, Object givenObject){
		field.setAccessible(true);
		try{
			if(field.getGenericType() == float.class 
					|| field.getGenericType() == double.class
					|| field.getGenericType() == Float.class
					|| field.getGenericType() == Double.class){
				return (flEq(field.getDouble(this), field.getDouble(givenObject)));
			}
			else{
				return field.get(this) == field.get(givenObject);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// to do: add checking for list/arraylist fields (order-sensitive)
	// to do: add checking for array fields
	// actually those should be impossible lol too many possibilities
	private boolean isEqualFieldValues(Object givenObject){
		
		List<Field> givenFA = this.getAllFields(givenObject);

		for(int i = 0; i < givenFA.size(); i++){
			givenFA.get(i).setAccessible(true);
			try {
				if(givenFA.get(i).get(this).getClass().isPrimitive() 
						|| isWrapperType(givenFA.get(i).get(this).getClass())){
					if(!isEqualPrimitiveValue(givenFA.get(i), givenObject)){
						return false;
					}
				}
				else {
					if(!givenFA.get(i).get(this).equals(givenFA.get(i).get(givenObject))){
							return false;
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private List<Field> getAllFields(){
		return getAllFields(this);
	}
	
	/** Get list of all fields in this object. <br>
	 * This breaks encapsulation but oh well... <br>
	 * 
	 * @param Given object
	 * @return List of fields in given object
	 */
	private List<Field> getAllFields(Object object) {
		
	    List<Field> result = new ArrayList<Field>();

	    Class<?> i = object.getClass();
	    // Class of given object cannot be null, Object, primitive or wrapped prim!
	    while (i != null && i != Object.class && !i.isPrimitive()
	    		&& !isWrapperType(i) && i != Collections.class && i != List.class) {
	        Collections.addAll(result, i.getDeclaredFields());
	        i = i.getSuperclass();
	    }
	    return result;
	}

	/** Reimplementation of equals for objects extending BaseObject. <br>
	 * Checks for equality of field values rather than memory location. <br>
	 * If a field is floating point (float or double), checks equality with flEq.
	 * <br>
	 * Two equal BaseObject-descendants may not have the same hashcode if
	 * floating point fields are present.
	 * 
	 * @param An object to compare field equality with
	 * @return Boolean whether objects are field-equal
	 */
	@Override
	public boolean equals(Object givenObject){
		if(!this.isComparableWith(givenObject)){
			return false;
		}		
		return this.isEqualFieldValues(givenObject);
	}
	
	/** Checks if two objects have the same hash.
	 * 
	 * @param An object to compare hashcode with
	 * @return Boolean whether both objects have the same hashcode
	 */
	public boolean equalHash(Object givenObject){
		if(!this.isComparableWith(givenObject)){
			return false;
		}		
		return this.hashCode() == givenObject.hashCode();
	}
	
	/** Checks if a given object is comparable with this object. <br>
	 * Objects are not comparable if given object is null, primitive, 
	 * wrapped primitive or not the same class.
	 * 
	 * @param An object to compare with
	 * @return Boolean if objects are not comparable
	 */
	public boolean isComparableWith(Object givenObject){
		if(givenObject == null || givenObject.getClass().isPrimitive()
				|| isWrapperType(givenObject.getClass()) 
				|| !this.isSameClass(givenObject)){
				return false;
			}
		return true;
	}
	
	/** Determines equality between two floating point numbers 
	 * to  <br>
	 * Usual error should not exceed 1 part in 2^53 for doubles...
	 * 
	 * @param First double number to compare
	 * @param Second double number to compare 
	 * @return Boolean whether both doubles are equal within error margin.
	 */
	public boolean flEq(double fl1, double fl2){
		double errMar1 = (0.000001) * fl1;
		double errMar2 = (0.000001) * fl2;
		
		return (Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1 - fl2) < errMar2);
	}
	
	/** Basically a toString function.
	 * 
	 * @return String containing fields of this object.
	 */
	public String getObjectDetails(){
		return getObjectDetails(this);
	}
	
	/** Checks if given class is a wrapped primitive <br> i.e. Boolean, Integer, 
	 * Character, Byte, Short, Double, Long and Float.
	 * 
	 * @param A given class
	 * @return Boolean whether given class is a wrapped primitive.
	 */
	private static boolean isWrapperType(Class<?> clazz) {
	    return clazz.equals(Boolean.class) || 
	        clazz.equals(Integer.class) ||
	        clazz.equals(Character.class) ||
	        clazz.equals(Byte.class) ||
	        clazz.equals(Short.class) ||
	        clazz.equals(Double.class) ||
	        clazz.equals(Long.class) ||
	        clazz.equals(Float.class);
	}

	/** Returns a string consisting of class name and fields.
	 * 
	 * @return
	 */
	public String getObjectDetails(Object object) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		List<Field> lf = getAllFields(object);
		for(int i = 0; i < lf.size(); i++){
			lf.get(i).setAccessible(true);	
			try {
				sb.append(" " + lf.get(i).get(object).getClass().getSimpleName() 
						+ " " 
						+ lf.get(i).getName()  + " = " 
						+ lf.get(i).get(object));
				
				if(!lf.get(i).get(object).getClass().isPrimitive() 
						&& !isWrapperType(lf.get(i).get(object).getClass())){
					sb.append(getObjectDetails(lf.get(i).get(object)));
				}
			} 
			catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			lf.get(i).setAccessible(false);
			if(i != lf.size() - 1){
				sb.append(",");
			}
		}
		sb.append(" }");
		return sb.toString();
	}
	
	/** Shortcut to System.out.println
	 * 
	 * @param str
	 */
	public void print(Object str){
		System.out.println(str.toString());
	}
	
	/** GetString with a given varName appended to the front.
	 * 
	 * @param varName
	 */
	public void prnObjInfo(Object object){
		print(object.getClass().getName() + " = " 
				+ /*object.getClass().getSimpleName() + */
				getObjectDetails(object));
	}
	
	/** GetString with a given varName appended to the front.
	 * 
	 * @param varName
	 */
	public void prnObjInfo(){
		prnObjInfo(this);
	}
	
	public boolean denyNullArgs(){
		List<Field> fa = this.getAllFields();
		if(fa.size() > 0){
			for(int i = 0; i < fa.size(); i++){
				try {
					if(fa.get(i).get(this) == null){
						throw new NullConstructorException();
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
