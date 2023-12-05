package ru.kuzmin.apicatalog;

import org.junit.jupiter.api.Test;
import org.quickperf.junit5.QuickPerfTest;
import org.quickperf.spring.sql.QuickPerfSqlConfig;
import org.quickperf.sql.annotation.AnalyzeSql;
import org.quickperf.sql.annotation.ExpectMaxQueryExecutionTime;
import org.quickperf.sql.annotation.ExpectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.kuzmin.apicatalog.domain.entity.Currency;
import ru.kuzmin.apicatalog.repository.CurrencyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@Import(QuickPerfSqlConfig.class)
@QuickPerfTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyRepositoryTest {
    @Autowired
    CurrencyRepository currencyRepository;
    public static PostgreSQLContainer postgreSQL =
            new PostgreSQLContainer("postgres:16.1-alpine")
                    .withUsername("postgres")
                    .withPassword("password")
                    .withDatabaseName("catalog");

    //Start the container
    static {
        postgreSQL.start();
    }

    //Override the configuration of Spring Boot to use the container
    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQL::getJdbcUrl);
        registry.add("spring.datasource.username",
                postgreSQL::getUsername);
        registry.add("spring.datasource.password",
                postgreSQL::getPassword);
    }

    @Test
    @ExpectSelect()
    @ExpectMaxQueryExecutionTime(thresholdInMilliSeconds = 2)
    @AnalyzeSql
    public void should_get_a_currency() {
        Optional<Currency> currency = currencyRepository.findById(1L);
        assertAll(
                () -> assertTrue(currency.isPresent()),
                () -> assertEquals("USD", currency.get().getCode())
        );
    }
}
