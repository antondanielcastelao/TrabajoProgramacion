import java.util.Scanner;

public class View {
    public static void menu() {
        // antes de mostrar nada,
        // pide al controller cargar los datos de la DB
        Controller.cargaLibros();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do { // mientras la opcion no sea 0 (salir) se mantiene ejecutando el menu en bucle
            System.out.println("\n--- MENÚ ---");
            System.out.println("1 - Listar libros disponibles");
            System.out.println("2 - Añadir un libro");
            System.out.println("3 - Borrar un libro");
            System.out.println("4 - Editar datos de un libro");
            System.out.println("5 - Buscar libro");
            System.out.println("6 - Importar lista de libros desde un archivo");
            System.out.println("0 - Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1: // lista libros
                    msg(Controller.listarLibros(), false); // imprime por pantalla la lista de libros pidiendola al controller
                    break;

                case 2: // añade libro
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Fecha de publicación: ");
                    String fecha_publi = scanner.nextLine();
                    boolean engadido = Controller.anhadirLibro(titulo, autor, Integer.parseInt(isbn), fecha_publi);
                    if (engadido) {
                        msg("Libro añadido", false);
                    } else {
                        msg("El libro no se ha podido añadir ya que hay otro con el mismo ISBN", true);
                    }
                    break;

                case 3: // borra libro
                    System.out.print("Introduce el ISBN del libro a eliminar: ");
                    int isbnEliminar = Integer.parseInt(scanner.nextLine());
                    boolean eliminado = Controller.eliminarLibro(isbnEliminar);
                    if (eliminado) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró un libro con ese ISBN.");
                    }
                    break;

                case 4: // edita libro
                    System.out.print("Introduce el ISBN del libro a editar: ");
                    int isbnEditar = Integer.parseInt(scanner.nextLine());
                    Libro libroAEditar = Controller.buscarPorISBN(isbnEditar);

                    if (libroAEditar != null) {
                        System.out.print("Nuevo título (actual: " + libroAEditar.getTitulo() + "): ");
                        String nuevoTitulo = scanner.nextLine();
                        System.out.print("Nuevo autor (actual: " + libroAEditar.getAutor() + "): ");
                        String nuevoAutor = scanner.nextLine();
                        System.out.println("Fecha de publicación (actual: "+libroAEditar.getFecha_publi() + "): ");
                        String nuevafecha_publi = scanner.nextLine();
                        Controller.editarLibro(isbnEditar, nuevoTitulo, nuevoAutor, nuevafecha_publi);
                        System.out.println("Libro actualizado.");
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;

                case 5: // busca libro
                    System.out.print("Buscar por (1. Título / 2. Autor / 3. ISBN): ");
                    int criterio = Integer.parseInt(scanner.nextLine());
                    System.out.print("Introduce el valor de búsqueda: ");
                    String valor = scanner.nextLine();
                    System.out.println("\n" + Controller.buscarLibro(criterio, valor).toString());
                    break;

                case 6: // importar desde archivo
                    System.out.print("Ruta del archivo CSV separado por , (ej. data/libros.csv): ");
                    String ruta = scanner.nextLine();
                    Controller.importarDesdeArchivo(ruta);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }

        } while (opcion != 0);
        scanner.close();
        // Pide al controller subir
        // los cambios hechos a la coleccion de libros
        Controller.subeLibros();

    }

    public static void msg(String texto, boolean error) {
        String mensaje = "\n";
        if (error) {
            mensaje += "[!] ";
        }
        mensaje += texto;

        System.out.println(mensaje);
    }
}
