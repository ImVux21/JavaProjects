import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO: your code here
        Scanner scanner = new Scanner(System.in);
        File file = new File("books.txt");
        ArrayList<Book> books = new ArrayList<Book>();

        BookManager manager = new BookManager(books);

        int options;
        boolean isChecked = true;

        if (file.exists()) {
            books = manager.loadFromFile();
        }

        while (isChecked) {
            System.out.print("-----------------------------------\n" +
                    "1. list all books\n" +
                    "2. add a new book\n" +
                    "3. edit book\n" +
                    "4. delete a book\n" +
                    "5. search books by name\n" +
                    "6. sort books descending by price\n" +
                    "\n" +
                    "0. save & exit\n" +
                    "-----------------------------------\n" +
                    "Your option: ");
            options = scanner.nextInt();
            scanner.nextLine();

            switch (options) {
                case 1:
                    manager.printBooks(books);
                    break;

                case 2:
                    System.out.print("Enter book id: ");
                    int bookId1 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter book name: ");
                    String bookName1 = scanner.nextLine();


                    System.out.print("Enter book price: ");
                    double bookPrice1 = scanner.nextDouble();

                    Book book = new Book(bookId1, bookName1, bookPrice1);

                    if (manager.add(book)) {
                        System.out.println("Added successfully.");
                    } else {
                        System.out.println("Duplicated ID!");
                    }
                    break;

                case 3:
                    System.out.print("Enter book id: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    if (manager.getBookById(bookId) != null) {
                        System.out.print("Enter book name: ");
                        String bookName = scanner.nextLine();


                        System.out.print("Enter book price: ");
                        double bookPrice = scanner.nextDouble();

                        manager.getBookById(bookId).setName(bookName);
                        manager.getBookById(bookId).setPrice(bookPrice);

                        System.out.println("Updated successfully.");
                    }
                    break;

                case 4:
                    System.out.print("Enter book id: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine();

                    if (manager.getBookById(bookId) != null) {
                        manager.remove(manager.getBookById(bookId));
                        System.out.println("Deleted successfully.");
                    }
                    break;

                case 5:
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();

                    manager.printBooks(manager.searchByName(keyword));
                    break;

                case 6:
                    manager.sortDescByPrice();
                    break;

                case 0:
                    manager.saveToFile();
                    isChecked = false;
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }


    }
}
