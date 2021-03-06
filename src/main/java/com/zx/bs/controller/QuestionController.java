package com.zx.bs.controller;

import com.zx.bs.entity.Answer;
import com.zx.bs.entity.Question;
import com.zx.bs.service.AnswerService;
import com.zx.bs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

//TODO 编写问题

@Controller
public class QuestionController {

    //分页查询 mybaties
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value="/")
    //TODO ???是否返回界面 是否返回回答列表
    public ModelAndView index(Map<String,Object> map){
        //TODO
        List<Question> questionList=questionService.findQuestion();
        map.put("question",questionList);
        return new ModelAndView("index",map);
    }

    //添加问题
    @RequestMapping(value="/questionAdd", method = {RequestMethod.POST})
    @ResponseBody
    public Integer addTeacher(Question question){
        Integer result=questionService.insertQuestion(question);
        return result;
    }

    //通过问题id查询问题
    @RequestMapping(value="/question/{id}", method = {RequestMethod.GET})
    //TODO ???是否返回界面 是否返回回答列表 是 是
    public ModelAndView findQuestionById(@PathVariable("id") Integer id ,Map<String,Object> map){
        //TODO
        Question question= questionService.findQuestionById(id);
        List<Answer> answerList=answerService.findAnswerByQuestionId(id);
        map.put("question",question);
        map.put("answers",answerList);
        return new ModelAndView("question",map);
    }

    @RequestMapping(value="/updatequestion/{id}", method = {RequestMethod.GET})
    //TODO ???是否返回界面
    @ResponseBody
    public String updateQuestion(Question question){
        Integer result=questionService.updateQuestion(question);
        return ""+result;
    }


    //通过课程id查询问题，返回问题列表
//    @RequestMapping(value="/questionFind/{id}", method = {RequestMethod.GET})
//    //???
//    @ResponseBody
//    public List<Question> findQuestionByCourseId(@PathVariable("id") Integer id){
//        //TODO
//        return questionService.findQuestionByCourseId(id);
//    }
}
