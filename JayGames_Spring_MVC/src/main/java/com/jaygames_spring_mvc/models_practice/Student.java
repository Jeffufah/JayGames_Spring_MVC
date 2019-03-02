package com.jaygames_spring_mvc.models_practice;

import com.jaygames_spring_mvc.custom_annotations_practice.IsValidHobby;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Jeffrey McMullen II
 */
public class Student 
{
    /*
    @Pattern annotation used to reject names that contain numbers and treats
    numbers as an error for @Valid annotation in StudentAdmissionController.
    Pattern reference URL: http://en.wikipedia.org/wiki/Regular_expression
    */
    @Pattern(regexp = "[^0-9]*")
    private String studentName;
      
    /*
    @Size annotation means size constraint.
    Example custom error message from within this class.
    @Size(min = 2, max = 30, message = "Please enter a hobby that is between {min} and {max} characters.")
    */
    /*
    Pulling error message from WEB-INF/properties/Student_Form_Messages_en.properties file.
    In the properties file placeholder {0} = studentHobby, {1} = max, and {2} = min.
    Placeholder number/variable mapping is done alphabetically.
    */
    @Size(min = 2, max = 30) @IsValidHobby(listOfValidHobbies = "Music|Football|Cricket|Hockey") //@IsValidHobby parameterless is now acceptable
    private String studentHobby;
      
    /*
    @Max annotation treats any number greater than parameter set as a binding error.
    */
    @Max(9999999)
    private Long studentMobile;
    
    /*
    @Past annotation treats any date beyond current date as a binding error.
    */
    @Past
    private Date studentDOB;
    
    private List<String> studentSkillSet;
    
    private Address studentAddress;

    public String getStudentName() 
    {
        return studentName;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentHobby() 
    {
        return studentHobby;
    }

    public void setStudentHobby(String studentHobby) 
    {
        this.studentHobby = studentHobby;
    }

    public Long getStudentMobile() 
    {
        return studentMobile;
    }

    public void setStudentMobile(Long studentMobile) 
    {
        this.studentMobile = studentMobile;
    }

    public Date getStudentDOB() 
    {
        return studentDOB;
    }

    public void setStudentDOB(Date studentDOB) 
    {
        this.studentDOB = studentDOB;
    }

    public List<String> getStudentSkillSet() 
    {
        return studentSkillSet;
    }

    public void setStudentSkillSet(List<String> studentSkillSet) 
    {
        this.studentSkillSet = studentSkillSet;
    }

    public Address getStudentAddress()
    {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress)
    {
        this.studentAddress = studentAddress;
    }
}
