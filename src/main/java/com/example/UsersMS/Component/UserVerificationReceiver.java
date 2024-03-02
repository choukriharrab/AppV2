package com.example.UsersMS.Component;

import com.example.UsersMS.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVerificationReceiver {

    private UserRepo userRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${user.verification.routingKey}")
    private String userVerificationRoutingKey;

    @RabbitListener(queues = "${user.verification.queue}")
    public Boolean verifyUserExists(Long userId) {
        // Logic to check if the user exists
        return userRepo.existsById(userId);
    }
}

