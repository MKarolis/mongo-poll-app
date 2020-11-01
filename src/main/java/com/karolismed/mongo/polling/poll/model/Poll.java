package com.karolismed.mongo.polling.poll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Poll {
    @Id
    private ObjectId id;
    private String title;
    private boolean multiChoice;
    private List<Option> options;
    private String ownerName;
}
