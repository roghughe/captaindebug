/**
 * 
 */
package com.captaindebug.siteproperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Class written to be as big as mess as possible, doing lots of things and
 * breaking the Single Responsibility Principal
 */
public class SitePropertiesManager {

	private static final String sql = "select * from site properties";

	private final String serialfileName = "";

	private String url;

	private String username;

	private String password;

	private PreparedStatement ps;
	private static long MILLIS_IN_HOUR = 1000 * 60 * 60;

	private long lastUpdateTime;

	private SitePropertiesManager INSTANCE;

	public SitePropertiesManager getInstance() {
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void init() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);

		ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String propertyKey = rs.getString("propkey");
			String propertyValue = rs.getString("propval");
			String locale = rs.getString("locale");

		}
		ps.close();

		// serialize the results

	}

	public void rebuild() throws Exception {

		long currentTime = System.currentTimeMillis();
		if (currentTime > (lastUpdateTime + MILLIS_IN_HOUR)) {
			lastUpdateTime = currentTime;

			// cut n paste init code
		}

	}

}
