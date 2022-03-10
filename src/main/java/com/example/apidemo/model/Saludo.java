package com.example.apidemo.model;

import lombok.Getter;

@Getter
public class Saludo {

    private final long id;
    private final String content;

    public Saludo(long id, String content) {
        this.id = id;
        this.content = content;
    }


}
