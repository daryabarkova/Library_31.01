package library.dao.db;

import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import library.bean.Author;
import library.bean.Book;
import library.dao.BookDao;

public class BookDaoMySQLImpl implements BookDao {
	
	private static final String SQL_SELECT_BOOKS = "Select books.id, books.title, authors.id, authors.name, authors.surname, authors.birthDate from books INNER JOIN authors ON books.author_id=authors.id";
    private static final String SQL_INSERT_BOOK = "insert into books (title, author_id, year) values (?, ?, ?)";
    
    {
    	try {
    		Class.forName(getConnectInitValue()[3]);
    	}catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
	
	@Override
	public void create(Book entity) {
		
		if(entity != null) {
			
			String url = getConnectInitValue()[0];
			String login = getConnectInitValue()[1];
			String pass = getConnectInitValue()[2];
			
			
		try {
			Connection cn = DriverManager.getConnection(url, login, pass);
			
			PreparedStatement ps = cn.prepareStatement(SQL_INSERT_BOOK);
			ps.setString(1, entity.getTitle());
			ps.setInt(2, entity.getAuthor().getId());
			ps.setString(3, "2007");
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
				
			
		}
		
		
	}

	@Override
	public Book read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Book t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> readAll() {
		
		List<Book> list = new ArrayList<>();
		
		Connection cn = null;
		
		try {
			
			String url = getConnectInitValue()[0];
			String login = getConnectInitValue()[1];
			String pass = getConnectInitValue()[2];
			
			cn = DriverManager.getConnection(url, login, pass);
			System.out.println("connected");
			
			Statement st = cn.createStatement();
			
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);
			
			while(rs.next()){
				
				Book book = new Book();
				
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("book_title"));
				book.setAuthor(new Author(rs.getInt("author_id"), rs.getString("author_name"), rs.getString("author_surname"), rs.getDate("author_birthDate")));
				//rs.getInt("authorId");
				//rs.getDate("year");
				
				list.add(book);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			}finally{
			try{
				if(cn != null) {
				cn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
  }
	
	private String[] getConnectInitValue() {
		ResourceBundle rb = ResourceBundle.getBundle("db_config");
		String dbUrl = rb.getString("db.url");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		String driver = rb.getString("db.driver.name");
		
		return new String[]{dbUrl, login, pass, driver};
		
	}
	
}
