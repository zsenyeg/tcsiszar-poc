package hu.tcsiszar.service;

import hu.tcsiszar.dao.repository.PaymentTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentTransactionProcessService {
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processPaymentTransaction(Long id) {
        var paymentTransaction = paymentTransactionRepository.findById(id).orElseThrow();
        try {
            // process payment transaction
            paymentTransaction.setProcessed(Boolean.TRUE);
        } finally {
            // unlock
            paymentTransaction.setLockingDate(null);
            paymentTransaction.setLockedBy(null);
            paymentTransaction.setLockedTo(null);
        }
    }
}
