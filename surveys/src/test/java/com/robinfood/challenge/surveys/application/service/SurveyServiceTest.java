package com.robinfood.challenge.surveys.application.service;

import com.robinfood.challenge.surveys.application.dto.SurveyDto;
import com.robinfood.challenge.surveys.application.dto.SurveyQuestionDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionOptionDto;
import com.robinfood.challenge.surveys.shared.QuestionType;
import com.robinfood.challenge.surveys.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyServiceTest {
    @Autowired
    SurveyService service;

    @Test
    public void getSurveysSuccess(){


        List<SurveyDto> surveys = service.getSurveys();
        assertNotNull(surveys);
        
    }

    @Test
    public void createSurveyTestSuccess(){

        CreateSurveyQuestionDto createQuestion = CreateSurveyQuestionDto.builder()
                .option(new CreateSurveyQuestionOptionDto("option1"))
                .type(QuestionType.MULTIPLE)
                .questionStatement("question1").build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle("survey1")
                .question(createQuestion)
                .build();

        SurveyDto surveyDto = service.createSurvey(survey);

        assertNotNull(surveyDto);
        assertEquals("survey1",surveyDto.getTittle());

        assertNotNull(surveyDto.getQuestions());
        assertEquals(1,surveyDto.getQuestions().size());

        SurveyQuestionDto surveyQuestionDto = surveyDto.getQuestions().stream().findFirst().get();
        assertEquals("question1",surveyQuestionDto.getQuestionStatement());
        assertEquals(QuestionType.MULTIPLE,surveyQuestionDto.getType());

        assertNotNull(surveyQuestionDto.getOptions());
        assertEquals(1,surveyQuestionDto.getOptions().size());

        assertEquals("option1",surveyQuestionDto.getOptions().stream().findFirst().get().getDescription());

    }

    @Test
    public void createSurveyTestFailBadSurvey(){

        CreateSurveyQuestionDto createQuestion = CreateSurveyQuestionDto.builder()
                .option(new CreateSurveyQuestionOptionDto("option1"))
                .type(QuestionType.MULTIPLE)
                .questionStatement("question1").build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle("")
                .question(createQuestion)
                .build();

        CreateSurveyDto survey1 = CreateSurveyDto.builder()
                .tittle("survey1")
                .build();

        assertThrows(ConstraintViolationException.class,()->service.createSurvey(survey1));
        assertThrows(ConstraintViolationException.class,()->service.createSurvey(survey));

    }

    @Test
    public void createSurveyTestFailBadSurveyQuestion(){

        CreateSurveyQuestionDto createQuestion = CreateSurveyQuestionDto.builder()
                .type(QuestionType.MULTIPLE)
                .questionStatement("").build();

        CreateSurveyQuestionDto createQuestion2 = CreateSurveyQuestionDto.builder()
                .questionStatement("").build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle("survey1")
                .question(createQuestion)
                .build();

        CreateSurveyDto survey1 = CreateSurveyDto.builder()
                .tittle("survey1")
                .question(createQuestion2)
                .build();

        assertThrows(ConstraintViolationException.class,()->service.createSurvey(survey));
        assertThrows(ConstraintViolationException.class,()->service.createSurvey(survey1));

    }

    @Test
    public void createSurveyTestFailBadSurveyQuestionOption(){

        CreateSurveyQuestionDto createQuestion = CreateSurveyQuestionDto.builder()
                .option(new CreateSurveyQuestionOptionDto(""))
                .type(QuestionType.MULTIPLE)
                .questionStatement("question1").build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle("")
                .question(createQuestion)
                .build();

        assertThrows(ConstraintViolationException.class,()->service.createSurvey(survey));

    }

    @Test
    public void createSurveyTestSuccessWithMultipleQuestion(){

        CreateSurveyQuestionDto createQuestion1 = CreateSurveyQuestionDto.builder()
                .option(new CreateSurveyQuestionOptionDto(TestUtils.generateString(50)))
                .option(new CreateSurveyQuestionOptionDto(TestUtils.generateString(50)))
                .option(new CreateSurveyQuestionOptionDto(TestUtils.generateString(50)))
                .type(QuestionType.MULTIPLE)
                .questionStatement(TestUtils.generateString(100)).build();

        CreateSurveyQuestionDto createQuestion2 = CreateSurveyQuestionDto.builder()
                .type(QuestionType.OPEN)
                .questionStatement(TestUtils.generateString(100)).build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle(TestUtils.generateString(100))
                .question(createQuestion1)
                .question(createQuestion2)
                .build();

        SurveyDto surveyDto = service.createSurvey(survey);

        assertNotNull(surveyDto);
        assertNotNull(surveyDto.getQuestions());
        assertEquals(2,surveyDto.getQuestions().size());
    }

    @Test
    public void createSurveyTestWithMultipleOptions(){

        CreateSurveyQuestionDto createQuestion1 = CreateSurveyQuestionDto.builder()
                .option(new CreateSurveyQuestionOptionDto(TestUtils.generateString(50)))
                .option(new CreateSurveyQuestionOptionDto(TestUtils.generateString(50)))
                .option(new CreateSurveyQuestionOptionDto(TestUtils.generateString(50)))
                .type(QuestionType.MULTIPLE)
                .questionStatement(TestUtils.generateString(100)).build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle(TestUtils.generateString(100))
                .question(createQuestion1)
                .build();

        SurveyDto surveyDto = service.createSurvey(survey);

        assertNotNull(surveyDto);
        assertNotNull(surveyDto.getQuestions());
        assertEquals(3,surveyDto.getQuestions().stream().findFirst().get().getOptions().size());
    }


}