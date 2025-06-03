import java.util.ArrayList;
import java.util.List;

public class Model {
    /**
     * Clase Controller que gestiona las operaciones sobre la lista de libros.
     * Actúa como intermediario entre el modelo (Libro) y la vista (consola u otra).
     */

        // Lista que almacena los libros de la biblioteca
        private static List<Libro> listaLibros = new ArrayList<>();

        // este codigo crea un par de libros de prueba en memoria
        static {
            anhadirLibro(
                    "Las aventuras de perico de los palotes",
                    "Jose de troya",
                    123456789,
                    "11/11/11");
            anhadirLibro(
                    "Prueba",
                    "yo",
                    123,
                    "11/11/12");
        }
        /**
         * Lista todos los libros actualmente registrados.
         * Si la lista está vacía, informa al usuario.
         */
        public static String listarLibros() {
            String s = "---- Lista de libros ----\n";
            if (listaLibros.isEmpty()) {
                Controller.enviarMsg("No hay libros disponibles.", true);
                return "";
            } else {
                for (Libro l : listaLibros) {
                    s += l.toString() + "\n";
                }
            }
            return s;
        }

        /**
         * Añade un nuevo libro a la lista.
         * @param titulo Título del libro
         * @param autor Autor del libro
         * @param isbn ISBN del libro
         * @return El libro recién creado
         */
        public static boolean anhadirLibro(String titulo, String autor, int isbn, String fecha_publi) {
            Libro libro = new Libro(titulo, autor, isbn, fecha_publi);
            return listaLibros.add(libro);
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
        public static Libro buscarPorISBN(int isbn) {
            for (Libro l : listaLibros) {
                if (l.getIsbn() == isbn) {
                    return l;
                }
            }
            return null;
        }

        /**
         * Edita los datos (título y autor) de un libro identificado por su ISBN.
         *
         * @param nuevoTitulo Nuevo título
         * @param nuevoAutor  Nuevo autor
         */
        public static void editarLibro(int isbn, String nuevoTitulo, String nuevoAutor, String nuevaFechaPubli) {
            Libro l = buscarPorISBN(isbn);
            if (l != null) {
                l.setTitulo(nuevoTitulo);
                l.setAutor(nuevoAutor);
                l.setFecha_publi(nuevaFechaPubli);
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
                            Controller.enviarMsg(l.toString(), false);
                        }
                        break;
                    case 2: // Búsqueda por autor
                        if (l.getAutor().equalsIgnoreCase(valor)) {
                            Controller.enviarMsg(l.toString(), false);
                        }
                        break;
                    case 3: // Búsqueda por ISBN
                        if (l.getIsbn() == Integer.parseInt(valor)) {
                            Controller.enviarMsg(l.toString(), false);
                        }
                        break;
                    default:
                        Controller.enviarMsg("Criterio no válido.", true);
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
            Controller.enviarMsg("Funcionalidad de importación no implementada (placeholder).", true);
        }
    }

