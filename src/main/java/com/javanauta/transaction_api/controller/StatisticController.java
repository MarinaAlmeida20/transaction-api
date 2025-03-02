package com.javanauta.transaction_api.controller;

import com.javanauta.transaction_api.business.services.StatisticService;
import com.javanauta.transaction_api.controller.dtos.StatisticResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nabusca de estatiticas de transactions"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StatisticResponseDTO> findStatistics(
            @RequestParam(value = "intervalSearch", required = false, defaultValue = "60")
                                                               Integer intervalSearch){
        return ResponseEntity.ok(
                statisticService.calculateStatisticTransactions(intervalSearch));

    }
}
