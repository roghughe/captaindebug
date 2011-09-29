/**
 * 
 */
package com.captaindebug;

/**
 * @author RogerHughes
 * 
 */
public class UsesNewToInstantiateClass {

	public String createThing() {

		AnyOldClass myclass = new AnyOldClass();

		String returnValue = myclass.someMethod();
		return returnValue;
	}
}
