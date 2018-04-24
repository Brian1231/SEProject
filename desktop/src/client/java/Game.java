package client.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static ArrayList<Location> locations = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();
    public static VillainGang villainGang = new VillainGang();
    public static ObservableList<Player> observablePlayers = FXCollections.observableList(players);
    public static Boolean gameStarted = false;
    public static int playerTurn;
    public static boolean locationsSet = false;
    public static PlayerCanvas pCanvas;

    // Player Methods
    // Updates players from server.
    public static void updatePlayers(List<Player> plyrs, String action){
        for(Player p : plyrs){
            if(!observablePlayers.contains(p)) {
                addPlayer(p);
            }
            else{
                updatePlayerData(p,action);
            }
        }
        // Not working right now
        // for(Player ply : players){
        //    if(!plyrs.contains(ply)){
        //        removePlayer(ply);
        //    }
        // }
    }

    // Adds new player to player list.
    public static void addPlayer(Player player){
        observablePlayers.add(player);
        pCanvas.addPlayerToken(player);
    }

    public static void updateVillains(int pos, boolean status){
    	villainGang.setPosition(pos);
    	villainGang.setState(status);
    }
    // Updates player on player list
    private static void updatePlayerData(Player player, String action){
        if(observablePlayers.contains(player)){

            int index = observablePlayers.indexOf(player);
            Player oldData = observablePlayers.get(index);
            observablePlayers.get(index).setBalance(player.getBalance());
            observablePlayers.get(index).setFuel(player.getFuel());

            // Only animates movement when action info == "roll".
            if(oldData.getPosition() != player.getPosition()){
                if(action.contains("spaces")){
                    pCanvas.animatePlayer(oldData,player.getPosition());
                    observablePlayers.get(index).setPosition(player.getPosition());
                }else{
                    observablePlayers.get(index).setPosition(player.getPosition());
                    pCanvas.relocatePlayer( observablePlayers.get(index));
                }
            }
        }
    }

    // Removes player from the draw loop. (quits game etc)
    public static void removePlayer(Player player){
        if(observablePlayers.contains(player)){
            observablePlayers.remove(player);
        }
    }

    public static Player getPlayer(int id){
        for(Player player: observablePlayers){
            if(player.getId() == id){
                return player;
            }
        }
        return null;
    }

    // Location Methods
    public static void initializeLocations(){
        for(int index = 0; index<39; index++){
            String initName = Integer.toString(index);
            locations.add(new Location(initName, index, 0,0,0, Color.GOLD, false, 0));
        }
    }

    // Adds new updated location to location list.
    public static void updateLocations(List<Location> locs) throws IOException, JSONException {
        for(Location l : locs){
            if(locations.contains(l)) {
                updateLocationData(l);
            }
            else{
                System.out.println("Couldn't find that location.");
            }
        }
    }

    private static void updateLocationData(Location location){
        if(locations.contains(location)){
            int index = locations.indexOf(location);
            locations.get(index).setName(location.getName());
            locations.get(index).setRent(location.getRent());
            locations.get(index).setPrice(location.getPosition());
            locations.get(index).setOwnerID(location.getOwnerID());
            locations.get(index).setColour(location.getColour());
            locations.get(index).setMortgaged(location.isMortgaged());
            locations.get(index).setHouses(location.getHouses());
            // etc
        }
    }

    public static Location getLocation(int position){
        for(Location loc: locations){
            if(loc.getPosition() == position){
                return loc;
            }
        }
        return null;
    }

    public static void setPlayerCanvas(PlayerCanvas canvas){
        pCanvas = canvas;
    }
}
