package main.utility;

import main.exceptions.NullConstructorException;

public class BaseObject {

	public BaseObject(){
	}
	
	public BaseObject(Object object){
		nullConstrArgCheck(object);
	}
	
	public boolean nullConstrArgCheck(Object object){
		if(object == null){
			throw new NullConstructorException();
		}
		return true;
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
	
	/*
	public int rndInt(){
		return (int) Math.random();
	}
	*/
}
