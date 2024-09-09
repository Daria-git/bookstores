
package books.database;

import books.Book;
import books.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MySqlImpl implements Database {

    private Statement statement;
    private String author;
    private String price;
    private String image;
    private String title;


    private static Statement getStatement() throws SQLException {
        Statement statement = getNewConnection().createStatement();
        return statement;
    }

    public static Connection getNewConnection() {
        String url = "jdbc:mysql://localhost:3306/bookshop?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String password = "Veselova777";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public boolean addUser(User user) throws SQLException {
        String formatString = "INSERT INTO USER VALUES ('%s', '%s')";
        String userEntryQuery = String.format(formatString, user.getLogin(), user.getPassword());
        try {
            getStatement().executeUpdate(userEntryQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public User getUser(String login) throws SQLException {
        String formatString = "SELECT * FROM USER WHERE login =\"%s\" ";
        String query = String.format(formatString, login);
        ResultSet rs = getStatement().executeQuery(query);
        rs.next();
        String password = rs.getString("password");
        return new User(login, password);
    }


    @Override
    public void updatePassword(String login, String newPassword) throws SQLException {
        String formatString = "UPDATE USER SET password= \"%s\" WHERE login =\"%s\" ";
        String query = String.format(formatString, newPassword, login);
        getStatement().executeUpdate(query);
    }


    @Override
    public boolean addBook(Book book) {
        String queryFindBook = "SELECT title, author, price, image FROM books  WHERE title =\"%s\" ";
        String sql = String.format(queryFindBook, title);
        try {
            statement = getStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "insert into books (title, author, price, image)"
                    + " values ('%s', '%s','%s', '%s')";
            String booksEntryQuery = String.format(query, book.getTitle(), book.getAuthor(), book.getPrice(), book.getImage());
            try {
                getStatement().executeUpdate(booksEntryQuery);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Book> getAllBooks() throws Exception {
        String sql = "SELECT title, author, price, image FROM books";
        ResultSet rs = getStatement().executeQuery(sql);
        List<Book> list = new ArrayList<Book>();
        while (rs.next()) {
            Book book = new Book(title, author, price, image);
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getString("price"));
            book.setImage(rs.getString("image"));
            list.add(book);
        }
        Set<Book> books = new HashSet<>();
        books.addAll(list);
        list.clear();
        list.addAll(books);
        return list;
    }


    @Override
    public List<Book> getBookByAuthor(String author) throws Exception {
        String sql = "SELECT title, author, price, image FROM books  WHERE author =\"%s\" ";
        String query = String.format(sql, author);
        ResultSet rs = getStatement().executeQuery(query);
        List<Book> list = new ArrayList<Book>();
        while (rs.next()) {
            Book book = new Book();
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getString("price"));
            book.setImage(rs.getString("image"));
            list.add(book);
        }
        return list;
    }

    private void addAllBooks() throws SQLException {
        List<Book> books = new ArrayList<Book>();
        Book book1 = new Book("A Floating City", "Jules Verne", "18", "img\\AFloatingCity.jpg");
        Book book2 = new Book("All Around THe Moon", "Jules Verne", "21", "img\\AroundtheMoon.jpg");
        Book book3 = new Book("Captain Nemo", "Jules Verne", "26", "img\\CaptainNemo.jpg");
        Book book4 = new Book("Journey To The Center Of The Earth", "Jules Verne", "23", "img\\CenteroftheEarth.jpg");
        Book book5 = new Book("The Comfort Book", "Matt Haig", "24", "img\\ComfortBook.jpg");
        Book book6 = new Book("Floating Hotel", "Grace Curtis", "23", "img\\FloatingHotel.jpg");
        Book book7 = new Book("The Imposition Of Unnecessary Obstacles", "Malka Older", "32", "img\\Impotion.png");
        Book book8 = new Book("A Killer Romance", "Maggie Blackburn", "18", "img\\KillerRomance.jpg");
        Book book9 = new Book("The Lost Bookshop", "Evie Woods", "30", "img\\LostBookshop.jpg");
        Book book10 = new Book("Murder At The Leaning Tower", "T.A. Williams", "27", "img\\Murder.jpg");
        Book book11 = new Book("Quo Vadis", "Henryk Sienkiewicz", "18", "img\\QuaVadis.jpg");
        Book book12 = new Book("Robinson Crusoe", "Daniel Defoe", "20", "img\\Robinsoncrusoe.png");
        Book book13 = new Book("The Adventures Of Sherlock Holmes", "Sir Arthur Conan Doyle", "25", "img\\SherlockHolms.jpg");
        Book book14 = new Book("The Golden Volcano", "Jules Verne", "23", "img\\ThegoldenVolcano.jpg");
        Book book15 = new Book("Twenty Thousand Leagues Under The Sea", "Jules Verne", "24", "img\\UndertheSea.jpg");
        Book book16 = new Book("A Stranger In The Family", "Jane Casey", "30", "img\\StrangerFamily.jpg");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);
        books.add(book12);
        books.add(book13);
        books.add(book14);
        books.add(book15);
        books.add(book16);
        books.forEach(book -> {
            try {
                addBook(book);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void init() throws SQLException {
        String booksTableQuery = "CREATE TABLE IF NOT EXISTS BOOKS " + "(title TEXT, author TEXT,price TEXT,image TEXT)";
        getStatement().executeUpdate(booksTableQuery);

        addAllBooks();

    }
}

