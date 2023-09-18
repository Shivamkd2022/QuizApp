package com.telusco.Service;

import com.telusco.Dao.QuestionDao;
import com.telusco.Entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question q) {
        questionDao.save(q);
        return new ResponseEntity<>("successfully question added.", HttpStatus.CREATED);

    }

    public Question updateQuestion(int qid, Question updated) {
        Question existingQues = questionDao.findById(qid).orElse(null);
        if (existingQues == null) {
            System.out.println("Invalid Question Id entered");
        }
        existingQues.setQuestionTitle(updated.getQuestionTitle());
        existingQues.setOption1(updated.getOption1());
        existingQues.setOption2(updated.getOption2());
        existingQues.setOption3(updated.getOption3());
        existingQues.setOption4(updated.getOption4());
        existingQues.setRightAnswer(updated.getRightAnswer());
        existingQues.setDifficultyLevel(updated.getDifficultyLevel());
        existingQues.setCategory(updated.getCategory());

        questionDao.save(existingQues);

        return existingQues;
    }


    public String deleteQues(int qid) {
        Question delete = questionDao.findById(qid).orElse(null);
        if (delete == null) {
            return "This question Id is not availabale";
        }
        else {
            questionDao.deleteById(qid);
            return "Question deleted Successfully";

        }

    }
}

