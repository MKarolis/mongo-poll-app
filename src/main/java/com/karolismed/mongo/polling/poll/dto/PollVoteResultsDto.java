package com.karolismed.mongo.polling.poll.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class PollVoteResultsDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId pollId;
    private String title;
    private int totalVotes;

    @Builder.Default
    private List<OptionVoteResultsDto> optionVotes = new ArrayList<>();
}
