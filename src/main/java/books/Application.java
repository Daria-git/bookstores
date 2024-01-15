
package books;

import books.database.Database;
import books.database.MongoDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static Database database;

    public static void main(String[] args) throws Exception {
        //you can choose any database you want
        /*database = new MySqlImpl();*/
        database = new MongoDB();
        database.init();
        SpringApplication.run(Application.class, args);
    }

}
