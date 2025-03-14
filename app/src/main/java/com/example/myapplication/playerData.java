package com.example.myapplication;

public class playerData {
    private int money;
    private int power;
    private int magic;
    private int money_count;
    public playerData(int money, int power, int magic, int money_count){
        this.money = money;
        this.power = power;
        this.magic = magic;
        this.money_count = money_count;
    }

    public int getMoney(){
        return money;
    }
    public int getPower(){
        return power;
    }
    public int getMagic(){
        return magic;
    }
    public int getMoney_count(){
        return money_count;
    }
    public void setMoney(int money){
        this.money = money;
    }
    public void setPower(int power){
        this.power = power;
    }
    public void setMagic(int magic){
        this.magic = magic;
    }
    public void setMoney_count(int money_count){
        this.money_count = money_count;
    }
}
