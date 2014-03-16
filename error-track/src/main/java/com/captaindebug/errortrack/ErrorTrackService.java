package com.captaindebug.errortrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.captaindebug.errortrack.file.FileLocator;
import com.captaindebug.errortrack.report.Results;

@Service
public class ErrorTrackService {

	@Autowired
	private FileLocator fileLocator;

	@Autowired
	private Results results;

	@Autowired
	@Qualifier("textFormatter")
	private Formatter formatter;

	@Autowired
	@Qualifier("emailPublisher")
	private Publisher publisher;

	public void trackErrors() {

		fileLocator.findFile();
		results.generate(formatter, publisher);
	}

}
