package com.karolismed.mongo.polling.auth.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String displayName;
    private String password;

    @Builder.Default
    private List<ObjectId> pollIds = new ArrayList<>();
}
