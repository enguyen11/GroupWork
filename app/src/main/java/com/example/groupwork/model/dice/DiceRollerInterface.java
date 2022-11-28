package com.example.groupwork.model.dice;

import java.util.Map;

/**
 * This interface is intended to be used as a module for rolling dice in the dnd game.
 * This class should receive a set of parameters and be able to randomly simulate a dice throw with some special
 * conditions. Dice throws should yield integer values
 */
public interface DiceRollerInterface {

    /**
     * This method is supposed to set the dice that are going to be used in the throw.
     * @param dice is a map that describe the dice to be used and the amount. The format is Dice type and amount.
     *             For example: (20,5),(4,2) represents a group of 5 twenty sided dice and 2 four sided dice.
     */
    public void setupDice(Map<Integer, Integer> dice);

    /**
     * Performs a die throw and result.
     * @return DiceThrow instance
     */
    public String throwDice();


    /**
     * Performs a diceThrow and d
     * @param target
     * @return if the throw was successful or not
     */
    public boolean throwDice(int target, int bonus);



}