package com.ww.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ww.spring.model.Transaction;

@Controller
@RequestMapping("/validate")
public class ValidateController {
    
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(ValidateController.class);

    @RequestMapping(value = "/annotation", method = RequestMethod.POST)
    public ModelAndView annotationValidate(@Valid Transaction trans, Errors errors) {
        // 是否存在错误
        if (errors.hasErrors()) {
            // 获取错误信息
            List<FieldError> fieldErrors = errors.getFieldErrors();
            for (FieldError error : fieldErrors) {
                logger.info("field:" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
            }
        }
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
