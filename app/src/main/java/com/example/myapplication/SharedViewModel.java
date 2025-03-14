package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    int totalVictoryPoints = 0;
    int koppenCount = 0;
    int panyaCount = 0;
    int powerCount = 0;
    int moneyCount = 0;

    private MutableLiveData<List<cardData>> playersItemLiveData = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Integer> moneyNumber = new MutableLiveData<>(0);
    private MutableLiveData<Integer> powerNumber = new MutableLiveData<>(0);
    private MutableLiveData<Integer> magicNumber = new MutableLiveData<>(0);
    private MutableLiveData<Integer> MoneyCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> victoryPoints = new MutableLiveData<>(0);

    public LiveData<Integer> getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(int money) {
        moneyNumber.setValue(money);
    }

    public LiveData<Integer> getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(int power) {
        powerNumber.setValue(power);
    }

    public LiveData<Integer> getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magic) {
        magicNumber.setValue(magic);
    }
    public LiveData<Integer> getMoneyCount() {
        return MoneyCount;
    }
    public void setMoneyCount(int moneyCount){
        MoneyCount.setValue(moneyCount);
    }

    public LiveData<Integer> getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int points) {
        victoryPoints.setValue(points);
    }

    public LiveData<List<cardData>> getPlayersItem() {
        return playersItemLiveData;
    }

    public void addItem(cardData item) {
        List<cardData> currentList = playersItemLiveData.getValue();
        if (currentList != null) {
            currentList.add(item);
            playersItemLiveData.setValue(currentList);  // 更新
        }
    }
    public void deleteItem(String data) {
        List<cardData> currentList = playersItemLiveData.getValue();
        for (cardData check : currentList) {
            if (check.getName().equals(data)) {
                currentList.remove(check);
                break;
            }
        }
    }

    public int updateVictoryPoint(List<cardData> playerdata) {

        totalVictoryPoints = 0;
        koppenCount = 0;
        panyaCount = 1;
        powerCount = 0;
        moneyCount = getMoneyCount().getValue();

        for (cardData item : playerdata) {
            if(item.getName().equals("パンの妖精　コッペン")){
                koppenCount++;
            }
            if(item.getName().equals("パン屋")){
                panyaCount+=2;
            }
            powerCount += item.getPower();
        }
        // playersItem に含まれるカードの勝利ポイント（vp）を合計する
        for (cardData item : playerdata) {
            if(item.getName().equals("パンの妖精　コッペン")){
                item.setVp(koppenCount);
            }
            if(item.getName().equals("脱法パン屋")){
                item.setVp(panyaCount);
            }
            if(item.getName().equals("麗しき女王像")){
                item.setVp(powerCount/4);
            }
            totalVictoryPoints += item.getVp();  // vp（勝利ポイント）の合計を計算
        }
        // "闇金庫"が含まれているかチェック
        for (cardData item : playerdata) {
            if (item.getName().equals("闇金庫")) {
                totalVictoryPoints += moneyCount;  // "闇金庫"があればmoneyCountを加算
                break;  // 見つかったらループを抜ける
            }
        }
        setVictoryPoints(totalVictoryPoints);
        return totalVictoryPoints;
    }
}