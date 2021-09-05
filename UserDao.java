package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;
import utils.DButil;

public class UserDao implements Closeable {
	private Connection connection;
	private PreparedStatement insertStatement;
	private PreparedStatement validationStatement;
	public UserDao() throws Exception{
		this.connection = DButil.getConnection();
		this.validationStatement = this.connection.prepareStatement("SELECT * FROM users WHERE email=? and password=?");
	} 
	public int insert(User user) throws Exception {
		this.insertStatement.setString(1, user.getFullName());
		this.insertStatement.setString(2, user.getEmail());
		this.insertStatement.setString(3, user.getPassword());
		this.insertStatement.setString(4, user.getPhoneNumber());
		this.insertStatement.setString(5, user.getCountry());
		return this.insertStatement.executeUpdate();
	}
	public User validate( String email, String password )throws Exception{
		this.validationStatement.setString(1, email);
		this.validationStatement.setString(2, password);
		try( ResultSet rs = this.validationStatement.executeQuery()){
			if( rs.next()) 
				return new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		}
		return null;
	}
	@Override
	public void close() throws IOException {
		try {
			this.validationStatement.close();
			this.connection.close();
		} catch (SQLException cause) {
			throw new IOException(cause);
		}
	}
	
}
