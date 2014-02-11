/**
 * Copyright 2014 Marin Solutions
 */
package com.captaindebug.errortrack;

import java.io.File;

/**
 * Callback interface, event handler, observer - call it what you will.
 * Gets called by the FileLocator for every matching file.
 * @author Roger
 *
 */
public interface FileFoundHandler {

	/** Gets called when a file is found */
	public void foundFile(File file);
	
}
