import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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
     *
     * @return texto del listado de libros
     */
    public static String listarLibros() {
            String s = "---- Lista de libros ----\n";
            if (listaLibros.isEmpty()) { // si la lista esta vacia envia un error de que no hay libros cargados en memoria
                Controller.enviarMsg("No hay libros disponibles.", true);
                return "";
            } else {
                for (Libro l : listaLibros) { // si no concatena los datos de cada libro en un string
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
         * @return true si se pudo crear
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
         * Recorre el arraylist de libros y en cada caso, compara el parametro del libro con el buscado
         * @param criterio Criterio de búsqueda
         * @param valor Valor a buscar
         */
        public static Libro buscarLibro(int criterio, String valor) {
            String criterioString = "";
            for (Libro l : listaLibros) {
                switch (criterio) {
                    case 1: // Búsqueda por título
                        criterioString = "titulo";
                        if (l.getTitulo().equalsIgnoreCase(valor)) {
                            return l;
                        }
                        break;
                    case 2: // Búsqueda por autor
                        criterioString = "autor";
                        if (l.getAutor().equalsIgnoreCase(valor)) {
                            return l;
                        }
                        break;
                    case 3: // Búsqueda por ISBN
                        criterioString = "ISBN";
                        if (l.getIsbn() == Integer.parseInt(valor)) {
                            return l;

                        }
                        break;
                    default:
                        Controller.enviarMsg("Criterio no válido.", true);
                        break;
                }
            }
            Controller.enviarMsg("No hay ningun libro con " + valor + " como " + criterioString, true);
            return null;
        }
        /**
         * Método placeholder para importar libros desde un archivo externo.
         * De momento solo muestra un mensaje de que la funcionalidad no está implementada.
         * @param ruta Ruta del archivo a importar
         */
        public static void importarDesdeArchivo(String ruta) {
            // Aquí deberías implementar la lógica real con BufferedReader
            // Controller.enviarMsg("Funcionalidad de importación no implementada (placeholder).", true);

            // abrimos el archivo a importar con buffer reader
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                // comprobamos primero que el csv dispone de la cabecera
                String[] cabecera = br.readLine().split(",");
                if (!cabecera[0].equals("isbn")
                        || !cabecera[1].equals("titulo")
                        || !cabecera[2].equals("autor")
                        || !cabecera[3].equals("fecha_publi")) {
                    // no existe la cabecera, manda un mensaje de error
                    Controller.enviarMsg("El csv no dispone de la cabecera isbn,titulo,autor,fecha_publi", true);
                } else {
                    // existe la cabecera
                    String linea;
                    while ( (linea = br.readLine()) != null ) {
                        String[] pedazos = linea.split(","); // dividimos la linea por el separador
                        // recorremos el archivo creando un libro en la coleccion por linea
                        anhadirLibro(
                                pedazos[1], // titulo
                                pedazos[2], // autor
                                Integer.parseInt(pedazos[0]), // isbn
                                pedazos[3] // fecha_publi
                        );

                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    /**
     * carga en memoria todos los libros de la base de datos
     */
    public static void cargaLibros() {
        try (Connection db = DBConnection.getConnection()) {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM libros");

            while (rs.next()) {
                int ISBN = rs.getInt("isbn");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String fecha_publi = rs.getString("fecha_publi");

                listaLibros.add(new Libro(titulo, autor, ISBN, fecha_publi));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Esta funcion se encarga de insertar los datos en memoria a la tabla de libros
     */
    public static void subeLibros() {

        // primero VACIA la tabla antes de insertar

        try (Connection db = DBConnection.getConnection()) {
            Statement st = db.createStatement();
            st.executeUpdate("DELETE FROM libros");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // por cada libro en memoria, lo insertamos en la BD
        String consulta = "INSERT INTO libros (isbn, titulo, autor, fecha_publi)  values (?, ?, ?, ?)";
        for (Libro l : listaLibros) {
            try (Connection con = DBConnection.getConnection()) {
                // hacemos un statement preparado para sustituir los valores
                // y evitar inyecciones de codigo sql al concatenar
                PreparedStatement st = con.prepareStatement(consulta);
                st.setInt(1, l.getIsbn());
                st.setString(2, l.getTitulo());
                st.setString(3, l.getAutor());
                st.setString(4, l.getFecha_publi());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    }

