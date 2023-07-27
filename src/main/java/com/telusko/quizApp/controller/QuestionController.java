package com.telusko.quizApp.controller;

import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("allQuestions")
    public ResponseEntity getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @RequestMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionCategory(@PathVariable String category) {
        return questionService.getQuestionCategory(category);

    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
         String msg=questionService.addQuestion(question);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
