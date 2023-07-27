package com.telusko.quizApp.dao;


import com.telusko.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM (SELECT * FROM question q where q.category=:category ORDER BY dbms_random.value) WHERE rownum =:numQ ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
