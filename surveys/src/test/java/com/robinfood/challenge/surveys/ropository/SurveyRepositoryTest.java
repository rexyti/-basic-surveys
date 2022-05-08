package com.robinfood.challenge.surveys.ropository;

import com.robinfood.challenge.surveys.model.Survey;
import com.robinfood.challenge.surveys.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyRepositoryTest {
    @Autowired
    SurveyRepository surveyRepository;

    @Test
    void createSurveySuccess(){
        Survey surveyIn= new Survey();
        surveyIn.setTittle(TestUtils.generateString(100));
        Survey surveyOut = surveyRepository.save(surveyIn);
        assertNotNull(surveyOut.getSurveyId());
    }

    @Test
    void createSurveyFailWithTittleNull(){
        Survey surveyIn= new Survey();
        assertThrows (DataIntegrityViolationException.class, ()->surveyRepository.save(surveyIn)) ;
    }
}