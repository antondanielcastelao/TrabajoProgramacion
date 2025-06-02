import java.util.Scanner;

public class View {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1 - Listar libros disponibles");
            System.out.println("2 - Añadir un libro");
            System.out.println("3 - Borrar un libro");
            System.out.println("4 - Editar datos de un libro (título, autor, ISBN, etc.)");
            System.out.println("5 - Buscar libro por título, autor o ISBN");
            System.out.println("6 - Importar lista de libros desde un archivo");
            System.out.println("0 - Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    Controller.listarLibros();
                    break;

                case 2:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Fecha de publicación: ");
                    String fecha_publi = scanner.nextLine();
                    Libro nuevo = Controller.añadirLibro(titulo, autor, Integer.parseInt(isbn), fecha_publi);
                    System.out.println("Libro añadido: " + nuevo);
                    break;

                case 3:
                    System.out.print("Introduce el ISBN del libro a eliminar: ");
                    String isbnEliminar = scanner.nextLine();
                    boolean eliminado = Controller.eliminarLibro(isbnEliminar);
                    if (eliminado) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró un libro con ese ISBN.");
                    }
                    break;

                case 4:
                    System.out.print("Introduce el ISBN del libro a editar: ");
                    String isbnEditar = scanner.nextLine();
                    Libro libroAEditar = Controller.buscarPorISBN(isbnEditar);
                    if (libroAEditar != null) {
                        System.out.print("Nuevo título (actual: " + libroAEditar.getTitulo() + "): ");
                        String nuevoTitulo = scanner.nextLine();
                        System.out.print("Nuevo autor (actual: " + libroAEditar.getAutor() + "): ");
                        String nuevoAutor = scanner.nextLine();
                        System.out.println("Fecha de publicación: ");
                        String nuevafecha_publi = scanner.nextLine();
                        Controller.editarLibro(Integer.parseInt(isbnEditar), nuevoTitulo, nuevoAutor, nuevafecha_publi);
                        System.out.println("Libro actualizado.");
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Buscar por (1. Título / 2. Autor / 3. ISBN): ");
                    int criterio = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    System.out.print("Introduce el valor de búsqueda: ");
                    String valor = scanner.nextLine();
                    Controller.buscarLibro(criterio, valor);
                    break;

                case 6:
                    System.out.print("Ruta del archivo (ej. data/libros.txt): ");
                    String ruta = scanner.nextLine();
                    // Controller.importarDesdeArchivo(ruta);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }

        } while (opcion != 0);

    }

    public static void msg(String texto, boolean error) {
        String mensaje = "\n";
        if (error) {
            mensaje.concat("[!] ");
        }
        mensaje.concat(texto);

        System.out.println(mensaje);
    }
}
