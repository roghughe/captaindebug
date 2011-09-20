/**
 * Copyright 2011 Marin Solutions
 */
package dependency_injection_with_annotations.testclasses;

import dependency_injection_with_annotations.annotations.MyAutoWire;
import dependency_injection_with_annotations.annotations.MyComponent;

/**
 * @author Roger
 * 
 */
@MyComponent
public class ClassWithAttributes {

	private String str1;

	private int value1;

	@MyAutoWire
	private InjectedClass injectedClass;

	@MyAutoWire
	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public InjectedClass getInjectedClass() {
		return injectedClass;
	}

	public void setInjectedClass(InjectedClass injectedClass) {
		this.injectedClass = injectedClass;
	}

	public int getValue1() {
		return value1;
	}
}
