package com.karolismed.mongo.polling.auth.service;

import com.karolismed.mongo.polling.auth.dto.RegisterRequestDto;
import com.karolismed.mongo.polling.auth.model.User;
import com.karolismed.mongo.polling.core.exception.ConflictException;
import com.karolismed.mongo.polling.core.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class UserService {

    private final MongoTemplate mongoTemplate;
    private final PasswordEncoder passwordEncoder;

    public User getByIdOrThrow(ObjectId userId) {
        return Optional.ofNullable(
            mongoTemplate.findById(userId, User.class)
        ).orElseThrow(
            () -> new NotFoundException(String.format("User with id %s does not exist", userId))
        );
    }

    public void registerUser(RegisterRequestDto registerRequestDto) {
        Optional.ofNullable(
            mongoTemplate.findOne(
                new Query(where("username").is(registerRequestDto.getUsername())), User.class)
        ).ifPresent((user) -> {
            throw new ConflictException(
                String.format("User with username %s already exists", user.getUsername())
            );
        });

        User user = User.builder()
            .username(registerRequestDto.getUsername())
            .password(passwordEncoder.encode(registerRequestDto.getPassword()))
            .displayName(registerRequestDto.getDisplayName())
            .build();
        mongoTemplate.insert(
            user
        );
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(
            mongoTemplate.findOne(new Query(where("username").is(username)), User.class)
        );
    }
}
