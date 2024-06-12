package org.example.services;

import org.example.dtos.TransactionDTO;
import org.example.entity.TransactionEntity;
import org.example.entity.UserEntity;
import org.example.repository.TransactionRepository;
import org.example.utils.constants.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransService {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    //Validações da transação
    public void senderValidation(UserEntity sender, BigDecimal value) throws Exception{
        if(sender.getType() == null){
            throw new Exception(" - Sender não encontrado na transação.");
        }
        if(sender.getType() == UserType.MERCHANT){
            throw new Exception(" - Ação não permitida para usuário lojista");
        }
        if(sender.getBalance().compareTo(value) < 0){
            throw new Exception(" - Saldo insuficiente.");
        }
    }
    public void saveTransaction(){

    }

    public TransactionEntity createTransaction(TransactionDTO transaction) throws Exception{
        UserEntity sender = this.userService.UserFindById(transaction.senderId());
        UserEntity receiver = this.userService.UserFindById(transaction.receiverId());
        senderValidation(sender, transaction.value());
        boolean isAuthorized = getAuth();
        if(!isAuthorized){
            throw new Exception("Transação negada");
        }
        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setAmount(transaction.value());
        transactionEntity.setSender(sender);
        transactionEntity.setReceiver(receiver);
        transactionEntity.setCreationDate(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        userService.UserSave(sender);
        receiver.setBalance(receiver.getBalance().add(transaction.value()));
        userService.UserSave(receiver);
        TransactionEntity newTransaction = repository.save(transactionEntity);

        //notificationService.notifyUser(sender, "Transação realizada");


        return newTransaction;

    }
    public boolean getAuth(){
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(response.getStatusCode().equals(HttpStatus.OK) && response.getBody().get("status").equals("success")){
            return true;
        }else{
            return false;
        }
    }

}
