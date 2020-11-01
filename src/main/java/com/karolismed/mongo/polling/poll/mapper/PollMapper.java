package com.karolismed.mongo.polling.poll.mapper;

import com.karolismed.mongo.polling.config.security.UserDetailsImpl;
import com.karolismed.mongo.polling.poll.dto.CreatePollDto;
import com.karolismed.mongo.polling.poll.dto.OptionVoteResultsDto;
import com.karolismed.mongo.polling.poll.dto.PollDto;
import com.karolismed.mongo.polling.poll.dto.PollVoteResultsDto;
import com.karolismed.mongo.polling.poll.model.Option;
import com.karolismed.mongo.polling.poll.model.Poll;
import com.karolismed.mongo.polling.poll.model.aggregation.PollVoteResultsAggregation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PollMapper {
    public static Poll map(CreatePollDto createPollDto, UserDetailsImpl userDetails) {
        return Poll.builder()
            .id(new ObjectId())
            .multiChoice(createPollDto.isMultiChoice())
            .ownerName(userDetails.getDisplayName())
            .title(createPollDto.getTitle())
            .options(createPollDto.getOptions().stream().map(
                option -> Option.builder()
                    .id(new ObjectId()).value(option).voteCount(0).build()
            ).collect(Collectors.toList()))
            .build();
    }

    public static PollVoteResultsDto map(PollVoteResultsAggregation resultsAggregation) {
        return PollVoteResultsDto.builder()
            .pollId(resultsAggregation.getPollId())
            .title(resultsAggregation.getTitle())
            .totalVotes(resultsAggregation.getTotalVoteCount())
            .optionVotes(
                resultsAggregation.getVoteStats().stream().map(
                    option -> OptionVoteResultsDto.builder()
                        .optionId(option.getOptionId())
                        .value(option.getValue())
                        .votes(option.getVotes())
                        .percentage(BigDecimal.valueOf(option.getPercentage()).setScale(2, RoundingMode.HALF_UP))
                        .build()
                ).collect(Collectors.toList())
            )
            .build();
    }

    public static PollDto map(Poll poll) {
        return PollDto.builder()
            .id(poll.getId())
            .multiChoice(poll.isMultiChoice())
            .title(poll.getTitle())
            .options(poll.getOptions().stream().map(OptionMapper::map).collect(Collectors.toList()))
            .owner(poll.getOwnerName())
            .build();
    }
}
