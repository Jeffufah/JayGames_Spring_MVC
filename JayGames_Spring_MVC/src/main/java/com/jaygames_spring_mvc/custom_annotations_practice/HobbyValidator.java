/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaygames_spring_mvc.custom_annotations_practice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * A helper class for @IsValidHobby annotation.
 * @author Jeffrey McMullen II
 */
public class HobbyValidator implements ConstraintValidator<IsValidHobby, String>
{
    private String listOfValidHobbies;
    
    /**
     * Gets called before isValid.
     * @param isValidHobby 
     */

    public void initialize(IsValidHobby isValidHobby)
    {
        /*
        Assign this class field's listOfValidHobbies the parameters acquired from
        @IsValidHobby annotation in the Student class.
        */
        this.listOfValidHobbies = isValidHobby.listOfValidHobbies();
    }
    
    @Override
    public boolean isValid(String studentHobby, ConstraintValidatorContext cvc)
    {
        if (studentHobby == null)
        {
            return false;
        }
        
        //Returns true if form value matches any of these hobbies.
        return (studentHobby.matches(listOfValidHobbies));
    }
}
