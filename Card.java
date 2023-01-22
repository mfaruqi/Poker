/**
 * This class if for an individual card
 */
public class Card {
    
    /**
     * This is the name of the card
     */
    private String name;

    /**
     * This is the value of the card
     */
    private int value;

    /**
     * This is the code of the card
     */
    private String code;

    /**
     * This is the suit of the card
     */
    private String suit;

    /**
     * This is the image of the card
     */
    private String image;

    /**
     * This constructor creates a card by giving it its properties
     * @param name this is the name of the card, e.g: "Queen, Ace"
     * @param value this is the integer value for a card, e.g: Q = 12
     * @param code this is the code for the card
     * @param suit this is the suit of the card, e.g "Diamonds, Spades"
     * @param image this is the image of the card
     */
    public Card(String name, int value, String code, String suit, String image) {
        this.name = name;
        this.value = value;
        this.code = code;
        this.suit = suit;
        this.image = image;

    }

    /**
     * This method gets the value of the card
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * This method returns the code of the card
     * @return the code
     */
    public String getCode() {
        return code; 
    }

    /**
     * This method gets the suit of the card
     * @return returns the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * This method gets the image of the card
     * @return image of the card
     */
    public String getImage() {
        return image;
    }

    /**
     * This method is a toString method for a card
     * @return string version of the card
     */
    public String toString() {
        return value + " of " + suit + " " + code; 
    }
}

