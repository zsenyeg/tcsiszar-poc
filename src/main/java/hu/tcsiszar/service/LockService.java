package hu.tcsiszar.service;

import hu.tcsiszar.dao.repository.PaymentTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class LockService {
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean lockPaymentTransaction(Long id) {
        var lockedCount = paymentTransactionRepository.lock(id, LocalDateTime.now(), "me", LocalDateTime.now().plusMinutes(1));
        return lockedCount == 1;
    }
}
