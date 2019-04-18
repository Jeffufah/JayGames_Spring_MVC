  import javafx.application.Application;
  import javafx.stage.Stage;
  import javafx.collections.FXCollections;
  import javafx.collections.ObservableList;
  import javafx.scene.Scene;
  import javafx.scene.control.ComboBox;
  import javafx.scene.control.Label;
  import javafx.scene.image.ImageView;
  import javafx.scene.layout.BorderPane;
  
  //Used to read the file line by line.
import java.util.Scanner;

//Stores the path to file.txt
import java.nio.file.Path;

//Used to find the path to this project.
import java.nio.file.Paths;

//Used to work with files.
import java.io.File;

//Used to handle instances where a file does not exist.
import java.io.FileNotFoundException;

//Used to handle instances where encoding is improper.
import java.io.UnsupportedEncodingException;

public class Exercise16_27 extends Application {
    // Declare an array of Strings for flag titles
    private String[] flagTitles = { "Canada", "China", "Denmark", "France", "Germany", "India", "Norway",
                    "United Kingdom", "United States of America" };

    // Declare an ImageView array for the national flags of 9 countries
    private ImageView[] flagImage = { new ImageView("image/ca.png"), new ImageView("image/china.png"),
                    new ImageView("image/denmark.png"), new ImageView("image/fr.png"), new ImageView("image/germany.png"),
                    new ImageView("image/india.png"), new ImageView("image/norway.png"), new ImageView("image/uk.png"),
                    new ImageView("image/us.png") };

    // Declare an array of strings for flag descriptions
    private String[] flagDescription = new String[9];

    // Declare and create a description pane
    private DescriptionPane descriptionPane = new DescriptionPane();

    // Create a combo box for selecting countries
    private ComboBox<String> cbo = new ComboBox<>(); // flagTitles;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        for (int i = 0; i < flagDescription.length; i++)
        {
            //Stores the path to file.txt
            String filePath = getFilePath("description" + i + ".txt");

            //Stores the file contained in root project folder.
            File file = new File(filePath);

            //If the file exists, concatenate all the file contents to a string
            //and assign that string to descriptions index i
            if(file.exists()) 
            {                
                // Create a Scanner for the file
                Scanner input = new Scanner(file);

                //Used to concatenate description.
                String description = "";

                // Read data from a file
                while (input.hasNextLine()) 
                {
                    description += input.nextLine() + "\n";
                }

                // Close the file
                input.close();
                
                //Assing the contents of description to the appropriate country
                //flag description
                flagDescription[i] = description;
            }
        }

        // Set the first country (Canada) for display
        setDisplay(0);

        // Add combo box and description pane to the border pane
        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);

        cbo.setPrefWidth(400);
        cbo.setValue("Canada");

        ObservableList<String> items = FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);

        // Display the selected country
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ComboBoxDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /** Set display information on the description pane */
    public void setDisplay(int index) {
            descriptionPane.setTitle(flagTitles[index]);
            descriptionPane.setImageView(flagImage[index]);
            descriptionPane.setDescription(flagDescription[index]);
    }
    
    public static String getFilePath(String fileName)
    {
        //Get the path to the root of this project folder.
        Path projectPath = Paths.get("");
        
        return projectPath + fileName;
    }
}