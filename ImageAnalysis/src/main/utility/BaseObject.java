package main.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import main.exceptions.NullConstructorException;

public class BaseObject {

	public BaseObject(){
	}
	
	/**
	 * 
	 * @param givenObject
	 */
	public BaseObject(Object givenObject){	
		if(givenObject == null){
			throw new NullConstructorException();
		}
	}
	
	public int hashCode(){
		return Objects.hash(this.getObjectDetails());
	}
	
	private boolean isSameClass(Object givenObject){
		return (givenObject.getClass().getName() == this.getClass().getName());
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
	
	private List<Field> getAllFields(Object object) {
		
	    List<Field> result = new ArrayList<Field>();

	    Class<?> i = object.getClass();
	    while (i != null && i != Object.class && !i.isPrimitive()) {
	        Collections.addAll(result, i.getDeclaredFields());
	        i = i.getSuperclass();
	    }

	    return result;
	}

	/** Reimplementation of equals for objects extending BaseObject.
	 * Checks for equality of field values rather than memory location.
	 * If a field is floating point (float or double), checks equality with flEq.
	 * Two equal BaseObject-descendants may not have the same hashcode if
	 * floating point fields are present.
	 * 
	 */
	@Override
	public boolean equals(Object givenObject){
		if(givenObject == null || givenObject.getClass().isPrimitive()
			|| isWrapperType(givenObject.getClass()) 
			|| !this.isSameClass(givenObject)){
			return false;
		}		
		return this.isEqualFieldValues(givenObject);
	}
		
		
		
	
	/** Determines equality between two floating pt numbers 
	 * to 0.001% precision of either value
	 * 
	 * @param fl1
	 * @param fl2
	 * @return
	 */
	public boolean flEq(double fl1, double fl2){
		double errMar1 = (0.000001) * fl1;
		double errMar2 = (0.000001) * fl2;
		
		return (Math.abs(fl1 - fl2) < errMar1 && Math.abs(fl1 - fl2) < errMar2);
	}
	
	/** Returns a string consisting of class name and fields.
	 * 
	 * @return
	 */
	/*
	public String getString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.getClass().getSimpleName() + "{");
		
		List<Field> lf = this.getAllFields();
		for(int i = 0; i < lf.size(); i++){
			lf.get(i).setAccessible(true);
			try {
				sb.append(" " + lf.get(i).get(this).getClass().getSimpleName() + " " 
						+ lf.get(i).getName()  + " = " 
						+ lf.get(i).get(this));
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
	*/
	
	public String getObjectDetails(){
		return getObjectDetails(this);
	}
	
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
				sb.append(" " + lf.get(i).get(object).getClass().getSimpleName() + " " 
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
