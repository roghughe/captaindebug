/*
 * XMLStates.java
 *
 * Created on 09 August 2006, 15:29
 *
 * Marin JavaTips. A set of micillaneous source code for training 
 * and educational purposes.
 *
 * Copyright (C) 2006  Roger Hughes
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package com.captaindebug.statemachine.unpackxml;

/**
 * This is the set of states for OUR particular application - it defines the
 * possible states.
 * 
 * @author Roger
 * @Date 09 August 2006
 * @Date Converted to Enum Easter 2012
 */
public enum XMLState {

	DEFAULT("Default", "Default handler - writes the data to the output stream"), //
	CAPTURE_START_TAG("Capture Start Tag", "Capture and process the start tag info"), //
	/**
	 * This is the start tag state - The associated action captures the start
	 * tag - if the tag is <CompressedPart> then the state is switched to
	 * UNCOMPRESS. If the first character processed is a '/' then we're
	 * processing an end tag - so send that out to the output stream. If the tag
	 * isn't <CompressedPart> then send the captured data to the output stream.
	 */
	/**
	 * This is the uncompress state. The associated action reads in, stores and
	 * then, on finding the </CompressedPart> end tag, uncompresses the data and
	 * bungs it out to the output stream.
	 */
	UNCOMPRESS("Uncompress", "Uncompress the bytes in the input stream");

	private final String name;
	private final String description;

	XMLState(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {

		return "XmlState: " + name + " - " + description;
	}

}
