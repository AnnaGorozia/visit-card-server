package models;



/**
 * Created by khrak on 7/21/16.
 */
public class Card {

    private double xCoordinate, yCoordinate;
    private FieldType fieldType;
    private String fieldValue;
    private String color;
    private String font;  // Italic, Aerial...
    private String fontSize;


    public Card(double xCoordinate, double yCoordinate, FieldType fieldType, String fieldValue,
                String color, String font, String fontSize) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
        this.color = color;
        this.font = font;
        this.fontSize = fontSize;
    }

    public String getFontSize() {
        return fontSize;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }
}
