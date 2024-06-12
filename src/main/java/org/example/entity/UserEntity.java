package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.utils.constants.UserType;

import java.math.BigDecimal;

@Entity(name = "tabuser")
@Table(name = "tabuser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String nome;
    @Column(name = "cpfcnpj", nullable = false, unique = true)
    private String cpf;
    @Column(name = "pwd", nullable = false)
    private String senha;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

}
