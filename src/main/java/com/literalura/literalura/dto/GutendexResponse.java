package com.literalura.literalura.dto;
import java.util.List;

public class GutendexResponse {
    private int count;
    private List<GutendexBook> results;

    public int getCount() {
        return count;
    }

    public List<GutendexBook> getResults() {
        return results;
    }
}

