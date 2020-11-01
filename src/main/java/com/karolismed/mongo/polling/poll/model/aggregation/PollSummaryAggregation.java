package com.karolismed.mongo.polling.poll.model.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollSummaryAggregation {
    @Id
    private ObjectId pollId;
    private String title;
    private int voteCount;
}
