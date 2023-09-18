package com.telusco.Service;

import com.telusco.Dao.QuestionDao;
import com.telusco.Dao.QuizDao;
import com.telusco.Entities.Question;
import com.telusco.Entities.QuestionWrapper;
import com.telusco.Entities.Quiz;
import com.telusco.Entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public String createQuizMethod(String category, int numOfQs, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numOfQs);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        quizDao.save(quiz);
        return "Successfully quiz is created";


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQues(int quizid) {
        Optional<Quiz> quizById = quizDao.findById(quizid);
        List<Question> questionListFromDB = quizById.get().getQuestionList();
        List<QuestionWrapper> questionListForUser = new ArrayList<>();


        for (Question q : questionListFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getQuesId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionListForUser.add(qw);
        }
        return new ResponseEntity<>(questionListForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> caluclateResult(int quizid, List<Response> responses) {
        Quiz quiz = quizDao.findById(quizid).get();
        List<Question> quizQuesitons = quiz.getQuestionList();
        int right=0;

        for(int i=0; i<quizQuesitons.size(); i++) {

            for (Response res : responses) {
                if (res.getAnswer().equals(quizQuesitons.get(i).getRightAnswer()))
                    right++;


            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
