package books;

import books.database.Database;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;


public class Buying {
    private static final Log log = LogFactory.getLog(Buying.class);
    Database database;

    public Buying(Database database) {
        this.database = database;
    }

    public enum PasswordValidation {
        INVALID,
        NOTMACH,
        VALID
    }

    public enum SignInValidation {
        NOTFOUND,
        NOTSIGIN,
        SIGNIN
    }

    private String titleBook = "";
    private String fullName = "";
    private String toEmail = " ";
    String email;
    private String address = "";
    private int quantity = 0;

    public Buying() {
        this.titleBook = titleBook;
        this.fullName = fullName;
        this.toEmail = email;
        this.address = address;
        this.quantity = quantity;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return toEmail;
    }

    public String getAddress() {
        return address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.toEmail = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<Book> getAllBooks() throws Exception {
        return database.getAllBooks();
    }

    public List<Book> getBookByAuthorFromDB(String author) throws Exception {
        return database.getBookByAuthor(author);
    }

    public String addBook(Book book) throws Exception {
        if (database.addBook(book)) {
            return "You add book ";
        } else {
            throw new Exception("the book was not added");

        }
    }

    private String addUserDatabase(User user) throws Exception {
        if (database.addUser(user)) {
            return "You have register ";
        } else {
            throw new Exception("this email is exist");
        }
    }

    public String signUp(User user, String checkPassword) throws Exception {
        PasswordValidation passwordValidation = PasswordValidation.VALID;
        String password = user.getPassword();
        String result = "";
        if (!user.getPassword().equals(checkPassword)) {
            passwordValidation = PasswordValidation.NOTMACH;
        }
        switch (passwordValidation) {
            case INVALID:
                result = "Your password is incorrect";
                break;
            case NOTMACH:
                result = "Password don't confirm checkPassword";
                break;
            case VALID:
                result = addUserDatabase(user);
                break;
        }
        return result;
    }

    public User getUserFromDB(String login) throws Exception {
        return database.getUser(login);
    }

    public String signIn(User user) throws Exception {
        User userDB = database.getUser(user.getLogin());
        return signInValidation(user, userDB);
    }

    public String signInValidation(User user, User userDB) throws Exception {
        SignInValidation signInValidation = SignInValidation.SIGNIN;
        String result = "";
        if (userDB == (null)) {
            signInValidation = SignInValidation.NOTFOUND;
        } else if (!user.getPassword().equals(userDB.getPassword())) {
            signInValidation = SignInValidation.NOTSIGIN;

        }
        switch (signInValidation) {
            case NOTFOUND:
                log.error("This user nor found");
                throw new Exception("This user nor found");
            case NOTSIGIN:
                log.error("you don't sign in");
                throw new Exception("You don't sign in");
            case SIGNIN:
                result = "You sign in";

        }
        return result;
    }

    public void resetPassword(String login) throws Exception {
        String newPassword = generatePassword();
        sendEmail(login, newPassword);
        database.updatePassword(login, newPassword);
    }

    public static String generatePassword() throws Exception {
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            int index = (int) (10 * Math.random());
            sb.append(index);
        }
        return sb.toString();
    }

    public void sendEmail(String toEmail, String newPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("annaiv2588@gmail.com", "Anna2588");

            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("annaiv2588@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            String subject = "Reset password";
            String text = "You send request on reset password.Your new password is  " + newPassword;
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Your password didn't send");
        }
    }

    public void sendEmail(String titlebook, String fullname, String toEmail, String address, int quantity) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("annaiv2588@gmail.com", "Anna2588");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ivanovadaria512@gmail.com"));
            String subject = "Buying Book";
            String text = "titleBook = " + titleBook + " fullName = " + fullName + "address = " + this.address + " quantity = " + this.quantity;
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Email didn't send");
        }
    }

    public void sendEmail(String titlebook, String fullname, String toEmail, String address) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("annaiv2588@gmail.com", "Anna2588");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("annaiv2588@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            String subject = "Buying Book";
            String text = "Hello, " + fullname
                    + System.lineSeparator() + "Thanks for buying in BUKOVKA. You successfully bought this book " + "" + titlebook.toUpperCase()
                    + System.lineSeparator() + "We hope you enjoy your purchase! And if you change your mind, you"
                    + System.lineSeparator() + " have 30 days to make a refund. For more information, see the Exchanges. "
                    + System.lineSeparator() + "Recipient Address : " + address
                    + System.lineSeparator() + "Yours faithfully, Bukovka! ";
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Email didn't send");
        }
    }
}

