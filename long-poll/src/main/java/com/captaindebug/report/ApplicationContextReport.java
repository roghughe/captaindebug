package com.captaindebug.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Simple class to send the contents an application context to a log4j --
 * 
 * @author Roger
 * 
 *         Created 10:54:05 19 Jan 2014
 * 
 */
@Service
public class ApplicationContextReport implements ApplicationContextAware, InitializingBean {

	private static final String LINE = "====================================================================================================\n";
	private static final Logger logger = LoggerFactory.getLogger("ContextReport");

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		report();
	}

	public void report() {

		StringBuilder sb = new StringBuilder("\n" + LINE);
		sb.append("Application Context Report\n");
		sb.append(LINE);

		createHeader(sb);
		createBody(sb);
		sb.append(LINE);

		logger.info(sb.toString());
	}

	private void createHeader(StringBuilder sb) {

		addField(sb, "Application Name: ", applicationContext.getApplicationName());
		addField(sb, "Display Name: ", applicationContext.getDisplayName());

		String startupDate = getStartupDate(applicationContext.getStartupDate());
		addField(sb, "Start Date: ", startupDate);

		Environment env = applicationContext.getEnvironment();
		String[] activeProfiles = env.getActiveProfiles();
		if (activeProfiles.length > 0) {
			addField(sb, "Active Profiles: ", activeProfiles);
		}
	}

	private void addField(StringBuilder sb, String name, String... values) {

		sb.append(name);
		for (String val : values) {
			sb.append(val);
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append("\n");
	}

	private String getStartupDate(long startupDate) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return df.format(new Date(startupDate));
	}

	private void createBody(StringBuilder sb) {
		addColumnHeaders(sb);
		addColumnValues(sb);
	}

	private void addColumnHeaders(StringBuilder sb) {
		sb.append("\nBean Name\tSimple Name\tSingleton\tFull Class Name\n");
		sb.append(LINE);
	}

	private void addColumnValues(StringBuilder sb) {
		String[] beanNames = applicationContext.getBeanDefinitionNames();

		for (String name : beanNames) {
			addRow(name, sb);
		}
	}

	private void addRow(String name, StringBuilder sb) {
		Object obj = applicationContext.getBean(name);

		String fullClassName = obj.getClass().getName();
		if (!fullClassName.contains("org.springframework")) {

			sb.append(name);
			sb.append("\t");
			String simpleName = obj.getClass().getSimpleName();
			sb.append(simpleName);
			sb.append("\t");
			boolean singleton = applicationContext.isSingleton(name);
			sb.append(singleton ? "YES" : "NO");
			sb.append("\t");
			sb.append(fullClassName);
			sb.append("\n");
		}
	}
}
