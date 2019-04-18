import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the background to be displayed on screen.
 * @author Jeffrey McMullen II
 */
public class GameBackground 
{
    //Stores a white background whose screen resolution is 1920 x 1080.
    private Rectangle background = null;
    
    /**
     * Default constructor for Background object.
     */
    GameBackground()
    {
        
    }
    
    /**
     * Constructs a Background according to specified dimensions. It will contain a
     * an event that will call the GameManager's static method, hideChipIndicators
     * when the player moves their mouse over top of the background.
     * @param width An integer to be used to assign screen width of the Background.
     * @param height An integer to be used to assign screen height of the Background.
     * @param arrowIndicators An array of type ArrowIndicator that contains
     * the location of each arrowIndicator at each column.
     * @param root A StackPane that this Background object will add itself to
     * once it has finished construction.
     */
    GameBackground(int width, int height, ArrowIndicator[] arrowIndicators)
    {
        background = new Rectangle();  

        //Setting the properties of the rectangle 
        background.setWidth(width); 
        background.setHeight(height);  
        background.setFill(Color.WHITE);
        
        background.setOnMouseEntered(mouseOverEvent ->
        {
            GameManager.hideChipIndicators(arrowIndicators);
        });
    }
    
    /**
     * @return A Rectangle object named background.
     */
    public Rectangle getBackground()
    {
        return background;
    }
}