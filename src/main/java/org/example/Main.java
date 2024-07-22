package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "")
                .locations("classpath:db/migration")
                .load();


        flyway.migrate();

        System.out.println("Database migrations applied successfully!");
    }
}
