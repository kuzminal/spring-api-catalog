package ru.kuzmin.apicatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.service.CurrencyService;

import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CurrencyDTO> getById(@PathVariable Long id) {
        Optional<CurrencyDTO> response = currencyService.getById(id);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CurrencyDTO> save(@RequestBody CurrencyDTO
                                                    currencyDTO) {
        CurrencyDTO response = currencyService.save(currencyDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CurrencyDTO> update(@RequestBody CurrencyDTO
                                                      currencyDTO) {
        Optional<CurrencyDTO> response = currencyService.update(currencyDTO);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
