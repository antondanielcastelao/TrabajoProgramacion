public class Libro {

        private String titulo;
        private String autor;
        private int isbn;
        private String fecha_publi;

        public Libro(String titulo, String autor, int isbn, String fecha_publi) {
            this.titulo = titulo;
            this.autor = autor;
            this.isbn = isbn;
            this.fecha_publi = fecha_publi;
        }

        public String getTitulo() { return titulo; }
        public String getAutor() { return autor; }
        public int getIsbn() { return isbn; }

        public void setTitulo(String titulo) { this.titulo = titulo; }
        public void setAutor(String autor) { this.autor = autor; }
        public void setIsbn(int isbn) {
            this.isbn = isbn;
        }

    }


