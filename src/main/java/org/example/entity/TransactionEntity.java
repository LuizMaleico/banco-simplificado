package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tabtrans")
@Table(name = "tabtrans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_gen")
    @SequenceGenerator(name = "trans_gen", sequenceName = "trans_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "receiver")
    private UserEntity receiver;
    private LocalDateTime creationDate;
}
