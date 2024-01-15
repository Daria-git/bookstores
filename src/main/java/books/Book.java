
package books;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("books")
public class Book {
    @Indexed(unique = true)
    private String title;
    private String author;
    private String price;
    private String image;


    public Book(String title, String author, String price, String image) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;

    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "title = " + " " + title + " author = " + author + " price = " + price + "image = " + image + " ";

    }
}


