package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@Component
public class CSVReader extends BufferedReader {
    @Autowired
    CompanyInterface company;
    public CSVReader(@Value("/home/t/Schreibtisch/Ring Chart Data.csv") String src) throws FileNotFoundException {
        super(new FileReader(src));
    }

    public List<String[]> readCSVLine() {
        String l = ".";
        List<String[]> values = new ArrayList<>();
        int counter = 0;
        try {
            while ((l = this.readLine()) != null && l.length() != 0) {
                if (counter > 0) {
                    String[] abs = l.split(",");
                    values.add(abs);
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return values;
    }

    public void writeToDatabase(){
         List<String[]> values = readCSVLine();
         values.forEach(x -> {
            this.company.save(new Company(x[0], x[1], Float.parseFloat(x[2])));
         });
    }

    public List<Company> getAllCompanies(){
        return company.findAll();
    }

}
