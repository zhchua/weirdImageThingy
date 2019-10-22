package main.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

import main.exceptions.NullConstructorException;

public class BaseObject {

	public BaseObject(){
	}
	
	public BaseObject(Object object){
		nullConstrArgCheck(object);
	}
	
	private boolean nullConstrArgCheck(Object object){
		if(object == null){
			throw new NullConstructorException();
		}
		return true;
	}
	
	public int hashCodeO() {
		ArrayList<Object> objs = new ArrayList<Object>();
		Field[] thisFA = this.getClass().getDeclaredFields();
		
		for(int i = 0; i < thisFA.length; i++){
			try {
				thisFA[i].setAccessible(true);
				objs.add(thisFA[i].get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		Object[] objA = objs.toArray();
		return Objects.hash(objA);
	}
	
	public int hashCode(){
		return Objects.hash(getString());
	}
	
	private boolean isSameClass(Object givenObject){
		return (givenObject.getClass().getName() == this.getClass().getName());
	}
	
	private boolean isEqualPrimitiveValue(Field field, Object givenObject){
		field.setAccessible(true);
		try{
			if(field.getGenericType() == float.class 
					|| field.getGenericType() == double.class){
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
		
		Field[] givenFA = givenObject.getClass().getDeclaredFields();

		for(int i = 0; i < givenFA.length; i++){
			givenFA[i].setAccessible(true);
			if(givenFA[i].getClass().isPrimitive()){
				if(!isEqualPrimitiveValue(givenFA[i], givenObject)){
					return false;
				}
			}
			else try {
				if(!givenFA[i].get(this).equals(givenFA[i].get(givenObject))){
						return false;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

	public boolean equals(Object givenObject){
		if(givenObject == null || givenObject.getClass().isPrimitive() 
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
	
	public void throwConstrException(){
		throw new NullConstructorException();
	}
	
	/** Returns a string consisting of class name and fields.
	 * 
	 * @return
	 */
	public String getString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.getClass().getName() + " : ");
		
		Field[] fa = this.getClass().getDeclaredFields();
		for(int i = 0; i < fa.length; i++){
			try {
				fa[i].setAccessible(true);
				sb.append(fa[i].getGenericType() + " " 
						+ fa[i].getName()  + " " 
						+ fa[i].get(this) + " ");
				fa[i].setAccessible(false);
				if(i != fa.length - 1){
					sb.append(", ");
				}
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sb.append(".");
		return sb.toString();
	}

	/** Shortcut to System.out.println
	 * 
	 * @param str
	 */
	public void print(String str){
		System.out.println(str);
	}
	
	/** GetString with a given varName appended to the front.
	 * 
	 * @param varName
	 */
	public void prnObjInfo(String varName){
		print(varName + " == " + getString());
	}
}
