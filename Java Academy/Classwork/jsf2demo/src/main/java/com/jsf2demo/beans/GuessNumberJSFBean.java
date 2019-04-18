package com.jsf2demo.beans;

import java.io.Serializable; //Required or GuessNumberJSFBean has a compiler error.
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Owner
 */
@Named(value = "guessNumber")
@ViewScoped
public class GuessNumberJSFBean implements Serializable
{
    private int number;
    private String guessString;

    public GuessNumberJSFBean()
    {
        number = (int) (Math.random() * 100);
    }

    public String getGuessString() 
    {
        return guessString;
    }

    public void setGuessString(String guessString) 
    {
        this.guessString = guessString;
    }

    public String getResponse() 
    {
        if (guessString == null) 
        {
            return ""; // No user input yet
        }
        int guess = Integer.parseInt(guessString);
        if (guess < number) 
        {
            return "Too low";
        } 
        else if (guess == number) 
        {
            return "You got it";
        } 
        else 
        {
            return "Too high";
        }
    }
}
