import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Main loop
    public void run() {
        while (true) {
            showMenu();
            int choice = readIntInput();
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("\nWelcome to Library App!");
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
        System.out.print("Choose an option: ");
    }

    private int readIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> printAllBooks();
            case 2 -> addNewBook();
            case 3 -> searchByTitle();
            case 4 -> borrowBook();
            case 5 -> returnBook();
            case 6 -> deleteBook();
            case 7 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid option. Try again.");
        }
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void addNewBook() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = readIntInput();

        try {
            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchByTitle() {
        scanner.nextLine();
        System.out.print("Enter part of the title: ");
        String search = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(search)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int id = readIntInput();

        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isAvailable()) {
                    book.markAsBorrowed();
                    System.out.println("Book borrowed.");
                } else {
                    System.out.println("Book is already borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = readIntInput();

        for (Book book : books) {
            if (book.getId() == id && !book.isAvailable()) {
                book.markAsReturned();
                System.out.println("Book returned.");
                return;
            }
        }
        System.out.println("Book not found or already available.");
    }

    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = readIntInput();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.remove(i);
                System.out.println("Book deleted.");
                return;
            }
        }
        System.out.println("Book not found.");
    }


}