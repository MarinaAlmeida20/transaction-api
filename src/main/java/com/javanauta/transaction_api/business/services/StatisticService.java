package com.javanauta.transaction_api.business.services;

import com.javanauta.transaction_api.controller.dtos.StatisticResponseDTO;
import com.javanauta.transaction_api.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {
    public final TransactionService transactionService;

    public StatisticResponseDTO calculateStatisticTransactions(Integer intervalSearch){
        log.info("Iniciada busca de estatistica de transacoes pelo periodo de tempo " + intervalSearch);

        List<TransactionRequestDTO> transactionRequestDTOList =
                transactionService.findTransactions(intervalSearch);

        if(transactionRequestDTOList.isEmpty()){
            return new StatisticResponseDTO(
                    0L,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
        }

        DoubleSummaryStatistics statisticsTransactions =
                transactionRequestDTOList.stream()
                        .mapToDouble(TransactionRequestDTO::value)
                        .summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");

        return new StatisticResponseDTO(
                statisticsTransactions.getCount(),
                statisticsTransactions.getSum(),
                statisticsTransactions.getAverage(),
                statisticsTransactions.getMin(),
                statisticsTransactions.getMax()
        );
    }
}
