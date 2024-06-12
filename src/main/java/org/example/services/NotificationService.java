package org.example.services;

import org.example.dtos.NotificationDTO;
import org.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void notifyUser(UserEntity user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify",notificationRequest, String.class);

        if(notificationResponse.getStatusCode() != HttpStatus.OK){
            System.out.println("Erro na notificação");
            throw new Exception("Falha no serviço de notificação");
        }

    }


}
