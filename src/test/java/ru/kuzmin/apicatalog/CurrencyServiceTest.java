package ru.kuzmin.apicatalog;

import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quickperf.jvm.allocation.AllocationUnit;
import org.quickperf.jvm.annotations.ExpectNoHeapAllocation;
import org.quickperf.jvm.annotations.HeapSize;
import org.quickperf.jvm.annotations.MeasureHeapAllocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.domain.entity.Currency;
import ru.kuzmin.apicatalog.repository.CurrencyRepository;
import ru.kuzmin.apicatalog.service.CurrencyService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyServiceTest {
    private CurrencyService service;
    private CurrencyRepository currencyRepository;

    @BeforeEach
    public void setUp() {
        currencyRepository = mock(CurrencyRepository.class);
        Validator validator = mock(Validator.class);
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        PlatformTransactionManager transactionManager = mock(PlatformTransactionManager.class);
        service = new CurrencyService(currencyRepository, validator, transactionTemplate, transactionManager);
    }

    @Test
    @MeasureHeapAllocation
    @ExpectNoHeapAllocation
    //@HeapSize(value = 1, unit = AllocationUnit.BYTE)
    public void should_save_a_country() {
        CurrencyDTO currencyDTO = new CurrencyDTO("ARG", "Argentina", Boolean.TRUE, 2);
        Currency currency = new Currency();
        when(currencyRepository.save(any())).thenReturn(currency);
        assertNotNull(service.save(currencyDTO));
    }
}
