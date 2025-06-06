public class Controller {
    /**
     * Se usa desde el model para comunicar a la view que se quiere enviar mensajes
     * @param texto
     * @param error
     */
    public static void enviarMsg(String texto, boolean error) {
        View.msg(texto,error);
    }

    /**
     * Obtiene del model una lista de libros
     */
    public static String listarLibros() {
        return Model.listarLibros();
    }

    /**
     * Manda la señal al controller para crear un libro
     * @param titulo
     * @param autor
     * @param isbn
     * @param fecha_publi
     * @return true si lo pudo crear false
     */
    public static boolean anhadirLibro(String titulo, String autor, int isbn, String fecha_publi) {
        if (Model.buscarPorISBN(isbn) != null){ // si encuentra el libro al buscarlo, devuelve false
                                                // ya que no deben insertarse 2 libros con mismo ISBN
            return false;
        } else {
            return Model.anhadirLibro(titulo,autor,isbn,fecha_publi);
        }
    }

    /**
     * LLama al controller para eliminar un libro por ISBN
     * @param isbnEliminar
     * @return true si se ha conseguido eliminar false si no.
     */
    public static boolean eliminarLibro(int isbnEliminar) {
        return Model.eliminarLibro(isbnEliminar);
    }

    /**
     *
     * @param isbnEditar
     * @return Devuelve la instancia del libro buscado por ISBN
     */
    public static Libro buscarPorISBN(int isbnEditar) {
        return Model.buscarPorISBN(isbnEditar);
    }

    /**
     * Modifica los parametros de un libro
     * @param isbnEditar
     * @param nuevoTitulo
     * @param nuevoAutor
     * @param fecha_publi
     */
    public static void editarLibro(int isbnEditar, String nuevoTitulo, String nuevoAutor, String fecha_publi) {
         Model.editarLibro(isbnEditar, nuevoTitulo, nuevoAutor, fecha_publi);
    }

    /**
     * LLama al Model para buscar un libro en memoria
     * @param criterio 1 titulo, 2 autor, 3 ISBN
     * @param valor
     * @return Instancia del libro a buscar
     */
    public static Libro buscarLibro(int criterio, String valor) {
        return Model.buscarLibro(criterio, valor);
    }

    // funciones para comunicarse con el model para la BD
    public static void cargaLibros() { Model.cargaLibros(); }
    public static void subeLibros() { Model.subeLibros(); }

}
