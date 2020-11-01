package com.karolismed.mongo.polling.poll.model.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionVoteResultsAggregation {
    private ObjectId optionId;
    private String value;
    private int votes;
    private double percentage;
}
