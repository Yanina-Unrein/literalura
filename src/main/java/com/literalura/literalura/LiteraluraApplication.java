package com.literalura.literalura;

import com.literalura.literalura.entity.Author;
import com.literalura.literalura.entity.Book;
import com.literalura.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.println("\n📚 Bienvenido a Literalura");
			System.out.println("1 - Buscar libro por título");
			System.out.println("2 - Listar libros registrados");
			System.out.println("3 - Listar autores registrados");
			System.out.println("4 - Listar autores vivos en un año");
			System.out.println("5 - Listar libros por idioma");
			System.out.println("0 - Salir");
			System.out.print("Seleccione una opción: ");
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					System.out.print("Ingrese el título del libro: ");
					String title = scanner.nextLine();
					String resultado = bookService.buscarYRegistrarLibro(title);
					System.out.println(resultado);
					break;
				case 2:
					List<Book> books = bookService.listarLibros();
					books.forEach(System.out::println);
					break;
				case 3:
					List<Author> authors = bookService.listarAutores();
					authors.forEach(System.out::println);
					break;
				case 4:
					System.out.print("Ingrese el año: ");
					int year = scanner.nextInt();
					List<Author> autoresVivos = bookService.listarAutoresVivosEnAnio(year);
					autoresVivos.forEach(System.out::println);
					break;
				case 5:
					System.out.print("Ingrese el código de idioma (ES, EN, FR, PT): ");
					String language = scanner.nextLine();
					List<Book> librosPorIdioma = bookService.listarLibrosPorIdioma(language);
					librosPorIdioma.forEach(System.out::println);
					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("❌ Opción inválida");
			}

		} while (option != 0);
	}
}
