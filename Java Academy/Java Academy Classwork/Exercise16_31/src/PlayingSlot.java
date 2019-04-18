import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

/**
 * Create a class for the PlayingSlots to be stored in the playingGrid.
 * @author Jeffrey McMullen II
 */
public class PlayingSlot 
{
    //Stores the border of the playing chip.
    private Circle border = null;
    
    //Stores the playing chip for the game.
    private Circle chip = null;
    
    /**
     * Default constructor for PlayingSlot.
     */
    PlayingSlot ()
    {
        
    }
    
    /**
     * Constructs the PlayingSlot at the Vector2 value made from columnOffset 
     * and rowOffset. The PlayingSlot will contain two events, one for when the 
     * mouse is hovering over the PlayingSlot, and one for when the mouse
     * clicks on the PlayingSlot.
     * @param columnOffset A double used for the horizonal placement of ArrowIndicator.
     * @param columnIndex  An integer used for storing with the indicators.
     * @param arrowIndicators An array of ArrowIndicators to provide the GameManager
     * with a reference for displaying and clearing the ArrowIndicator objects that appear
     * on screen.
     * @param playingGrid A two dimensional array of type PlayingSlot containing the 
     * locations of every playable playingGrid slot.
     * @param resetButton A button whose text will be assigned in the event the
     * PlayingSlot was clicked and the event resulted in a win.
     */
    PlayingSlot(double columnOffset, double rowOffset, ArrowIndicator[] arrowIndicators, PlayingSlot[][] playingGrid, ResetButton resetButton)
    {
        //Create a border for the chip slot.
        border = new Circle(48, 48, 48);
        border.getTransforms().add(new Translate(columnOffset, rowOffset));
        border.setFill(Color.BLACK);

        //Create a chip.
        chip = new Circle(43, 43, 43);
        chip.getTransforms().add(new Translate(columnOffset, rowOffset));
        chip.setFill(Color.WHITE);
        

        chip.setOnMouseEntered(mouseOverEvent ->
        {
            if (!GameManager.getIsGameOver())
            {
                if (chip.getFill() == Color.WHITE)
                {
                    ArrowIndicator arrowIndicator = arrowIndicators[Integer.parseInt(chip.getId())];
                    GameManager.hideChipIndicators(arrowIndicators);
                    GameManager.showChipIndicator(arrowIndicator);
                }
                else
                {
                    ArrowIndicator arrowIndicator = arrowIndicators[Integer.parseInt(chip.getId())];
                    GameManager.hideChipIndicators(arrowIndicators);
                }
            }
        });

        chip.setOnMouseClicked(clickEvent -> 
        {
            if (!GameManager.getIsGameOver())
            {
                if (clickEvent.getButton() == MouseButton.PRIMARY)
                {
                    if (chip.getFill() == Color.WHITE)
                    {
                        Exercise16_31.dropChip(chip.getId(), playingGrid, arrowIndicators, resetButton);
                    }
                }
            }
        });
    }
    
    /**
     * @return The chip for this playing slot.
     */
    public Circle getChip()
    {
        return chip;
    }
    
    /**
     * @return The border for the chip contained in this object.
     */
    public Circle getBorder()
    {
        return border;
    }
}