/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_spring_mvc.controllers_practice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/*
    Required lines of code in spring-dispatcher-servlet for this controller to work.
    <bean id="HandlerMapping" class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    <bean name="/welcome.html" class="com.jaygames_springmvc.controllers.HelloController"/>
*/
/**
 *
 * @author Jeffrey McMullen II
 */
public class HelloController_Deprecated extends AbstractController 
{
    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception 
    {
        ModelAndView modelandview = new ModelAndView("HelloPage");
        //variable name, value
        modelandview.addObject("welcomeMessage", "Hi User, welcome to the first Spring MVC Application");

        return modelandview;
    }
}
