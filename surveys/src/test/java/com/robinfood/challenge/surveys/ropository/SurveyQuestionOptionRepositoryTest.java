package com.robinfood.challenge.surveys.ropository;

import com.robinfood.challenge.surveys.model.Survey;
import com.robinfood.challenge.surveys.model.SurveyQuestion;
import com.robinfood.challenge.surveys.model.SurveyQuestionOption;
import com.robinfood.challenge.surveys.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SurveyQuestionOptionRepositoryTest {

    @Autowired
    SurveyQuestionOptionRepository repository;

    @Autowired
    SurveyQuestionRepository surveyQuestionrepository;

    @Autowired
    SurveyRepository surveyRepository;

    SurveyQuestion surveyQuestion;

    @BeforeAll
    void setSurvey(){
        Survey survey= new Survey();
        survey.setTittle(TestUtils.generateString(100));
        surveyQuestion= new SurveyQuestion();
        surveyQuestion.setQuestionStatement(TestUtils.generateString(100));
        surveyQuestion.setSurvey(surveyRepository.save(survey));
        surveyQuestion=surveyQuestionrepository.save(surveyQuestion);
    }

    @Test
    void createSurveyQuestionSuccess(){
        SurveyQuestionOption surveyQuestionOptionIn= new SurveyQuestionOption();
        surveyQuestionOptionIn.setDescription(TestUtils.generateString(100));
        surveyQuestionOptionIn.setQuestion(surveyQuestion);
        SurveyQuestionOption surveyQuestionOut = repository.save(surveyQuestionOptionIn);
        assertNotNull(surveyQuestionOut.getSurveyQuestionOptionId());
    }

    @Test
    void createSurveyQuestionFailWithSurveyNull(){
        SurveyQuestionOption surveyQuestionOptionIn= new SurveyQuestionOption();
        assertThrows (DataIntegrityViolationException.class, ()->repository.save(surveyQuestionOptionIn)) ;
    }
}