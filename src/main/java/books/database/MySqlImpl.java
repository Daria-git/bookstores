
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
        Book book1 = new Book("Архивариус древнего рода", "Элла Рейн", "180 грн", "https://s1.livelib.ru/boocover/1003404249/200x305/a567/boocover.jpg");
        Book book2 = new Book("Дарксити", "Остап Соколюк", "210 грн", "https://i.livelib.ru/workpic/1002971410/200x305/954c/Ostap_Sokolyuk__Darksiti.jpg");
        Book book3 = new Book("Леди Непредсказуемость", "Рэйн Элла", "260 грн", "https://andronum.com/images-44/44337-serzh-best-lunnaya-istoriya.jpg");
        Book book4 = new Book("Вынужденная мера", "Рэйн Элла", "230 грн", "https://s1.livelib.ru/boocover/1002677098/200x305/e63f/boocover.jpg");
        Book book5 = new Book("Город мертвых отражений", "Наталья Тимошенко, Лена Обухова", "240 грн", "https://s1.livelib.ru/boocover/1002833355/200x305/a761/boocover.jpg");
        Book book6 = new Book("Капитан Немо", "Жюль Верн", "230 грн", "https://s1.livelib.ru/boocover/1001772909/200x305/0810/boocover.jpg");
        Book book7 = new Book("Вокруг Луны", "Жюль Верн", "320 грн", "https://s1.livelib.ru/boocover/1000650835/200x305/91dd/boocover.jpg");
        Book book8 = new Book("Война и мир", "Лев Толстой", "80 грн", "https://s1.livelib.ru/boocover/1000256740/200x305/2093/boocover.jpg");
        Book book9 = new Book("Двадцать тысяч лье под водой", "Жюль Верн", "300 грн", "https://s1.livelib.ru/boocover/1000566095/200x305/e88d/boocover.jpg");
        Book book10 = new Book("Проклятие пражской синагоги", "Наталья Тимошенко, Лена Обухова", "270 грн", "https://s1.livelib.ru/boocover/1002797179/200x305/114e/boocover.jpg");
        Book book11 = new Book("Мертвые души", "Николай Гоголь", "80 грн", "https://s1.livelib.ru/boocover/1000429218/200x305/d0f2/boocover.jpg");
        Book book12 = new Book("Призрак Оперы", "Гастон Леру", "200 грн", "https://s1.livelib.ru/boocover/1000437992/200x305/9e7e/boocover.jpg");
        Book book13 = new Book("Преступление и наказание", "Федор Достоевский", "250 грн", "https://s1.livelib.ru/boocover/1000485163/200x305/13c9/boocover.jpg");
        Book book14 = new Book("Qua vadis", "Генрик Сенкевич", "230 грн", "https://s1.livelib.ru/boocover/1000324859/200x305/81f0/boocover.jpg");
        Book book15 = new Book("Зойкина квартира", "Михаил Булгаков", "240 грн", "https://s1.livelib.ru/boocover/1000685009/200x305/fa9e/boocover.jpg");
        Book book16 = new Book("Последняя Любовь Энштейна", "Ольга Трифонова", "300 грн", "https://s1.livelib.ru/boocover/1000760535/200x305/6139/boocover.jpg");
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

