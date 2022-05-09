package com.robinfood.challenge.surveys.application.dto.in;

import com.robinfood.challenge.surveys.shared.QuestionType;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSurveyQuestionDto {

    @NotBlank
    private String questionStatement;

    @NotNull
    private QuestionType type;

    @Singular
    @Valid
    private Set<CreateSurveyQuestionOptionDto> options;
}
