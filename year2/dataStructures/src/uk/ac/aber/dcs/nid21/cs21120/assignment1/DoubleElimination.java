package uk.ac.aber.dcs.nid21.cs21120.assignment1;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;
import uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures.Queue;

import java.util.ArrayList;

public class DoubleElimination implements IManager {

    private Queue<String> winnersBracket;
    private Queue<String> loosersBracket;
    private ArrayList<String> finalists = new ArrayList<String>();
    private boolean foundWinner = false;
    private Match currentMatch;
    private boolean takenFromLoosers = false;

    @Override
    public void setPlayers(ArrayList<String> players) {
        winnersBracket = new Queue<String>(players);
        loosersBracket = new Queue<String>(players.size());
    }

    @Override
    public boolean hasNextMatch() {
        return winnersBracket.itemCount() > 0;
    }

    @Override
    public Match nextMatch() throws NoNextMatchException {
        if(winnersBracket == null || loosersBracket == null)
            throw new NoNextMatchException("DOUBLE ELIMINATION, NO MORE MATCHES AVAILABLE");
        if(winnersBracket.itemCount() == 1 && loosersBracket.itemCount() == 1){
            currentMatch = new Match(loosersBracket.pop(), winnersBracket.pop());
            takenFromLoosers = true;
            foundWinner = true;
        } else if(winnersBracket.itemCount() > loosersBracket.itemCount()){
            currentMatch = new Match(winnersBracket.pop(), winnersBracket.pop());
            takenFromLoosers = false;
        } else {
            currentMatch = new Match(loosersBracket.pop(), loosersBracket.pop());
            takenFromLoosers = true;
        }
        return currentMatch;
    }

    @Override
    public void setMatchWinner(boolean player1) {
        if(takenFromLoosers){
            if(player1){
                loosersBracket.push(currentMatch.getPlayer1());
                finalists.add(currentMatch.getPlayer2());
            } else {
                loosersBracket.push(currentMatch.getPlayer2());
                finalists.add(currentMatch.getPlayer1());
            }
        } else {
            if(player1){
                winnersBracket.push(currentMatch.getPlayer1());
                loosersBracket.push(currentMatch.getPlayer2());
            } else {
                winnersBracket.push(currentMatch.getPlayer2());
                loosersBracket.push(currentMatch.getPlayer1());
            }
        }

        if(foundWinner){
            //The winner will have been pushed to the loosers bracket, get them out. they don't belong in that filth...
            finalists.add(loosersBracket.pop());
        }
    }

    @Override
    public String getPosition(int n) {
        return finalists.get(finalists.size() - n - 1);
    }
}
