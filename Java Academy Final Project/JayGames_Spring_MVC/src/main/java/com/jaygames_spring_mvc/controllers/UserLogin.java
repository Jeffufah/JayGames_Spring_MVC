package com.jaygames_spring_mvc.controllers;
import com.jaygames_spring_mvc.models.User;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jeffrey McMullen II
 */
@Controller
@SessionAttributes("user")
public class UserLogin 
{
    /**
     * Post method for collecting user login data.
     * 
     * @param user
     * @param result BindingResult object to contain form validation errors.
     * @return A model containing a header message and the form values stored
     * in the Student object.
     */
    @RequestMapping(value = "/Home", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("user")
            User user, BindingResult result)
    {
        user.setLoginStatus("Logged in as: " + user.getUserName());
        ModelAndView reloadHome = new ModelAndView("/Home");
        reloadHome.addObject(user);
        return reloadHome;
    }
    
    /**
     * Required method to create user if it hasn't been initialized so that it
     * may become session scoped.
     * @return A User object to be populated with form values.
     */
    @ModelAttribute("user")
    public User initializeUser()
    {
        return new User(); // populates form for the first time if its null
    }
}