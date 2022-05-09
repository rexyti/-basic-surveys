package com.robinfood.challenge.surveys.controller;

import com.robinfood.challenge.surveys.application.dto.SurveyDto;
import com.robinfood.challenge.surveys.application.dto.in.CreateSurveyDto;
import com.robinfood.challenge.surveys.application.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/survey/v0")
public class SurveyRestController {

    SurveyService surveyService;

    public SurveyRestController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping(value ="/survey", produces = "application/json")
    public ResponseEntity<SurveyDto> getSurvey(){
        return new ResponseEntity(surveyService.getSurveys(), HttpStatus.OK);
    }

    @PostMapping(value ="/survey", produces = "application/json")
    public ResponseEntity postSurvey(@RequestBody @Valid CreateSurveyDto createSurveyDto){
        surveyService.createSurvey(createSurveyDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
