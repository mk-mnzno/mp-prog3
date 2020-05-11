package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class playerController {

    @FXML
    private Button twoPlayers;
    @FXML
    private Button threePlayers;
    @FXML
    private Button fourPlayers;
    @FXML
    private TextField playerOneName;
    @FXML
    private TextField playerTwoName;
    @FXML
    private TextField playerThreeName;
    @FXML
    private TextField playerFourName;
    @FXML
    private Label nameLabel;


    @FXML
    private ListView<String> tilesList;
    @FXML
    private TextArea tilesFinal;
    @FXML
    private Label tilesCount;
    @FXML
    private Button nextButton;
    @FXML
    private Button startButton;


    int j = 0;
    int numOfPlayers = 0;
    ObservableList<Integer> playerCount = FXCollections.observableArrayList(2, 3, 4);

    Player p;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<String> tiles = new ArrayList<>();


    public void initialize() {
        loadTiles();
        playerOneName.setPromptText("name (required)");
        playerTwoName.setPromptText("name (required)");
        playerThreeName.setPromptText("name (required)");
        playerFourName.setPromptText("name (required)");
        playerOneName.setDisable(true);
        playerTwoName.setDisable(true);
        playerThreeName.setDisable(true);
        playerFourName.setDisable(true);
        if (j == 0) {
            tilesFinal.appendText("Space 1: START\n");
            tiles.add("START");
            j++;
        }
        startButton.setVisible(false);
    }

    private void loadTiles() {
        tilesList.getItems().addAll("Almond Drive", "Kasoy Street", "Rodeo Drive", "Orange Street", "Ventura Street",
                "Juan Luna", "Ylaya", "J. Abad Santos", "Madison", "Annapolis", "Connecticut", "Bougainvilla", "Dama de Noche", "Acacia",
                "Solar Street", "Galaxy Street", "9th Street", "5th Avenue", "Chance Space", "Chance Space", "Chance Space",
                "North", "South", "Metro", "Electric", "Water", "Luxury Tax", "Income Tax");

    }

    public void updatelabelTilesCount() {
        if (j == 7 || j == 15 || j == 23)
            tilesCount.setText("Space " + (j + 3));
        else if (j < 31)
            tilesCount.setText("Space " + (j + 2));
        else
            tilesCount.setText("");
    }

    public void tilesNext() {
        String textAreaString = tilesList.getSelectionModel().getSelectedItem();

        if (tilesList.getItems().isEmpty() && numOfPlayers != 0) {
            nextButton.setVisible(false);
            startButton.setVisible(true);
        }
        if (textAreaString != null) {
            updatelabelTilesCount();
            tilesList.getItems().remove(textAreaString);
            tilesFinal.appendText("Space " + (j + 1) + ": " + textAreaString + "\n");
            tiles.add(textAreaString);
            if (j == 7) {
                textAreaString = "Community Service";
                tilesFinal.appendText("Space 9: " + textAreaString + "\n");
                tiles.add(textAreaString);
                j++;
            } else if (j == 15) {
                textAreaString = "Free Parking";
                tilesFinal.appendText("Space 17: " + textAreaString + "\n");
                tiles.add(textAreaString);
                j++;
            } else if (j == 23) {
                textAreaString = "JAIL";
                tilesFinal.appendText("Space 25: " + textAreaString + "\n");
                tiles.add(textAreaString);
                j++;
            }
            j++;
        }
    }

    public void generatePlayersName(ActionEvent e) {
        nameLabel.setVisible(true);
        if (e.getSource() == twoPlayers) {
            numOfPlayers = 2;
            playerOneName.setDisable(false);
            playerTwoName.setDisable(false);
            playerThreeName.setDisable(true);
            playerFourName.setDisable(true);
        } else if (e.getSource() == threePlayers) {
            numOfPlayers = 3;
            playerOneName.setDisable(false);
            playerTwoName.setDisable(false);
            playerThreeName.setDisable(false);
            playerFourName.setDisable(true);
        }
        if (e.getSource() == fourPlayers) {
            numOfPlayers = 4;
            playerOneName.setDisable(false);
            playerTwoName.setDisable(false);
            playerThreeName.setDisable(false);
            playerFourName.setDisable(false);
        }
        if (tilesList.getItems().isEmpty()) {
            startButton.setVisible(true);
            nextButton.setVisible(false);
        }
    }

    public void startGame(ActionEvent event) {
        int k;
        try {
            if (numOfPlayers == 2) {
                if (!playerOneName.getText().isEmpty() && !playerTwoName.getText().isEmpty()) {
                    p = new Player(playerOneName.getText());
                    players.add(p);
                    p = new Player (playerTwoName.getText());
                    players.add(p);
                    openSecondScene(event);
                }
            } else if (numOfPlayers == 3) {
                if (!playerOneName.getText().isEmpty() && !playerTwoName.getText().isEmpty() && !playerThreeName.getText().isEmpty()) {
                    p = new Player(playerOneName.getText());
                    players.add(p);
                    p = new Player (playerTwoName.getText());
                    players.add(p);
                    p = new Player (playerThreeName.getText());
                    players.add(p);
                    openSecondScene(event);
                }
            } else if (numOfPlayers == 4) {
                if (!playerOneName.getText().isEmpty() && !playerTwoName.getText().isEmpty() && !playerThreeName.getText().isEmpty()
                        && !playerFourName.getText().isEmpty()) {
                    p = new Player(playerOneName.getText());
                    players.add(p);
                    p = new Player (playerTwoName.getText());
                    players.add(p);
                    p = new Player (playerThreeName.getText());
                    players.add(p);
                    p = new Player (playerFourName.getText());
                    players.add(p);
                    openSecondScene(event);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Cannot open second scene!");
        }
    }

    public void openSecondScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("boardView.fxml"));
            Parent root = loader.load();
            boardController secController = loader.getController();
            secController.passData(tiles, players);
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            Scene scene = new Scene (root);
            stage.setScene(scene);
            scene.getStylesheets().add("/sample/images.css");
            stage.show();
            stage.setResizable(false);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Cannot open second scene!!!");
        }
    }
}