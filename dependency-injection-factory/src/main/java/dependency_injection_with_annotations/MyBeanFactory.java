/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations;

/**
 * @author Roger
 * 
 */
public interface MyBeanFactory {

	/**
	 * Method that returns a bean instance given a type
	 */
	public <T> T getBean(Class<T> clazz);

}
