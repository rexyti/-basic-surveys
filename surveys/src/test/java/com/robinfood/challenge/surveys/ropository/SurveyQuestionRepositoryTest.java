package com.robinfood.challenge.surveys.ropository;

import com.robinfood.challenge.surveys.model.Survey;
import com.robinfood.challenge.surveys.model.SurveyQuestion;
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
class SurveyQuestionRepositoryTest {
    @Autowired
    SurveyQuestionRepository repository;

    @Autowired
    SurveyRepository surveyRepository;

    Survey survey;

    @BeforeAll
    void setSurvey(){
        Survey surveyIn= new Survey();
        surveyIn.setTittle(TestUtils.generateString(100));
        survey = surveyRepository.save(surveyIn);
    }

    @Test
    void createSurveyQuestionSuccess(){
        SurveyQuestion surveyQuestionIn= new SurveyQuestion();
        surveyQuestionIn.setQuestionStatement(TestUtils.generateString(100));
        surveyQuestionIn.setSurvey(survey);
        SurveyQuestion surveyQuestionOut = repository.save(surveyQuestionIn);
        assertNotNull(surveyQuestionOut.getSurveyQuestionId());
    }

    @Test
    void createSurveyQuestionFailWithSurveyNull(){
        SurveyQuestion surveyQuestionIn= new SurveyQuestion();
        assertThrows (DataIntegrityViolationException.class, ()->repository.save(surveyQuestionIn)) ;
    }
}