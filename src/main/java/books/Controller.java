
package books;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {
    Buying buying = new Buying(Application.database);

    @RequestMapping(value = "/BuyBook", method = RequestMethod.POST)
    public String BuyBook(@RequestParam String titlebook, @RequestParam String fullname, @RequestParam String toEmail, @RequestParam String address, @RequestParam int quantity) throws Exception {
        buying.sendEmail(titlebook, fullname, toEmail, address, quantity);
        buying.sendEmail(titlebook, fullname, toEmail, address);
        return "Email already send";
    }

    @RequestMapping(value = "GetAllBooks", method = RequestMethod.POST)
    public List<Book> getAllBooks() throws Exception {
        return buying.getAllBooks();
    }

    @RequestMapping(value = "GetBookByAuthor", method = RequestMethod.POST)
    public List<Book> getBookByAuthor(@RequestParam String author) throws Exception {
        List<Book> books = buying.getBookByAuthorFromDB(author);
        return books;
    }

    @RequestMapping(value = "/AddBook", method = RequestMethod.POST)
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String price, @RequestParam String image) throws Exception {
        Book book = new Book(title, author, price, image);
        buying.addBook(book);
        return "add new book";
    }

    @RequestMapping(value = "Login", method = RequestMethod.POST)
    public String login(@RequestParam String password) throws Exception {
        User userDB = buying.getUserFromDB("admin");
        boolean result = userDB.getPassword().equals(password);
        if (!result) throw new Exception("Your password don't valid");
        return "your sign in";
    }

    @RequestMapping(value = "/Resetpassword", method = RequestMethod.POST)
    public String resetPassword(@RequestParam(value = "login") String login) throws Exception {
        if (!Validation.validationSendEmail(login)) throw new Exception("Your email invalid");
        buying.resetPassword(login);
        return login;
    }

    @RequestMapping(value = "/SignIn", method = RequestMethod.POST)
    public String signIn(@RequestParam String login, @RequestParam String password) throws Exception {
        User user = new User(login, password);
        return buying.signIn(user);
    }

    @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
    public String signUp(@RequestParam String login, @RequestParam String password, @RequestParam String checkPassword) throws Exception {
        User user = new User(login, password);
        return buying.signUp(user, checkPassword);
    }
}


