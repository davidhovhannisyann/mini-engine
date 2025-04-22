package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.bootcamp.demo.data.game.*;
import com.bootcamp.demo.managers.API;
import lombok.Getter;
import lombok.Setter;

public class SaveData {

    @Getter
    private final EquippedMilitaryGearsSaveData equippedMilitaryGearsSaveData = new EquippedMilitaryGearsSaveData();
    @Getter
    private final EquippedTacitcalsSaveData equippedTacitcalsSaveData = new EquippedTacitcalsSaveData();
    @Getter
    private static final ObjectMap<MStat, Float> statMap = new ObjectMap<>();

    static {
        for (MStat stat : MStat.values()) {
            statMap.put(stat, 0f);
        }
    }

    public void calculateCurrentStats () {
        calcuateTacticalStats();
        calculateMilitaryGearStats();
    }

    private void calcuateTacticalStats () {
        Array<TacticalSaveData> equippedTacitcals = equippedTacitcalsSaveData.getEquippedTacitcals();
        TacticalsGameData tacticalsGameData = API.get(GameData.class).getTacticalsGameData();
        ObjectMap<String, TacticalItemData> tacticalsMap = tacticalsGameData.getTacticalsMap();

        for (TacticalSaveData equippedTacitcal : equippedTacitcals) {
            TacticalItemData tacticalItemData = tacticalsMap.get(equippedTacitcal.getName());
            for (ObjectMap.Entry<MStat, Float> stat : tacticalItemData.getStats()) {
                float value = tacticalItemData.calculateStatBasedOnLevel(stat.key, equippedTacitcal.getLevel());
                updateStatValue(stat.key, value);
            }
        }
    }

    private void calculateMilitaryGearStats() {

    }

    private void updateStatValue(MStat stat, float value) {
        Float v = statMap.get(stat);
        statMap.put(stat, v + value);
    }
}
