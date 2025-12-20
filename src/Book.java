import java.time.Year;

public class Book {

    private int id;
    private static int idGen = 1;
    private String title;
    private String author;
    private int year;
    private boolean available;

    // Default constructor
    public Book() {
        this.id = idGen++;
        this.available = true;
    }

    // Constructor with parameters
    public Book(String title, String author, int year) {
        this();
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters with validation
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author must not be empty");
        }
        this.author = author;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1500 || year > currentYear) {
            throw new IllegalArgumentException("Year must be between 1500 and " + currentYear);
        }
        this.year = year;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Borrow / return methods
    public void markAsBorrowed() {
        this.available = false;
    }

    public void markAsReturned() {
        this.available = true;
    }

    @Override
    public String toString() {
        return "Book [id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", available=" + (available ? "Yes" : "No") +
                "]";
    }
}