package sample;
import java.util.*;
/**
 * The class Game represents a game object with Players, Board, and Bank
 * and can assign turns after every turn. The action of every tile landed is also done here.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Game {

    private final String name;
    private ArrayList <Player> players;
    private Board board;
    private Bank bank;
    private int turn;
    private Player currPlayer;
    private Tile currTile;
    private	ArrayList <ChanceCard> finalCards;
    private	ArrayList <ChanceCard> usedCards;



    public Game (){
        this.name = "My Empire";
        this.players = new ArrayList <Player>();
        this.board = new Board();
        this.bank = new Bank();
        this.turn = 0;
        this.finalCards = new ArrayList <>();
        this.usedCards = new ArrayList <>();
        generateAllCards();
    }

    // methods -----------
    /** This method rolls the dice of the player for the game and move the current player depending on the randomly generated
     * value of the dice.
     *
     * @return the value of the dice.
     */
    public int rollDice()
    {
        int n = currPlayer.rollDice();
        currPlayer.move(n);
        currTile = board.getTiles().get(currPlayer.getCurrTile());
        System.out.println(currPlayer.getName() + " landed on [" + currPlayer.getCurrTile() + "] " + currTile.getName());
        return n;
    }

    /** This method returns true if the game is already over. Otherwise, false.
     * It checks the whether a player is already bankrupt, or the bank is bankrupt, or
     * the current player owns two full sets of properties.
     *
     * @param p is the current player object of the game.
     * @param b is the bank object of the game.
     * @return true if the game is over. Otherwise, false.
     */
    public boolean isOver(Player p, Bank b){
        return (p.isBankrupt() || b.isBankrupt() || p.ownsTwoColors());
    }

    /** This method assigns the ranking of every player after the game. This will determine who
     * will be the winner of the game.
     *
     */
    public void computeRankings(){
        Comparator<Player> playerValRank = (p1, p2) -> (int) (p1.getPlayerVal() - p2.getPlayerVal());
        for (int i = 0; i < players.size(); i++)
        {
            players.get(i).computePlayerVal();
            System.out.println(players.get(i).getName() + players.get(i).getPlayerVal());
        }
        players.sort(Collections.reverseOrder(playerValRank));
    }

    /** This method generates the cards to be used in the game.
     *
     */
    public void generateAllCards(){

        System.out.println("\t\tTESTING generateAllCards().. ");
        Random rand = new Random();

        int i;
        for (i = 0; i < 2; i++){ //generates 2 cards from Group 1
            finalCards.add(new ChanceCard(1,1));
        }

        for (i = 0; i < 6; i++){ //generates 6 cards from Group 2
            int n = rand.nextInt(3) + 2;

            finalCards.add(new ChanceCard(n,2));
        }

        for (i = 0; i < 6; i++){ //generates 6 cards from Group 3
            int n = rand.nextInt(5) + 5;

            finalCards.add(new ChanceCard(n,3));
        }

        for (i = 0; i < 4; i++){ //generates 4 cards from Group 4
            int n = rand.nextInt(2) + 10;

            finalCards.add(new ChanceCard(n,4));
        }

        for (i = 0; i < 7; i++){ //generates 7 cards from Group 5
            int n = rand.nextInt(5) + 12;

            finalCards.add(new ChanceCard(n,5));
        }

        for (i = 0; i < 3; i++){ //generates 3 cards from Group 6
            int n = rand.nextInt(2) + 17;

            finalCards.add(new ChanceCard(n,6));
        }

        //TESTING
        System.out.println("\t\tTRACING before <Shuffle>: List of [FINAL] Cards");
        System.out.println("\t\t\tNo. of cards: " + finalCards.size());
        for (i = 0; i < finalCards.size(); i++) {
            System.out.println("\t\t" +finalCards.get(i));
        }
        //END.

        shuffleCards(finalCards); //shuffles the cards to be used in Game

        //TESTING
        if(finalCards.size() == 28)
            System.out.println("TESTING: All 28 Cards are generated!\n");

        displayCards();
        //END.
    }

    /** This method shuffles the cards to be used as final cards in the game.
     *
     * @param cards represents the arraylist of cards to be shuffled.
     */
    public void shuffleCards(ArrayList <ChanceCard> cards){
        Collections.shuffle(cards);
    }

    /** This method discards the card after use of the player.
     *  The card will be remove from the final cards and added to the used cards arraylist.
     *
     * @param card to be discarded.
     */
    public void disCard(ChanceCard card) {
        finalCards.remove(card);
        usedCards.add(card);
    }

    /** For testing purpsoses only.
     *
     */
    public void displayCards(){ //for TESTING

        System.out.println("\tTRACING Game(): List of [FINAL] Cards");
        System.out.println("\tNo. of cards: " + finalCards.size());
        for (int i = 0; i < finalCards.size(); i++) {
            System.out.println("\t[" + (i+1) + "]" + finalCards.get(i) + finalCards.get(i).getDesc());
        }

        if(!usedCards.isEmpty()){
            System.out.println("\tTRACING Game(): List of [USED] Cards");
            System.out.println("\tNo. of cards: " + usedCards.size());
            for (int i = 0; i < usedCards.size(); i++) {
                System.out.println("\t" + (i+1) + "]" + usedCards.get(i));
            }
        }
    }
    // getters -----------

    /** This method returns the list of the finals cards in the game.
     *
     * @return the final cards in the game.
     */
    public ArrayList <ChanceCard> getFinalCards ()
    {
        return finalCards;
    }

    /** This method returns the list of the used cards in the game.
     *
     * @return the used cards in the game.
     */
    public ArrayList <ChanceCard> getUsedCards ()
    {
        return usedCards;
    }

    /** This method returns the name of the game.
     *
     * @return the name of the game.
     */
    public String getName() {
        return name;
    }

    /** This method returns the list of the players playing in the game.
     *
     * @return the players playing in the game.
     */
    public ArrayList <Player> getPlayers() {
        return players;
    }

    /** This method returns the board used in the game.
     *
     * @return the board used in the game.
     */
    public Board getBoard() {
        return board;
    }

    /** This method returns the bank object of the game.
     *
     * @return the bank object f the game.
     */
    public Bank getBank() {
        return bank;
    }

    /** This method returns the current turn of the game.
     *
     * @return the integer value of the current turn.
     */
    public int getTurn() {
        return turn;
    }

    /** This method returns the current player of the game.
     *
     * @return the current player of the game.
     */
    public Player getCurrPlayer () {
        return currPlayer;
    }

    /** This method returns the current tile landed of the current player in the game.
     *
     * @return the current tile landed of the current player.
     */
    public Tile getCurrTile() {
        return currTile;
    }

    // setters -----------

    /** This method sets the current player of the game.
     *
     * @param currPlayer player object to be set as the current player in the game.
     */
    public void setCurrPlayer (Player currPlayer)
    {
        this.currPlayer = currPlayer;
    }

    /** This method sets the current tile of the game.
     *
     * @param currTile is the tile object to be set as the current tile in the game.
     */
    public void setCurrTile (Tile currTile)
    {
        this.currTile = currTile;
    }

    /** This method sets the current turn of the game. It increments to one while the current player is less than the player size.
     * If the turn corresponds to the last player of the game, when incrementing, set the turn back to 0.
     *
     */
    public void setTurn() {
        turn++;
        if (turn < players.size())
            currPlayer = players.get(turn);
        else
        {
            turn = 0;
            currPlayer = players.get(turn);
        }
    }

    /** This method sets the final cards to be used again in the game when all the final cards are used up.
     *
     * @param disCards represents the list of used cards in the game to be added again in the final cards list.
     */
    public void setFinalCards(ArrayList<ChanceCard> disCards) {
        finalCards.addAll(disCards);
    }

    /** This method sets the cash on hand or the current amount of the bank depending on the size of the players playing
     * in the game.
     *
     */
    public void setBankMoney()
    {
        bank.setCurrAmount(2500 * players.size());
    }

}