package hu.tcsiszar.dao.repository;

import hu.tcsiszar.dao.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    @Modifying
    @Query("UPDATE PaymentTransaction pt SET pt.lockingDate = :lockingDate, pt.lockedBy = :lockedBy, pt.lockedTo = :lockedTo WHERE pt.id = :id AND pt.lockingDate IS NULL AND pt.lockedBy IS NULL")
    int lock(@Param("id") Long id, @Param("lockingDate") LocalDateTime lockingDate, @Param("lockedBy") String lockedBy, @Param("lockedTo") LocalDateTime lockedTo);
}
