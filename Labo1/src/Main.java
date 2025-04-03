import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Loans loans = new Loans();
        Book book = new Book();

        // Inicialización de libros
        book.addItemToList(new Book("LC-0001-A", "Cien años de soledad", "Gabriel García Márquez", "Libro", "available"));
        book.addItemToList(new Book("LC-0002-B", "El nombre del viento", "Patrick Rothfuss", "Libro", "available"));
        book.addItemToList(new Book("LC-0003-C", "Orgullo y prejuicio", "Jane Austen", "Libro", "borrowed"));

        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.print("Ingrese una opción: \n" +
                    "1) Listado de libros \n" +
                    "2) Realizar préstamo \n" +
                    "3) Agregar un nuevo libro \n" +
                    "0) Salir \n");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
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
                    System.out.println("Ingrese su nombre:");
                    String peopleName = scanner.nextLine();

                    System.out.println("Si es mayor de edad ingrese su documento:");
                    String dui = scanner.nextLine();

                    System.out.println("Ingrese el ID del libro:");
                    String bookId = scanner.nextLine();

                    boolean check = book.isBorrowed(bookId);
                    if (check) {
                        System.out.println("El libro ya está prestado.");
                    } else {
                        loans.addItem(new Loans(
                                bookId,
                                java.time.LocalDate.now().toString(),
                                java.time.LocalDate.now().plusMonths(3).toString(),
                                peopleName,
                                dui
                        ));
                        book.lendBook(bookId);
                        System.out.println("El préstamo se realizó correctamente.");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el título del libro:");
                    String title = scanner.nextLine();

                    System.out.println("Ingrese el autor:");
                    String author = scanner.nextLine();

                    System.out.println("Ingrese el tipo (Libro/Manga/Periódico):");
                    String type = scanner.nextLine();

                    String newId = generateBookId(type);
                    book.addItemToList(new Book(newId, title, author, type, "available"));
                    System.out.println("Libro agregado con éxito. ID asignado: " + newId);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    public static String generateBookId(String type) {
        Random random = new Random();
        int num = 1000 + random.nextInt(9000);
        char letter = (char) ('A' + random.nextInt(26));
        String prefix = switch (type.toLowerCase()) {
            case "manga" -> "MG";
            case "periódico" -> "PR";
            default -> "LC";
        };
        return prefix + "-" + num + "-" + letter;
    }
}
