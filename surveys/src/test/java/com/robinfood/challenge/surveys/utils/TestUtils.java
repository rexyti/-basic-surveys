package com.robinfood.challenge.surveys.utils;

import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyQuestionDto;
import com.robinfood.challenge.surveys.shared.QuestionType;
import com.google.gson.Gson;
import java.nio.charset.Charset;
import java.util.Random;

public class TestUtils {
    public static String generateString(int size){
        byte[] array = new byte[size];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    public static String getValidRequest(){
        String tittle = TestUtils.generateString(100);
        CreateSurveyQuestionDto createQuestion1 = CreateSurveyQuestionDto.builder()
                .type(QuestionType.OPEN)
                .questionStatement(tittle).build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle(tittle)
                .question(createQuestion1)
                .build();
        return new Gson().toJson(survey);
    }

    public static String getInvalidRequest(){

        CreateSurveyQuestionDto createQuestion1 = CreateSurveyQuestionDto.builder()
                .questionStatement("").build();

        CreateSurveyDto survey = CreateSurveyDto.builder()
                .tittle("")
                .question(createQuestion1)
                .build();
        return new Gson().toJson(survey);
    }

}
