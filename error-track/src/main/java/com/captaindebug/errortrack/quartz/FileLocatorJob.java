package com.captaindebug.errortrack.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.captaindebug.errortrack.file.FileLocator;

public class FileLocatorJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(FileLocatorJob.class);

	private final FileLocator fileLocator;

	public FileLocatorJob() {
		// Load the ContextSingletonBeanFactoryLocator
		BeanFactoryLocator factoryLocator = ContextSingletonBeanFactoryLocator.getInstance("classpath:beanRefContext.xml");

		// Get hold of the factory to use
		BeanFactoryReference ref = factoryLocator.useBeanFactory("nodeContextFactory");
		BeanFactory factory = ref.getFactory();
		// Get the file locator bean
		fileLocator = factory.getBean(FileLocator.class);
	}

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
