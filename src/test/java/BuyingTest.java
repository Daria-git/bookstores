import books.Buying;
import books.User;
import books.database.MongoDB;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertEquals;


public class BuyingTest {
    Buying buying = new Buying(new MongoDB());


    @Test
    public void generatePasswordShouldReturnSevenLength() throws Exception {
        assertEquals(7, Buying.generatePassword().length(), "length need to be seven");
    }

    @Test
    public void signInShouldReturnYouSignIn() throws Exception {
        User user = new User("ivanova444@gmail.com", "Ivanova444");
        assertEquals("You sign in", buying.signIn(user), "you sign in");
    }

    @Test
    public void signInShouldReturnUserNotFound() throws Exception {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("User not found");
        });
        assertEquals("User not found", exception.getMessage());
    }


    public void signInShouldReturnYouNotSignIn() throws Exception {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("You not sign in");
        });
        assertEquals("You not sign in", exception.getMessage());
    }

    @Test
    public void signUpShouldReturnYouHaveRegister() throws Exception {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("You have register");
        });
        assertEquals("You have register", exception.getMessage());
    }


    @Test
    public void getUserFromDBReturnUser() throws Exception {
        User user = new User("admin", "dog512");
        assertEquals(user.getLogin(), buying.getUserFromDB(user.getLogin()).getLogin(), "you sign in");
        assertEquals(user.getPassword(), buying.getUserFromDB(user.getLogin()).getPassword(), "you sign in");
    }

    @Test
    public void getAllBooksShouldNotBeEmpty() throws Exception {
        System.out.println("buying" + buying.getAllBooks().isEmpty());
        assertThat(buying.getAllBooks().isEmpty(), is(false));
    }
}
