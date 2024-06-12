package org.example.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.utils.constants.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String password, String email, String document, BigDecimal balance, UserType type){

}