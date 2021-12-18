package com.example.springfrontend;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Food {
    private String name;
    private String size;
    private float price;
}
