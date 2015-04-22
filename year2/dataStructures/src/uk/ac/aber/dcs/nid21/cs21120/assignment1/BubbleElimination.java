package uk.ac.aber.dcs.nid21.cs21120.assignment1;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;
import uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures.PriorityQueue;
import uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures.Stack;

import java.util.ArrayList;

public class BubbleElimination implements IManager {

    PriorityQueue<String> priorityQueue;
    Stack<String> players;
    Match currentMatch;

    @Override
    public void setPlayers(ArrayList<String> players) {
        this.players = new Stack<String>(players);
        priorityQueue = new PriorityQueue<>(players.size());
    }

    @Override
    public boolean hasNextMatch() {
        return !this.players.isEmpty();
    }

    @Override
    public Match nextMatch() throws NoNextMatchException {
        if(currentMatch == null && priorityQueue.itemCount() == 0){
            currentMatch = new Match(players.pop(), players.pop());
        }
        return currentMatch;
    }

    @Override
    public void setMatchWinner(boolean player1) {

    }

    @Override
    public String getPosition(int n) {
        return null;
    }
}
