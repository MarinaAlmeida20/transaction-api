package com.javanauta.transaction_api.controller;

import com.javanauta.transaction_api.business.services.StatisticService;
import com.javanauta.transaction_api.controller.dtos.StatisticResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    public ResponseEntity<StatisticResponseDTO> findStatistics(
            @RequestParam(value = "intervalSearch", required = false, defaultValue = "60")
                                                               Integer intervalSearch){
        return ResponseEntity.ok(
                statisticService.calculateStatisticTransactions(intervalSearch));

    }
}
