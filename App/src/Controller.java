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

    public static boolean anhadirLibro(String titulo, String autor, int isbn, String fecha_publi) {
        if (Model.buscarPorISBN(isbn) != null){ // si encuentra el libro al buscarlo, devuelve false
                                                // ya que no deben insertarse 2 libros con mismo ISBN
            return false;
        } else {
            return Model.anhadirLibro(titulo,autor,isbn,fecha_publi);
        }
    }

    public static boolean eliminarLibro(int isbnEliminar) {
        return Model.eliminarLibro(isbnEliminar);
    }

    public static Libro buscarPorISBN(int isbnEditar) {
        return Model.buscarPorISBN(isbnEditar);
    }

    public static void editarLibro(int isbnEditar, String nuevoTitulo, String nuevoAutor, String fecha_publi) {
         Model.editarLibro(isbnEditar, nuevoTitulo, nuevoAutor, fecha_publi);
    }

    public static void buscarLibro(int criterio, String valor) {
        Model.buscarLibro(criterio, valor);
    }
}
