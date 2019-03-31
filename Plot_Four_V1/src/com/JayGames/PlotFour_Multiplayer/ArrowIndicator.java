package com.JayGames.PlotFour_Multiplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * Create a class for the indicators to be placed above each column.
 */
public class ArrowIndicator 
{
    private Circle indicator = null;
    private Rectangle arrowStem = null;
    private Polygon arrowHead = null;
    
    /**
     * Constructs an ArrowIndicator at the values stored in columnOffset
     * and columnIndex.
     * @param columnOffset A double used for the horizonal placement of ArrowIndicator.
     * @param columnIndex  An integer used for storing with the indicators.
     * setId method.
     */
    ArrowIndicator(double columnOffset, int columnIndex)
    {
        //Create the indicator chip
        indicator = new Circle(30, 30, 30);
        indicator.getTransforms().add(new Translate(columnOffset, -325));
        indicator.setFill(Color.color(244.0 / 255.0, 244.0 / 255.0, 244.0 / 255.0));
        indicator.setId(Integer.toString(columnIndex));

        //Create the stem of the arrow
        arrowStem = new Rectangle();  
        arrowStem.setWidth(10.0f); 
        arrowStem.setHeight(30.0f);  
        arrowStem.getTransforms().add(new Translate(columnOffset, -330));
        arrowStem.setFill(Color.color(244.0 / 255.0, 244.0 / 255.0, 244.0 / 255.0));

        //Create the head of the arrow
        arrowHead = new Polygon();
        arrowHead.getPoints().addAll(new Double[]
        {
            -10.0, 20.0,
            20.0, 20.0,
            5.0, 40.0
        });
        arrowHead.getTransforms().add(new Translate(columnOffset, -313));
        arrowHead.setFill(Color.color(244.0 / 255.0, 244.0 / 255.0, 244.0 / 255.0));
    }
    
    /**
     * @return A Circle object named indicator.
     */
    public Circle getIndicator()
    {
        return indicator;
    }
    
    /**
     * @return A Rectangle object named arrowStem.
     */
    public Rectangle getArrowStem()
    {
        return arrowStem;
    }
    
    /**
     * @return A Polygon object named arrowHead.
     */
    public Polygon getArrowHead()
    {
        return arrowHead;
    }
}