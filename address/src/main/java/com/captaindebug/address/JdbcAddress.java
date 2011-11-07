/*
 * JdbcTestDao.java
 *
 * Created on 30 October 2006, 16:13
 */

package com.captaindebug.address;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

/**
 * Simple JDBC Data Access object that gets hold of an address object, given an id
 */
@Component
public class JdbcAddress extends JdbcDaoSupport implements AddressDao {

	/**
	 * This is an instance of the query object that'll sort out the results of the SQL and produce whatever values
	 * objects are required
	 */
	private MyQueryClass query;

	/** This is the SQL with which to run this DAO */
	private static final String sql = "select * from addresses where id = ?";

	/**
	 * A class that does the mapping of row data into a value object.
	 */
	class MyQueryClass extends MappingSqlQuery<Address> {

		public MyQueryClass(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		/**
		 * This the implementation of the MappingSqlQuery abstract method. This method creates and returns a instance of
		 * our value object associated with the table / select statement.
		 * 
		 * @param rs
		 *            This is the current ResultSet
		 * @param rowNum
		 *            The rowNum
		 * @throws SQLException
		 *             This is taken care of by the Spring stuff...
		 */
		@Override
		protected Address mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new Address(rs.getInt("pkseq"), rs.getString("street"), rs.getString("town"), rs.getString("post_code"),
					rs.getString("country"));
		}
	}

	/**
	 * Override the JdbcDaoSupport method of this name, calling the super class so that things get set-up correctly and
	 * then create the inner query class.
	 */
	@Override
	protected void initDao() throws Exception {
		super.initDao();
		query = new MyQueryClass(getDataSource(), sql);
	}

	/**
	 * Return an address object based upon it's id
	 */
	public Address findAddress(int id) {
		return query.findObject(id);
	}

}
