package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DButil {
	private static Properties properties;
	public static void setProperties(Properties properties) {
		DButil.properties = properties;
	}
	public static Connection getConnection( )throws Exception{
		Class.forName(properties.getProperty("Driver"));
		return DriverManager.getConnection(properties.getProperty("Url"), properties.getProperty("User"), properties.getProperty("Password"));
	} 
}
