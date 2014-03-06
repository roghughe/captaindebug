package com.captaindebug.errortrack.validator;

import java.io.File;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.captaindebug.errortrack.Validator;

/**
 * Validate the age of a file, if it's recent enough then it will return true.
 * 
 * @author Roger
 * 
 *         Created 17:20:33 15 Feb 2014
 * 
 */
@Service
public class FileAgeValidator implements Validator {

	@Value("${max.days}")
	private int maxDays;

	/**
	 * Validate the age of the file.
	 * 
	 * @see com.captaindebug.errortrack.Validator#validate(java.lang.Object)
	 */
	@Override
	public <T> boolean validate(T obj) {

		File file = (File) obj;
		Calendar fileDate = getFileDate(file);

		Calendar ageLimit = getFileAgeLimit();

		boolean retVal = false;
		if (fileDate.after(ageLimit)) {
			retVal = true;
		}

		return retVal;
	}

	private Calendar getFileAgeLimit() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1 * maxDays);
		return cal;
	}

	private Calendar getFileDate(File file) {

		long fileDate = file.lastModified();
		Calendar when = Calendar.getInstance();
		when.setTimeInMillis(fileDate);
		return when;
	}

}
