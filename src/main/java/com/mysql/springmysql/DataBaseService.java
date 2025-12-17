package com.mysql.springmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
public class DataBaseService {

    @Autowired
    private DataSource dataSource;

    public List<String> listDataBase(){
        List<String> databases = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES")) {

            while (resultSet.next()){
                databases.add(resultSet.getString(1));
            }

        }catch (Exception e){
            throw new RuntimeException("Error al recuperar la base de datos " +e);
        }
        return databases;
    }

    public  List<String> listTables(String databaseName){
        List<String> tables = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("USE " + databaseName);
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while (resultSet.next()){
                tables.add(resultSet.getString(1));
            }
        }catch (Exception e){
            throw new RuntimeException("Error al recuperar la base de datos " +e);
        }
        return tables;
    }
}
