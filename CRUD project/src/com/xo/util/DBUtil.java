package com.xo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static Connection connection;
	
	public static Connection getConnection() {
		if( connection != null )
			return connection;
		
		InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream( "/db.properties" );
		Properties properties = new Properties();
		try {
			properties.load( inputStream );
			String driver = properties.getProperty( "driver" );
			String url = properties.getProperty( "url" );
			String user = properties.getProperty( "user" );
			String password = properties.getProperty( "password" );
			Class.forName( driver );
			connection = DriverManager.getConnection( url, user, password );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void closeConnection( Connection toBeClosed ) {
		if( toBeClosed == null )
			return;
		try {
			toBeClosed.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
