import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    /**
     * Clase Controller que gestiona las operaciones sobre la lista de libros.
     * Actúa como intermediario entre el modelo (Libro) y la vista (consola u otra).
     */

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
     *
     * @return texto del listado de libros
     */
    public static String listarLibros() {
        StringBuilder s = new StringBuilder("---- Lista de libros ----\n");
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM libros ORDER BY titulo")) {
            boolean hayLibros = false;
            while (rs.next()) {
                hayLibros = true;
                Libro l = new Libro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("isbn"),
                        rs.getString("fecha_publi")
                );
                s.append(l.toString()).append("\n");
            }
            if (!hayLibros) {
                Controller.enviarMsg("No hay libros disponibles.", true);
                return "";
            }
        } catch (SQLException e) {
            Controller.enviarMsg("Error al listar libros: " + e.getMessage(), true);
            return "";
        }
        return s.toString();
    }

    /**
     * Añade un nuevo libro a la lista.
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param isbn ISBN del libro
     * @return true si se pudo crear
     */
    public static boolean anhadirLibro(String titulo, String autor, int isbn, String fecha_publi) {
        String sql = "INSERT INTO libros (isbn, titulo, autor, fecha_publi) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, isbn);
            ps.setString(2, titulo);
            ps.setString(3, autor);
            ps.setString(4, fecha_publi);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Si el error es por clave duplicada (ISBN ya existe)
            if (e.getSQLState().equals("23505")) {
                return false;
            }
            Controller.enviarMsg("Error al añadir libro: " + e.getMessage(), true);
            return false;
        }
    }

    /**
     * Elimina un libro por su ISBN.
     * @param isbn ISBN del libro a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public static boolean eliminarLibro(int isbn) {
        String sql = "DELETE FROM libros WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, isbn);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            Controller.enviarMsg("Error al eliminar libro: " + e.getMessage(), true);
            return false;
        }
    }

    /**
     * Busca un libro en la lista por su ISBN.
     * @param isbn ISBN a buscar
     * @return El libro encontrado, o null si no existe
     */
    public static Libro buscarPorISBN(int isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getInt("isbn"),
                            rs.getString("fecha_publi")
                    );
                }
            }
        } catch (SQLException e) {
            Controller.enviarMsg("Error al buscar libro: " + e.getMessage(), true);
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
        String sql = "UPDATE libros SET titulo = ?, autor = ?, fecha_publi = ? WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoTitulo);
            ps.setString(2, nuevoAutor);
            ps.setString(3, nuevaFechaPubli);
            ps.setInt(4, isbn);
            ps.executeUpdate();
        } catch (SQLException e) {
            Controller.enviarMsg("Error al editar libro: " + e.getMessage(), true);
        }
    }

    /**
     * Busca libros por un criterio (1: título, 2: autor, 3: ISBN) y muestra los que coincidan.
     * Recorre el arraylist de libros y en cada caso, compara el parametro del libro con el buscado
     * @param criterio Criterio de búsqueda
     * @param valor Valor a buscar
     */
    public static Libro buscarLibro(int criterio, String valor) {
        String sql = "";
        switch (criterio) {
            case 1: // Título
                sql = "SELECT * FROM libros WHERE LOWER(titulo) = LOWER(?)";
                break;
            case 2: // Autor
                sql = "SELECT * FROM libros WHERE LOWER(autor) = LOWER(?)";
                break;
            case 3: // ISBN
                sql = "SELECT * FROM libros WHERE isbn = ?";
                break;
            default:
                Controller.enviarMsg("Criterio no válido.", true);
                return null;
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (criterio == 3) {
                ps.setInt(1, Integer.parseInt(valor));
            } else {
                ps.setString(1, valor);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getInt("isbn"),
                            rs.getString("fecha_publi")
                    );
                }
            }
        } catch (SQLException e) {
            Controller.enviarMsg("Error al buscar libro: " + e.getMessage(), true);
        }
        Controller.enviarMsg("No hay ningun libro con " + valor, true);
        return null;
    }

    /**
     * Método placeholder para importar libros desde un archivo externo.
     * De momento solo muestra un mensaje de que la funcionalidad no está implementada.
     * @param ruta Ruta del archivo a importar
     */
    public static void importarDesdeArchivo(String ruta) {
        Controller.enviarMsg("Funcionalidad de importación no implementada (placeholder).", true);
    }
}

