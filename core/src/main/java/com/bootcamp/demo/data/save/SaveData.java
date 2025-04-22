package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.bootcamp.demo.data.game.*;
import com.bootcamp.demo.managers.API;
import lombok.Getter;

public class SaveData {

    @Getter
    private final EquippedMilitaryGearsSaveData equippedMilitaryGear = new EquippedMilitaryGearsSaveData();
    @Getter
    private final EquippedTacitcalsSaveData equippedTacticals = new EquippedTacitcalsSaveData();
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
        Array<TacticalSaveData> equippedTacitcals = this.equippedTacticals.getEquippedTacitcals();
        TacticalsGameData tacticalsGameData = API.get(GameData.class).getTacticalsGameData();
        ObjectMap<String, TacticalItemData> tacticalsMap = tacticalsGameData.getTacticalsMap();

        for (TacticalSaveData equippedTactical : equippedTacitcals) {
            TacticalItemData tacticalItemData = tacticalsMap.get(equippedTactical.getName());
            for (ObjectMap.Entry<MStat, Float> stat : tacticalItemData.getStats()) {
                float value = tacticalItemData.calculateStatBasedOnLevel(stat.key, equippedTactical.getLevel());
                updateStatValue(stat.key, value);
            }
        }
    }

    private void calculateMilitaryGearStats() {
        ObjectMap<MilitaryGearSlot, MilitaryGearSaveData> equippedMilitaryGears = equippedMilitaryGear.getEquippedMilitaryGears();
        MilitaryGearsGameData militaryGearsGameData = API.get(GameData.class).getMilitaryGearsGameData();
        for (ObjectMap.Entry<MilitaryGearSlot, MilitaryGearSaveData> gear : equippedMilitaryGears) {
            ObjectFloatMap<MStat> statsMap = gear.value.getStatsMap();
            for (ObjectFloatMap.Entry<MStat> mStat : statsMap) {
                updateStatValue(mStat.key, mStat.value);
            }
        }
    }

    private void updateStatValue(MStat stat, float value) {
        Float v = statMap.get(stat);
        statMap.put(stat, v + value);
    }
}
