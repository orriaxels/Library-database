package is.ru.honn.models;

public class BooksOnLoan
{
    private String title;

    private String personName;

    public BooksOnLoan(String title, String personName)
    {
        this.title = title;
        this.personName = personName;
    }

    public String getTitle() {
        return title;
    }
    public String getPersonName() {
        return personName;
    }
}
