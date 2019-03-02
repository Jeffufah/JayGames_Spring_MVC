/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_spring_mvc.controllers;

import com.jaygames_spring_mvc.models.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jeffrey McMullen II
 */
@Controller
public class Games
{
    /**
     * Get method for directing the web page when /index.html is opened.
     *
     * @return A model to display the /Home.jsp file.
     */
    @RequestMapping(value = "/Games", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getHomePage(HttpServletRequest request)
    {
        User user = (User)request.getSession().getAttribute("user");
        
        ModelAndView games = new ModelAndView("Games");

        if (user == null)
        {
            user = new User();
            user.setLoginStatus("You are not logged in.");
        }

        games.addObject("user", user);
        return games;
    }
}
