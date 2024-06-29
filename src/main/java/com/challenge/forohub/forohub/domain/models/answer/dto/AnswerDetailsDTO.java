package com.challenge.forohub.forohub.domain.models.answer.dto;

import java.time.LocalDate;

import com.challenge.forohub.forohub.domain.models.enums.Solved;

public record AnswerDetailsDTO(String username, String user_response, LocalDate date, Solved solved) {    
}
