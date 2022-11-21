package com.example.groupwork.model.dice;

import java.util.Map;

public class DiceRoller implements DiceRollerInterface{

    DiceThrow thrower;

    public DiceRoller(){
        thrower = null;
    }

    /**
     * This method is supposed to set the dice that are going to be used in the throw.
     *
     * @param dice   is a map that describe the dice to be used and the amount. The format is Dice type and amount.
     *               For example: (20,5),(4,2) represents a group of 5 twenty sided dice and 2 four sided dice.
     */
    @Override
    public void setupDice(Map<Integer, Integer> dice) {
        this.thrower = new DiceThrow(dice);
    }

    /**
     * Performs a die throw and result.
     *
     * @return DiceThrow instance
     */
    @Override
    public String throwDice() {
        this.thrower.throwDice();
        return this.thrower.toString();
    }

    /**
     * Performs a diceThrow and d
     *
     * @param target
     * @param bonus
     * @return if the throw was successful or not
     */
    @Override
    public boolean throwDice(int target, int bonus) {
        if (thrower != null){
            thrower.throwDice();
            return thrower.getResult() + bonus >= target;
        }
        return false;
    }
}