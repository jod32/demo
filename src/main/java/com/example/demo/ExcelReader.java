package com.example.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExcelReader {
    @Autowired
    CountryInterface country;
    String path;
    public ExcelReader(@Value("/home/t/Schreibtisch/piechart-data.xls") String path){
        this.path = path;
    }
    public Map<String, Float> readVL(String path) {
        HashMap<String, Float> c1 = null;
        try (HSSFWorkbook work = new HSSFWorkbook(new FileInputStream(path));) {
            c1 = new HashMap<>();
            HSSFSheet countries = work.getSheet("Sheet1");
            Iterator<Row> rowIterator = countries.iterator();
            rowIterator.next();
            Row row;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                Cell d1, d2;
                d1 = row.getCell(0);
                d2 = row.getCell(1);
                if (d1 != null && d2 != null) {
                    c1.put(d1.toString(), Float.parseFloat(d2.toString()));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return c1;
    }
    public void writeToDatabase(){
        Map<String,Float> c1 = readVL(this.path);
        for(Map.Entry<String, Float> entry : c1.entrySet()){
            country.save(new Country(entry.getKey(), entry.getValue()));
        }
    }

    public List<Country> getAllCountries(){
        return country.findAll();
    }
}