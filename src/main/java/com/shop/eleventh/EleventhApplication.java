package com.shop.eleventh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EleventhApplication {

    public static void main(String[] args) {
        SpringApplication.run(EleventhApplication.class, args);
    }
}

@RestController
@RequestMapping("/items")
class RestApiDemoController {
    private List<Item> items = new ArrayList<>();

    public RestApiDemoController() {
        items.addAll(List.of(
                new Item("Macbook 14"),
                new Item("Apple watch 4"),
                new Item("iphone SE"),
                new Item("ipad Air 3")
        ));
    }

    @GetMapping
    Iterable<Item> getItems() {
        return items;
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