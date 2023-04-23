package com.shop.eleventh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class EleventhApplication {

    public static void main(String[] args) {
        SpringApplication.run(EleventhApplication.class, args);
    }

}

class Item {
    private final String id;
    private String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}