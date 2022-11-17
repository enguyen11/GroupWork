package com.example.groupwork.model.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents a simple dice throw that has happened or has yet to be realized.
 */
public class DiceThrow {

    private Map<Integer, Integer> dice;
    private Integer numberOfDice;
    private Integer[] results;
    private int lastResult;

    /**
     * This is the basic constructor for the dice throw.
     * input is a map that describe the dice to be used and the amount. The format is Dice type and amount.
     *             For example: (20,5),(4,2) represents a group of 5 twenty sided dice and 2 four sided dice.
     */
    public DiceThrow(Map<Integer, Integer> dice){
        this.dice = dice;
        this.numberOfDice = dice.keySet().stream()
                .map(number -> dice.get(number)).collect(Collectors.summingInt(Integer::intValue));
        this.results = new Integer[this.numberOfDice];
        this.lastResult = -1;
    }


    /**
     * This class simulates a throw of this set of dice and returns an integer.
     * @return
     */
    public List<Integer> throwDice(){
        Random rand = new Random();
        int j = 0;
        lastResult = 0;
        for (int key: dice.keySet()){
            for (int i = 0; i < dice.get(key);i++){
                results[j] = rand.ints(1,1,key+1).sum();
                lastResult += results[j];
            }
        }
        return new ArrayList<Integer>(List.of(this.results));
    }


    /**
     * getter for the number of dice in the throw.
     * @return number of dice involved in the throw
     */
    public int getNumberOfDice() {
        return numberOfDice;
    }

    /**
     * Getter method for last result.
     * @return last result in integer form
     */
    public int getResult(){
        return lastResult;
    }

    /**
     * Getter function for the dice involved in the last throw.
     * @return
     */
    public Map<Integer, Integer> getDice(){
        return dice;
    }
}