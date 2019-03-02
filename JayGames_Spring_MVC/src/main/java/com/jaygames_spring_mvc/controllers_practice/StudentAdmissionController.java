package com.jaygames_spring_mvc.controllers_practice;

import com.jaygames_spring_mvc.models_practice.Student;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jeffrey McMullen II
 */
@Controller
public class StudentAdmissionController 
{
    /**
     * Disallow studentMobile number to be bound to Student model. Also uses
     * a CustomDateEditor to require date values acquired from the form in the
     * format of yyyy****MM****dd instead of yyyy/mm/dd.
     * 
     * @param binder A WebDataBinder object for preventing studentMobile
     * form data from being bound to the Student model.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        //Allows for ignoring the studentMobile form field.
        //binder.setDisallowedFields(new String[] {"studentMobile"});
               
        //Enforcing desired date format for form submission.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy****MM****dd");
        binder.registerCustomEditor(Date.class, "studentDOB", 
                new CustomDateEditor(dateFormat, false));
        
        //Utilizing custom made StudentNameEditor class to prefix studentName
        //with "Mr. " if the name does not contain "Mr." or "Ms.".
        binder.registerCustomEditor(String.class, "studentName", 
                new StudentNameEditor());
        /*
            Link to spring property editor reference.
            https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
        */
    }
    
    /**
     * Creates a model with a header message. Saves the trouble of creating the same
     * model in the GET or POST methods because it can be done from here.
     * @ModelAttribute is called before @RequestMapping.
     * 
     * @param model The Model object to contain a header message for both 
     * GET and POST requests in this class.
     */
    @ModelAttribute //Spring MVC always calls @ModelAttribute first.
    public void addGenericStudentAdmissionObjects(Model model) 
    {
        model.addAttribute("headerMessage", "DTCC Java Academy, Dover");
    }
    
    /**
     * Get method for directing the web page when /admissionForm.html is opened.
     * @return A model to display the AdmissionForm.jsp file.
     */
    @RequestMapping(value = "/admissionForm.html", method = RequestMethod.GET)
    public ModelAndView getAdmissionForm()
    {
        ModelAndView model = new ModelAndView("AdmissionForm");
        return model;
    }
    
    /**
     * Post method for collecting student form data.
     * 
     * @Valid Tells the @ModelAttribute (Student class) to use @Size validation 
     * when binding information from the form to the Student class fields. 
     * (Required maven dependencies for @Valid are: hibernate-validator, 
     * jboss-logging, validation-api, and classmate.)
     * @param newStudent Binds form values to Student class fields.
     * @param result BindingResult object to contain form validation errors.
     * @return A model containing a header message and the form values stored
     * in the Student object.
     */
    @RequestMapping(value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("newStudent") Student newStudent,
                    BindingResult result)
    { 
        if (result.hasErrors())
        {
            ModelAndView redoFormPage = new ModelAndView("AdmissionForm");
            return redoFormPage;
        }

        ModelAndView successPage = new ModelAndView("AdmissionSuccess");
        return successPage;
    }
    
    /**
     * Post method for collecting student form data using @RequestMapping
     * annotation and manual binding to Student fields.
     * 
     * @param formArgs Binds form values to a map.
     * @return A model containing a header message and the form values stored
     * in the Student object.
     */
    /*
    @RequestMapping(value="/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@RequestParam Map<String, String> formArgs)
    {
        Student newStudent = new Student();
        
        String name = formArgs.get("studentName");
        String hobby = formArgs.get("studentHobby");
        String mobile = formArgs.get("studentMobile");
        String DOB = formArgs.get("studentDOB");
        String skillSet = formArgs.get("studentSkillSet");
            
        try
        {
            newStudent.setStudentName(name);
            
            newStudent.setStudentHobby(hobby);
            
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(DOB);           
            newStudent.setStudentDOB(date);
            
            newStudent.setStudentMobile(Long.parseLong(mobile));
            
            List<String> skillSetList = new ArrayList<>();         
            skillSetList.addAll(Arrays.asList(skillSet));   
            newStudent.setStudentSkillSet(skillSetList);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
               
        ModelAndView model = new ModelAndView("AdmissionSuccess");
        model.addObject("newStudent", newStudent);
        
        return model;
    }
    */
    
    /**
     * Post method for collecting student form data using @RequestMapping
     * annotation and manual binding to Student fields.
     *
     * @param name A String containing the student's full name.
     * @param hobby A String containing the student's hobby.
     * @param mobile A String containing the student's mobile phone number.
     * @param DOB A String containing the student's date of birth.
     * @param skillSet A String containing the student's set of skills.
     * @return A model containing a header message and the form values stored in
     * the Student object.
     */
    /*
    @RequestMapping(value="/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@RequestParam("studentName") String name,
            @RequestParam("studentHobby") String hobby,
            @RequestParam("studentMobile") String mobile,
            @RequestParam("studentDOB") String DOB,
            @RequestParam("studentSkillSet") String skillSet)
    {
        Student newStudent = new Student();
    
        try
        {
            newStudent.setStudentName(name);
            
            newStudent.setStudentHobby(hobby);
            
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(DOB);           
            newStudent.setStudentDOB(date);
            
            newStudent.setStudentMobile(Long.parseLong(mobile));
            
            List<String> skillSetList = new ArrayList<>();         
            skillSetList.addAll(Arrays.asList(skillSet));   
            newStudent.setStudentSkillSet(skillSetList);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
               
        ModelAndView model = new ModelAndView("AdmissionSuccess");
        model.addObject("newStudent", newStudent);
        
        return model;
    }
    */
}