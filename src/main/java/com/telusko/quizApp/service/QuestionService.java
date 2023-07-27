package com.telusko.quizApp.service;

import com.telusko.quizApp.dao.QuestionDao;
import com.telusko.quizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity getAllQuestion() {
       try {
           return new ResponseEntity(questionDao.findAll(), HttpStatus.OK);
       }catch (Exception exp){
           exp.printStackTrace();
       }
        return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public String addQuestion(Question question) {
       Question question1=  questionDao.save(question);

         return "data inserted success" ;
    }
}
