package com.jaygames_spring_mvc.custom_annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This class utilizes the HobbyValidator helper class to determine if the hobby
 * form parameter is valid. This class is reached by using @IsValidHobby annotation
 * on a String field in the Student class.
 * @author Jeffrey McMullen II
 */
@Documented
@Constraint(validatedBy = HobbyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidHobby
{
    /*
    String to carry valid hobbies from @isValidHobby annotation.
    The default keyword is used to make the use of parameters in @isValidHobby annotation
    optional instead of mandatory. Without default, using @IsValidHobby without
    parenthesis () would cause a compiler error.
    */
    String listOfValidHobbies() default "Music|Football|Cricket|Hockey";
    
    /*
    Default String to carry a message to be displayed to the user when an invalid
    hobby is provided from the form. Override this by using the properties file in
    /WEB-INF/properties/Student_Form_Messages_en.properties.
    */
    String message() default "Please provide a valid Hobby. " +
            "Accepted hobbies are: Music, Football, Cricket, and Hockey.";
    
    /*
    TODO: Explain this code below.
    */
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}