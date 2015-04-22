package uk.ac.aber.dcs.nid21.cs21120.assignment1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SingleEliminationTest {

    private IManager manager;

    @Before
    public void before(){
        manager = IManagerFactory.getManager("uk.ac.aber.dcs.nid21.cs21120.assignment1.SingleElimination");
    }

    @After
    public void after(){
        manager = null;
    }

    @Test
    public void testEvenPlayerNumber() throws FileNotFoundException {
        ArrayList<String> players = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
            add("D");
            add("E");
            add("F");
            add("G");
            add("H");
        }};

        System.out.println(String.format("Testing single elimination with %s players", players.size()));

        ArrayList<String> ExpectedFinishingOrder = new ArrayList<String>() {{
            add("F");
            add("C");
            add("G");
            add("B");
            add("H");
            add("E");
            add("D");
            add("A");
        }};

        Stack<Boolean> inputs = new Stack<Boolean>() {{
            push(false);
            push(true);
            push(false);
            push(true);
            push(false);
            push(true);
            push(false);
        }};

        boolean input;
        Match match;
        manager.setPlayers(players);
        while(manager.hasNextMatch()){
            match = manager.nextMatch();
            input = inputs.pop();
            System.out.println(String.format("Match between %s and %s, setting winner to %s", match.getPlayer1(), match.getPlayer2(), (input)?match.getPlayer1():match.getPlayer2()));
            manager.setMatchWinner(input);
        }

        for(int i = 0; i < players.size(); i++){
            Assert.assertEquals(ExpectedFinishingOrder.get(i), manager.getPosition(i));
        }
    }

    @Test
    public void testOddPlayerNumber() throws FileNotFoundException {
        ArrayList<String> players = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
            add("D");
            add("E");
            add("F");
            add("G");
        }};

        System.out.println(String.format("Testing single elimination with %s players", players.size()));

        ArrayList<String> ExpectedFinishingOrder = new ArrayList<String>() {{
            add("G");
            add("F");
            add("C");
            add("B");
            add("E");
            add("D");
            add("A");
        }};

        Stack<Boolean> inputs = new Stack<Boolean>() {{
            push(true);
            push(false);
            push(true);
            push(false);
            push(true);
            push(false);
        }};

        boolean input;
        Match match;
        manager.setPlayers(players);
        while(manager.hasNextMatch()){
            match = manager.nextMatch();
            input = inputs.pop();
            System.out.println(String.format("Match between %s and %s, setting winner to %s", match.getPlayer1(), match.getPlayer2(), (input)?match.getPlayer1():match.getPlayer2()));
            manager.setMatchWinner(input);
        }

        for(int i = 0; i < players.size(); i++){
            Assert.assertEquals(ExpectedFinishingOrder.get(i), manager.getPosition(i));
        }
    }

    @Test
    public void testNoPlayers(){
        manager.setPlayers(new ArrayList<String>());
        Match match;
        while(manager.hasNextMatch()){
            match = manager.nextMatch();
            Assert.fail(String.format("There should not be any matches to do, got match {%s,%s}", match.getPlayer1(), match.getPlayer2()));
        }
    }

    @Test(expected = NoNextMatchException.class)
    public void testNoMatchException(){
        manager.nextMatch();
        Assert.fail("There should have been a NoNextMatchException thrown");
    }
}
