package com.robinfood.challenge.surveys.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyQuestionOptionDto {

    private Long surveyQuestionOptionId;

    private String description;
}
