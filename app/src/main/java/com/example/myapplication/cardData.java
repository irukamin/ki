package com.example.myapplication;

public class cardData {
    private String name;
    private int money;
    private int power;
    private int magic;
    private int vp;

    // コンストラクタ
    public cardData(String name, int money, int power, int magic, int vp) {
        this.name = name;
        this.money = money;
        this.power = power;
        this.magic = magic;
        this.vp = vp;
    }

    // Getterメソッド
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getPower() {
        return power;
    }

    public int getMagic() {
        return magic;
    }

    public int getVp() {
        return vp;
    }


    // Setterメソッド（必要であれば）
    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }

}
