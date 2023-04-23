package com.shop.eleventh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final List<Item> items = new ArrayList<>();

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

    @GetMapping("{id}")
    Optional<Item> getItemById(@PathVariable String id) {
        for (Item c : items) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    @PostMapping
    Item postItem(@RequestBody Item item) {
        items.add(item);
        return item;
    }

    @PutMapping("/{id}")
    ResponseEntity<Item> putItem(@PathVariable String id,
                                 @RequestBody Item item) {
        int itemIndex = -1;

        for (Item c : items) {
            if (c.getId().equals(id)) {
                itemIndex = items.indexOf(c);
                items.set(itemIndex, item);
            }
        }

        //HTTP status codes are required for PUT method responses
        return (itemIndex == -1) ?
                new ResponseEntity<>(postItem(item), HttpStatus.CREATED) :
                new ResponseEntity<>(item, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    void deleteItem(@PathVariable String id) {
        items.removeIf(c -> c.getId().equals(id));
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