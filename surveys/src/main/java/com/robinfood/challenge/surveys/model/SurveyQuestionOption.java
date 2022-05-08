package com.robinfood.challenge.surveys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OPTIONS")
public class SurveyQuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyQuestionOptionId;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="survey_id", nullable=false)
    private SurveyQuestion question;
}
