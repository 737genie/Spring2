package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {
	private Long id;

    private String name;
    private int price;
    private int stock;
    private String imageFileName;
}
