package org.tutorial.ehcache.stock.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private String uuid;
    private String title;
    private String author;
    private String publisher;
    private int stock;

    public Stock(String uuid, String title, String author, String publisher) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
