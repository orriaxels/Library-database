package is.ru.honn.models;

public class Book
{
    private String title;
    private String firstName;
    private String lastName;
    private String publised;
    private String isbn;

    public Book(String title, String firstName, String lastName, String published, String isbn)
    {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.publised = published;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPublised() {
        return publised;
    }

    public void setPublised(String publised) {
        this.publised = publised;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
