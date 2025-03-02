package com.javanauta.transaction_api.business.services;

import com.javanauta.transaction_api.controller.dtos.TransactionRequestDTO;
import com.javanauta.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final List<TransactionRequestDTO> transactionRequestDTOList = new ArrayList<>();

    public void addTransactions(TransactionRequestDTO dto){

        log.info("Iniciado o processament de gravar transacoes");

        if(dto.dateHour().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atual");
        }

        if(dto.value() < 0){
            log.error("Valor nao pode ser menor que 0");
            throw new UnprocessableEntity("Valor nao pode ser menor que 0");
        }

        // Armazena a transaction in memory
        transactionRequestDTOList.add(dto);
    }

    // limpar transactions
    public void clearTransactions(){
        transactionRequestDTOList.clear();
    }

    public List<TransactionRequestDTO> getTransactions(Integer intervalSearch){

        /*
        * Get all the transactions made 60 seconds before actual time
        */
        OffsetDateTime dateTimeInterval =
                OffsetDateTime.now().minusSeconds(intervalSearch);

        /*
        * From list
        * filter if the transaction date and hour
        * if date and hour is after the date and hour now (60 seconds ago)
        * add to list
        */
        return transactionRequestDTOList.stream()
                .filter(transaction -> transaction.dateHour()
                        .isAfter(dateTimeInterval))
                .toList();
    }

}
