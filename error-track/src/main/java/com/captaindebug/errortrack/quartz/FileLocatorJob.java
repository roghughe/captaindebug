package com.captaindebug.errortrack.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.captaindebug.errortrack.file.FileLocator;

public class FileLocatorJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(FileLocatorJob.class);

	@Autowired
	private FileLocator fileLocator;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			logger.info("Running FileLocatorJob..");
			fileLocator.findFile();
		} catch (Exception e) {
			logger.error("Error in file locator job: " + e.getMessage(), e);
			throw new JobExecutionException(e);
		}
	}

}
