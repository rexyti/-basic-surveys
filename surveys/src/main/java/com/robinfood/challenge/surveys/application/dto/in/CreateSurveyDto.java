package com.robinfood.challenge.surveys.application.dto.in;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSurveyDto {

    @NotBlank
    private String tittle;

    @Singular
    @NotEmpty
    @Valid
    private Set<CreateSurveyQuestionDto> questions;
}
