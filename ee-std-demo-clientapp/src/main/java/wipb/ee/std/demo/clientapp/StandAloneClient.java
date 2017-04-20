package wipb.ee.std.demo.clientapp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import wipb.jee.clientdemo.ejb.MessageBeanRemote;
import wipb.jee.clientdemo.model.Book;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dr
 */
public class StandAloneClient {

    public static void main(String[] args) throws NamingException {
        System.out.println("Initializing remote EJB bean...");
        InitialContext ic = new InitialContext();
        // pobranie referencji do ejb (proxy) do komponentu EJB z usługi katalogowej JNDI serwera aplikacyjnego
        MessageBeanRemote ejb = (MessageBeanRemote) ic.lookup("java:global/ee-std-client-demo-ear-1.0/ee-std-client-demo-ejb-1.0/MessageBean!wipb.jee.clientdemo.ejb.MessageBeanRemote");
        // wywołanie metody ejb z serwera aplikacyjnego                            
        String result = ejb.getMessage();
        System.out.println("Remote message result: "+result);

        Scanner scanner = new Scanner(System.in);
        String text1, text2;
        Integer value;

        while(true) {
            System.out.println("1 - Add new book.\n" +
                    "2 - Find all books.\n" +
                    "3 - Find all books by length .\n" +
                    "4 - Send e-mail.\n" +
                    "Other number - Exit.");
            switch(Integer.parseInt(scanner.nextLine())) {
                // Dodawanie ksiazek
                case 1:
                    System.out.print("Enter title: ");
                    text1 = scanner.nextLine();

                    System.out.println("Adding new book");
                    Book b1 = new Book();
                    b1.setTitle(text1);
                    ejb.save(b1);
                    break;

                // Wyswietlanie ksiazek
                case 2:
                    System.out.println("Books:");
                    for (Book b:ejb.findAll()) {
                        System.out.println(b.getId()+", "+b.getTitle());
                    }
                    break;

                    // Wyświetlenie wartości
                case 3:
                    System.out.print("Enter length: ");
                    value = Integer.parseInt(scanner.nextLine());

                    List<Book> books = ejb.findByLength(value);
                    if (!books.isEmpty()) {
                        System.out.println("Book with title lenght equal to " + value);
                        for (Book b : books) {
                            System.out.println(b.getId() + ", " + b.getTitle());
                        }
                    } else
                        System.out.println("List of books is empty.");
                    break;

                case 4:
                    System.out.print("Enter email address: ");
                    text1 = scanner.nextLine();
                    System.out.print("Enter message: ");
                    text2 = scanner.nextLine();
                    ejb.sendEmail(text1, text2);
                    break;

                default:
                    return;
            }
            System.out.println("********************************************");
        }

    }
}
