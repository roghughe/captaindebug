package com.captaindebug.store.beans;

import java.util.Collections;
import java.util.List;

/**
 * Models the user's selections - data returned by the form.
 * 
 * @author Roger
 * 
 *         Created 07:33:38 21 Apr 2013
 * 
 */
public class UserSelections {

	private List<String> selection = Collections.emptyList();

	public List<String> getSelection() {
		return selection;
	}

	public void setSelection(List<String> selection) {
		this.selection = selection;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Selections are: ");

		for (String str : selection) {
			sb.append(str);
			sb.append(",  ");
		}

		return sb.toString();
	}
}
