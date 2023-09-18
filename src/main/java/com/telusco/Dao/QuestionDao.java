package com.telusco.Dao;

import com.telusco.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>
{
  List<Question> findByCategory(String category);

  @Query(value = "select * from question q where q.category= :category ORDER BY RANDOM() limit :numOfQs", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numOfQs);
}
