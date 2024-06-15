package hu.tcsiszar.service;

import hu.tcsiszar.dao.entity.PaymentTransaction;
import hu.tcsiszar.dao.repository.PaymentTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class PaymentTransactionServiceTest {
    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Test
    void test_processPaymentTransaction() {
        var paymentTransaction = paymentTransactionRepository.save(new PaymentTransaction());

        paymentTransactionService.processPaymentTransactions();

        paymentTransaction = paymentTransactionRepository.findById(paymentTransaction.getId()).orElse(null);

        assertNotNull(paymentTransaction);
        assertNull(paymentTransaction.getLockingDate());
        assertNull(paymentTransaction.getLockedBy());
        assertNull(paymentTransaction.getLockedTo());
        assertEquals(Boolean.TRUE, paymentTransaction.getProcessed());
    }
}