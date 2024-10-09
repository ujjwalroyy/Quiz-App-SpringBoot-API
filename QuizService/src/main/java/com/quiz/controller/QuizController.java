package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entities.Quiz;
import com.quiz.services.QuizServices;

/**
 * QuizController
 */
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizServices quizServices;

    public QuizController(QuizServices quizServices){
        this.quizServices = quizServices;
    }

    @PostMapping
    public Quiz create(@RequestBody Quiz quiz){
        return quizServices.add(quiz);
    }

    @GetMapping
    public List<Quiz> getQuiz(){
        return quizServices.get();
    }

    @GetMapping("/{id}")
    public Quiz getOneQuiz(@PathVariable Long id){
        return quizServices.get(id);
    }

}