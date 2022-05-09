package com.robinfood.challenge.surveys.ropository;

import com.robinfood.challenge.surveys.model.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SurveyRepository extends JpaRepository<Survey,Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,attributePaths = {
            "questions",
            "questions.options"
    })
    List<Survey> findAll();
}
