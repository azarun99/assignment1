import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new LibraryApp().run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch(choice) {
                case 1 -> printAllBooks();
                case 2 -> addNewBook();
                case 3 -> searchByTitle();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> deleteBook();
                case 7 -> {
                    System.out.println("goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void printAllBooks() {
       if (books.isEmpty()) {
           System.out.println("No books found.");
           return;
       }
    }

    private void printMenu() {
        System.out.println("\nWelcome to Library App!");

        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }

    private void addNewBook () {
        try {
            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("Year: ");
            int year = readInt();

            Book book = new Book(title, author, year);
            books.add(book);

            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchByTitle () {
        System.out.print("Enter part of title: ");
        String query = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Books not found!");
        }
    }

    private void borrowBook () {
        System.out.print("Enter book id: ");
        int id = readInt();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.markAsBorrowed();
            System.out.println("Book borrowed successfully.");
        }
    }

    private void returnBook () {
        System.out.print("Enter book id: ");
        int id = readInt();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("Book is already available.");
        } else {
            book.markAsReturned();
            System.out.println("Book returned successfully.");
        }
    }

    private void deleteBook () {
        System.out.print("Enter book id: ");
        int id = readInt();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            books.remove(book);
            System.out.println("Book deleted successfully.");
        }
    }

    private Book findBookById (int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}