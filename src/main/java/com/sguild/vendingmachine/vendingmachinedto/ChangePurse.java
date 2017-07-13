/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedto;

/**
 *
 * @author apprentice
 */
public class ChangePurse {

    private int dollars;
    private int quarters;
    private int dimes;
    private int nickles;
    private int pennies;
    
    /*public void usersChange(int remainingBalance){
        
    }*/

    public ChangePurse(int remainingBalance) {
        dollars = remainingBalance / 100;
        int centsLeft = remainingBalance % 100;
        
        setDollars(dollars);

        quarters = centsLeft / 25;
        centsLeft = centsLeft % 25;

        setQuarters(quarters);

        dimes = centsLeft / 10;
        centsLeft = centsLeft % 10;

        setDimes(dimes);

        nickles = centsLeft / 5;
        centsLeft = centsLeft % 5;

        setNickles(nickles);
        setPennies(centsLeft);
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickles() {
        return nickles;
    }

    public void setNickles(int nickles) {
        this.nickles = nickles;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

}
