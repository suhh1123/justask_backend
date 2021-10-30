package com.example.JustAsk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExtractRequestBody {

    @JsonProperty("data")
    public List<String> data;

    @JsonProperty("max_keywords")
    public int maxKeywords;

    public ExtractRequestBody(List<String> data, int maxKeywords) {
        this.data = data;
        this.maxKeywords = maxKeywords;
    }
}
