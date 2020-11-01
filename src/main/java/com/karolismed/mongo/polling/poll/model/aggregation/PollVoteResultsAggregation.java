package com.karolismed.mongo.polling.poll.model.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollVoteResultsAggregation {
    private ObjectId pollId;
    private String title;
    private int totalVoteCount;
    @Builder.Default
    private List<OptionVoteResultsAggregation> voteStats = new ArrayList<>();
}
