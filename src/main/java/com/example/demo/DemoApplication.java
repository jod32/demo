package com.example.demo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.client.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	CSVReader csvReader;
	@Autowired
	ExcelReader excelReader; 

	@Override
	public void run(String... args) throws Exception {
		try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");) {
			MongoDatabase database = mongoClient.getDatabase("myMongoDb");			
			csvReader.writeToDatabase();
			excelReader.writeToDatabase();
			csvReader.getAllCompanies().forEach(x -> System.out.println(x.name));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
