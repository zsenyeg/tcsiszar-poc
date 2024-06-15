package hu.tcsiszar.service;

import hu.tcsiszar.dao.repository.PaymentTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;

    private final LockService lockService;

    private final PaymentTransactionProcessService paymentTransactionProcessService;

    public void processPaymentTransactions() {
        var paymentTransactions = paymentTransactionRepository.findAll();
        paymentTransactions.forEach(paymentTransaction -> {
            if (lockService.lockPaymentTransaction(paymentTransaction.getId())) {
                paymentTransactionProcessService.processPaymentTransaction(paymentTransaction.getId());
            }
        });
    }
}
