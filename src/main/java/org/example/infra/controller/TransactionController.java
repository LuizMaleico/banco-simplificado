package org.example.infra.controller;

import org.example.dtos.TransactionDTO;
import org.example.dtos.UserDTO;
import org.example.entity.TransactionEntity;
import org.example.entity.UserEntity;
import org.example.services.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransService service;

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception{
        TransactionEntity newTransaction = service.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }



}
