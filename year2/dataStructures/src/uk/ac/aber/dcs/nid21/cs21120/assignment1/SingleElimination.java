package uk.ac.aber.dcs.nid21.cs21120.assignment1;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;
import uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures.Queue;

import java.util.ArrayList;

public class SingleElimination implements IManager{

    Queue<String> currentBracket;
    Match currentMatch = null;
    ArrayList<String> finishingOrder = new ArrayList<String>();
    boolean winnerFound = false;

    //Entry point to class here
    @Override
    public void setPlayers(ArrayList<String> players) {
        currentBracket = new Queue<String>(players);
    }

    @Override
    public boolean hasNextMatch() {
        return currentBracket.itemCount() > 1;
    }

    @Override
    public Match nextMatch() throws NoNextMatchException {
        if (currentBracket == null)
            throw new NoNextMatchException("SINGLE ELIMINATION, NO MORE MATCHES AVAILABLE");
        currentMatch = new Match(currentBracket.pop(), currentBracket.pop());
        return currentMatch;
    }

    @Override
    public void setMatchWinner(boolean player1) {
        if(player1) {
            currentBracket.push(currentMatch.getPlayer1());
            finishingOrder.add(currentMatch.getPlayer2());
        } else {
            currentBracket.push(currentMatch.getPlayer2());
            finishingOrder.add(currentMatch.getPlayer1());
        }
        if (currentBracket.itemCount() == 1) {
            finishingOrder.add(currentBracket.pop());
            winnerFound = true;
        }
    }

    @Override
    public String getPosition(int n) {
        return finishingOrder.get(finishingOrder.size() - n - 1);
    }

}
