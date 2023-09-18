package com.telusco.Entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionWrapper
{
    private int quesId;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
