public class Controller {
    /**
     * Se usa desde el model para enviar mensajes
     * @param texto
     * @param error
     */
    public static void enviarMsg(String texto, boolean error) {
        View.msg(texto,error);
    }

    public static void listarLibros() {
        Model.listarLibros();
    }

    public static Libro añadirLibro(String titulo, String autor, int isbn, String fecha_publi) {
        return Model.añadirLibro(titulo,autor,isbn,fecha_publi);
    }

    public static boolean eliminarLibro(String isbnEliminar) {
        Model.eliminarLibro(Integer.parseInt(isbnEliminar));
        return false;
    }

    public static Libro buscarPorISBN(String isbnEditar) {
       return Model.buscarPorISBN(Integer.parseInt(isbnEditar));
    }

    public static void editarLibro(int isbnEditar, String nuevoTitulo, String nuevoAutor, String fecha_publi) {
         Model.editarLibro(isbnEditar, nuevoTitulo, nuevoAutor, fecha_publi);
    }

    public static void buscarLibro(int criterio, String valor) {
        Model.buscarLibro(criterio, valor);
    }
}
