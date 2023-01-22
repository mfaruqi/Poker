import java.util.*;
import java.io.*;
public class Tester {

    public static void main(String[] args) throws FileNotFoundException {

        //test Hands.java
        ArrayList<Card> hand = new ArrayList<>(); 
        hand.add(new Card("14 of Spades", 13, "14S", "Hearts", ""));
        hand.add(new Card("Q of Spades", 12, "12S", "Spades", ""));
        hand.add(new Card("J of Spades", 11, "11S", "Spades", ""));
        hand.add(new Card("K of Spades", 7, "13S", "Spades", ""));
        hand.add(new Card("9 of Spades", 9, "9S", "Spades", ""));
        hand.add(new Card("5 of Hearts", 5, "5H", "Hearts", ""));
        hand.add(new Card("Q of Hearts", 6, "11H", "Hearts", ""));
        System.out.println("Hand: " + Hands.getHandString(hand));




        ArrayList<Card> hand1 = new ArrayList<>(); 
        hand1.add(new Card("14 of Spades", 12, "14S", "Hearts", ""));
        hand1.add(new Card("Q of Spades", 14, "12S", "Spades", ""));
        hand1.add(new Card("J of Spades", 10, "11S", "Spades", ""));
        hand1.add(new Card("K of Spades", 9, "13S", "Spades", ""));
        hand1.add(new Card("9 of Spades", 8, "9S", "Spades", ""));
        hand1.add(new Card("Q of Hearts", 2, "12H", "Hearts", ""));
        hand1.add(new Card("J of Hearts", 11, "11H", "Hearts", ""));
        System.out.println("Hand: " + Hands.getHandString(hand1));
        
        System.out.println(Hands.getWinner(hand, hand1));
        //does not work as I removed the constructor, just wanted to show how I tested a little
//        System.out.println(projectEulerCheck());
    }
    
/*    public static int projectEulerCheck() throws FileNotFoundException {
    File f = new File("/Applications/APCS/Poker/poker.txt");
    Scanner in = new Scanner(f);
    int player1Wins = 0;
    while(in.hasNextLine()) {
        String line1 = in.nextLine();
        String[] line = line1.split(" ");
        ArrayList<Card> player1 = new ArrayList<Card>();
        ArrayList<Card> player2 = new ArrayList<Card>();
        Card card11 = new Card(line[0]); player1.add(card11);
        player1.add(new Card(line[1]));
        player1.add(new Card(line[2]));
        player1.add(new Card(line[3]));
        player1.add(new Card(line[4]));
        player2.add(new Card(line[5]));
        player2.add(new Card(line[6]));
        player2.add(new Card(line[7]));
        player2.add(new Card(line[8]));
        player2.add(new Card(line[9]));
        System.out.print(player1);
        System.out.print(" " + player2);
        System.out.print(" " + Hands.getHandString(player1) + " " + Hands.getHandString(player2) + " " + "|||| Player " + Math.abs(Hands.getWinner(player1, player2)-2) + " wins");
        System.out.println();
        if(Hands.getWinner(player1, player2) == 1) player1Wins++;

    }
    return player1Wins;

    } */

}
