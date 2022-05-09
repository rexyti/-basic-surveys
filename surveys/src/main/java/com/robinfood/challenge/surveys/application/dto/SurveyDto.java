package com.robinfood.challenge.surveys.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyDto {
    private Long surveyId;
    private String tittle;
    private Set<SurveyQuestionDto> questions;
}
