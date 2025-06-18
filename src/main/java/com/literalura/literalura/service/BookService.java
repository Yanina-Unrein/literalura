package com.literalura.literalura.service;

import com.literalura.literalura.dto.GutendexBook;
import com.literalura.literalura.dto.AuthorDto;
import com.literalura.literalura.entity.Author;
import com.literalura.literalura.entity.Book;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GutendexService gutendexService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GutendexService gutendexService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.gutendexService = gutendexService;
    }

    public String buscarYRegistrarLibro(String titulo) {
        Optional<Book> existente = bookRepository.findByTitleIgnoreCase(titulo);
        if (existente.isPresent()) {
            return "✅ El libro ya está registrado: " + existente.get().getTitle();
        }

        Optional<GutendexBook> optionalLibro = gutendexService.buscarLibroPorTitulo(titulo);
        if (optionalLibro.isEmpty()) {
            return "❌ No se encontró el libro o se canceló la selección";
        }


        GutendexBook gBook = optionalLibro.get();

        // Extraer autor
        if (gBook.getAuthors().isEmpty()) {
            return "❌ El libro no tiene autor registrado en la API.";
        }

        AuthorDto dtoAutor = gBook.getAuthors().get(0);
        String[] nombres = dtoAutor.getName().split(", ");
        String lastName = nombres[0];
        String firstName = nombres.length > 1 ? nombres[1] : "";

        // Buscar o crear autor
        Author autor = authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseGet(() -> {
                    Author nuevo = new Author();
                    nuevo.setFirstName(firstName);
                    nuevo.setLastName(lastName);
                    nuevo.setBirthYear(dtoAutor.getBirth_year());
                    nuevo.setDeathYear(dtoAutor.getDeath_year());
                    return authorRepository.save(nuevo);
                });

        // Crear y guardar libro
        Book nuevoLibro = new Book();
        nuevoLibro.setTitle(gBook.getTitle());
        nuevoLibro.setLanguage(gBook.getLanguages().isEmpty() ? "Desconocido" : gBook.getLanguages().get(0));
        nuevoLibro.setDownloadCount(gBook.getDownload_count());
        nuevoLibro.setAuthor(autor);
        bookRepository.save(nuevoLibro);

        return "📘 Libro registrado correctamente: " + nuevoLibro.getTitle();
    }

    public List<Book> listarLibros() {
        return bookRepository.findAll();
    }

    public List<Author> listarAutores() {
        return authorRepository.findAll();
    }

    public List<Author> listarAutoresVivosEnAnio(int year) {
        return authorRepository.findAll().stream()
                .filter(a -> a.getBirthYear() != null && a.getBirthYear() <= year &&
                        (a.getDeathYear() == null || a.getDeathYear() >= year))
                .toList();
    }

    public List<Book> listarLibrosPorIdioma(String language) {
        return bookRepository.findByLanguageIgnoreCase(language);
    }
}
