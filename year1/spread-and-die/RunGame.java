/* "Spread and Die"
 *
 * A First year java assignment for
 * CS12130.
 *
 * Candidate: Nicholas Dart nid21
 * Supervisor: Reyer Zwiggelaar
 */

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RunGame{

	private static String[] menus = {
		"Play the Whole Game",
		"Play Only Level 1",
		"Play Only Level 2",
		"Play Only Level 3",
		"Play Only Level 4",
		"View High Scores",
		"Set Field Dimentions",
		"Set Simulation Speed",
		"Quit"
	};

	private static int height = 12,
			   		   width  = 12,
			   		   iterations = 0,
			   		   cumulativeScore = 0,
			   		   sleepTimer = 1000,
			   		   scoreToWin = 20;

	private static boolean debug = true;

	private static int doMenu(){
		for(int i = 0; i < menus.length; i++){
			System.out.println(i + ": " + menus[i]);
		}
		int selection;
		do{
			System.out.print("--> ");
			selection = Keyboard.readInt();
		}while(selection < 0 || selection > menus.length-1);
		return selection;
	}

	private static void clearSceen(){
		//Unix only!
		//http://stackoverflow.com/questions/228477/how-do-i-programmatically-determine-operating-system-in-java (18:55 30/11/13)
		//Clears the screen
		//http://stackoverflow.com/questions/10241217/how-to-clear-console-in-java  (17:19 03/11/13)
		if(System.getProperty("os.name").startsWith("Linux")){
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}

	private static int getValidatedInput(int min, int max, String message){
		int toReturn;
		do{
			System.out.print(message + " --> ");
			toReturn = Keyboard.readInt();
		}while(toReturn < min || toReturn > max);
		return toReturn;
	}

	private static boolean doLevel(Player p, int regions, int x, int y, int level){
		iterations = 0;

		clearSceen();	
		Field f  = new Field(height, width, regions, x-1, y-1);
		//main game loop
		do{
			clearSceen();

			System.out.println("\n\t===LEVEL " + level + "===\nGeneration " + (++iterations) + "\n");
			f.printField();
			f.nextGeneration(p.cure());
			while(!f.doPlayersMove(p.move(f)));
			f.doPlayerFieldManipulation(p.manipulateRegions(f));
			try {
				Thread.sleep(sleepTimer);
			} catch (InterruptedException e){
				if(debug) e.printStackTrace();
			}

		}while(!f.playerIsDiseased());

		cumulativeScore += iterations;
		declareWinner();
		try{
			Thread.sleep(5000);
		} catch (InterruptedException e){
			if(debug) e.printStackTrace();
		}
		return iterations>=scoreToWin;
	}

	private static void setField(){
		clearSceen();
		System.out.print("Ender the Field Height: ");
		height = Keyboard.readInt();
		System.out.print("Enter the Field Width : ");
		width  = Keyboard.readInt();
	}

	private static void setSimSpeed(){
		do{
			clearSceen();
			System.out.print("Simulation Speed is currently " + sleepTimer/1000 + "\nSet simulation speed In seconds: ");
			sleepTimer = (int)(Keyboard.readFloat()*1000);
		}while(sleepTimer <0);
	}

	private static void declareWinner(){
		System.out.println("You have survived " + iterations + " iterations! Your cumulative score for this session is " + cumulativeScore);
		System.out.println("You" + ((iterations >= scoreToWin)?" Passed ":" Failed ") + "the level");
	}

	private static void highScores(){

	}

	public static void main(String[] args){
		boolean quit = false;
		do{
			clearSceen();
			int r,x,y;
			switch(doMenu()){
				case 0: 
						r = getValidatedInput(2,4,"Enter the number of regions (2-4) -> ");
						y = getValidatedInput(1,width, "Enter Disease starting Y coordinate (1-"+width+") -> ");
						x = getValidatedInput(1,height,"Enter Disease starting X coordinate (1-"+height +") -> ");
						while(!doLevel(new SimplePlayer(),r,x,y,1));
						while(!doLevel(new AiPlayer(),r,x,y,2));
						while(!doLevel(new RegionsPlayer(),r,x,y,3));
						while(!doLevel(new BiologistPlayer(),r,x,y,4));
						System.out.println("Congratulations, you have completed SPREAD AND DIE!");
						try{
							Thread.sleep(5000);
						}catch(InterruptedException e){
							if(debug) e.printStackTrace();
						}
						break;
				case 1: r = getValidatedInput(1,4,"Enter the number of regions (2-4) -> ");
						y = getValidatedInput(1,width, "Enter Disease starting Y coordinate (1-"+width+") -> ");
						x = getValidatedInput(1,height,"Enter Disease starting X coordinate (1-"+height +") -> ");
						doLevel(new SimplePlayer(),r,x,y,1);
						break;
				case 2: r = getValidatedInput(2,4,"Enter the number of regions (2-4) -> ");
						y = getValidatedInput(1,width, "Enter Disease starting Y coordinate (1-"+width+") -> ");
						x = getValidatedInput(1,height,"Enter Disease starting X coordinate (1-"+height +") -> ");
						doLevel(new AiPlayer(),r,x,y,2);
						break;
				case 3: r = getValidatedInput(2,4,"Enter the number of regions (2-4) -> ");
						y = getValidatedInput(1,width, "Enter Disease starting Y coordinate (1-"+width+") -> ");
						x = getValidatedInput(1,height,"Enter Disease starting X coordinate (1-"+height +") -> ");
						doLevel(new RegionsPlayer(),r,x,y,3);
						break;
				case 4: r = getValidatedInput(2,4,"Enter the number of regions (2-4) -> ");
						y = getValidatedInput(1,width, "Enter Disease starting Y coordinate (1-"+width+") -> ");
						x = getValidatedInput(1,height,"Enter Disease starting X coordinate (1-"+height +") -> ");
						doLevel(new BiologistPlayer(),r,x,y,4);
						break;
				case 5: highScores();
						break;
				case 6: setField();
						break;
				case 7: setSimSpeed();
						break;
				case 8: quit = true;
						break;
			}
		}while(!quit);
		
	}

}
