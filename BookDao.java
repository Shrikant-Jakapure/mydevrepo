package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import pojo.Book;
import utils.DButil;

public class BookDao implements Closeable {
	private Connection connection;
	private PreparedStatement stmtDistinctSubjects;
	private PreparedStatement stmtBooksOfSubject;
	private PreparedStatement stmtBookOfId;
	public BookDao() throws Exception {
		this.connection = DButil.getConnection();
		this.stmtDistinctSubjects = this.connection.prepareStatement("SELECT DISTINCT subject_name FROM books");
		this.stmtBooksOfSubject = this.connection.prepareStatement("SELECT * FROM books WHERE subject_name=?");
		this.stmtBookOfId = this.connection.prepareStatement("SELECT * FROM books WHERE book_id=?");
	}
	public Set<String> getDistinctSubjects( )throws Exception{
		Set<String> subjects = new TreeSet<>();
		try( ResultSet rs = this.stmtDistinctSubjects.executeQuery()){
			while( rs.next()) 
				subjects.add(rs.getString("subject_name") );
		}
		return subjects;
	}
	public List<Book> getBooks(String subject)throws Exception {
		this.stmtBooksOfSubject.setString(1, subject);
		List<Book> books = new ArrayList<Book>();
		try( ResultSet rs = this.stmtBooksOfSubject.executeQuery()){
			while (rs.next()) {
				Book book = new Book(rs.getInt("book_id"), rs.getString("subject_name"), rs.getString("book_name"),rs.getString("author_name"), rs.getFloat("price"));
				books.add(book);
			}
		}
		return books;
	}
	public Book getBook(Integer bookId)throws Exception {
		this.stmtBookOfId.setInt(1, bookId);
		try( ResultSet rs = this.stmtBookOfId.executeQuery()){
			if (rs.next()) 
				return new Book(rs.getInt("book_id"), rs.getString("subject_name"), rs.getString("book_name"),rs.getString("author_name"), rs.getFloat("price"));
		}
		return null;
	}
	@Override
	public void close() throws IOException {
		try {
			this.stmtDistinctSubjects.close();
			this.connection.close();
		} catch (SQLException cause) {
			throw new IOException( cause );	//Exception Chaining
		}
	}
	

}
