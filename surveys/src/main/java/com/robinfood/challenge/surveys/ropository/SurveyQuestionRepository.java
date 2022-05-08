package com.robinfood.challenge.surveys.ropository;

import com.robinfood.challenge.surveys.model.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion,Long> {
}
