package com.bootcamp.demo.data.save;

import lombok.Getter;

public class SaveData {

    @Getter
    private final EquippedMilitaryGearsSaveData equippedMilitaryGearsSaveData = new EquippedMilitaryGearsSaveData();
    @Getter
    private final EquippedTacitcalsSaveData equippedTacitcalsSaveData = new EquippedTacitcalsSaveData();
    @Getter
    private final StatsSaveData statsSaveData = new StatsSaveData();
}
