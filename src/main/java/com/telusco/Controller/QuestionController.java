package com.telusco.Controller;

import com.telusco.Entities.Question;
import com.telusco.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController
{
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions()
    {
        //return "Hi, these are your Questions.";
         return questionService.getAllQuestions();

    }
    @GetMapping("category/{categoryName}")
    public List<Question> getQuestionByCategory (@PathVariable("categoryName") String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question q)
    {
        return questionService.addQuestion(q);
    }

    @PutMapping("update/{questionId}")
    public Question updateQuestion(@PathVariable("questionId") int qid, @RequestBody Question updatedques)
    {
        Question updated = questionService.updateQuestion(qid, updatedques);

        return updated;
    }
    @DeleteMapping("delete/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") int qid)
    {
       return questionService.deleteQues(qid);

    }


}
