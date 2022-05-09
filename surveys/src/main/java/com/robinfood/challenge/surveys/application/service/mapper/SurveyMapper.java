package com.robinfood.challenge.surveys.application.service.mapper;

import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionOptionDto;
import com.robinfood.challenge.surveys.application.dto.SurveyDto;
import com.robinfood.challenge.surveys.application.dto.SurveyQuestionDto;
import com.robinfood.challenge.surveys.application.dto.SurveyQuestionOptionDto;
import com.robinfood.challenge.surveys.model.Survey;
import com.robinfood.challenge.surveys.model.SurveyQuestion;
import com.robinfood.challenge.surveys.model.SurveyQuestionOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    @Mapping(target = "questions", ignore = true)
    Survey toSurvey(CreateSurveyDto createSurveyDto);

    @Mapping(target = "options", ignore = true)
    SurveyQuestion toSurveyQuestion(CreateSurveyQuestionDto createSurveyQuestionDto);

    SurveyQuestionOption toSurveyQuestionOption(CreateSurveyQuestionOptionDto createSurveyQuestionDto);

    SurveyDto toSurveyDto(Survey Survey);

    List<SurveyDto> toSurveyDtoList(List<Survey> Surveys);

    SurveyQuestionDto toSurveyQuestionDto(SurveyQuestion SurveyQuestion);

    SurveyQuestionOptionDto toSurveyQuestionOptionDto(SurveyQuestionOption surveyQuestion);

    Set<SurveyQuestionDto> toSurveyQuestionSetDto(Set<SurveyQuestion> surveyQuestionSet);

    Set<SurveyQuestionOptionDto> toSurveyQuestionOptionSetDto(Set<SurveyQuestionOption> surveyQuestionSet);
}
