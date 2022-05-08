package com.robinfood.challenge.surveys.model;

import com.robinfood.challenge.surveys.shared.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "QUESTIONS")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyQuestionId;

    @Column(nullable = false)
    private String questionStatement;

    @ManyToOne
    @JoinColumn(name="survey_id", nullable=false)
    private Survey survey;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany
    @JoinColumn(name = "survey_question_id")
    private Set<SurveyQuestionOption> options;
}
