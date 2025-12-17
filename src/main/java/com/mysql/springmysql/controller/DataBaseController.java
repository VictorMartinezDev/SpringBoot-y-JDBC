package com.mysql.springmysql.controller;

import com.mysql.springmysql.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataBaseController {

    @Autowired
    private DataBaseService dataBaseService;

    @GetMapping("/databases")
    public List<String> getAllDataBase(){
        return dataBaseService.listDataBase();
    }

    @GetMapping("/databases/{nameDatabe}/tables")
    public List<String> getAllTables(@PathVariable("nameDatabe") String nameDatabase){
        return dataBaseService.listTables(nameDatabase);
    }




}
