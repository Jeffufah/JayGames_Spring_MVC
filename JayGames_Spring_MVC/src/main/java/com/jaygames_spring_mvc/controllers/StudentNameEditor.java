package com.jaygames_spring_mvc.controllers;

import java.beans.PropertyEditorSupport;

/**
 * When you submit the admission form, Spring MVC will run setAsText method 
 * in this class before performing its data binding task for the studentName 
 * property of Student class.
 * 
 * @author Jeffrey McMullen II
 */
public class StudentNameEditor extends PropertyEditorSupport
{
    /**
     * Whatever value you provide to setValue method, spring MVC will use the same
     * value to perform the data binding task for studentName property.
     * @param studentName A String acquired from studentName form entry.
     * @throws IllegalArgumentException 
     */
    @Override
    public void setAsText(String studentName) throws IllegalArgumentException
    {
        if (studentName.contains("Mr.") || studentName.contains("Ms."))
        {
            setValue(studentName);
        }
        else
        {
            studentName = "Mr. " + studentName;
            
            setValue(studentName);
        }
    }
}
