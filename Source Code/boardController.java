package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static com.sun.javafx.application.PlatformImpl.exit;

public class boardController {
    Game myEmpire = new Game();


    @FXML private Label tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18,
            tile19, tile20, tile21, tile22, tile23, tile24, tile25, tile26, tile27, tile28, tile29, tile30, tile31, tile32;
    @FXML private Button buy, trade, roll, end;
    @FXML private Label player1Label, player2Label, player3Label, player4Label, playerTurn, playerAction;
    @FXML private TextArea player1Properties, player2Properties, player3Properties, player4Properties;
    @FXML private Tooltip tool1, tool2, tool3, tool4, tool5, tool6, tool7, tool8,tool9, tool10, tool11, tool12, tool13, tool14, tool15, tool16,
            tool17, tool18, tool19, tool20, tool21,  tool22, tool23, tool24, tool25, tool26, tool27, tool28, tool29, tool30, tool31, tool32;
    @FXML StackPane piece1, piece2, piece3, piece4;

    @FXML private Label player1Money, player2Money, player3Money, player4Money, bankMoney; // LABELS FOR THE MONEY OF THE BANK AND THE PLAYERS

    @FXML private Pane rankings;
    @FXML private Label rank1, rank2, rank3, rank4, winner;
    @FXML private Button endGame;
    @FXML private ArrayList<Label> ranks;

    @FXML private HBox trading;
    @FXML private ChoiceBox propertiesAvail = new ChoiceBox();

    @FXML private Button use, store;




    private ArrayList <Label> labels;
    private ArrayList <Tooltip> tools;
    private ArrayList <StackPane> pieces;
    private ArrayList <TextArea> textAreas;



    int dice;
    public void initialize ()
    {
        labels = new ArrayList<>();
        tools = new ArrayList<>();
        pieces = new ArrayList<>();
        textAreas = new ArrayList<>();
        ranks = new ArrayList<>();
        rankings.setVisible(false);
        trading.setVisible(false);
        use.setVisible(false);
        store.setVisible(false);
        labels.add(tile1);  labels.add(tile2);  labels.add(tile3);  labels.add(tile4);  labels.add(tile5);  labels.add(tile6);  labels.add(tile7);
        labels.add(tile8);  labels.add(tile9);  labels.add(tile10); labels.add(tile11); labels.add(tile12); labels.add(tile13); labels.add(tile14);
        labels.add(tile15); labels.add(tile16); labels.add(tile17); labels.add(tile18); labels.add(tile19); labels.add(tile20); labels.add(tile21);
        labels.add(tile22); labels.add(tile23); labels.add(tile24); labels.add(tile25); labels.add(tile26); labels.add(tile27); labels.add(tile28);
        labels.add(tile29); labels.add(tile30); labels.add(tile31); labels.add(tile32);

        tools.add(tool1);   tools.add(tool2);   tools.add(tool3);    tools.add(tool4);  tools.add(tool5);   tools.add(tool6);   tools.add(tool7);    tools.add(tool8);
        tools.add(tool9);   tools.add(tool10);   tools.add(tool11);    tools.add(tool12);    tools.add(tool13);   tools.add(tool14);   tools.add(tool15);    tools.add(tool16);
        tools.add(tool17);   tools.add(tool18);   tools.add(tool19);    tools.add(tool20); tools.add(tool21);   tools.add(tool22);   tools.add(tool23);    tools.add(tool24);
        tools.add(tool25);   tools.add(tool26);   tools.add(tool27);    tools.add(tool28); tools.add(tool29);   tools.add(tool30);   tools.add(tool31);    tools.add(tool32);
    }

    public void passData(ArrayList <String> tiles, ArrayList <Player> players)
    {
        int i;
        for (i = 0; i < tiles.size(); i++)
        {
            myEmpire.getBoard().addTile(tiles.get(i));
        }
        myEmpire.getPlayers().addAll(players);

        setScreen();
        setStart();
    }

    public void setTiles()
    {
        int i;
        for (i = 0; i < 32; i++)
        {
            labels.get(i).setText(myEmpire.getBoard().getTiles().get(i).displayTileName());

            /* SET TILE IMAGE ----- START */
            if (myEmpire.getBoard().getTiles().get(i) instanceof Property)
            {
                if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Red")
                {
                    labels.get(i).getStyleClass().add("red-nohouse");
                }
                else if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Green")
                {
                    labels.get(i).getStyleClass().add("green-nohouse");
                }
                else if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Violet")
                {
                    labels.get(i).getStyleClass().add("violet-nohouse");
                }
                else if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Blue")
                {
                    labels.get(i).getStyleClass().add("blue-nohouse");
                }
                else if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Gray")
                {
                    labels.get(i).getStyleClass().add("gray-nohouse");
                }
                else if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Orange")
                {
                    labels.get(i).getStyleClass().add("orange-nohouse");
                }
                else if (((Property) myEmpire.getBoard().getTiles().get(i)).getColor() == "Yellow")
                {
                    labels.get(i).getStyleClass().add("yellow-nohouse");
                }
            }
            else if (myEmpire.getBoard().getTiles().get(i) instanceof ChanceSpace)
            {
                labels.get(i).getStyleClass().add("tile-chance");
            }
            else if (myEmpire.getBoard().getTiles().get(i) instanceof Income)
            {
                labels.get(i).getStyleClass().add("tile-income");
            }
            else if (myEmpire.getBoard().getTiles().get(i) instanceof Luxury)
            {
                labels.get(i).getStyleClass().add("tile-luxury");
            }
            else if (myEmpire.getBoard().getTiles().get(i) instanceof FreeParking)
            {
                labels.get(i).getStyleClass().add("tile-freeparking");
            }
            else if (myEmpire.getBoard().getTiles().get(i) instanceof Jail)
            {
                labels.get(i).getStyleClass().add("tile-jail");
            }
            else
            {
                labels.get(i).getStyleClass().add("tile-plain");
            }

            /* SET TILE IMAGE ----- END */
        }
    }

    public void setScreen() {
        int i;

        myEmpire.setBankMoney();
        if (myEmpire.getPlayers().size() == 2)
        {
            // SETTING OF THE LABEL OF THE PLAYER NAME IN THE PROPERTIES AREA.
            player1Label.setText(myEmpire.getPlayers().get(0).getName());
            player2Label.setText(myEmpire.getPlayers().get(1).getName());
            // ADDING THE TEXT AREA TO AN ARRAYLIST
            textAreas.add(player1Properties);
            textAreas.add(player2Properties);
            // SETTING OF THE LABEL FOR THE PLAYER MONEY
            updateMoneyLabel();
            player3Money.setVisible(false);
            player4Money.setVisible(false);
            // SETTING OF THE THE PLAYER NAME AND PROPERTIES BOX
            player3Label.setVisible(false);
            player3Properties.setVisible(false);
            player4Label.setVisible(false);
            player4Properties.setVisible(false);
            // SETTING OF THE PIECES OF THE PLAYERS
            piece3.setVisible(false);
            piece4.setVisible(false);
            // ADD PIECES TO ARRAYLIST
            pieces.add(piece1); pieces.add(piece2);
            // ADD RANKS
            ranks.add(rank1);   ranks.add(rank2);
            rank3.setVisible(false);    rank4.setVisible(false);
        }
        else if (myEmpire.getPlayers().size() == 3)
        {
            // SETTING OF THE LABEL OF THE PLAYER NAME IN THE PROPERTIES AREA.
            player1Label.setText(myEmpire.getPlayers().get(0).getName());
            player2Label.setText(myEmpire.getPlayers().get(1).getName());
            player3Label.setText(myEmpire.getPlayers().get(2).getName());
            // ADDING THE TEXT AREA TO AN ARRAYLIST
            textAreas.add(player1Properties);
            textAreas.add(player2Properties);
            textAreas.add(player3Properties);
            // SETTING OF THE THE PLAYER NAME AND PROPERTIES BOX
            updateMoneyLabel();
            player4Money.setVisible(false);
            player4Label.setVisible(false);
            player4Properties.setVisible(false);
            // SETTING OF THE PIECES OF THE PLAYERS
            piece4.setVisible(false);
            // ADD PIECES TO ARRAYLIST
            pieces.add(piece1); pieces.add(piece2); pieces.add(piece3);
            // ADD RANKS
            ranks.add(rank1);   ranks.add(rank2);   ranks.add(rank3);
            rank4.setVisible(false);
        }
        else if (myEmpire.getPlayers().size() == 4)
        {
            // SETTING OF THE LABEL OF THE PLAYER NAME IN THE PROPERTIES AREA.
            player1Label.setText(myEmpire.getPlayers().get(0).getName());
            player2Label.setText(myEmpire.getPlayers().get(1).getName());
            player3Label.setText(myEmpire.getPlayers().get(2).getName());
            player4Label.setText(myEmpire.getPlayers().get(3).getName());
            // ADDING THE TEXT AREA TO AN ARRAYLIST
            textAreas.add(player1Properties);
            textAreas.add(player2Properties);
            textAreas.add(player3Properties);
            textAreas.add(player4Properties);
            // SETTING OF THE THE PLAYER NAME AND PROPERTIES BOX
            updateMoneyLabel();
            // ADD PIECES TO ARRAYLIST
            pieces.add(piece1); pieces.add(piece2); pieces.add(piece3); pieces.add(piece4);
            // ADD RANKS
            ranks.add(rank1);   ranks.add(rank2);   ranks.add(rank3);   ranks.add(rank4);
        }

        playerTurn.setText("[PLAYER 1] " + myEmpire.getPlayers().get(0).getName() + "'s " + "TURN");


        /* PRINTING -- CHECKING*/
        System.out.println("SECOND SCENE: \n\tPLAYERS:");
        for (i = 0; i < myEmpire.getPlayers().size(); i++) {
            myEmpire.getPlayers().get(i).displayPlayerStatus();
        }
        System.out.print("\n\tTILES:\t");
        for (i = 0; i < myEmpire.getBoard().getTiles().size(); i++) {
            System.out.print(myEmpire.getBoard().getTiles().get(i).getName() + ", ");
        }
        System.out.println("\n\t" + myEmpire.getBank());
        /* PRINTING */

        setTiles();
        updateTools();
    }

    public void setStart() {
        myEmpire.setCurrPlayer(myEmpire.getPlayers().get(0));

        /* SETTING OF FOOT TRAFFIC LIMIT ----- START */
        for (int i = 0; i < myEmpire.getBoard().getTiles().size(); i++)
        {
            if (myEmpire.getBoard().getTiles().get(i) instanceof Property)
                ((Property) myEmpire.getBoard().getTiles().get(i)).setFootTrafficLimit(myEmpire.getPlayers().size());
        }
        /* SETTING OF FOOT TRAFFIC LIMIT ----- START */

        buy.setDisable(true);
        trade.setDisable(true);
        roll.setDisable(false);
        end.setDisable(true);
        playerAction.setWrapText(true);
    }

    public void movePiece() {
        int i;
        /* Moving of piece ----- START */
        for (i = 0; i < pieces.size(); i++)
        {
            if (myEmpire.getPlayers().get(i) == myEmpire.getCurrPlayer()) {
                if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) < 8 && myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) > 0)
                {
                    pieces.get(i).setTranslateY(0);
                    pieces.get(i).setTranslateX(-88 * myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()));
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) == 8)
                {
                    pieces.get(i).setTranslateX(-88 * 8);
                    pieces.get(i).setTranslateY(0);
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) > 8 && myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) < 16)
                {
                    pieces.get(i).setTranslateX(-88 * 8);
                    pieces.get(i).setTranslateY((-90 * (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) - 8)));
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) == 16)
                {
                    pieces.get(i).setTranslateX(-88 * 8);
                    pieces.get(i).setTranslateY(-90 * 8);
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) > 16 && myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) < 24)
                {
                    pieces.get(i).setTranslateX(-88 * (8 - (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) - 16)));
                    pieces.get(i).setTranslateY(-90 * 8);
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) == 24)
                {
                    pieces.get(i).setTranslateX(0);
                    pieces.get(i).setTranslateY(-90 * 8);
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) > 24 && myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) < 32)
                {
                    pieces.get(i).setTranslateX(0);
                    pieces.get(i).setTranslateY((-90 * (8 - (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) - 24))));
                }
                else if (myEmpire.getBoard().getTiles().indexOf(myEmpire.getCurrTile()) == 0)
                {
                    pieces.get(i).setTranslateX(0);
                    pieces.get(i).setTranslateY(0);
                }
            }
        }
        /* Moving of piece ----- END */
    }

    public void propInstance () {
        if (myEmpire.getCurrTile() instanceof Property)
        {
            if (((Property) myEmpire.getCurrTile()).getIsOwned()) {
                ((Property) myEmpire.getCurrTile()).setFootCount(); //CHECK
                if (((Property) myEmpire.getCurrTile()).getOwner() == myEmpire.getCurrPlayer()) {
                    int developedHouses = ((Property) myEmpire.getCurrTile()).getNumHouses(); //FOR playerAction DISPLAY PURPOSES. [IF SUCCESSFULLY DEVELOPED.
                    playerAction.setText(playerAction.getText() + " You landed on your own property! ");
                    if (((Property) myEmpire.getCurrTile()).getNumHouses() < 5)
                    {
                        System.out.println("TRACING PUMASOK LESS THAN 5");
                        myEmpire.getCurrPlayer().develop((Property)myEmpire.getCurrTile(), myEmpire.getBank()); // Call develop method.
                        if (((Property) myEmpire.getCurrTile()).getNumHouses() > developedHouses) //If successfully develop, update the playerAction display and the properties list.
                        {
                            updateTile();
                            playerAction.setText(playerAction.getText() + myEmpire.getCurrTile().getName() + " has been developed!");
                            textAreas.get(myEmpire.getTurn()).setText("" + myEmpire.getCurrPlayer());
                        }
                    }
                    /*CHECKING IF END GAME */
                    if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                        openThirdScene();
                    } /*CHECKING IF END GAME */

                    buy.setDisable(true);
                    trade.setDisable(true);
                    roll.setDisable(true);
                    end.setDisable(false);
                }
                else {
                    if (myEmpire.getCurrPlayer().getOwnedProperties().size() != 0) {
                        myEmpire.getCurrPlayer().payRent((Property) myEmpire.getCurrTile());
                        buy.setDisable(true);
                        trade.setDisable(false);
                        roll.setDisable(true);
                        end.setDisable(false);
                        playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " pays rent of $" + ((Property) myEmpire.getCurrTile()).computeRent() + " to " + ((Property) myEmpire.getCurrTile()).getOwner().getName());
                        System.out.println(myEmpire.getCurrPlayer());
                        System.out.println(myEmpire.getCurrPlayer().getOwnedProperties());
                        System.out.println(((Property) myEmpire.getCurrTile()).getOwner());
                    }
                    else
                    {
                        myEmpire.getCurrPlayer().payRent((Property) myEmpire.getCurrTile());
                        buy.setDisable(true);
                        trade.setDisable(true);
                        roll.setDisable(true);
                        end.setDisable(false);
                        /*CHECKING IF END GAME */
                        if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                            openThirdScene();
                        } /*CHECKING IF END GAME */
                        playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " pays rent of $" + ((Property) myEmpire.getCurrTile()).computeRent() + " to " + ((Property) myEmpire.getCurrTile()).getOwner().getName());
                    }
                }
            }
            else {
                buy.setDisable(false);
                trade.setDisable(true);
                roll.setDisable(true);
                end.setDisable(false);
            }
            updateTools();
        }
    }

    public void startInstance() {
        if (myEmpire.getCurrPlayer().getHasPassed() == true)
        {
            ((Start) myEmpire.getBoard().getTiles().get(0)).landAction(myEmpire.getCurrPlayer(), myEmpire.getBank());
            myEmpire.getCurrPlayer().setHasPassed(false);
            /*CHECKING IF END GAME */
            if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                openThirdScene();
            } /*CHECKING IF END GAME */
        }
    }

    public void utilInstance() {
        if (myEmpire.getCurrTile() instanceof Utility)
        { // AYUSIN MO TO
            if (((Utility) myEmpire.getCurrTile()).getIsOwned())
            {
                if (myEmpire.getCurrPlayer() != ((Utility) myEmpire.getCurrTile()).getOwner()) {
                    double rent = ((Utility) myEmpire.getCurrTile()).computeRent(((Utility) myEmpire.getCurrTile()).getOwner().getOwnedUtilities().size(), dice);
                    myEmpire.getCurrPlayer().payRent((Utility) myEmpire.getCurrTile(), dice);
                    /*CHECKING IF END GAME */
                    if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                        openThirdScene();
                    } /*CHECKING IF END GAME */
                    buy.setDisable(true);
                    trade.setDisable(true);
                    roll.setDisable(true);
                    end.setDisable(false);
                    playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " paid an amount of $" + rent + " to " + ((Utility) myEmpire.getCurrTile()).getOwner().getName());
                }
                else
                {
                    buy.setDisable(true);
                    trade.setDisable(true);
                    roll.setDisable(true);
                    end.setDisable(false);
                    playerAction.setText((playerAction.getText() + " You already own this Utility!"));
                }
            }
            else
            {
                System.out.println("4");
                buy.setDisable(false);
                trade.setDisable(true);
                roll.setDisable(true);
                end.setDisable(false);
            }
        }
    }

    public void railroadInstance () {
        if (myEmpire.getCurrTile() instanceof Railroad)
        { // AYUSIN MO TO
            if (((Railroad) myEmpire.getCurrTile()).getIsOwned())
            {
                if (myEmpire.getCurrPlayer() != ((Railroad) myEmpire.getCurrTile()).getOwner()) {
                    double rent = ((Railroad) myEmpire.getCurrTile()).computeRent(((Railroad) myEmpire.getCurrTile()).getOwner().getOwnedRailroads().size(), dice);
                    myEmpire.getCurrPlayer().payRent((Railroad) myEmpire.getCurrTile(), dice);
                    /*CHECKING IF END GAME */
                    if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                        openThirdScene();
                    } /*CHECKING IF END GAME */
                    buy.setDisable(true);
                    trade.setDisable(true);
                    roll.setDisable(true);
                    end.setDisable(false);
                    playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " paid an amount of $" + rent + " to " + ((Railroad) myEmpire.getCurrTile()).getOwner().getName());
                }
                else
                {
                    buy.setDisable(true);
                    trade.setDisable(true);
                    roll.setDisable(true);
                    end.setDisable(false);
                    playerAction.setText((playerAction.getText() + " You already own this railroad!"));
                }
            }
            else
            {
                buy.setDisable(false);
                trade.setDisable(true);
                roll.setDisable(true);
                end.setDisable(false);
            }
        }
    }

    public void rollDice()
    {
        if (myEmpire.getCurrPlayer().getInJail() == true)
        {
            ((Jail) myEmpire.getBoard().getTiles().get(24)).landAction(myEmpire.getCurrPlayer(), myEmpire.getBank());
            myEmpire.getCurrPlayer().setInJail(false);
            /*CHECKING IF END GAME */
            if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                openThirdScene();
            } /*CHECKING IF END GAME */
            playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " paid an amount of $50. You are now out of jail! ");
        }

        dice = myEmpire.rollDice();
        playerAction.setText(myEmpire.getCurrPlayer().getName() + " rolled the dice! Value of the dice is " + dice + ". You landed on tile " + myEmpire.getCurrTile().getName() + ".");
        playerAction.setWrapText(true);

        /* MOVE PIECE */
        movePiece();
        /* MOVE PIECE */



        System.out.println("1" + myEmpire.getCurrPlayer().getName());
        /* Determine what tile landed on. ----- START */

        /* START CHECK */
        startInstance();
        /* START CHECK */

        System.out.println("2" + myEmpire.getCurrPlayer().getName());

        /* PROPERTY CHECK */
        propInstance();
        /* PROPERTY CHECK */





        if (myEmpire.getCurrTile() instanceof Jail || myEmpire.getCurrTile() instanceof CommunityService || myEmpire.getCurrTile() instanceof Start || myEmpire.getCurrTile() instanceof FreeParking)
        {
            buy.setDisable(true);
            trade.setDisable(true);
            roll.setDisable(false);
            end.setDisable(true);
            if (myEmpire.getCurrTile() instanceof Start) {
                playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " collected an amount of $200 from the bank.");
                myEmpire.setTurn();
                playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
            }

            else if (myEmpire.getCurrTile() instanceof CommunityService)
            {
                ((CommunityService) myEmpire.getBoard().getTiles().get(myEmpire.getCurrPlayer().getCurrTile())).landAction(myEmpire.getCurrPlayer(), myEmpire.getBank());
                /*CHECKING IF END GAME */
                if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                    openThirdScene();
                } /*CHECKING IF END GAME */
                playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " donates $50 to the bank.");
                myEmpire.setTurn();
                playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
            }
            else if (myEmpire.getCurrTile() instanceof FreeParking)
            {
                ((FreeParking) myEmpire.getBoard().getTiles().get(myEmpire.getCurrPlayer().getCurrTile())).landAction(myEmpire.getCurrPlayer(), myEmpire.getBank());
                playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " waits for his next turn.");
                myEmpire.setTurn();
                playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
            }
            else if (myEmpire.getCurrTile() instanceof Jail)
            {
                myEmpire.getCurrPlayer().setInJail(true);
                playerAction.setText(playerAction.getText() + " You will pay $50 on your next turn.");
                if (myEmpire.getCurrPlayer().getStoredCards().size() != 0)
                {
                    use.setVisible(true);
                    store.setVisible(true);
                }
                else
                {
                    myEmpire.setTurn();
                    playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
                }
            }

        }

        else if (myEmpire.getCurrTile() instanceof Tax)
        {
            double taxComputed = ((Tax) myEmpire.getCurrTile()).computeTax(myEmpire.getCurrPlayer().getCash());
            myEmpire.getCurrPlayer().payTax(taxComputed, myEmpire.getBank());
            /*CHECKING IF END GAME */
            if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
                openThirdScene();
            } /*CHECKING IF END GAME */
            playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " paid an amount of $" + taxComputed + " to the bank.");
            myEmpire.setTurn();
            playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
        }

        railroadInstance();
        utilInstance();

        if (myEmpire.getCurrTile() instanceof ChanceSpace)
        {
            if (myEmpire.getFinalCards().size() == 0)
            {
                myEmpire.setFinalCards(myEmpire.getUsedCards());
                myEmpire.shuffleCards(myEmpire.getFinalCards());
                myEmpire.getUsedCards().clear();
            }

            myEmpire.displayCards();
            Random rand = new Random ();
            int randCard = rand.nextInt(29);

            ChanceCard givenCard = myEmpire.getFinalCards().get(randCard);
            System.out.println("" + givenCard.getDesc());
            System.out.println("" + givenCard.getCardNo());

            playerAction.setText(playerAction.getText() + " You got CARD " + givenCard.getDesc());
            System.out.println("\t\tYou got CARD [" + givenCard +"]");
            System.out.println("\t\tYou got CARD [" + givenCard.getCardNo() +"]");
            System.out.println("\t\tYou got CARD [" + givenCard.getDesc() +"]");
            if (givenCard.getCardNo() == 1) {
                use.setVisible(true);
                store.setVisible(true);
                myEmpire.disCard(givenCard);
            }
            else if (givenCard.getCardNo() == 2 || givenCard.getCardNo() == 3 || givenCard.getCardNo() == 4 || givenCard.getCardNo() == 7 ||  givenCard.getCardNo() == 10 ||givenCard.getCardNo() == 11) {
                myEmpire.getCurrPlayer().useCard(givenCard, myEmpire.getBoard().getTiles(), myEmpire.getBank(), myEmpire, dice);
                movePiece();
                myEmpire.disCard(givenCard);
                if (givenCard.getCardNo() == 7 || givenCard.getCardNo() == 10 || (givenCard.getCardNo() == 3 && ((Utility)myEmpire.getCurrTile()).getIsOwned() == true))
                {
                    myEmpire.setTurn();
                    if (givenCard.getCardNo() == 10)
                    {
                        playerAction.setText(playerAction.getText() + " Player pays $50 on his next turn.");
                    }
                }
            }
            else {
                myEmpire.getCurrPlayer().useCard(givenCard, myEmpire.getBoard().getTiles(), myEmpire.getBank(), myEmpire, dice);
                myEmpire.disCard(givenCard);
            }

            propInstance();
            startInstance();
            railroadInstance();
            if (myEmpire.getCurrTile() instanceof Utility){
               if (((Utility) myEmpire.getCurrTile()).getIsOwned() == false) {
                utilInstance();
               }
            }

            playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
        }

        updateMoneyLabel();
        /*CHECKING IF END GAME */
        if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
            openThirdScene();
        } /*CHECKING IF END GAME */

        /* Determine what tile landed on. ----- START */
    }


    public void buy () {
        if (myEmpire.getCurrTile() instanceof Property) {
            myEmpire.getCurrPlayer().purchase((Property) myEmpire.getCurrTile(), myEmpire.getBank());
            playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " bought the property " + myEmpire.getCurrTile().getName() + " for a price of $" + ((Property) myEmpire.getCurrTile()).getPrice());
        }
        else if (myEmpire.getCurrTile() instanceof Railroad) {
            myEmpire.getCurrPlayer().purchase((Railroad) myEmpire.getCurrTile(), myEmpire.getBank());
            playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " bought the railroad " + myEmpire.getCurrTile().getName() + " for a price of $" + ((Railroad) myEmpire.getCurrTile()).getPrice());
        }
        else if (myEmpire.getCurrTile() instanceof Utility) {
            myEmpire.getCurrPlayer().purchase((Utility) myEmpire.getCurrTile(), myEmpire.getBank());
            playerAction.setText(playerAction.getText() + " " + myEmpire.getCurrPlayer().getName() + " bought the utility " + myEmpire.getCurrTile().getName() + " for a price of $" + ((Utility) myEmpire.getCurrTile()).getPrice());
        }

        updateTools();
        updateMoneyLabel();
        buy.setDisable(true);
        trade.setDisable(true);
        roll.setDisable(false);
        end.setDisable(true);

        /* UPDATING PROPERTIES LIST ----- START */
        for (int i = 0; i < myEmpire.getPlayers().size(); i++)
        {
            if (myEmpire.getTurn() == i)
            {
                textAreas.get(i).setText("" + myEmpire.getCurrPlayer());
            }
        }
        /* UPDATING PROPERTIES LIST ----- END */

        /*CHECKING IF END GAME */
        if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
            openThirdScene();
        } /*CHECKING IF END GAME */
        myEmpire.setTurn();
        playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
    }

    public void doNothing () {
        /*CHECKING IF END GAME */
        if (myEmpire.isOver(myEmpire.getCurrPlayer(), myEmpire.getBank())) {
            openThirdScene();
        } /*CHECKING IF END GAME */
        myEmpire.setTurn();
        playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
        playerAction.setText(myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
        playerAction.setWrapText(true);
        buy.setDisable(true);
        trade.setDisable(true);
        roll.setDisable(false);
        end.setDisable(true);
    }

    public void trade () {
        trade.setDisable(true);
        end.setDisable(true);
        ObservableList<String> list = FXCollections.observableArrayList();
        list.removeAll(list);
        for (int i = 0; i < myEmpire.getCurrPlayer().getOwnedProperties().size(); i++) {
            list.add(myEmpire.getCurrPlayer().getOwnedProperties().get(i).getName());
            System.out.println(myEmpire.getCurrPlayer().getOwnedProperties().get(i).getName());
        }
        System.out.println(list);
        propertiesAvail.setItems(list);
        trading.setVisible(true);
    }

    public void acceptTrade() {
        for (int i = 0; i < myEmpire.getCurrPlayer().getOwnedProperties().size(); i++) {
            if (myEmpire.getCurrPlayer().getOwnedProperties().get(i).getName() == propertiesAvail.getValue()) {
                myEmpire.getCurrPlayer().trade(myEmpire.getCurrPlayer().getOwnedProperties().get(i), (Property)myEmpire.getCurrTile(), ((Property) myEmpire.getCurrTile()).getOwner());
                break;
            }
        }

        /* UPDATING PROPERTIES LIST ----- START */
        for (int i = 0; i < myEmpire.getPlayers().size(); i++)
        {
            textAreas.get(i).setText("" + myEmpire.getPlayers().get(i));
        }
        /* UPDATING PROPERTIES LIST ----- END */

        playerAction.setText(playerAction.getText() + " TRADE REQUEST ACCEPTED... SUCCESSFUL TRADE!");
        updateTools();
        trading.setVisible(false);
        end.setDisable(false);
    }

    public void rejectTrade () {
        end.setDisable(false);
        trading.setVisible(false);
        playerAction.setText(playerAction.getText() + " TRADE REQUEST REJECTED!");
    }

    public void useCard () {
        ChanceCard c = new ChanceCard(1, 1);
        myEmpire.getCurrPlayer().useCard(c, myEmpire.getBoard().getTiles(), myEmpire.getBank(), myEmpire, dice);
        use.setVisible(false);
        store.setVisible(false);
        myEmpire.setTurn();
        playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
        playerAction.setText(myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
    }

    public void storeCard () {
        ChanceCard c = new ChanceCard(1, 1);
        myEmpire.getCurrPlayer().storeCard(c);
        use.setVisible(false);
        store.setVisible(false);
        myEmpire.setTurn();
        playerTurn.setText("[PLAYER " + (myEmpire.getTurn() + 1) + "] " + myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
        playerAction.setText(myEmpire.getCurrPlayer().getName() + "'s " + "TURN");
    }

    public void updateMoneyLabel () {
        int i;
        bankMoney.setText("Bank: $" + myEmpire.getBank().getCurrAmount());
        for (i = 0; i < myEmpire.getPlayers().size(); i++)
        {
            if (i == 0)
                player1Money.setText(myEmpire.getPlayers().get(0).getName() + ": $" + myEmpire.getPlayers().get(0).getCash());
            else if (i == 1)
                player2Money.setText(myEmpire.getPlayers().get(1).getName() + ": $" + myEmpire.getPlayers().get(1).getCash());
            else if (i == 2)
                player3Money.setText(myEmpire.getPlayers().get(2).getName() + ": $" + myEmpire.getPlayers().get(2).getCash());
            else if (i == 3)
                player3Money.setText(myEmpire.getPlayers().get(3).getName() + ": $" + myEmpire.getPlayers().get(3).getCash());
        }
    }

    public void updateTools () {
        int i;
        for (i = 0; i < myEmpire.getBoard().getTiles().size(); i++) {
            if (myEmpire.getBoard().getTiles().get(i) instanceof Property) {
                tools.get(i).setText("" + ((Property) myEmpire.getBoard().getTiles().get(i)));
            }
            else
                tools.get(i).setText("");
        }
    }

    public void updateTile () {
        if (((Property) myEmpire.getCurrTile()).getNumHouses() == 1) {
            if (((Property) myEmpire.getCurrTile()).getColor() == "Red") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("red-onehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Gray") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("gray-onehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Green") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("green-onehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Violet") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("violet-onehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Blue") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("blue-onehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Orange") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("orange-onehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Yellow") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("yellow-onehouse");
            }
        }
        else if (((Property) myEmpire.getCurrTile()).getNumHouses() == 2) {
            if (((Property) myEmpire.getCurrTile()).getColor() == "Red") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("red-twohouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Gray") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("gray-twohouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Green") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("green-twohouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Violet") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("violet-twohouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Blue") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("blue-twohouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Orange") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("orange-twohouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Yellow") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("yellow-twohouse");
            }
        }
        else if (((Property) myEmpire.getCurrTile()).getNumHouses() == 3) {
            if (((Property) myEmpire.getCurrTile()).getColor() == "Red") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("red-threehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Gray") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("gray-threehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Green") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("green-threehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Violet") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("violet-threehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Blue") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("blue-threehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Orange") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("orange-threehouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Yellow") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("yellow-threehouse");
            }
        }
        else if (((Property) myEmpire.getCurrTile()).getNumHouses() == 4) {
            if (((Property) myEmpire.getCurrTile()).getColor() == "Red") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("red-fourhouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Gray") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("gray-fourhouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Green") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("green-fourhouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Violet") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("violet-fourhouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Blue") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("blue-fourhouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Orange") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("orange-fourhouse");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Yellow") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("yellow-fourhouse");
            }
        }
        if (((Property) myEmpire.getCurrTile()).getNumHouses() == 5) {
            if (((Property) myEmpire.getCurrTile()).getColor() == "Red") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("red-hotel");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Gray") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("gray-hotel");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Green") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("green-hotel");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Violet") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("violet-hotel");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Blue") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("blue-hotel");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Orange") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("orange-hotel");
            }
            else if (((Property) myEmpire.getCurrTile()).getColor() == "Yellow") {
                labels.get(myEmpire.getCurrPlayer().getCurrTile()).getStyleClass().add("yellow-hotel");
            }
        }
    }

    public void openThirdScene() {
        myEmpire.computeRankings();
        for (int i = 0; i < myEmpire.getPlayers().size(); i++)
        {
            if (myEmpire.getPlayers().get(i).isBankrupt())
            {
                ranks.get(myEmpire.getPlayers().size() - 1).setText("BANKRUPT: " + myEmpire.getPlayers().get(i));
            }
            ranks.get(i).setText("RANK " + (i + 1) + ": " + myEmpire.getPlayers().get(i).getName());
        }

        winner.setText("CONGRATULATIONS " + myEmpire.getPlayers().get(0).getName() + "!");

        rankings.setVisible(true);
    }

    public void endGame()
    {
        exit();
    }
}