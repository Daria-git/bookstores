
package books.database;

import books.Book;
import books.User;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


public class MongoDB implements Database {
    private static final Log log = LogFactory.getLog(MongoDB.class);
    static MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "bookshop");


    @Override
    public boolean addUser(User user) {
        log.info("try to add user" + user);
        try {
            mongoOps.insert(user);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addBook(Book book) {
        log.info("try to add book" + book);
        try {
            mongoOps.insert(book);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Book> getAllBooks() throws MongoException {
        return mongoOps.findAll(Book.class);
    }


    @Override
    public List<Book> getBookByAuthor(String author) throws MongoException {
        return mongoOps.find(query(where("author").is(author)), Book.class);
    }


    @Override
    public User getUser(String login) throws MongoException {
        User user = mongoOps.findOne(query(where("login").is(login)), User.class);
        log.info("try to get user" + user);
        return user;
    }

    @Override
    public void updatePassword(String login, String newPassword) throws MongoException {
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        Update update = new Update();
        update.set("password", newPassword);
        mongoOps.findAndModify(
                query, update,
                new FindAndModifyOptions().returnNew(true), User.class);
        log.info("try update password" + newPassword);
    }


    private void addAllBooks() {
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
        mongoOps.insertAll(books);
    }

    @Override
    public void init() throws MongoException {

        try {
            addAllBooks();
        } catch (Exception e) {
            log.info("database was initialize this books");
        }

    }
}





