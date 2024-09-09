
package books.database;

import books.Book;
import books.User;

import java.util.List;


public interface Database {

    public boolean addUser(User user) throws Exception;

    public User getUser(String login) throws Exception;

    public void updatePassword(String login, String newPassword) throws Exception;

    public boolean addBook(Book book) throws Exception;

    public List<Book> getAllBooks() throws Exception;

    public List<Book> getBookByAuthor(String author) throws Exception;

    public void init() throws Exception;
}
