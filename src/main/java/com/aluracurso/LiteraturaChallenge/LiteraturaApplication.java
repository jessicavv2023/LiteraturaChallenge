package com.aluracurso.LiteraturaChallenge;

import com.aluracurso.LiteraturaChallenge.model.AutorEntity;
import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import com.aluracurso.LiteraturaChallenge.model.Respuesta;
import com.aluracurso.LiteraturaChallenge.repository.AutorRepository;
import com.aluracurso.LiteraturaChallenge.repository.LibroRepository;
import com.aluracurso.LiteraturaChallenge.service.ConsumoAPI;
import com.aluracurso.LiteraturaChallenge.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

/**
 * ------------------------------------------------------
 * CLASE PRINCIPAL DE SPRING BOOT
 * ------------------------------------------------------
 */
@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	/**
	 * Con este CommandLineRunner, al arrancar la aplicación,
	 * se invoca el método 'menu()' de ClienteLiteratura.
	 */
	@Bean
	public CommandLineRunner correrMenu(ClienteLiteratura cliente) {
		return args -> {
			cliente.menu();
		};
	}

}

/**
 * ------------------------------------------------------------------
 * CLASE ClienteLiteratura
 * (Se marca con @org.springframework.stereotype.Service o @Component)
 * ------------------------------------------------------------------
 */
@org.springframework.stereotype.Service
class ClienteLiteratura {

	private final String URL_BASE = "https://gutendex.com/books/?search=";
	private Scanner teclado = new Scanner(System.in);

	// Inyectamos nuestras clases de consumo/parseo de la API,
	// que también están anotadas con @Service/@Component (ver más abajo).
	private final ConsumoAPI consumoApi;
	private final ConvierteDatos conversor;

	private final LibroRepository libroRepositorio;
	private final AutorRepository autorRepositorio;

	// Usamos constructor injection (recomendado en Spring)
	public ClienteLiteratura(ConsumoAPI consumoApi,
							 ConvierteDatos conversor,
							 LibroRepository libroRepositorio,
							 AutorRepository autorRepositorio) {
		this.consumoApi = consumoApi;
		this.conversor = conversor;
		this.libroRepositorio = libroRepositorio;
		this.autorRepositorio = autorRepositorio;
	}

	public void menu() {
		var opcion = -1;
		while (opcion != 0) {
			var menu = """
                Elija la opción a través de su número:
                    1.- Buscar libro por título (API)
                    2.- Lista libros registrados
                    3.- Lista autores registrados
                    4.- Lista autores vivos en un determinado año
                    5.- Listar libros por idioma
                    0 - Salir
                """;
			System.out.println(menu);

			try {
				opcion = teclado.nextInt();  // <- si no es un número, salta InputMismatchException
			} catch (InputMismatchException e) {
				System.out.println("Por favor, ingresa un número válido.");
				teclado.nextLine(); // Limpia la entrada que causó el error
				continue;           // Vuelve al while para pedirlo de nuevo
			}
			teclado.nextLine(); // limpiar buffer

			switch (opcion) {
				case 1 -> buscarLibroWeb();
				case 2 -> buscarLibros();
				case 3 -> buscarAutores();
				case 4 -> buscarAutoresVivos();
				case 5 -> buscarPorIdiomas();
				case 0 -> System.out.println("Adiós, ¡Vuelva pronto!");
				default -> System.out.println("Opción inválida");
			}
		}
	}


	private void buscarLibros() {
		List<LibroEntity> libros = libroRepositorio.findAll();

		if (!libros.isEmpty()) {
			System.out.println("\n\n---------- LIBROS -------");
			for (LibroEntity libro : libros) {
				System.out.println("Título: " + libro.getTitulo().replace(" ", "+"));
				System.out.println("Autor: " + libro.getAutor().getNombre());
				System.out.println("Idioma: " + libro.getLenguaje());
				System.out.println("Descargas: " + libro.getDescargas());
				System.out.println("-------------------------");
			}
		} else {
			System.out.println("\n\n----- NO SE ENCONTRARON RESULTADOS ----\n");
		}
	}

	private void buscarAutores() {
		List<AutorEntity> autores = autorRepositorio.findAll();

		if (!autores.isEmpty()) {
			System.out.println("\n\n---------- AUTORES -------");
			for (AutorEntity autor : autores) {
				System.out.println("Nombre: " + autor.getNombre());
				System.out.println("Fecha de Nacimiento: " + autor.getFechaNacimiento());
				System.out.println("Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
				System.out.println("Libros: ");
				if (autor.getLibros() != null && !autor.getLibros().isEmpty()) {
					autor.getLibros().forEach(libro -> System.out.println(" - " + libro.getTitulo()));
				} else {
					System.out.println(" - Sin libros asociados");
				}
				System.out.println("-------------------------");
			}
		} else {
			System.out.println("\n\n----- NO SE ENCONTRARON RESULTADOS ----\n");
		}
	}

	private void buscarAutoresVivos() {
		System.out.println("Escriba el año para el que desea conocer los autores vivos: ");
		var anio = teclado.nextInt();
		teclado.nextLine();

		List<AutorEntity> autores = autorRepositorio.findForYear(anio);

		if (!autores.isEmpty()) {
			System.out.println("\n\n---------- AUTORES VIVOS -------");
			for (AutorEntity autor : autores) {
				System.out.println("Nombre: " + autor.getNombre());
				System.out.println("Fecha de Nacimiento: " + autor.getFechaNacimiento());
				System.out.println("Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
				System.out.println("Libros: ");
				if (autor.getLibros() != null && !autor.getLibros().isEmpty()) {
					autor.getLibros().forEach(libro -> System.out.println(" - " + libro.getTitulo()));
				} else {
					System.out.println(" - Sin libros asociados");
				}
				System.out.println("-------------------------");
			}
		} else {
			System.out.println("\n\n----- NO SE ENCONTRARON RESULTADOS ----\n");
		}
	}

	private void buscarPorIdiomas() {
		var menu = """
                \n
                Seleccione un idioma, escribe 1 o 2 :
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
		}else{
			System.out.println("Escribe una opción valida");
		}

		List<LibroEntity> libros = libroRepositorio.findForLanguage(seleccion);

		if (!libros.isEmpty()) {
			System.out.println("\n\n---------- LIBROS POR IDIOMA -------");
			for (LibroEntity libro : libros) {
				System.out.println("Título: " + libro.getTitulo());
				System.out.println("Autor: " + libro.getAutor().getNombre());
				System.out.println("Idioma: " + libro.getLenguaje());
				System.out.println("Descargas: " + libro.getDescargas());
				System.out.println("-------------------------");
			}
		} else {
			System.out.println("\n\n----- NO SE ENCONTRARON RESULTADOS ----\n");
		}
	}

	private void buscarLibroWeb() {
		Respuesta datos = getDatosSerie();  // <-- aquí obtienes el JSON parseado

		// Agrega inmediatamente tus validaciones:
		if (datos == null) {
			System.out.println("Error: no se obtuvo nada de la API");
			return; // Salimos del método
		}
		if (datos.getResults() == null || datos.getResults().isEmpty()) {
			System.out.println("No hay resultados en la API para esa búsqueda");
			return; // Salimos del método
		}

		// Aquí ya estás seguro de que results() no es null ni está vacío
		var primerResultado = datos.getResults().get(0);
		// ... Continúa con tu lógica (guardar el libro, etc.)

		System.out.println("Datos obtenidos:\n" + datos);
	}


	private Respuesta getDatosSerie() {
		System.out.println("Ingresa el nombre del libro que deseas buscar: ");
		var titulo = teclado.nextLine();
		titulo = titulo.replace(" ", "%20");

		System.out.println("Título (param URL): " + titulo.replace("%20", " "));
		String urlBusqueda = URL_BASE + titulo;
		System.out.println("URL final: " + urlBusqueda);

		// Obtenemos el JSON desde la API
		var json = consumoApi.obtenerDatos(urlBusqueda);

		// Convertimos el JSON a objeto Respuesta
		return conversor.obtnerDatos(json, Respuesta.class);
	}
}
