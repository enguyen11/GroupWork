package com.example.groupwork.model.dice;

import java.util.Map;

public class DiceRoller implements DiceRollerInterface{

    DiceThrow lastThrow;
    int target;
    int bonus;

    public DiceRoller(){
        lastThrow = null;
        target = -1;
        bonus = -1;
    }

    /**
     * This method is supposed to set the dice that are going to be used in the throw.
     *
     * @param dice   is a map that describe the dice to be used and the amount. The format is Dice type and amount.
     *               For example: (20,5),(4,2) represents a group of 5 twenty sided dice and 2 four sided dice.
     * @param bonus
     * @param target
     */
    @Override
    public void setupDice(Map<Integer, Integer> dice, int bonus, int target) {
        this.lastThrow = new DiceThrow(dice);
        this.bonus = bonus;
        this.target = target;
    }

    /**
     * If the result is equal or larger than the target then the check is passed. Else
     * it's not. This method only modifies the target.
     *
     * @param target an integer value representing the points needed to pass the check.
     */
    @Override
    public void setTarget(Integer target) {
        this.target = target;
    }

    /**
     * Performs a die throw and result.
     *
     * @return DiceThrow instance
     */
    @Override
    public DiceThrow throwDice() {
        lastThrow = throwDice();
        return lastThrow;
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
        if (lastThrow != null){
            lastThrow.throwDice();
            if (lastThrow.getResult() + bonus >= target){
                return true;
            }
        }
        return false;
    }
}