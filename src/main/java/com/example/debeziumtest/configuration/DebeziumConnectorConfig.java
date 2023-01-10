package com.example.debeziumtest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class DebeziumConnectorConfig {

    /**
     * Student Database details.
     */
    @Value("${customer.datasource.host}")
    private String customerDbHost;

    @Value("${customer.datasource.databasename}")
    private String customerDBName;

    @Value("${customer.datasource.port}")
    private String customerDBPort;

    @Value("${customer.datasource.username}")
    private String customerDBUserName;

    @Value("${customer.datasource.password}")
    private String customerDBPassword;

    private String CUSTOMER_TABLE_NAME = "public.customer";

    /**
     * Student database connector.
     *
     * @return Configuration.
     */
    @Bean
    public io.debezium.config.Configuration studentConnector() {
        return io.debezium.config.Configuration.create()
            .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
            .with("offset.storage",  "org.apache.kafka.connect.storage.FileOffsetBackingStore")
            .with("offset.storage.file.filename", "/Users/rbg831/Documents/Sohan/Projects/POC/embedded-debezium/student-cdc-relay/student-offset.dat")
            .with("offset.flush.interval.ms", 60000)
            .with("name", "student-postgres-connector")
            .with("database.server.name", customerDbHost +"-"+ customerDBName)
            .with("database.hostname", customerDbHost)
            .with("database.port", customerDBPort)
            .with("database.user", customerDBUserName)
            .with("database.password", customerDBPassword)
            .with("database.dbname", customerDBName)
            .with("table.whitelist", CUSTOMER_TABLE_NAME).build();
    }
}