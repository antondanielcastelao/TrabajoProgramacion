CREATE DATABASE biblioteca;

\c biblioteca;

CREATE TABLE libros(
    ISBN integer,
    titulo varchar(50),
    autor varchar(50),
    fecha_publi varchar(20),
    primary key (ISBN)
);

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978849759, 'Cien años de soledad', 'Gabriel García Márquez', '1967-05-30');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978843760, 'Rayuela', 'Julio Cortázar', '1963-06-28');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978840805, 'La sombra del viento', 'Carlos Ruiz Zafón', '2001-04-01');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978842047, '1984', 'George Orwell', '1949-06-08');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978846633, 'El principito', 'Antoine de Saint-Exupéry', '1943-04-06');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978849838, 'Don Quijote', 'Miguel de Cervantes', '1605-01-16');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978842641, 'Crimen y castigo', 'Fiódor Dostoyevski', '1866-01-01');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978843763, 'Ficciones', 'Jorge Luis Borges', '1944-05-01');

INSERT INTO libros (ISBN, titulo, autor, fecha_publi)
VALUES (978840814, 'Los pilares de la tierra', 'Ken Follett', '1989-10-01');