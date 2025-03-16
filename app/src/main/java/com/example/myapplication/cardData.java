package com.example.myapplication;

public class cardData {
    private String name;
    private int tag;
    /*
    上のくらいから
    シリーズ　　9:初代, 1:拡張第一弾->23
    ジャンル　　1:スタンダード, 2:ランダマイザ―, 3:レジェンドⅠ, 4:レジェンドⅡ
    カテゴリー　1:建築物, 2:アイテム, 3:魔導書, 4:組織, 5:イベント, 6:精霊, 7:聖地
     */
    private int money;
    private int power;
    private int magic;
    private int vp;

    // コンストラクタ
    public cardData(String name, int tag, int money, int power, int magic, int vp) {
        this.name = name;
        this.tag = tag;
        this.money = money;
        this.power = power;
        this.magic = magic;
        this.vp = vp;
    }

    // Getterメソッド
    public String getName() {
        return name;
    }

    public int getTag(){
        return tag;
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
    public void setTag(int tag) { this.tag = tag;}

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
