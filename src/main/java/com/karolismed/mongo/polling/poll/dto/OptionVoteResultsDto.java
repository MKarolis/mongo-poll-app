package com.karolismed.mongo.polling.poll.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionVoteResultsDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId optionId;
    private String value;
    private int votes;
    private BigDecimal percentage;
}
