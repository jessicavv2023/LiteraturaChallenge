package com.aluracurso.LiteraturaChallenge.client;

import com.aluracurso.LiteraturaChallenge.model.AutorEntity;
import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import com.aluracurso.LiteraturaChallenge.model.Respuesta;
import com.aluracurso.LiteraturaChallenge.repository.AutorRepository;
import com.aluracurso.LiteraturaChallenge.repository.LibroRepository;
import com.aluracurso.LiteraturaChallenge.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;



public class ClienteLiteratura {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private com.aluracurso.LibrariGutendex.service.ConsumoAPI consumoApi = new com.aluracurso.LibrariGutendex.service.ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public ClienteLiteratura(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opción a través de su número:
                        1.- Buscar libro por título
                        2.- Lista libros registrados
                        3.- Lista autores registrados
                        4.- Lista autores vivos en un determinado año
                        5.- Listar libros por idioma
                        0 - Salir
                        otra opcion 
                        """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    buscarLibros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivos();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("Adiós, ¡Vuelva pronto!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibros() {
        List<LibroEntity> libros = libroRepositorio.findAll();

        if (!libros.isEmpty()) {
            for (LibroEntity libro : libros) {
                System.out.println("\n\n---------- LIBROS -------\n");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getLenguaje());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarAutores() {
        List<AutorEntity> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (AutorEntity autor : autores) {
                System.out.println("\n\n---------- AUTORES -------\n");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println("Libros: ");
                autor.getLibros().forEach(libro -> System.out.println(" - " + libro.getTitulo()));
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarAutoresVivos() {
        System.out.println("Escriba el año para el que desea conocer los autores vivos: ");
        var anio = teclado.nextInt();
        teclado.nextLine();

        List<AutorEntity> autores = autorRepositorio.findForYear(anio);

        if (!autores.isEmpty()) {
            for (AutorEntity autor : autores) {
                System.out.println("\n\n---------- AUTORES VIVOS -------\n");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println("Libros: ");
                autor.getLibros().forEach(libro -> System.out.println(" - " + libro.getTitulo()));
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarPorIdiomas() {
        var menu = """
                Seleccione un idioma:
                    1.- Español
                    2.- Inglés
                """;
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String seleccion = "";

        if (idioma == 1) {
            seleccion = "es";
        } else if (idioma == 2) {
            seleccion = "en";
        }

        List<LibroEntity> libros = libroRepositorio.findForLanguage(seleccion);

        if (!libros.isEmpty()) {
            for (LibroEntity libro : libros) {
                System.out.println("\n\n---------- LIBROS POR IDIOMA -------\n");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getLenguaje());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void buscarLibroWeb() {
        Respuesta datos = getDatosSerie();

        if (!datos.results().isEmpty()) {
            LibroEntity libro = new LibroEntity(datos.results().get(0));
            libro = libroRepositorio.save(libro);
        }

        System.out.println("Datos: ");
        System.out.println(datos);
    }

    private Respuesta getDatosSerie() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Título: " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.obtenerDatos(URL_BASE + titulo);
        System.out.println(json);
        Respuesta datos = conversor.obtenerDatos(json, Respuesta.class);
        return datos;
    }
}