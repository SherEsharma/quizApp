package com.telusko.quizApp.controller;

import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.model.QuestionWapper;
import com.telusko.quizApp.model.Response;
import com.telusko.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity createQuiz(@RequestParam String category, @RequestParam int numQ ,@RequestParam String title){

       return  quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWapper>> getQuizQuestion(@PathVariable Integer id){

           return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return  quizService.calculaterResult(id,responses);

    }
}
