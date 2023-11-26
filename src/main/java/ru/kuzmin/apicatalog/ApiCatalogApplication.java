package ru.kuzmin.apicatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApiCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCatalogApplication.class, args);
    }

}
