package pojo;
public class Book {
	private int bookId;
	private String subjectName, bookName, authorName;
	private float price;
	public Book() {
	}
	public Book(int bookId, String subjectName, String bookName, String authorName, float price) {
		this.bookId = bookId;
		this.subjectName = subjectName;
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return String.format("%-5d%-10s%-60s%-35s%-8.2f",bookId, subjectName, bookName, authorName, price);
	}
}
