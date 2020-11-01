package com.karolismed.mongo.polling.poll.mapper;

import com.karolismed.mongo.polling.poll.dto.OptionDto;
import com.karolismed.mongo.polling.poll.model.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionMapper {
    public static OptionDto map(Option option) {
        return OptionDto.builder()
            .id(option.getId())
            .value(option.getValue())
            .build();
    }
}
