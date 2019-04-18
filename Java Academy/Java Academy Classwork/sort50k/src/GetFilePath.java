import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create a class for returning the path to a file.
 * @author Jeffrey McMullen II, Amanda Cottman, Yumei Qu
 */
public class GetFilePath 
{
    /**
     * Main method for GetFilePath to get the root access to project folder.
     * @param fileName A string containing the name of the file and its extension.
     * @return The path directory to the file to be accessed.
     */
    public static String main(String fileName)
    {
        Path projectPath = Paths.get("");
        
        return projectPath.toAbsolutePath().toString() + fileName;
    }
}
