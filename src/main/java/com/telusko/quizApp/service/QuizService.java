package com.telusko.quizApp.service;

import com.telusko.quizApp.dao.QuestionDao;
import com.telusko.quizApp.dao.QuizDao;
import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.model.QuestionWapper;
import com.telusko.quizApp.model.Quiz;
import com.telusko.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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



    public ResponseEntity<String> createQuiz(String category,int numQ,String title){
        List<Question> questions =questionDao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return  new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWapper>> getQuizQuestions(Integer id) {
       Optional<Quiz>  quiz = quizDao.findById(id);

      List<Question> questionFromDB=quiz.get().getQuestions();
      List<QuestionWapper> questionForUser=  new ArrayList<>();

        for(Question q : questionFromDB){
            QuestionWapper questionWapper = new QuestionWapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4() );
               questionForUser.add(questionWapper);
        }


       return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculaterResult(Integer id, List<Response> responses) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right=0;
        int i=0;
        for (Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
                i++;
            }
        }

        return  new ResponseEntity<>(right,HttpStatus.OK);
    }
}
