package com.robinfood.challenge.surveys.application.dto;

import com.robinfood.challenge.surveys.shared.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyQuestionDto {
    private Long surveyQuestionId;

    private String questionStatement;

    private QuestionType type;

    private Set<SurveyQuestionOptionDto> options;
}
