package ru.kuzmin.apicatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.service.CurrrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrrencyService currrencyService;

    @Autowired
    public CurrencyController(CurrrencyService currrencyService) {
        this.currrencyService = currrencyService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CurrencyDTO> getById(@PathVariable Long id) {
        CurrencyDTO response = currrencyService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CurrencyDTO> save(@RequestBody CurrencyDTO
                                                    currencyDTO) {
        CurrencyDTO response = currrencyService.save(currencyDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CurrencyDTO> update(@RequestBody CurrencyDTO
                                                      currencyDTO) {
        CurrencyDTO response = currrencyService.update(currencyDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currrencyService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
