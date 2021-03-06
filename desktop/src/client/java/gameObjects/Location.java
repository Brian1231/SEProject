package client.java.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/*
    Represents a locations data.
 */


public class Location {

    // Data
    private String name;
    private int position;
    private int price;
    private int rent;
    private int ownerID;
    private Color colour;
    private boolean isMortgaged;
    private Image image;
    private int houses;
    private String type;
    private boolean mortgagedLabelled = false;
    private boolean typeLabelled = false;

    public Location(String name, int position, int price, int rent, int owner, Color c, boolean isMortgaged, int houses, String type) {
        this.name = name;
        this.position = position;
        this.price = price;
        this.rent = rent;
        this.houses = houses;
        this.ownerID = owner;
        this.colour = c;
        this.isMortgaged = isMortgaged;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Location.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Location other = (Location) obj;

        return this.position == other.position;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int numHouses){
        houses = numHouses;
    }

    public boolean isMortgagedLabelled() {
        return mortgagedLabelled;
    }

    public void setMortgagedLabelled(boolean mortgagedLabelled) {
        this.mortgagedLabelled = mortgagedLabelled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTypeLabelled() {
        return typeLabelled;
    }

    public void setTypeLabelled(boolean typeLabelled) {
        this.typeLabelled = typeLabelled;
    }
}
