import org.json.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
/**
 * This is card deck class
 */
public class CardDeck {

    /**
     * This is the list of cards
     */
    private ArrayList<Card> deck;

    /**
     * This is the string id of deck from an api
     */
    private String id;

    /**
     * This constructor makes a api json object and fills the array with images and values from an online request
     * @param num different constructor
     */
    public CardDeck(int num) {
        deck = new ArrayList<>();
        String id = getJSON();
        JSONObject obj = null;
        try {
            Scanner in = new Scanner(System.in);
            String requestURL = "https://deckofcardsapi.com/api/deck/" + id + "/draw/?count=52"; 
            URL url = new URL(requestURL);
            Scanner sc = new Scanner(url.openStream());
            String res = sc.useDelimiter("\\Z").next();
            obj = new JSONObject(res);
        } catch(Exception e) {
            System.out.println(e);
        }
        //System.out.println(obj);
        JSONArray ja  = (JSONArray)obj.get("cards");
        //System.out.println(ja);
        Iterator it = ja.iterator();
        while(it.hasNext()) {
            JSONObject cardObj = (JSONObject)(it.next());
            String image = (String)cardObj.get("image");
            String value = (String)cardObj.get("value");
            String suit = (String)cardObj.get("suit");
            String code = (String)cardObj.get("code");
            int value1 = 0;
            if(value.equals("ACE")) value1 = 14;
            else if(value.equals("KING")) value1 = 13;
            else if(value.equals("QUEEN")) value1 = 12;
            else if(value.equals("JACK")) value1 = 11;
            else value1 = Integer.valueOf(value);
            suit = suit.charAt(0) + suit.substring(1).toLowerCase();
            deck.add(new Card(value + " of " + suit, value1, code, suit, image));

        }
    }

    /**
     * This method gets a card from the postion i
     * @param i the position of the card to get
     * @return the card at that position
     */
    public Card get(int i) {
        return deck.get(i);
    }

    /**
     * This method shuffles the deck using the online api
     */
    public void shuffle() {

        deck.clear();
        JSONObject obj = null;
        try {
            Scanner in = new Scanner(System.in);
            String requestURL = "https://deckofcardsapi.com/api/deck/"+id+"/shuffle/"; 
            URL url = new URL(requestURL);
            Scanner sc = new Scanner(url.openStream());
            String res = sc.useDelimiter("\\Z").next();
            obj = new JSONObject(res);
        } catch(Exception e) {
            System.out.println(e);
        }
        JSONObject obj2 = null;
        try {
            Scanner in = new Scanner(System.in);
            String requestURL = "https://deckofcardsapi.com/api/deck/"+id+"/draw/?count=52"; 
            URL url = new URL(requestURL);
            Scanner sc = new Scanner(url.openStream());
            String res = sc.useDelimiter("\\Z").next();
            obj2 = new JSONObject(res);
        } catch(Exception e) {
            CardDeck deck2 = new CardDeck(0);
            deck = deck2.getDeck();
        }

        JSONArray ja  = (JSONArray)obj2.get("cards");
        //System.out.println(ja);
        Iterator it = ja.iterator();
        while(it.hasNext()) {
            JSONObject cardObj = (JSONObject)(it.next());
            String image = (String)cardObj.get("image");
            String value = (String)cardObj.get("value");
            String suit = (String)cardObj.get("suit");
            String code = (String)cardObj.get("code");
            int value1 = 0;
            if(value.equals("ACE")) value1 = 14;
            else if(value.equals("KING")) value1 = 13;
            else if(value.equals("QUEEN")) value1 = 12;
            else if(value.equals("JACK")) value1 = 11;
            else value1 = Integer.valueOf(value);
            suit = suit.charAt(0) + suit.substring(1).toLowerCase();
            deck.add(new Card(value + " of " + suit, value1, code, suit, image));
        }
    }

    /**
     * To string for this deck
     * @return string for the deck
     */
    public String toString() {
        return deck.toString(); 
    }

    /**
     * This method connects with the api and gets a new deck id to use in the constructor
     * @return returns deck id
     */
    public String getJSON() {
        JSONObject obj = null;
        try {
            Scanner in = new Scanner(System.in);
            String requestURL = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1"; 
            URL url = new URL(requestURL);
            Scanner sc = new Scanner(url.openStream());
            String res = sc.useDelimiter("\\Z").next();
            obj = new JSONObject(res);
        } catch(Exception e) {
            System.out.println(e);
        }

        id = (String)obj.get("deck_id");
        return id;


    }

    /**
     * This method returns the deck of cards
     * @return the deck 
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
}
