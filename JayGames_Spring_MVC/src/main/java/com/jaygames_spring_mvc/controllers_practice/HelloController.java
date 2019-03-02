/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_spring_mvc.controllers_practice;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jeffrey McMullen II
 */
@Controller
public class HelloController
{
    /**
     * Opens up HelloPage.html and displays hello world.
     * 
     * @param pathVariables A map of the values entered into the browser path
     * with a suffix of /. Using /welcome/Any Word/ Any Word, would suffice
     * as parameters.
     * @return A ModelAndView object carrying an object named msg containing
     * a concatenated String of literals and variables.
     */
    @RequestMapping("/welcome/{countryName}/{userName}") //<-- Path variables
    public ModelAndView helloWorld(@PathVariable Map<String,String> pathVariables)
    {
        String name = pathVariables.get("userName");
        String country = pathVariables.get("countryName");
        
        ModelAndView model = new ModelAndView("HelloPage");
        model.addObject("msg", "Hello " + name + " from " + country + "!");
        
        return model;
    }
}
