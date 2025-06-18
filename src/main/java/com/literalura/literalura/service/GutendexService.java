package com.literalura.literalura.service;
import com.literalura.literalura.dto.GutendexBook;
import com.literalura.literalura.dto.GutendexResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class GutendexService {
    private final RestTemplate restTemplate;
    private final Scanner scanner;

    public GutendexService() {
        this.restTemplate = new RestTemplate();
        this.scanner = new Scanner(System.in);
    }

    public Optional<GutendexBook> buscarLibroPorTitulo(String titulo) {
        String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = "https://gutendex.com/books/?search=" + encodedTitulo;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            System.out.println("📚 Resultados encontrados:");
            for (int i = 0; i < response.getResults().size(); i++) {
                // Respeta exactamente tu formato de visualización
                System.out.println((i + 1) + " - " + response.getResults().get(i).getTitle());
            }

            if (response.getResults().size() == 1) {
                return Optional.of(response.getResults().get(0));
            }

            System.out.println("0 - Cancelar");
            System.out.print("Seleccione un libro: ");

            try {
                int seleccion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                if (seleccion == 0) {
                    return Optional.empty();
                }

                if (seleccion > 0 && seleccion <= response.getResults().size()) {
                    return Optional.of(response.getResults().get(seleccion - 1));
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Limpiar entrada inválida
            }

            System.out.println("❌ Selección inválida");
            return Optional.empty();
        }

        return Optional.empty();
    }
}
