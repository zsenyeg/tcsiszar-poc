package hu.tcsiszar.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime lockingDate;

    @Column
    private LocalDateTime lockedTo;

    @Column
    private String lockedBy;

    @Column
    private Boolean processed;
}
