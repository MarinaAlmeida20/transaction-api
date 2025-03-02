package com.javanauta.transaction_api.controller;

import com.javanauta.transaction_api.business.services.TransactionService;
import com.javanauta.transaction_api.controller.dtos.TransactionRequestDTO;
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
    public ResponseEntity<Void> addTransaction(
            @RequestBody TransactionRequestDTO dto){
        transactionService.addTransactions(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(){
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
