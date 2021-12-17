import java.io.*;
import java.util.*;

public class BookManager {
    // TODO: your code here
    // attribute books
    ArrayList<Book> books;

    public BookManager(ArrayList<Book> books) {
        // TODO: your code here
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        // TODO: your code here
        return books;
    }

    /**
     * update this.books by reading books from file books.txt
     */
    public ArrayList<Book> loadFromFile() throws Exception {
        // TODO: your code here
        File filePath = new File("books.txt");
        System.out.println("Loading books...");
        Scanner scanner = new Scanner(filePath);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            int id = Integer.parseInt(data.substring(0, 6).trim());
            String name = data.substring(6, 52).trim();
            double price = Double.parseDouble(data.substring(51).trim());
            this.getBooks().add(new Book(id, name, price));
        }
        return this.getBooks();
    }

    /**
     * print books (one/line) in required format
     */
    public void printBooks(ArrayList<Book> books) {
        // TODO: your code here
        if (!books.isEmpty()) {
            System.out.printf("%-5s %-45s %-10s", "ID", "Name", "Price");
            System.out.println("");
            for (Book book : books) {
                System.out.println(book.toString());
            }
        } else {
            System.out.println("(empty)");
        }
    }

    /**
     * if book.id is not duplicated, add book to this.books
     * return whether added or not
     */
    public boolean add(Book book) {
        // TODO: your code here
        boolean duplicated = false;
        for (Book b : this.getBooks()) {
            if (b.id == book.id) {
                duplicated = true;
            }
        }
        if (duplicated) {
            return false;
        } else {
            this.getBooks().add(book);
        }
        return true;
    }

    /**
     * return book specified by id, null if not found
     */
    public Book getBookById(int id) {
        // TODO: your code here
        Book subBook = null;
        for (Book book : this.getBooks()) {
            if (book.id == id ) {
                subBook = book;
                break;
            }
        }
        if (subBook == null) {
            System.out.println("Invalid ID!");
        }
        return subBook;
    }

    /**
     * delete book from this.books
     */
    public void remove(Book book) {
        // TODO: your code here
        this.getBooks().remove(book);

    }

    /**
     * update this.books to be sorted by price from high -> low
     */

    public void sortDescByPrice() {
        // TODO: your code here
        for (int i = 0; i < this.getBooks().size()-1; i++) {
            int maxIndex = i;
            for (int j = i+1; j < this.getBooks().size(); j++) {
                if (this.getBooks().get(j).price > this.getBooks().get(maxIndex).price) {
                    maxIndex = j;
                }
            }
            Book temp = this.getBooks().get(maxIndex);
            this.getBooks().set(maxIndex, this.getBooks().get(i));
            this.getBooks().set(i, temp);
        }
        System.out.println("After sorting: ");
        printBooks(this.getBooks());
    }

    /**
     * return all books having name contains keyword (case in-sensitive)
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();

        // TODO: your code here

        for (Book book : this.getBooks()) {
            keyword = keyword.toLowerCase();
            book.name = book.name.toLowerCase();
            if (book.name.contains(keyword)) {
                matches.add(book);
            }
        }
        return matches;
    }

    /**
     * write this.books to file books.txt in required format
     */
    public void saveToFile() throws Exception{
        // TODO: your code here
        FileWriter fileWriter = new FileWriter("books.txt");
        PrintWriter myWriter = new PrintWriter(new BufferedWriter(fileWriter));
        for (Book b : this.getBooks()) {
            myWriter.println(b.toString());
        }
        System.out.println("Saving to file....\n" +
                "Bye!");
        myWriter.close();
    }
}
