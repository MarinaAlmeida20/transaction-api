package com.javanauta.transaction_api.controller;

import com.javanauta.transaction_api.business.services.TransactionService;
import com.javanauta.transaction_api.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(description = "Endpoint responsavel por adicionar transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction gravada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos nao atendem os requisitos da transaction"),
            @ApiResponse(responseCode = "400", description = "Erro de requisicao"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> addTransaction(
            @RequestBody TransactionRequestDTO dto){
        transactionService.addTransactions(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsavel por deletar transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisicao"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteTransaction(){
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
