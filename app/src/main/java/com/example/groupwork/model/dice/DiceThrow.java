package com.example.groupwork.model.dice;

import android.os.Build;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Random;
/**
 * This class represents a simple dice throw that has happened or has yet to be realized.
 */
public class DiceThrow {

    private Map<Integer, Integer> dice;
    private Integer numberOfDice;
    private Integer[] results;
    private int lastResult;
    private Random rand;
    /**
     * This is the basic constructor for the dice throw.
     * input is a map that describe the dice to be used and the amount. The format is Dice type and amount.
     *             For example: (20,5),(4,2) represents a group of 5 twenty sided dice and 2 four sided dice.
     */
    public DiceThrow(Map<Integer, Integer> dice){
        rand = new Random();
        this.dice = dice;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.numberOfDice = dice.keySet().stream()
                    .map(number -> dice.get(number)).collect(Collectors.summingInt(Integer::intValue));
        }
        this.results = new Integer[this.numberOfDice];
        this.lastResult = -1;
    }


    /**
     * This class simulates a throw of this set of dice and returns an integer.
     * @return a list of all the results from the last roll.
     */
    public List<Integer> throwDice(){
        int j = 0;
        lastResult = 0;
        for (int key: dice.keySet()){
            int numberOfRolls = this.dice.get(key);
            for (int i = 0; i < numberOfRolls;i++){
                    results[j] = rand.nextInt(key) + 1;
                    lastResult += results[j];
                    j += 1;

            }
        }
        return List.of(results);
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
     * Getter method for all the results
     * @return an array list of all the results
     */
    public List<Integer> getResultList(){
        return List.of(this.results);
    }


    /**
     * Getter function for the dice involved in the last throw.
     * @return dice
     */
    public Map<Integer, Integer> getDice(){
        return dice;
    }


    @NonNull
    @Override
    public String toString(){
        StringBuilder finalString = new StringBuilder();
        for (int j = 0; j < results.length; j++) {
            if (j == results.length - 1){
                finalString.append(results[j].toString());
                finalString.append(" = ");
            } else {
                finalString.append(results[j].toString());
                finalString.append(" + ");
            }
        }
        finalString.append(lastResult);
        return finalString.toString();
    }
}