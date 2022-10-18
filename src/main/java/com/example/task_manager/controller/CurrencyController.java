package com.example.task_manager.controller;


import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.CurrencyDTO;
import com.example.task_manager.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PreAuthorize("hasAuthority('CURRENCY_READ_ALL')")
    @GetMapping
    public ResponseEntity getAll() {
        ApiResponse response = currencyService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('CURRENCY_READ')")
//    @GetMapping(value = "/{Ccy}")
    @RequestMapping(value ="/{Ccy}", method = RequestMethod.GET)
    public ResponseEntity getOne(@PathVariable String Ccy) {
        ApiResponse response = currencyService.getOne(Ccy);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAnyAuthority('CURRENCY_TRANSFER')")
    @GetMapping("/transfer")
    public ResponseEntity transfer(@RequestBody CurrencyDTO currencyDTO) {
        ApiResponse response = currencyService.transfer(currencyDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }


}
