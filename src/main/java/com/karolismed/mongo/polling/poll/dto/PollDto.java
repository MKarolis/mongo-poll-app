package com.karolismed.mongo.polling.poll.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String title;
    private boolean multiChoice;
    private List<OptionDto> options;
    private String owner;
}
