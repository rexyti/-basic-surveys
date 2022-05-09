package com.robinfood.challenge.surveys.application.service;

import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyDto;
import com.robinfood.challenge.surveys.application.dto.SurveyDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface SurveyService {

    List<SurveyDto> getSurveys();

    SurveyDto createSurvey(@Valid  CreateSurveyDto createSurveyDto);
}
