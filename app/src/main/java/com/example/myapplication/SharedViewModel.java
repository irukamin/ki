package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class SharedViewModel extends ViewModel {
    int totalVictoryPoints = 0;
    int koppenCount = 0;
    int panyaCount = 0;
    int sakabaCount = 0;
    int cakeCount = 0;
    int powerCount = 0;
    int moneyCount = 0;
    int tearCount = 0;
    int nobleCount = 0;
    int diceBreadCount = 0;
    int sumpower = 0;
    int sumcard = 0;
    int sumbook = 0;
    int sortcard = 0;
    int potetonCount = 0;


    private MutableLiveData<List<cardData>> playersItemLiveData = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Integer> moneyNumber = new MutableLiveData<>(0);
    private MutableLiveData<Integer> powerNumber = new MutableLiveData<>(0);
    private MutableLiveData<Integer> magicNumber = new MutableLiveData<>(0);
    private MutableLiveData<Integer> MoneyCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> victoryPoints = new MutableLiveData<>(0);
    private MutableLiveData<Integer> TearCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> NobleCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> DiceBreadCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> PotetonCount = new MutableLiveData<>(0);
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
        victoryPoints.postValue(points);
    }

    public LiveData<List<cardData>> getPlayersItem() {
        return playersItemLiveData;
    }
    public LiveData<Integer> getTearCount() { return TearCount;}

    public void setTearCount(int tears) {TearCount.setValue(tears);}
    public LiveData<Integer> getNobleCount() {
        return NobleCount;
    }
    public LiveData<Integer> getDiceBread() {
        return DiceBreadCount;
    }

    public void setDiceBreadCount(int dices) {
        DiceBreadCount.setValue(dices);
    }

    public void setNobleCount(int nobles) {
        NobleCount.setValue(nobles);
    }
    public LiveData<Integer> getPotetonCount(){return PotetonCount;}
    public void setPotetonCount(int potetons){PotetonCount.setValue(potetons);}
    private MutableLiveData<Integer> victoryPointsLiveData = new MutableLiveData<>();

    public LiveData<Integer> getVictoryPointsLiveData() {
        return victoryPointsLiveData;
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

    public int updateVictoryPoint(@NonNull List<cardData> playerdata) {
        totalVictoryPoints = 0;
        koppenCount = 0;
        panyaCount = 0;
        sakabaCount = 0;
        cakeCount = 0;
        powerCount = 0;
        moneyCount = getMoneyCount().getValue();
        boolean moneyCheck = false;
        tearCount = getTearCount().getValue();
        boolean tearCheck = false;
        nobleCount = getNobleCount().getValue();
        boolean nobleCheck = false;
        diceBreadCount = getDiceBread().getValue();
        boolean diBreCheck = false;
        sumcard = 0;
        sumbook = 0;
        sumpower = getMoneyNumber().getValue() + getPowerNumber().getValue() + getMagicNumber().getValue();
        potetonCount = 0;
        boolean potetonCheck = false;

        Set<String> uniqueNames = new HashSet<>();
        Map<String, Integer> nameCountMap = new HashMap<>();  // 名前ごとのカウント用

        for (cardData item : playerdata) {
            uniqueNames.add(item.getName());
            sumcard++;

            // カウント系の処理を一度でまとめる
            switch (item.getName()) {
                case "パンの妖精　コッペン":
                    koppenCount++;
                    break;
                case "パン屋":
                    panyaCount++;
                    break;
                case "酒場":
                    sakabaCount++;
                    break;
                case "ケーキ屋":
                    cakeCount++;
                    break;
                case "ポテトン":
                    potetonCount++;
                    break;
                default:
                    break;
            }

            // 特殊タグがある場合にsumbookを計算
            if (item.getTag() % 10 == 3) {
                sumbook++;
            }

            // カードの力（power）を加算
            powerCount += item.getPower();

            // 名前ごとの勝利ポイント計算用
            nameCountMap.put(item.getName(), nameCountMap.getOrDefault(item.getName(), 0) + 1);
        }

        // 名前ごとに勝利ポイントを計算
        for (cardData item : playerdata) {
            int vp = item.getVp();
            switch (item.getName()) {
                case "パンの妖精　コッペン":
                    vp = koppenCount;
                    break;
                case "脱法パン屋":
                    vp = panyaCount * 2 + 1;
                    break;
                case "麗しき女王像":
                    vp = powerCount / 4;
                    break;
                case "破壊された女王像":
                    vp = uniqueNames.size() / 3;
                    break;
                case "天地開闢のパン屋":
                    vp = 5 + panyaCount;
                    break;
                case "英雄神話の酒屋":
                    vp = 5 + sakabaCount;
                    break;
                case "世界豊穣のケーキ屋":
                    vp = 5 + cakeCount;
                    break;
                case "幻虹竜":
                    vp = sumpower / 3;
                    break;
                case "禁断の魔法研究所":
                    vp = sumbook * 2;
                    break;
                case "天へと至るエスカリエ":
                    vp = (sumcard / 3) * 2;
                    break;
                default:
                    break;
            }
            item.setVp(vp);  // カードごとにVP設定
            totalVictoryPoints += vp;
        }

        // 特別なカード「闇金庫」「女王のなみだ」などに対する勝利ポイント計算
        for (cardData item : playerdata) {
            if (item.getName().equals("闇金庫")&&moneyCheck == false) {
                totalVictoryPoints += moneyCount;
                moneyCheck = true;
            }
            if (item.getName().equals("女王のなみだ")&&tearCheck == false) {
                totalVictoryPoints += tearCount / 2;
                tearCheck = true;
            }
            if (item.getName().equals("闇落ち貴族")&&nobleCheck == false) {
                totalVictoryPoints += nobleCount;
                nobleCheck = true;
            }
            if (item.getName().equals("ダークコッペン")&&diBreCheck == false) {
                totalVictoryPoints += diceBreadCount;
                diBreCheck = true;
            }
            if (item.getName().equals("ポテトン")&&potetonCheck == false){
                totalVictoryPoints += potetonCount / 2;
                potetonCheck = true;
            }
        }

        setVictoryPoints(totalVictoryPoints);
        return totalVictoryPoints;
    }

}
//第二弾のみ開始->第三弾なかったわ
//破壊像