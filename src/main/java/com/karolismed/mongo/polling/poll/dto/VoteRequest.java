package com.karolismed.mongo.polling.poll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequest {
    @NotEmpty(message = "Options not specified")
    List<ObjectId> optionIds;
}
