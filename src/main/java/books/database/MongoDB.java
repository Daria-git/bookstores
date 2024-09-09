
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
        Book book1 = new Book("A Floating City", "Jules Verne", "18", "img\\AFloatingCity.jpg");
        Book book2 = new Book("A Floating City", "Jules Verne", "21", "img\\AroundtheMoon.jpg");
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





