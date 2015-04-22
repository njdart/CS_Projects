/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.dcs.bpt.cs21120.assignment1;

import java.util.ArrayList;

/**
 * Interface that defines the behaviour of a competition
 * @author bpt
 */
public interface IManager {
    /**
     * Set the players or teams to use in the competition
     * @param players the players or teams
     */
    public void setPlayers(ArrayList<String> players);
    
    /**
     * Return true if there is another match in the competition that can be fetched using nextMatch
     * @return returns true if the competition is still going
     */
    public boolean hasNextMatch();
    
    /**
     * Returns the nextMatch to play
     * @return returns the next match
     * @throws NoNextMatchException if the competition is over and no more matches
     */
    public Match nextMatch() throws NoNextMatchException;
    
    /** Sets the winner for the last retrieved Match
     * 
     * @param player1 should be true if player1 won the match, otherwise false
     */
    public void setMatchWinner(boolean player1);
    
    /** 
     * Get the name of the player/team that finished in position n.  
     * The returned value should be null if the competition is still running, or if the competition hasn't
     * determined who came in place n.  e.g. a single elimination competition can only (validly) return the
     * winner (n=0).
     * @param n the position to return
     * @return returns the name of the team/player, or null if competition still running or n too large
     */
    public String getPosition(int n);
}
