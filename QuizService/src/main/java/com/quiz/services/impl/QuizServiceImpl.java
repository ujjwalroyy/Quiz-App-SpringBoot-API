package com.quiz.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepo;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizServices;

@Service
public class QuizServiceImpl implements QuizServices {

    private QuizRepo quizRepo;

    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepo quizRepo, QuestionClient questionClient){
        this.quizRepo = quizRepo;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = quizRepo.findAll();
        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
            quiz.setQuestion(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
        return newQuizList;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setQuestion(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
    
}
