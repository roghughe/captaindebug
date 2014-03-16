package com.captaindebug.errortrack.report;

import org.springframework.stereotype.Service;

import com.captaindebug.errortrack.Formatter;

@Service
public class HtmlFormatter implements Formatter {

	@Override
	public <T> T format(Results report) {
		// TODO Add this some other day...
		return null;
	}

}
