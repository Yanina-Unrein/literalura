package com.literalura.literalura.dto;
import java.util.List;

public class GutendexBook {
    private String title;
    private List<AuthorDto> authors;
    private List<String> languages;
    private int download_count;

    public String getTitle() {
        return title;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public int getDownload_count() {
        return download_count;
    }
}
