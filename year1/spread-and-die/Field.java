/* "Spread and Die"
 *
 * A First year java assignment for
 * CS12130.
 *
 * Candidate: Nicholas Dart nid21
 * Supervisor: Reyer Zwiggelaar
 * 
 * This class reperesents the "Field" or
 * playing area of the game. It houses
 * the disease, as well as the players
 * position, and simulates the spread
 * of the disease
 */

/*   0
 *  0+----------->Y or Width
 *   | | | | | | 
 *   |-+-+-+-+-+
 *   | | | | | |
 *   |-+-+-+-+-+
 *   V
 *   X or Height
 */ 



import java.util.Random;
import java.util.HashMap;
import java.util.Map.Entry;

public class Field{

    //board size
    private final int width,
                      height;
    //the region characters
    private char[][] field;         //contains the regions this should never change
                                    //after the constructor
    private Disease[][] disease;    //contains the diseased and infected cells
    private final int[] diseaseStartingPosition;
    //cell states

    public static enum Disease{ //OUTOFBOUNDS is only used by the player to represent a cell outside of the board
        DISEASED, INFECTED, EMPTY, OUTOFBOUNDS
    }

    //movements the player can use
    public static enum Movements{
        UP, DOWN, LEFT, RIGHT, STAY
    }

    //the chars that will represent the diseased and infected cells and the player
    private final char diseased = 'D',
                       infected = 'I', 
                       player   = 'P';

    //used in the level 3 "Biologist" player
    //Bytes are from -128 to 127 ?!?!?
    private byte genome = (byte)0x00,
                 playersCure = (byte)0x00,
                 diseaseMutationChance = (byte)125,     //Small numbers means the disease is more likely to mutate
                 diseaseResistance = (byte)-100;        //Small numbers mean it's hard to cure the disease

    //field region characters
    public static final char[] regionCharacters = {'+','-','*','~'};
    private Random rand = new Random();
    
    private boolean playerOnDiseasedCell = false;
    private boolean playerIsImmune = false;
    private int[] playerPosition = {1,1}; //x, y
    
    /**
     * Constructs the field with the specified number of regions, with
     * the disease starting at the indicated position. The player will
     * never be placed ontop of the disease, but will be placed randomly
     * within the field.
     *
     * @param width The width of the playing field
     * @param height The height of the playing field
     * @param regions The number of regions to use in the field (1-4)
     * @param diseaseX The X coordinate of the disease starts from
     * @param diseaseY The Y coordinate of the disease starts from
     *
     */
    public Field(int height, int width, int regions, int diseaseX, int diseaseY){ ///Width, Height, Regions, disease x, disease y
        this.width = width;
        this.height = height;
        field = new char[height][width];
        disease = new Disease[height][width];
        generateField(regions);
        clearDisease();
        placePlayer(diseaseX,diseaseY);
        disease[diseaseX][diseaseY] = Disease.DISEASED;
        diseaseStartingPosition = new int[] {diseaseX, diseaseY};
        //mutate the disease initally
        byte[] b = new byte[1];
        rand.nextBytes(b);
        genome = b[0];
    }

    private void placePlayer(int diseaseX, int diseaseY){
        //generate a player x and y that isn
        do{
            playerPosition[0] = rand.nextInt(height);
        }while(playerPosition[0] == diseaseX);

        do{
            playerPosition[1] = rand.nextInt(width);
        }while(playerPosition[1] == diseaseY);
    }

    private Disease[][] copy2d(Disease[][] a){
        Disease[][] b = new Disease[height][width];

        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                b[i][j] = a[i][j];
        return b;
    }

    private void generateField(int regions){
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                //choose a random char from regionCharacters
                field[i][j] = regionCharacters[rand.nextInt(regions)];
    }

    private void clearDisease(){
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                disease[i][j] = Disease.EMPTY;
    }

    private void mutate(){
        byte[] b = new byte[1];
        rand.nextBytes(b);
        if(genome + b[0] - playersCure > diseaseMutationChance){
            genome = b[0];
            if(playerIsImmune)
                System.out.println("The disease has mutated");
            playerIsImmune = false;
        }
    }

    private void doPlayersCure(byte b){
        playersCure = b;
        if(b != (byte)0x00){
            if(b - genome < diseaseResistance){
                if(!playerIsImmune)
                    System.out.println("The player has cured the disease!");
                playerIsImmune = true;
            }
        }
    }


    /**
     * Gets a cells value
     * @param x the x coordinate
     * @param y the y coordinate
     * @return char of the current cell
     */
    public Disease getCell(int x, int y){
        if(x >= 0 && x < height && y >= 0 && y < width) return this.disease[x][y];
        else return Disease.OUTOFBOUNDS;
    }

    public Disease getCell(int[] pos){
        if(pos[0] >= 0 && pos[0] < height && pos[1] >= 0 && pos[1] < width) return this.disease[pos[0]][pos[1]];
        else return Disease.OUTOFBOUNDS;
    }

    public char getPlayersRegionCell(){
        return this.field[this.playerPosition[0]][this.playerPosition[1]];
    }

    /**
     * Prints the field to stdout
     */
    public void printField(){
        String out = "";
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(playerPosition[0] == i && playerPosition[1] == j)
                    out += player;
                else if(disease[i][j] != Disease.EMPTY){
                    switch(disease[i][j]){
                        case INFECTED:
                            out += infected;
                            break;
                        case DISEASED:
                            out += diseased;
                            break;
                    }
                }else out += field[i][j];
            }
            out += "\n";
        }
        System.out.println(out);
    }
    
    /**
     * simulates the next generation internally
     * 
     */
    public void nextGeneration(byte b){
        Disease[][] nextGeneration = copy2d(disease);
        //try to mutate the disease
        mutate();
        doPlayersCure(b);
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++){
                if(disease[i][j] == Disease.INFECTED)
                    nextGeneration[i][j] = Disease.DISEASED;
                else if (disease[i][j] == Disease.DISEASED){
                    int[][] positions = new int[][] { {1,0}, {-1,0}, {0,1}, {0,-1} };
                    for(int[] pos : positions){ //Directly adjacent Cells
                        int x = i+pos[0],
                            y = j+pos[1];
                        //check the lookup cell is within scope!
                        if(x>=0 && x<height && y>=0 && y<width){
                            if(disease[x][y] == Disease.EMPTY){
                                //if it's in the same region, become diseased
                                if(field[x][y] == field[i][j]){
                                    nextGeneration[x][y] = Disease.DISEASED;
                                //else, become infected
                                }else{
                                    nextGeneration[x][y] = Disease.INFECTED;
                                }
                            }
                        }
                    }
                }
                if(!playerOnDiseasedCell
                   && i == playerPosition[0]
                   && j == playerPosition[1]
                   && nextGeneration[i][j] == Disease.DISEASED
                   && !playerIsImmune)
                    playerOnDiseasedCell = true;
            }
        //move the generation forward
        disease = copy2d(nextGeneration);
    }
    /**
     * Returns the current player position on the board
     *
     * @return int[] {x, y} of the player position
     */
    public int[] getCurrentPlayerPosition(){
        return playerPosition;
    }

    public boolean playerIsDiseased(){
        return playerOnDiseasedCell;
    }
    
    /**
     * Moves the players position on the field,
     * 
     * @param m Integer repersenting the direction to move, anything >= 3 is considered not move.
     * @return bool if the move was valid
     */
    public boolean doPlayersMove(Movements d){
         switch(d){
            case DOWN:
                if(playerPosition[0] + 1 < height){
                    playerPosition[0]++; 
                    return true;
                } else return false;
            case UP:
                if(playerPosition[0] - 1 >= 0){
                    playerPosition[0]--;
                    return true;
                } else return false;
            case RIGHT:
                if(playerPosition[1] + 1 < width){
                    playerPosition[1]++;
                    return true;
                } else return false;
            case LEFT:
                if(playerPosition[1] - 1 >= 0){
                    playerPosition[1]--;
                    return true;
                } else return false;
            case STAY:
                return true;
         }
         return false;
    }

    //http://stackoverflow.com/questions/4234985/how-to-for-each-the-hashmap (05/12/13)
    public void doPlayerFieldManipulation(HashMap<Integer[], Character> map){
        for(Entry<Integer[], Character> entry : map.entrySet()){
            Integer[] k = entry.getKey();
            Character v = entry.getValue();
            System.out.println(k[0] + "," + k[1] + " -> " + v);
            if(k[0] >= 0 && k[0] < height && k[1] >= 0 && k[1] < width)
                field[k[0]][k[1]] = (char)v;

        }
    }

    public int[] getDiseaseStartingPosition(){
        return diseaseStartingPosition;
    }

    public int height(){
        return height;
    }

    public int width(){
        return width;
    }
}

