package org.example.dtos;

import org.example.entity.UserEntity;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {

}
