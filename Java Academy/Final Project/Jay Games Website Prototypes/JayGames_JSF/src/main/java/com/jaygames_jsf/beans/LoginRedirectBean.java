/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_jsf.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Jeffrey McMullen II
 */
@Named(value = "loginRedirectBean")
@SessionScoped
public class LoginRedirectBean implements Serializable 
{
    
    public LoginRedirectBean()
    {
        
    }
    
    /**
     * Redirects to homepage if logged in.
     * @param page
     * @return 
     */
    public String redirect(String page)
    {
        return page;
    }
}
