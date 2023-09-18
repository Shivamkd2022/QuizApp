package com.telusco.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Quiz
{
    @Id
    @GeneratedValue(strategy = AUTO )
    private int id;
    private String title;

    @ManyToMany
    private List<Question> questionList;

}
