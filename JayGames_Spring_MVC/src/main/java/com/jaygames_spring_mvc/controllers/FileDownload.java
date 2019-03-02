package com.jaygames_spring_mvc.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileDownload
{
    @RequestMapping("/download/{fileName:.+}") //fileName: and what ever the extension would be.
    public void downloadEXEResource(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("fileName") String fileName, @RequestHeader String referer)
    {
        System.out.println(referer);
        //Check the referer
        if (referer != null && !referer.isEmpty())
        {
            //do nothing or send error
        }
        
        //If user is not authorized - he should be thrown out from here itself
        
        //Authorized user will download the file
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/resources/downloads/games");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/zip");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            System.out.println(fileName);
        }
    }
}