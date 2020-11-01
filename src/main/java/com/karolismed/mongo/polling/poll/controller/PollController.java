package com.karolismed.mongo.polling.poll.controller;

import com.karolismed.mongo.polling.config.security.UserDetailsImpl;
import com.karolismed.mongo.polling.poll.dto.CreatePollDto;
import com.karolismed.mongo.polling.poll.dto.CreatePollResponse;
import com.karolismed.mongo.polling.poll.dto.OptionDto;
import com.karolismed.mongo.polling.poll.dto.PollDto;
import com.karolismed.mongo.polling.poll.dto.PollVoteResultsDto;
import com.karolismed.mongo.polling.poll.dto.UserPollsResponse;
import com.karolismed.mongo.polling.poll.dto.VoteRequest;
import com.karolismed.mongo.polling.poll.service.PollService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/poll")
@AllArgsConstructor
public class PollController {

    private final PollService pollService;

    @GetMapping("/{pollId}")
    public PollDto getPoll(@PathVariable ObjectId pollId) {
        return pollService.getPoll(pollId);
    }

    @GetMapping("/{pollId}/results")
    @PreAuthorize("hasAuthority('MANAGE_POLLING')")
    public PollVoteResultsDto getPollResults(
        @PathVariable ObjectId pollId, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return pollService.getPollResults(pollId, userDetails.getId());
    }

    @GetMapping("/{pollId}/options")
    public List<OptionDto> getPollOptions(@PathVariable ObjectId pollId) {
        return pollService.getPollOptions(pollId);
    }

    @GetMapping("/user-polls")
    @PreAuthorize("hasAuthority('MANAGE_POLLING')")
    public UserPollsResponse getUserPolls(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return pollService.getUserPolls(userDetails.getId());
    }

    @PostMapping("/{pollId}/vote")
    public void vote(@PathVariable ObjectId pollId, @Valid @RequestBody VoteRequest voteRequest) {
        pollService.vote(pollId, voteRequest);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_POLLING')")
    public CreatePollResponse createPoll(
        @Valid @RequestBody CreatePollDto createPollDto, @AuthenticationPrincipal UserDetailsImpl userDetails
        ) {
        return pollService.createPoll(createPollDto, userDetails);
    }
}
