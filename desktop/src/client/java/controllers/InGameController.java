package client.java.controllers;

import client.java.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class InGameController {

    @FXML
    public BorderPane rootPane;
    // Stacks each layer on top of each other.
    private StackPane layers = new StackPane();
    // 3 board layers.
    private BoardCanvas boardCanvas = new BoardCanvas();
    private PlayerCanvas playerCanvas = new PlayerCanvas();
    // infoPane will be replaced by ipane.
    private Pane infoPane = new Pane();
    private InformationPane ipane = new InformationPane();

    // Players
    private ObservableList<String> playerList = FXCollections.observableArrayList();

    // Networking.
    private final static String IP = "52.48.249.220";
    private final static int PORT = 8000;
    private NetworkConnection connection = new NetworkConnection(IP,PORT, input -> {
        try {
            onUpdateReceived(input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    });

    public void initialize() throws IOException, JSONException {
        setUpBoard();
        try {
            showLobbyWindow();
            connection.startConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeGame() {
        connection.gameEnd();
    }

    // Player lobby code
    public void showLobbyWindow() throws IOException {
        VBox lobbyRoot = new VBox();
        Button startGameButton = new Button("Start Game");
        ListView<String> playerListView = new ListView<>(playerList);

        lobbyRoot.getChildren().add(playerListView);
        lobbyRoot.getChildren().add(startGameButton);

        Scene lobbyScene = new Scene(lobbyRoot, 400,600);
        lobbyScene.getStylesheets().add("/client/resources/css/lobby.css");

        Stage lobbyStage = new Stage();
        lobbyStage.initStyle(StageStyle.UNDECORATED);
        lobbyStage.initModality(Modality.APPLICATION_MODAL);
        lobbyStage.setScene(lobbyScene);

        startGameButton.setOnAction((ActionEvent e) ->
        {
            try {
                JSONObject output = new JSONObject();
                output.put("id", 0);
                output.put("action", "start");
                connection.send(output);
                lobbyStage.close();
            } catch (JSONException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
                                    );
        lobbyStage.show();
    }

    // Called whenever a message/JSON is received form the server.
    public void onUpdateReceived(JSONObject update) throws JSONException {
        Platform.runLater(() -> {
            try {
                System.out.println("Current GameState: " + update.toString());

                int playerTurn = update.getInt("player_turn");
                String actionInfo = update.getString("action_info");

                // Redraw players according to new player positions
                List<Player> plyrs = new ArrayList<>();
                if(update.has("players")){
                    JSONArray playerObjects = update.getJSONArray("players");

                    for(int i=0;i<playerObjects.length();i++){
                        int balance = playerObjects.getJSONObject(i).getInt("balance");
                        int id = playerObjects.getJSONObject(i).getInt("id");
                        int position = playerObjects.getJSONObject(i).getInt("position");
                        String character = playerObjects.getJSONObject(i).getString("character");
                        int fuel = playerObjects.getJSONObject(i).getInt("fuel");
                        plyrs.add(new Player(balance,id,position,Color.WHITE,character,fuel));
                    }
                    playerCanvas.updatePlayers(plyrs);
                }

                // Redraw locations according to new Location information.
                List<Location> locs = new ArrayList<>();
                if(update.has("locations")){
                    JSONArray locationObjects = update.getJSONArray("locations");

                    for(int i=0;i<locationObjects.length();i++){
                        String id = locationObjects.getJSONObject(i).getString("id");
                        int price = locationObjects.getJSONObject(i).getInt("price");
                        int position = locationObjects.getJSONObject(i).getInt("location");
                        int owner = locationObjects.getJSONObject(i).getInt("owner");
                        String c = locationObjects.getJSONObject(i).getString("color");
                        Color color = (Color) Color.class.getField(c).get(null);
                        boolean isMortgaged = locationObjects.getJSONObject(i).getBoolean("is_mortgaged");
                        locs.add(new Location(id,position,price,0,owner, color, isMortgaged));
                    }
                    boardCanvas.updateLocations(locs);
                }

                // Update lobby list According to new players
                ArrayList<String> names = new ArrayList<>();
                for(Player p : plyrs){
                    String n = "Player " + p.getId();
                    names.add(n);
                }
                playerList.setAll(names);


                ipane.updateFeed(actionInfo);
                System.out.println("\n" + actionInfo);

            } catch (JSONException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) { e.printStackTrace(); } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }

    public void setUpBoard() throws IOException, JSONException{
        // Drafting Player stats ------------
        Label playerLabel = new Label("Player 1");
        playerLabel.setTextFill(Color.WHITE);
        playerLabel.setLayoutX(10);
        playerLabel.setLayoutY(10);

        Label balanceLabel = new Label("$2000");
        balanceLabel.setTextFill(Color.WHITE);
        balanceLabel.setLayoutX(10);
        balanceLabel.setLayoutY(30);

        ProgressBar fuelbar  = new ProgressBar(.5);
        fuelbar.setLayoutX(10);
        fuelbar.setLayoutY(50);
        fuelbar.setPrefSize(70,3);

        infoPane.getChildren().add(fuelbar);
        infoPane.getChildren().add(playerLabel);
        infoPane.getChildren().add(balanceLabel);
        // -----------------------------------

        layers.getChildren().add(boardCanvas);
        layers.getChildren().add(playerCanvas);
        layers.getChildren().add(infoPane);
        layers.getChildren().add(ipane);

        rootPane.setCenter(layers);
        boardCanvas.widthProperty().bind(rootPane.widthProperty());
        boardCanvas.heightProperty().bind(rootPane.heightProperty());
        playerCanvas.widthProperty().bind(rootPane.widthProperty());
        playerCanvas.heightProperty().bind(rootPane.heightProperty());

        boardCanvas.draw();
        playerCanvas.draw();
    }


    public void updateInfoPane(){

    }

}


