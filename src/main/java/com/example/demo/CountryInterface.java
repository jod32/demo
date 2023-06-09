package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryInterface extends MongoRepository<Country, String> {

  public Country findByName(String name);
}