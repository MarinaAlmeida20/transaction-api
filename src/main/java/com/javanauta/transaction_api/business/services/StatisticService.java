package com.javanauta.transaction_api.business.services;

import com.javanauta.transaction_api.controller.dtos.StatisticResponseDTO;
import com.javanauta.transaction_api.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    public final TransactionService transactionService;

    public StatisticResponseDTO calculateStatisticTransactions(Integer intervalSearch){
        List<TransactionRequestDTO> transactionRequestDTOList =
                transactionService.findTransactions(intervalSearch);

        DoubleSummaryStatistics statisticsTransactions =
                transactionRequestDTOList.stream()
                        .mapToDouble(TransactionRequestDTO::value)
                        .summaryStatistics();

        return new StatisticResponseDTO(
                statisticsTransactions.getCount(),
                statisticsTransactions.getSum(),
                statisticsTransactions.getAverage(),
                statisticsTransactions.getMin(),
                statisticsTransactions.getMax()
        );
    }
}
