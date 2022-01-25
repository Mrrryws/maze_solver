package maze;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
 
/**
* {@link Serializable} class that deals with the maze which is an object representing the maze to be solved.
* @author Marius Traian Hostinar
*/
public class Maze implements Serializable{

    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles;

    /**
    * Constructor that creates a maze by initializing an {@link ArrayList} of {@link List} of {@link Tile}.
    */
    private Maze(){
        tiles = new ArrayList<List<Tile>>();
    }

    /**
    * Method that transforms a text maze to a maze made out of multiple {@link Tile}.
    * @param text A maze in text form.
    * @return Returns a maze made out of multiple {@link Tile} instances.
    * @throws IOException Indicates failed or interrupted I/O operations.
    * @throws FileNotFoundException Indicates the {@link File} with the given name has not been found.
    * @throws InvalidMazeException Indicates the {@link Maze} is invalid.
    */
    public static Maze fromTxt(String text) throws IOException, FileNotFoundException, InvalidMazeException {
        Maze maze = new Maze();
 
        try (BufferedReader br = new BufferedReader(new FileReader(text)))
        {
            String mazeLine;
            while ((mazeLine = br.readLine()) != null) { 
                List<Tile> mazeRow = new ArrayList<Tile>();
                if(maze.tiles.size() > 0 && mazeLine.length() != maze.tiles.get(0).size())
                    throw new RaggedMazeException(); 
                maze.tiles.add(0, mazeRow);
                 
                for(int i = 0; i < mazeLine.length(); i++){
                    Tile tile = Tile.fromChar(mazeLine.charAt(i));
                    mazeRow.add(tile);
                    if (tile.getType() == Tile.Type.ENTRANCE)
                        if (maze.getEntrance() == null)
                            maze.setEntrance(tile);
                        else 
                            throw new MultipleEntranceException();
                    if (tile.getType() == Tile.Type.EXIT)
                        if (maze.getExit() == null)
                            maze.setExit(tile);
                        else 
                            throw new MultipleExitException();    
                }
            }
            if(maze.getEntrance() == null){
                throw new NoEntranceException();
            }
            if(maze.getExit() == null){
                throw new NoExitException();
            }
        } catch (FileNotFoundException exception) {
            throw exception;
        } catch (IOException exception) {
            throw exception;
        }
        return maze;
    }

    /**
    * Method that gets the next {@link Tile} in a given {@link Direction}. 
    * @param tile A specified {@link Tile}.
    * @param dir A valid {@link Direction}.
    * @return Returns a {@link Tile} Adjecent to the current (inputted) {@link Tile}.
    */
    public Tile getAdjacentTile(Tile tile, Direction dir){
        Coordinate currentCoordinates = getTileLocation(tile);
        int newCoordinateForX = currentCoordinates.getX();
        int newCoordinateForY = currentCoordinates.getY();

        if(dir == Direction.NORTH)
            newCoordinateForY = newCoordinateForY + 1;
        else if(dir == Direction.SOUTH)
            newCoordinateForY = newCoordinateForY - 1;
        else if(dir == Direction.EAST)
            newCoordinateForX = newCoordinateForX + 1;
        else
            newCoordinateForX = newCoordinateForX - 1;
        
        Coordinate newCoordinates = new Coordinate(newCoordinateForX, newCoordinateForY);
        return getTileAtLocation(newCoordinates);
    }

    /**
    * Method that gets the entrance of the {@link Maze}. 
    * @return Returns the entrance of the {@link Maze}.
    */
    public Tile getEntrance(){
        return entrance;
    }

    /**
    * Method that gets the exit of the {@link Maze}. 
    * @return Returns the exit of the {@link Maze}. 
    */
    public Tile getExit(){
        return exit;
    }

    /**
    * Method that gets the {@link Tile} at a specific inputted {@link Coordinate}. 
    * @param coord A specific {@link Coordinate}.
    * @return Returns a {@link Tile} at the specified location.
    */
    public Tile getTileAtLocation(Coordinate coord){
        if(coord.getY() < tiles.size() && coord.getX() < tiles.get(0).size() && coord.getY() >= 0 && coord.getX() >= 0)
            return tiles.get(coord.getY()).get(coord.getX());
        else 
            return null;
    }

    /**
    * Method that gets the {@link Coordinate} of a specified {@link Tile}.
    * @param tile A given {@link Tile} you want to get the {@link Coordinate} of.
    * @return Returns the location of a given {@link Tile}.
    */
    public Coordinate getTileLocation(Tile tile){
        for (int y = tiles.size() - 1; y >= 0 ; y-- )
            for (int x = 0; x < tiles.get(y).size(); x++)
                if(tiles.get(y).get(x).equals(tile))
                    return new Coordinate(x, y);
        return null;
    }

    /**
    * Method that gets the {@link Tile} of the {@link Maze}.
    * @return Returns a {@link List} of {@link List} of {@link Tile} that represent the tiles that make the {@link Maze}.
    */
    public List<List<Tile>> getTiles(){
        return tiles;
    }

    /**
    * Method that sets the entrance of the {@link Maze}. 
    * @param entrance A specified {@link Tile} that represents the entrance.
    * @throws MultipleEntranceException Indicates that the {@link Maze} has multiple entrances.
    */
    private void setEntrance(Tile entrance) throws MultipleEntranceException{
        if(getTileLocation(entrance) == null){
            return;
        }
        if(getEntrance() != null){
            throw new MultipleEntranceException();
        }
        else{
            this.entrance = entrance;
        }
    }

    /**
    * Method that sets the exit of the {@link Maze}.
    * @param exit A specified {@link Tile} that represents the exit.
    * @throws MultipleExitException Indicates that the {@link Maze} has multiple exits.
    */
    private void setExit(Tile exit) throws MultipleExitException{
        if(getTileLocation(exit) == null){
            return;
        }
        if(getExit() != null){
            throw new MultipleExitException();
        }
        else {
            this.exit = exit;
        }
    }

    /**
    * Method that transforms the {@link Coordinate} to a {@link String}. 
    * @return Returns a {@link String} that represents the {@link Maze} in a text form.
    */
    @Override
    public String toString() {
        String result = "";
        for (int y = tiles.size() - 1; y >= 0 ; y--){
            result += Integer.toString(y) + "    ";
            for (int x = 0; x < tiles.get(y).size(); x++){  
                Tile tile = tiles.get(y).get(x);
                result += (tile.toString()) + " ";
            }
            result += "\n";
        }
        result += "\n" + "     ";
        for(int i = 0; i < tiles.get(0).size(); i++)
            result += Integer.toString(i) + " ";
        return result;
    }

    /**
    * Class that deals with the {@link Maze} coordinates.
    */
    public static class Coordinate {

        private int x;
        private int y;
    
        /**
        * Constructor for the coordinates.
        */
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    
        /**
        * Method that gets the column number. 
        * @return Returns the column number.
        */
        public int getX() {
            return x;
        }
    
        /**
        * Method that gets the row number. 
        * @return Returns the row number.
        */
        public int getY() {
            return y;
        }
    
        /**
        * Method that transforms the coordinates to a {@link String}.
        * @return Returns the coordinates as a {@link String}.
        */
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";   
        }
    }

    /**
    * An enum representing all the possible directions to navigate.
    */
    public enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }
}