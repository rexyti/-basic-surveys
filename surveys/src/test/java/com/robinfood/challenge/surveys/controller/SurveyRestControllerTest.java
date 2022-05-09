package com.robinfood.challenge.surveys.controller;

import com.robinfood.challenge.surveys.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SurveyRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void postSurveyTestSuccess() throws Exception{
        mvc.perform(post("/survey/v0/survey").contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getValidRequest())).andExpect(status().isNoContent());
    }

    @Test
    void postSurveyTestFail() throws Exception{
        mvc.perform(post("/survey/v0/survey").contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getInvalidRequest())).andExpect(status().isBadRequest());
    }

    @Test
    void getSurveyTestSuccess() throws Exception{
        mvc.perform(get("/survey/v0/survey").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}