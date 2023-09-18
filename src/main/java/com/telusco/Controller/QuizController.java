package com.telusco.Controller;

import com.telusco.Entities.Question;
import com.telusco.Entities.QuestionWrapper;
import com.telusco.Entities.Response;
import com.telusco.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController
{
    @Autowired
    QuizService quizService;
    @PostMapping ("create")
    public String createQuiz(@RequestParam("category") String category, @RequestParam("numOfQs") int numOfQs, @RequestParam("title") String title)
    {
        return quizService.createQuizMethod(category,numOfQs,title);
    }
    @GetMapping("m1")
    public String m1()
    {
        System.out.println("this is m1 method");
        return "Hello this is m1 method";
    }

    @GetMapping("getquiz/{quizid}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizid)
    {
       return quizService.getQuizQues(quizid);
    }

    @PostMapping("submitquiz/{quizid}")
    public ResponseEntity<Integer> submitQuiz (@PathVariable int quizid, @RequestBody List<Response> responses)
    {
        return quizService.caluclateResult(quizid,responses);
    }

}
