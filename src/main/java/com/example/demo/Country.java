package com.example.demo;

import org.springframework.data.annotation.Id;

public class Country {
   @Id 
   String name;
   Float weight;

   public Country(String name, Float weight){
        this.name = name;
        this.weight = weight;
   }
}
