
package com.example.demo;

import org.springframework.data.annotation.Id;

public class Company{
    @Id
    public String name;
    public String datum;
    public float gewicht;

    public Company(String datum, String name, float gewicht){
        this.name = name;
        this.datum = datum;
        this.gewicht = gewicht;
    }

}