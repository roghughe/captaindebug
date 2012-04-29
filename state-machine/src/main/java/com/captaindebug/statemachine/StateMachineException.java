package com.captaindebug.statemachine;

/*
 *
 * Marin JavaTips. A set of miscellaneous source code for training
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
/**
 * Simple exception handling class.
 * 
 * @author Roger
 * 
 */
public class StateMachineException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StateMachineException() {
		super();
	}

	public StateMachineException(String s) {
		super(s);
	}

	public StateMachineException(String s, Throwable cause) {
		super(s, cause);
	}

}
