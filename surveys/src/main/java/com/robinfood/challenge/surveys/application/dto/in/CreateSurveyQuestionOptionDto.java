package com.robinfood.challenge.surveys.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSurveyQuestionOptionDto {
    @NotBlank
    private String description;
}
