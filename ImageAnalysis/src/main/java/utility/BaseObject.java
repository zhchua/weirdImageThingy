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
	
	/** Checks if a given field is float or double.
	 *
	 * @param Given field
	 * @return Boolean whether given field is float or double.
	 */
	private boolean isFloatingPoint(Field field){
		field.setAccessible(true);
		try{
			// Return true if given field is float or double.
			return (field.getGenericType() == float.class 
					|| field.getGenericType() == double.class
					|| field.getGenericType() == Float.class
					|| field.getGenericType() == Double.class);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** Get list of all fields in this object, including those inherited from
	 *  superclasses.
	 * @see #getAllFields(Object)
	 * @return List of fields in this object.
	 */
	private List<Field> getAllFields(){
		return getAllFields(this);
	}
	
	/** Get list of all fields in the given object, including those inherited from
	 * superclasses.				<br>
	 * Not recommended for anything other than objects of BaseObject descent.
	 * 
	 * @param Given object
	 * @return List of fields in given object
	 */
	private List<Field> getAllFields(Object object) {
		
	    List<Field> result = new ArrayList<Field>();

	    Class<?> i = object.getClass();
	    // Class of given object cannot be null, Object, primitive or wrapped prim!
	    while (i != null && i != Object.class && !i.isPrimitive()
	    		&& !isWrapperType(i)) {
	        Collections.addAll(result, i.getDeclaredFields());
	        i = i.getSuperclass();
	    }
	    return result;
	}

	/** Reimplementation of equals for objects extending BaseObject. <br>
	 * If a field is floating point (float or double), checks equality with flEq.
	 * <br>
	 * Two equal BaseObject-descendants may not have the same hashcode if
	 * floating point fields are present.<br>
	 * 
	 * @param An object to compare field equality with
	 * @return Boolean whether objects are field-equal
	 */
	@Override
	public boolean equals(Object givenObject){
		// Reject if given object is null or type not same
		if(givenObject == null || !givenObject.getClass().equals(this.getClass())){
			return false;
		}		
		// Get all declared fields of this object
		List<Field> thisFields = this.getAllFields();

		Field field = null;
		// Iterate through each field
		for(int i = 0; i < thisFields.size(); i++){
			field = thisFields.get(i);
			// Set accessible so private vars can also be compared
			field.setAccessible(true);
			try {
				// if field is a float/double, use flEq
				if(isFloatingPoint(field) && 
						!flEq(field.getDouble(this), field.getDouble(givenObject))){
					return false;
				}
				else {
					// call .equals, which should act as normal for other types
					// for BaseObject descents, this overriding .equals is invoked
					if(!field.get(this).equals(field.get(givenObject))){
							return false;
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/** Checks if two objects have the same hash.
	 * 
	 * @param An object to compare hashcode with
	 * @return Boolean whether both objects have the same hashcode
	 */
	public boolean equalHash(Object givenObject){
		// Reject if type not same
		if(givenObject.getClass().equals(this.getClass())){
			return false;
		}		
		return this.hashCode() == givenObject.hashCode();
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
		double errMar1 = (1/Math.pow(2, 26)) * fl1;
		double errMar2 = (1/Math.pow(2, 26)) * fl2;
		
		return (Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1 - fl2) < errMar2);
	}
	
	/** Basically a toString function.
	 * @see #getObjectDetails(Object)
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
	 * @param Given object to print field details from.
	 * @return
	 */
	public String getObjectDetails(Object object) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		List<Field> lf = getAllFields(object);
		Field field = null;
		for(int i = 0; i < lf.size(); i++){
			field = lf.get(i);
			// Set field to be accessible
			field.setAccessible(true);	
			try {
				// Appends classtype fieldname = fieldvalue to string
				sb.append(field.get(object).getClass().getSimpleName()  
						+ " " + field.getName()  + "=" 
						+ field.get(object));
				
				// Recursively calls if field is not primitive or wrapper
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
