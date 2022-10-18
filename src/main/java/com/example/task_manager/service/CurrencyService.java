package com.example.task_manager.service;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.CurrencyDTO;
import com.example.task_manager.entity.Constant;
import com.example.task_manager.entity.Currency;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {


    @SneakyThrows
    public ApiResponse getAll() {
        List<Currency> currencyList = ApiService.getCurrencyAll();
        return ApiResponse.builder().message("Take all").success(true).object(currencyList).build();
    }


    @SneakyThrows
    public ApiResponse getOne(String ccy) {
        Currency currency = ApiService.getCurrency(ccy);
        return ApiResponse.builder().message("Take one").success(true).object(currency).build();
    }

    @SneakyThrows
    public ApiResponse transfer(CurrencyDTO currencyDTO) {
        String ordinal = currencyDTO.getFrom();
        String target = currencyDTO.getTo();
        Double money = currencyDTO.getMoney();
        double result = CurrencyConversionService.getConversionRatio(Constant.valueOf(ordinal), Constant.valueOf(target), money);

        return ApiResponse.builder().message("Here").success(true).object(result).build();
    }
}
