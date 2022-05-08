package com.robinfood.challenge.surveys.ropository;

import com.robinfood.challenge.surveys.model.SurveyQuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionOptionRepository extends JpaRepository<SurveyQuestionOption,Long> {
}
