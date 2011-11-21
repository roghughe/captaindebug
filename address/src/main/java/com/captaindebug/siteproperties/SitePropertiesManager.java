/**
 * 
 */
package com.captaindebug.siteproperties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Class written to be as big as mess as possible, doing lots of things and
 * breaking the Single Responsibility Principal
 */
public class SitePropertiesManager implements Serializable, PropertiesManager {

	private static final String GLOBAL_LOCALE = "gbl";
	private static final String sql = "select * from properties";

	private String url;

	private String username;

	private String password;

	private transient PreparedStatement ps;
	private static long MILLIS_IN_HOUR = 1000 * 60 * 60;

	private long lastUpdateTime;

	private static transient SitePropertiesManager INSTANCE;

	HashMap<String, HashMap<String, String>> theBigMap = new HashMap<String, HashMap<String, String>>();

	public static SitePropertiesManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SitePropertiesManager();
		}

		return INSTANCE;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void init() {

		long lastSaveTime = 0L;
		try {
			FileInputStream aFIS = new FileInputStream("/Users/Roger/my-site-props.ser");
			ObjectInputStream aOIS = new ObjectInputStream(aFIS);

			Object o = aOIS.readObject();
			SitePropertiesManager tmp = (SitePropertiesManager) o;
			lastSaveTime = tmp.getLastUpdateTime();
			System.out.println("last save time was: " + lastSaveTime);

			aFIS.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (lastSaveTime < System.currentTimeMillis() - MILLIS_IN_HOUR) {

			try {

				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);

				ps = con.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String propertyKey = rs.getString("propkey");
					String propertyValue = rs.getString("propval");
					String locale = rs.getString("locale");

					HashMap<String, String> localeMap = null;
					if (!theBigMap.containsKey(propertyKey)) {

						localeMap = new HashMap<String, String>();
						theBigMap.put(propertyKey, localeMap);
					} else {

						localeMap = theBigMap.get(locale);
					}

					if ("".equals(locale)) {
						locale = GLOBAL_LOCALE;
					}
					localeMap.put(locale, propertyValue);

					System.out.println("The results are: " + theBigMap);
				}
				ps.close();

				lastUpdateTime = System.currentTimeMillis();

			} catch (Exception e) {
				e.printStackTrace();
				// whoops do nothing about the error.
			}
			// serialize the results

			try {
				FileOutputStream aFOS = new FileOutputStream("/Users/Roger/my-site-props.ser");
				ObjectOutputStream aOOS = new ObjectOutputStream(aFOS);

				aOOS.writeObject(this);
				aOOS.flush();
				aFOS.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see com.captaindebug.siteproperties.PropertiesManager#findProperty(java.lang.String)
	 */
	@Override
	public String findProperty(String propertyName) {

		HashMap<String, String> localeMap = theBigMap.get(propertyName);
		String retVal = localeMap.get(GLOBAL_LOCALE);
		if (retVal == null) {
			retVal = localeMap.get("en_GB");
		}
		return retVal;
	}

	/**
	 * @see com.captaindebug.siteproperties.PropertiesManager#findProperty(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String findProperty(String propertyName, String locale) {
		HashMap<String, String> localeMap = theBigMap.get(propertyName);
		String retVal = localeMap.get(locale);
		return retVal;

	}

	/**
	 * @see com.captaindebug.siteproperties.PropertiesManager#findListProperty(java.lang.String)
	 */
	@Override
	public List<String> findListProperty(String propertyName) {
		HashMap<String, String> localeMap = theBigMap.get(propertyName);
		String retVal = localeMap.get(GLOBAL_LOCALE);
		if (retVal == null) {
			retVal = localeMap.get("en_GB");
		}

		String[] split = retVal.split(",");
		return Arrays.asList(split);
	}

	/**
	 * @see com.captaindebug.siteproperties.PropertiesManager#findListProperty(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<String> findListProperty(String propertyName, String locale) {
		HashMap<String, String> localeMap = theBigMap.get(propertyName);
		String retVal = localeMap.get(locale);
		String[] split = retVal.split(",");
		return Arrays.asList(split);

	}

	/**
	 * @see com.captaindebug.siteproperties.PropertiesManager#findIntProperty(java.lang.String)
	 */
	@Override
	public int findIntProperty(String propertyName) {

		HashMap<String, String> localeMap = theBigMap.get(propertyName);
		String retVal = localeMap.get(GLOBAL_LOCALE);
		if (retVal == null) {
			retVal = localeMap.get("en_GB");
		}
		return new Integer(retVal).intValue();
	}

	/**
	 * @see com.captaindebug.siteproperties.PropertiesManager#findIntProperty(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int findIntProperty(String propertyName, String locale) {

		HashMap<String, String> localeMap = theBigMap.get(propertyName);
		String retVal = localeMap.get(locale);
		return new Integer(retVal).intValue();
	}

	/*
	 * Use this to test the class
	 */
	public static void main(String[] args) {

		SitePropertiesManager test = SitePropertiesManager.getInstance();

		test.setPassword("experience");
		test.setUsername("root");
		test.setUrl("jdbc:mysql://localhost/junit");

		test.init();

		String property1 = test.findProperty("key5");
		System.out.println("Test is: " + property1);
	}
}
