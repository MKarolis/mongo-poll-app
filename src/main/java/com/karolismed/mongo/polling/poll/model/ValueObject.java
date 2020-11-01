package com.karolismed.mongo.polling.poll.model;

import com.karolismed.mongo.polling.poll.model.aggregation.PollVoteResultsAggregation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValueObject {
    private Object id;
    private PollVoteResultsAggregation value;
}
