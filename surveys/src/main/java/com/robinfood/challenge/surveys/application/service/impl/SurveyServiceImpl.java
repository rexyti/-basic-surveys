package com.robinfood.challenge.surveys.application.service.impl;

import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyDto;
import com.robinfood.challenge.surveys.application.dto.SurveyDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionOptionDto;
import com.robinfood.challenge.surveys.application.service.SurveyService;
import com.robinfood.challenge.surveys.application.service.mapper.SurveyMapper;
import com.robinfood.challenge.surveys.model.Survey;
import com.robinfood.challenge.surveys.model.SurveyQuestion;
import com.robinfood.challenge.surveys.model.SurveyQuestionOption;
import com.robinfood.challenge.surveys.ropository.SurveyQuestionOptionRepository;
import com.robinfood.challenge.surveys.ropository.SurveyQuestionRepository;
import com.robinfood.challenge.surveys.ropository.SurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository surveyRepository;

    private SurveyQuestionRepository surveyQuestionRepository;

    private SurveyQuestionOptionRepository surveyQuestionOptionRepository;

    private SurveyMapper mapper;

    public SurveyServiceImpl(SurveyRepository surveyRepository,SurveyQuestionRepository surveyQuestionRepository, SurveyQuestionOptionRepository surveyQuestionOptionRepository, SurveyMapper mapper){
        this.surveyRepository = surveyRepository;
        this.surveyQuestionRepository = surveyQuestionRepository;
        this.surveyQuestionOptionRepository = surveyQuestionOptionRepository;
        this.mapper=mapper;
    }

    @Override
    public List<SurveyDto> getSurveys(){
        return mapper.toSurveyDtoList(surveyRepository.findAll());

    }

    /*private Survey fillSurveyQuestions(Survey survey){
        List<SurveyQuestion> surveyQuestions = surveyQuestionRepository.findBySurvey(survey);
        Set<SurveyQuestion> surveyQuestionsSet = surveyQuestions.stream().map(this::fillSurveyQuestionsOption).collect(Collectors.toSet());
        survey.setQuestions(surveyQuestionsSet);
        return survey;// List<Survey> surveys = surveyRepository.findAll();
       // surveys = surveys.stream().map(this::fillSurveyQuestions).collect(Collectors.toList());
       // return mapper.toSurveyDtoList(surveys);
    }

    private SurveyQuestion fillSurveyQuestionsOption(SurveyQuestion surveyQuestion){
        List<SurveyQuestionOption> surveyQuestionOptions = surveyQuestionOptionRepository.findByQuestion(surveyQuestion);
        surveyQuestion.setOptions(surveyQuestionOptions.stream().collect(Collectors.toSet()));
        return surveyQuestion;
    }*/

    @Override
    public SurveyDto createSurvey(CreateSurveyDto createSurveyDto){
        Survey survey = persistSurvey (createSurveyDto);
        createQuestions(createSurveyDto,survey);
        return mapper.toSurveyDto(survey);
    }

    private Survey persistSurvey (CreateSurveyDto createSurveyDto){
        return surveyRepository.save(mapper.toSurvey(createSurveyDto));
    }

    private void createQuestions(CreateSurveyDto createSurveyDto, Survey survey){
        Set<SurveyQuestion> questionSet = createSurveyDto.getQuestions().stream()
                .map(s->createSurveyQuestion(s,survey)).collect(Collectors.toSet());
        survey.setQuestions(questionSet);
    }

    private SurveyQuestion createSurveyQuestion (CreateSurveyQuestionDto createSurveyQuestion, Survey survey){
        SurveyQuestion surveyQuestion = mapper.toSurveyQuestion(createSurveyQuestion);
        surveyQuestion.setSurvey(survey);
        SurveyQuestion createdSurveyQuestion = surveyQuestionRepository.save(surveyQuestion);
        createdSurveyQuestion.setOptions(createOptions(createSurveyQuestion,createdSurveyQuestion));
        return createdSurveyQuestion;
    }

    private Set<SurveyQuestionOption> createOptions (CreateSurveyQuestionDto createSurveyQuestionDto, SurveyQuestion surveyQuestion){
        Optional<Set<CreateSurveyQuestionOptionDto>> optional =Optional.ofNullable(createSurveyQuestionDto.getOptions());
        if(optional.isPresent()){
            Set<CreateSurveyQuestionOptionDto>options = Optional.ofNullable(createSurveyQuestionDto.getOptions()).get();
            return createSurveyQuestionDto.getOptions().stream()
                   .map(q->createSurveyQuestionOption(q,surveyQuestion))
                   .collect(Collectors.toSet());
        }else{
            return null;
        }

    }

    private SurveyQuestionOption createSurveyQuestionOption (CreateSurveyQuestionOptionDto createSurveyQuestionOptionDto, SurveyQuestion surveyQuestion){
        SurveyQuestionOption surveyQuestionOption = mapper.toSurveyQuestionOption(createSurveyQuestionOptionDto);
        surveyQuestionOption.setQuestion(surveyQuestion);
        return surveyQuestionOptionRepository.save(surveyQuestionOption);
    }

}
