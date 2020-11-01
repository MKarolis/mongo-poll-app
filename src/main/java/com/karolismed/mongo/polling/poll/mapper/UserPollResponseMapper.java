package com.karolismed.mongo.polling.poll.mapper;

import com.karolismed.mongo.polling.poll.dto.UserPollDto;
import com.karolismed.mongo.polling.poll.dto.UserPollsResponse;
import com.karolismed.mongo.polling.poll.model.aggregation.PollSummaryAggregation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPollResponseMapper {
    public static UserPollsResponse map(List<PollSummaryAggregation> summaries) {
        return UserPollsResponse.builder()
            .polls(summaries.stream().map(summary -> UserPollDto.builder()
                .id(summary.getPollId())
                .title(summary.getTitle())
                .voteCount(summary.getVoteCount())
                .build()).collect(Collectors.toList())
            ).build();
    }

}
