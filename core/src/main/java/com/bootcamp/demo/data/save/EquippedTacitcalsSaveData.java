package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import lombok.Getter;

public class EquippedTacitcalsSaveData implements Json.Serializable{
    @Getter
    private Array<TacticalSaveData> equippedTacitcals = new Array<>();
    @Override
    public void write (Json json) {
        for (TacticalSaveData equippedTacitcal : equippedTacitcals) {
            json.writeValue(equippedTacitcal.getName(), equippedTacitcal);
        }
    }

    @Override
    public void read (Json json, JsonValue jsonValue) {
        equippedTacitcals.clear();

        for (JsonValue value : jsonValue) {
            TacticalSaveData tactical = json.readValue(TacticalSaveData.class, value);
            equippedTacitcals.add(tactical);
        }
    }
}
