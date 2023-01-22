import java.util.*;

/**
 * This class determines what type of hand you have our of 10, high card, one pair, two pair, three of a kind, straight, flush, full house, four of a kind, straight flush, royal flush
 */
public class Hands {

    /**
     * This method gets the winner of the two players hand
     * @param hand1 the first hand
     * @param hand2 the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int  getWinner(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        rankByValue(hand1); rankByValue(hand2);
        if(getHandValue(hand1) < getHandValue(hand2)) {
            return 1;
        }
        else if(getHandValue(hand1) > getHandValue(hand2)) {
            return 0;
        }
        else {

            if(royalFlush(hand1)) return -1;

            else if(straightFlush(hand1)) {
                return breakStraightTie(hand1, hand2);
            }
            else if(fourOfAKind(hand1)) {
                return breakFourOfAKindTie(hand1, hand2);
            }
            else if(fullHouse(hand1)) {
                return breakFullHouseTie(hand1, hand2);
            }
            else if(flush(hand1)) {
                return breakFlushTie(hand1, hand2);
            }
            else if(straight(hand1)) {
                return breakStraightTie(hand1, hand2);
            }
            else if(threeOfAKind(hand1)) {
                return breakThreeOfAKindTie(hand1, hand2);
            }
            else if(twoPair(hand1)) {
                return breakTwoPairTie(hand1, hand2);
            }
            else if(onePair(hand1)) {
                return breakOnePairTie(hand1, hand2);
            }
            else {
                return breakHighCardTie(hand1, hand2);
            }
        }
    }

    /**
     * This method converts the number into a string version of a card like 12, is Queen
     * @param i the number that needs to be converted
     * @return the string version of the value
     */
    public static String converter(int i) {
        if(i == 11) return "Jack";
        else if(i == 12) return "Queen"; 
        else if(i == 13) return "King";
        else if(i == 14) return "Ace";
        else return String.valueOf(i);
    }

    /**
     * This method breaks a high card tie
     * @param hand1 the first hand
     * @param hand2 the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie 
     */
    public static int breakHighCardTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        rankByValueDescending(hand1); rankByValueDescending(hand2);
        for(int i = 0 ; i < hand1.size(); i++) {
           if(hand1.get(i).getValue() > hand2.get(i).getValue()) {
                return 1;
           } else if(hand2.get(i).getValue() > hand1.get(i).getValue()) {
                return 0;
           } 
        }
        return -1;
    } 

    /**
     * This method breaks a four of a kind tie
     * @param hand1 the first hand
     * @param hand2 the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int breakFourOfAKindTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int quads1 = getThreeOfAKind(hand1);
        int quads2 = getThreeOfAKind(hand2);
        if(quads1 > quads2) return 1;
        else if(quads1 < quads2) return 0;
        else return -1;
    }

    /**
     * This method breaks a full house tie
     * @param hand1 this is the first hand
     * @param hand2 this is the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int breakFullHouseTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int trips1 = getThreeOfAKind(hand1);
        int trips2 = getThreeOfAKind(hand2);
        int pair1 = getPair(hand1, getThreeOfAKind(hand1)); 
        int pair2 = getPair(hand2, getThreeOfAKind(hand2));
        if(trips1 > trips2) return 1;
        else if(trips2 > trips1) return 0;
        else if(pair1 > pair2) return 1;
        else if(pair2 > pair1) return 0;
        else return -1;
    }

    /**
     * This method gets the value that appears in the hand 3 times
     * @param hand a players hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int getThreeOfAKind(ArrayList<Card> hand) {
        rankByValueDescending(hand);
        int counter = 0;
        for(int i = 1; i < hand.size(); i++) {
            if(hand.get(i).getValue() == hand.get(i - 1).getValue()) counter++;
            else counter = 0;
            if(counter == 2) return hand.get(i).getValue();
        }
        return -1;
    } 

    /**
     * This method breaks a flush tie
     * @param hand1 the first hand
     * @param hand2 the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */ 
    public static int breakFlushTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        String suit = getFlushSuit(hand1);
        ArrayList<Card> cards1 = getHighestCardsList(hand1, hand1.size(), -1);
        ArrayList<Card> cards2 = getHighestCardsList(hand2, hand2.size(), -1);
        rankByValueDescending(cards1);
        rankByValueDescending(cards2);
        //System.out.println(cards1);
        //System.out.println(cards2);

        for(int i = 0; i < cards1.size(); i++) {
            if(!cards1.get(i).getSuit().equals(suit)) {
                cards1.remove(i);
            }
        }
        for(int i = 0; i < cards2.size(); i++) {
            if(!cards2.get(i).getSuit().equals(suit)) {
                cards2.remove(i);
            }
        }
        //System.out.println(cards1);
        //System.out.println(cards2);
        
        for(int i = 0; i < cards1.size(); i++) {
            if(cards1.get(i).getValue() > cards2.get(i).getValue()) {
                return 1;
            } else if(cards1.get(i).getValue() < cards2.get(i).getValue()) {
                return 0;
            }
        }
        return -1;        
    }

    /**
     * This method gets teh highest flush card
     * @param hand1 a players hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static Card getHighestFlushCard(ArrayList<Card> hand1) {
        String suit = getFlushSuit(hand1);
        ArrayList<Card> cards1 = getHighestCardsList(hand1, hand1.size(), -1);
        rankByValueDescending(cards1);

        for(int i = 0; i < cards1.size(); i++) {
            if(!cards1.get(i).getSuit().equals(suit)) {
                cards1.remove(i);
            }
        }
        return cards1.get(0);
    } 

    /**
     * This method gets the suit of the flysh
     * @param hand a players hand
     * @return returns the suit of the flush
     */
    public static String getFlushSuit(ArrayList<Card> hand) {
        String heart = "Hearts";
        String club = "Clubs";
        String diomand = "Diamonds";
        String spade = "Spades";
        int hearts = 0; int clubs = 0; int diomands = 0; int spades = 0;
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getSuit().equals(heart)) {
                hearts++;
            }
            else if(hand.get(i).getSuit().equals(club)) {
                clubs++;
            }
            else if(hand.get(i).getSuit().equals(diomand)) {
                diomands++;
            }
            else if(hand.get(i).getSuit().equals(spade)) {
                spades++;
            }
        } 
        if(hearts >= 5) {
            return heart;
        }
        else if(clubs >= 5) {
            return club;
        }
        else if(diomands >= 5) {
            return diomand;
        }
        else {
            return spade;
        }
    }

    /**
     * This method breaks a straight tie
     * @param hand1 this is the first hand
     * @param hand2 this is the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */ 
    public static int breakStraightTie(ArrayList<Card> hand1, ArrayList<Card>hand2) {
        int highCard1 = getHighestStraightCard(hand1);
        int highCard2 = getHighestStraightCard(hand2);
        //System.out.println(highCard1 + "<<<<  " + highCard2);
        if(highCard1 > highCard2) {
            return 1;
        }
        else if(highCard2 > highCard1) {
            return 0;
        }
        else return -1;
    }

    /**
     * This method gets the highest straight card
     * @param hand a players hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */ 
    public static int getHighestStraightCard(ArrayList<Card> hand) {
        rankByValueDescending(hand);
        for(int i = 0; i < hand.size(); i++) {
            int counter = 0;
            if(hand.size()-i <  4) {
                break;
            }
            for(int j = 0; j < 5; j++) {
                //System.out.println(hand.get(i).getValue()-1 + " " +  hand.get(j+1).getValue());
                boolean edgeCase =  hand.get(j).getValue() == hand.get(j+1).getValue();
                if(edgeCase) j--;
                if(hand.get(i).getValue()-1 == hand.get(j+1).getValue() + j) {
                    counter++;
                    //System.out.println(counter + "FALSE");
                }
                if(counter == 4) {
                    return hand.get(i).getValue();
                }
            }
        }
        return -1;
    }

    /**
     * This method breaks a three of a kind tie
     * @param hand1 this is the first hand
     * @param hand2 this is the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int breakThreeOfAKindTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int set1 = getPair(hand1, -1);
        int set2 = getPair(hand2, -1);
        if(set1 > set2) {
            return 1;
        } else if(set1 < set2) {
            return 0;
        } else {
            int[] cards1 = getHighestCards(hand1, 2, set1);
            int[] cards2 = getHighestCards(hand2, 2, set2);
            for(int i = 0; i < cards1.length; i++) {
                if(cards1[i] > cards2[i]) {
                    return 1;
                } else if(cards1[i] < cards2[i]) {
                    return 0;
                }
            }
            return -1;
        }

    }

    /**
     * This method breaks a two pair tie
     * @param hand1 this is the first hand
     * @param hand2 this is the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int breakTwoPairTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int higherPair1 = getPair(hand1, -1); 
        int lowerPair1 = getPair(hand1, getPair(hand1, -1));
        int higherPair2 = getPair(hand2, -1);
        int lowerPair2 = getPair(hand2, getPair(hand2, -1));
        //System.out.println(lowerPair1 + " <<<  " + higherPair1);
        if(higherPair1 > higherPair2) {
            return 1;
        } else if(higherPair1 < higherPair2) {
            return 0;
        } else if(lowerPair1 > lowerPair2) {
            return 1;
        } else if(lowerPair1 < lowerPair2) {
            return 0;
        } else {
            int firstHand = getHighestCard(hand1, lowerPair1, higherPair1);
            int secondHand = getHighestCard(hand2, lowerPair2, higherPair2);
            if(firstHand>secondHand) {
                return 1;
            } else if(firstHand<secondHand) {
                return 0;
            } else return -1;
        }
    }

    /**
     * This method breaks a one pair tie
     * @param hand1 this is the first hand
     * @param hand2 this is the second hand
     * @return returns 1 if hand1 wins, 0 if hand2 wins, and -1 if its a tie
     */
    public static int breakOnePairTie(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int pairValue1 = getPair(hand1, -1);
        int pairValue2 = getPair(hand2, -1);
        //System.out.println(pairValue1 + "   " + pairValue2);
        if(pairValue1 > pairValue2) {
            return 1;
        }
        else if(pairValue1 < pairValue2) {
            return 0;
        }
        else {
            int[] cards1 = getHighestCards(hand1, 3, getPair(hand1, -1));
            int[] cards2 = getHighestCards(hand2, 3, getPair(hand2, -1));
            //System.out.println(Arrays.toString(cards1));
            //System.out.println(Arrays.toString(cards2));
            for(int i = 0; i < cards1.length; i++) {
                if(cards1[i] > cards2[i]) {
                    return 1;
                }
                else if(cards1[i] < cards2[i]) {
                    return 0;
                }
            }
            return -1;

        }

    }

    /**
     * This method gets the highest card
     * @param hand a players hand
     * @param excluding1 dont return this card
     * @param excluding2 dont return this card
     * @return the highest card excluding the two int params
     */
    public static int getHighestCard(ArrayList<Card> hand, int excluding1, int excluding2) {
        rankByValue(hand);
        for(int i = hand.size() - 1; i >= 0; i++) {
            if(hand.get(i).getValue() != excluding1 && hand.get(i).getValue() != excluding2) {
                return hand.get(i).getValue();
            }
        }
        return -1;
    }

    /**
     * This method gets the highestCards in a list
     * @param hand a players hand
     * @param numberOfCards the number of cards you want in the array
     * @param excluding dont put this card int the array
     * @return the list of cards
     */
    public static ArrayList<Card> getHighestCardsList(ArrayList<Card> hand, int numberOfCards, int excluding) {
        rankByValue(hand);
        //System.out.println(hand);
        ArrayList<Card> res = new ArrayList<Card>();
        if(excluding!= -1) {
            int j = 0;
            for(int i = hand.size()-1; i >= 0; i--) {
                if(res.get(numberOfCards-1).getValue() != 0) {
                    break; 
                }
                if(hand.get(i).getValue() != excluding) {
                    res.add(hand.get(i)); 
                    j++;
                }
            }
        }
        else {
            for(int i = 0; i < numberOfCards; i++) {
                res.add(hand.get(i));
            } 
        }
        return res;
    }

    /**
     * This method gets the highestCards in an array 
     * @param hand a players hand
     * @param numberOfCards the number of cards you want in the array
     * @param excluding dont put this card int the array
     * @return the array of cards
     */
    public static int[] getHighestCards(ArrayList<Card> hand, int numberOfCards, int excluding) {
        rankByValue(hand);
        //System.out.println(hand);
        int[] res = new int[numberOfCards];
        if(excluding!= -1) {
            int j = 0;
            for(int i = hand.size()-1; i >= 0; i--) {
                if(res[numberOfCards-1] != 0) {
                    break; 
                }
                if(hand.get(i).getValue() != excluding) {
                    res[j] = hand.get(i).getValue(); 
                    j++;
                }
            }
        }
        else {
            for(int i = 0; i < numberOfCards; i++) {
                res[i] = hand.get(i).getValue();
            } 
        }
        return res;
    }

    /**
     * This method gets a card that appears twice in the hand
     * @param hand a players hand
     * @param excluding not this card
     * @return the card appearing twice thats not the excluding
     */
    public static int getPair(ArrayList<Card> hand, int excluding) {
        rankByValueDescending(hand);
        int counter = 0;
        int prev = hand.get(0).getValue();
        for(int i = 1; i < hand.size(); i++) {
            if(prev == hand.get(i).getValue() && prev != excluding) {
                counter++;
            }
            else counter = 0;
            if(counter == 1) return hand.get(i).getValue();
            prev = hand.get(i).getValue();
        }
        return -1;
    }

    /**
     * This method gets the hand value of their hand
     * @param hand a players hand
     * @return hand value, 1 is best, 10 is worst
     */
    public static int getHandValue(ArrayList<Card> hand) {
        if(royalFlush(hand)) return 1;
        else if(straightFlush(hand)) return 2;
        else if(fourOfAKind(hand)) return 3;
        else if(fullHouse(hand)) return 4;
        else if(flush(hand)) return 5;
        else if(straight(hand)) return 6;
        else if(threeOfAKind(hand)) return 7;
        else if(twoPair(hand)) return 8;
        else if(onePair(hand)) return 9;
        else return 10;
    }

    /**
     * This is the way to get the string representation of your hand
     * @param hand a players hand
     * @return the string version of the hand
     */
    public static String getHandString(ArrayList<Card> hand) {
        if(royalFlush(hand)) return "Royal Flush";
        else if(straightFlush(hand)) return "Straight Flush " + converter(getHighestStraightCard(hand)) + " High";
        else if(fourOfAKind(hand)) return "Four Of A Kind " + converter(getThreeOfAKind(hand)) + "'s";
        else if(fullHouse(hand)) return "Full House " + converter(getThreeOfAKind(hand)) + "'s Full Of " + converter(getPair(hand, getThreeOfAKind(hand)));
        else if(flush(hand)) return "Flush " + converter(getHighestFlushCard(hand).getValue()) + " High";
        else if(straight(hand)) return "Straight " + converter(getHighestStraightCard(hand)) + " High";
        else if(threeOfAKind(hand)) return "Three Of A Kind " + converter(getPair(hand, -1)) + "\'s";
        else if(twoPair(hand)) return "Two Pair " + converter(getPair(hand, -1)) + "\'s and " + converter(getPair(hand, getPair(hand, -1))) + "\'s";
        else if(onePair(hand)) return "One Pair " + converter(getPair(hand, -1)) + "\'s";
        else return "High Card " + converter(getHighestCard(hand, -1, -1));
    }

    /**
     * This method checks if it is a royal flush
     * @param hand a players hand
     * @return true if royal flush else false
     */
    public static boolean royalFlush(ArrayList<Card> hand) {
        if(hand.size()<5) {
            return false;
        }
        hand  = rankByValue(hand);
        return flush(hand) && straight(hand) && checkBroadway(hand);
    }

    /**
     * This method checks if the cards contain 10,11,12,13,14
     * @param hand players hand
     * @return true if it does else false
     */
    public static boolean checkBroadway(ArrayList<Card> hand) {
        rankByValue(hand);
        boolean bool = false;
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getValue() == i + 10) {
                bool = true;
            }
            if(!bool && i == hand.size() - 1) return false;
        }
        return true;
    }

    /**
     * This method checks if it a straight flush
     * @param hand a players hand
     * @return true of false
     */
    public static boolean straightFlush(ArrayList<Card> hand) {
        return flush(hand) && straight(hand);
    } 

    /**
     * This method checks if it a four of a kind
     * @param hand a players hand
     * @return true or false
     */
    public static boolean fourOfAKind(ArrayList<Card> hand) {
        hand = rankByValue(hand);
        int counter = 1;
        Card card = hand.get(0);
        for(int i = 1; i < hand.size(); i++) {
            if(hand.get(i).getValue() == card.getValue()) {
                counter++;
            }
            else {
                card = hand.get(i);
                counter = 1;
            }
            if(counter == 4) return true;
        }
        return false;
    }

    /**
     * This method checks if the hand is a full house
     * @param hand a player's hand
     * @return true or false
     */
    public static boolean fullHouse(ArrayList<Card> hand) {
        // int counter= numberOfTimesInHand(hand);
        hand = rankByValue(hand);
        int counter = 0;
        if(hand.size() < 5) return false;
        Card card = hand.get(0);
        for(int i = 1; i < hand.size(); i++) {
            if(card.getValue() == hand.get(i).getValue()) {
                counter++;
            }
            else {
                card = hand.get(i);
            }
        }
        // System.out.println(counter);
        return counter == 3 && threeOfAKind(hand);
    }

    /**
     * This method checks if it is a flush or not
     * @param hand this is a players hand
     * @return true or false
     */
    public static boolean flush(ArrayList<Card> hand) {
        if(hand.size()<5) return false;
        String clubs = "Clubs";
        String spades = "Spades";
        String diamonds = "Diamonds";
        String hearts = "Hearts";
        int clubsC = 0;
        int spadesC = 0;
        int diamondsC = 0;
        int heartsC = 0;
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getSuit().equals(clubs)) 
                clubsC++;
            else if(hand.get(i).getSuit().equals(spades)) 
                spadesC++;
            else if(hand.get(i).getSuit().equals(diamonds)) 
                diamondsC++;
            else heartsC++;
        }
        return clubsC > 4 || spadesC > 4 || diamondsC > 4 || heartsC > 4;
    }

    /**
     * This method checks if it is a straight or not
     * @param hand a players hand
     * @return true or false
     */
    public static boolean straight(ArrayList<Card> hand) {
        if(hand.size()<5) {
            return false;
        }
        hand = rankByValue(hand);
        boolean a = false;
        boolean b = false; 
        if ( hand.get(hand.size()-1).getValue() == 14 )
        {
            // this checks for a straight with an ace
            a = checkValue(hand,2) && checkValue(hand,3) &&
                checkValue(hand,4) && checkValue(hand,5);
            b = checkValue(hand,10) && checkValue(hand,11) &&
                checkValue(hand,12) && checkValue(hand,13);

            return (a||b);
        }
        return straightHelp(hand);
    }

    /**
     * This method helps out in checking for a straight
     * @param hand this is a players hand
     * @return true or false
     */
    public static boolean straightHelp(ArrayList<Card> hand) {
        rankByValue(hand);
        int counter = 0;
        int testRank = hand.get(0).getValue() + 1;
        for(int i = 1; i < hand.size(); i++) {
            boolean edgeCase =  hand.get(i).getValue() == hand.get(i-1).getValue();
            if(hand.get(i).getValue() == hand.get(i-1).getValue() + 1) {
                counter++;
            } 
            else if(!edgeCase) {
                counter = 0;
            }
            if(counter == 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if the hand contains a value
     * @param hand a players hand
     * @param value the value to check
     * @return returns true or false
     */
    public static boolean checkValue(ArrayList<Card> hand, int value) {
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method ranks the cards by value
     * @param hand the hand to sort
     * @return the sorted list
     */
    public static ArrayList<Card> rankByValue(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++)  {
            for (int j = i + 1; j < hand.size(); j++) { 
                if (hand.get(i).getValue() > hand.get(j).getValue()) 
                {
                    Card temp = hand.get(i);
                    hand.set(i, hand.get(j));
                    hand.set(j, temp);
                }
            }
        }
        return hand;
    }

    /**
     * This method ranks the cards by value descending
     * @param hand the hand to sort
     * @return the sorted list
     */
    public static ArrayList<Card> rankByValueDescending(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++)  {
            for (int j = i + 1; j < hand.size(); j++) { 
                if (hand.get(i).getValue() < hand.get(j).getValue()) 
                {
                    Card temp = hand.get(i);
                    hand.set(i, hand.get(j));
                    hand.set(j, temp);
                }
            }
        }
        return hand;
    }

    /**
     * This method checks for a three of a kind
     * @param hand a players hand
     * @return true or false
     */
    public static boolean threeOfAKind(ArrayList<Card> hand) {
        hand = rankByValue(hand);
        int counter = 1;
        Card card = hand.get(0);
        for(int i = 1; i < hand.size(); i++) {
            if(hand.get(i).getValue() == card.getValue()) {
                counter++;
            }
            else {
                card = hand.get(i);
                counter = 1;
            }
            if(counter == 3) return true;
        }
        return false;

    }

    /**
     * This method checks if it is a one pair
     * @param hand a players hand
     * @return true or false
     */
    public static boolean onePair(ArrayList<Card> hand) {
        int counter = numberOfTimesInHand(hand);
        return counter == 2;
    }

    /**
     * This method checks how many times something is duplicate in a hand
     * @param hand a players hand
     * @return the number of times
     */
    public static int numberOfTimesInHand(ArrayList<Card> hand) {
        int counter = 0;
        Card card = hand.get(0);
        for(int i = 1; i < hand.size(); i++) {
            if(hand.get(i).getValue() == card.getValue())
                counter++;
            else card = hand.get(i);
            if(counter == 3)
                return 5; // full house
            else if(counter == 2) return 4; // two pair or three of a kind
        }
        return counter + 1; // one pair
    }

    /**
     * This method checks for if it is a two pari
     * @param hand this is the players hand
     * @return true or false
     */
    public static boolean twoPair(ArrayList<Card> hand) {
        if(threeOfAKind(hand)) return false;
        int sum = numberOfTimesInHand(hand);
        return sum == 4;
    }
}
