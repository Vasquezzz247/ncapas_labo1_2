import java.time.LocalDate;
import java.util.Scanner;

public class Main { ;

    public static void main(String[] args) {

       Loans loans = new Loans();
       Book book = new Book();

        // Inicialización de libros
        book.addItemToList(new Book("LC-XXXX-01", "Cien años de soledad", "Gabriel García Márquez", "Editorial Sudamericana", "available"));
        book.addItemToList(new Book("LC-XXXX-02", "El nombre del viento", "Patrick Rothfuss", "Plaza & Janés", "available"));
        book.addItemToList(new Book("LC-XXXX-03", "Orgullo y prejuicio", "Jane Austen", "Penguin Classics", "borrowed"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Gestión de libros");
        int option;

        while (true) {
            System.out.print("Ingrese una opción: \n" +
                             "1) Listado de libros \n" +
                             "2) Realizar préstamo \n" +
                             "0) Salir \n");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Listar libros
                    System.out.println("----------------------------------");
                    System.out.println("Listado de libros:");
                    System.out.println("----------------------------------");
                    book.getList().forEach(book1 -> {
                        System.out.println("ID: " + book1.getId());
                        System.out.println("Autor: " + book1.getAuthor());
                        System.out.println("Título: " + book1.getTitle());
                        System.out.println("Tipo: " + book1.getType());
                        System.out.println("Estado: " + book1.getState());
                        System.out.println("----------------------------------");
                    });
                    break;

                case 2:
                    // Realizar préstamo
                    String bookId;
                    String peopleName;
                    String dui;

                    System.out.println("Ingrese su nombre:");
                    peopleName = scanner.next();

                    System.out.println("Si es mayor de edad ingrese su documento:");
                    dui = scanner.next();

                    System.out.println("Ingrese el ID del libro:");
                    bookId = scanner.next();

                    boolean check = book.isBorrowed(bookId);
                    if (check) {
                        System.out.println("El libro ya está prestado.");
                    } else {
                        loans.addItem(new Loans(
                                bookId,
                                LocalDate.now().toString(),
                                LocalDate.now().plusMonths(3).toString(),
                                peopleName,
                                dui
                        ));
                        book.lendBook(bookId);
                        System.out.println("El préstamo se realizó correctamente.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return; // Finaliza el programa

                default:
                    System.out.println("Opción no válida. Por favor intenta de nuevo.");
                    break;
            }
        }
    }
}