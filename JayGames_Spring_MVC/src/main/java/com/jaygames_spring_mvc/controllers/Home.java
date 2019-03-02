/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_spring_mvc.controllers;

import com.jaygames_spring_mvc.models.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jeffrey McMullen II
 */

@Controller
@Scope("session")
public class Home
{    
    /**
     * Get method for directing the web page when /index.html is opened.
     *
     * @param request
     * @return A model to display the /Home.jsp file.
     */
    @RequestMapping(value = "/Home", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getHomePage(HttpServletRequest request)
    {
        User user = (User)request.getSession().getAttribute("user");
        
        ModelAndView home = new ModelAndView("Home");

        if (user == null)
        {
            user = new User();
            user.setLoginStatus("You are not logged in.");
        }

        home.addObject("user", user);
        return home;
    }
}