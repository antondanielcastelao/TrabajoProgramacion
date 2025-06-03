public class Libro {

        private String titulo;
        private String autor;
        private int isbn;
        private String fecha_publi;
        // constructor
        public Libro(String titulo, String autor, int isbn, String fecha_publi) {
            this.titulo = titulo;
            this.autor = autor;
            this.isbn = isbn;
            this.fecha_publi = fecha_publi;
        }

        // getters y setters
        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public int getIsbn() {
            return isbn;
        }
        public String getFecha_publi() {
            return fecha_publi;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public void setIsbn(int isbn) {
            this.isbn = isbn;
        }

        public void setFecha_publi(String fecha_publi) {
            this.fecha_publi = fecha_publi;
        }

        public String toString() {
            return this.getTitulo() + " - " + this.getAutor() +
                    " [ISBN: "+ this.getIsbn() + " | Fecha Publicacion: " + this.getFecha_publi() + " ]";
        }

    }


