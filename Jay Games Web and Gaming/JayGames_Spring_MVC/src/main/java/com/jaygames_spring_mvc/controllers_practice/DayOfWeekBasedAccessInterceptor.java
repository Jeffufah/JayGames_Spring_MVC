/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_spring_mvc.controllers_practice;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Prevents the website from being accessed on a specified day of the week.
 * Also prints to the console information regarding requests during postHandling
 * and afterCompletion.
 * @author Jeffrey McMullen II
 */
public class DayOfWeekBasedAccessInterceptor extends HandlerInterceptorAdapter
{
    /**
     * Gets called first before processing a request in a controller class.
     * @param request
     * @param response
     * @param handler
     * @return A Boolean which prevents the request from processing if it resolves
     * to false.
     * @throws Exception 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception
    {
        System.out.println("HandlerInterceptorAdapter : Spring MVC called "
                + "preHandle method for " + request.getRequestURI());
        
        Calendar calendar = Calendar.getInstance();
        
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            
        if (dayOfWeek == 1) //1 = sunday, 7 = saturday
        {
            String[] week = new String[]{"Sunday", "Monday", "Tuesday", 
                "Wednesday", "Thursday", "Friday", "Saturday"};
            
            response.getWriter().write("JayGames  Student-Form submission is "
                    + "closed on " + week[dayOfWeek - 1] + ". Please "
                    + "try accessing it on any other day of the week.");
            
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets called after preHandle method by the controller class processing a 
     * request provided that preHandle returned true.
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception 
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
        System.out.println("HandlerInterceptorAdapter : Spring MVC called "
                + "postHandle method for " + request.getRequestURI());
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception
    {
        System.out.println("HandlerInterceptorAdapter : Spring MVC called "
                + "afterCompletion method for " + request.getRequestURI());
    }
}
