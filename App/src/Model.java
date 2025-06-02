import java.util.ArrayList;
import java.util.List;

public class Model {
    /**
     * Clase Controller que gestiona las operaciones sobre la lista de libros.
     * Actúa como intermediario entre el modelo (Libro) y la vista (consola u otra).
     */

        // Lista que almacena los libros de la biblioteca
        private static List<Libro> listaLibros = new ArrayList<>();

        /**
         * Lista todos los libros actualmente registrados.
         * Si la lista está vacía, informa al usuario.
         */
        public static void listarLibros() {
            if (listaLibros.isEmpty()) {
                System.out.println("No hay libros disponibles.");
            } else {
                for (Libro l : listaLibros) {
                    System.out.println(l);
                }
            }
        }
        /**
         * Añade un nuevo libro a la lista.
         * @param titulo Título del libro
         * @param autor Autor del libro
         * @param isbn ISBN del libro
         * @return El libro recién creado
         */
        public static Libro añadirLibro(String titulo, String autor, int isbn, String fecha_publi) {
            Libro libro = new Libro(titulo, autor, isbn, fecha_publi);
            listaLibros.add(libro);
            return libro;
        }

        /**
         * Elimina un libro por su ISBN.
         * @param isbn ISBN del libro a eliminar
         * @return true si se eliminó, false si no se encontró
         */
        public static boolean eliminarLibro(int isbn) {
            return listaLibros.removeIf(libro -> libro.getIsbn() == isbn);
        }

        /**
         * Busca un libro en la lista por su ISBN.
         * @param isbn ISBN a buscar
         * @return El libro encontrado, o null si no existe
         */
        public static Libro buscarPorISBN(String isbn) {
            for (Libro l : listaLibros) {
                if (l.getIsbn().equalsIgnoreCase(isbn)) {
                    return l;
                }
            }
            return null;
        }

        /**
         * Edita los datos (título y autor) de un libro identificado por su ISBN.
         * @param isbn ISBN del libro a editar
         * @param nuevoTitulo Nuevo título
         * @param nuevoAutor Nuevo autor
         */
        public static void editarLibro(String isbn, String nuevoTitulo, String nuevoAutor) {
            Libro l = buscarPorISBN(isbn);
            if (l != null) {
                l.setTitulo(nuevoTitulo);
                l.setAutor(nuevoAutor);
            }
        }

        /**
         * Busca libros por un criterio (1: título, 2: autor, 3: ISBN) y muestra los que coincidan.
         * @param criterio Criterio de búsqueda
         * @param valor Valor a buscar
         */
        public static void buscarLibro(int criterio, String valor) {
            for (Libro l : listaLibros) {
                switch (criterio) {
                    case 1: // Búsqueda por título
                        if (l.getTitulo().equalsIgnoreCase(valor)) {
                            System.out.println(l);
                        }
                        break;
                    case 2: // Búsqueda por autor
                        if (l.getAutor().equalsIgnoreCase(valor)) {
                            System.out.println(l);
                        }
                        break;
                    case 3: // Búsqueda por ISBN
                        if (l.getIsbn().equalsIgnoreCase(valor)) {
                            System.out.println(l);
                        }
                        break;
                    default:
                        System.out.println("Criterio no válido.");
                        break;
                }
            }
        }

        /**
         * Método placeholder para importar libros desde un archivo externo.
         * De momento solo muestra un mensaje de que la funcionalidad no está implementada.
         * @param ruta Ruta del archivo a importar
         */
        public static void importarDesdeArchivo(String ruta) {
            // Aquí deberías implementar la lógica real con BufferedReader
            System.out.println("Funcionalidad de importación no implementada (placeholder).");
        }
    }

