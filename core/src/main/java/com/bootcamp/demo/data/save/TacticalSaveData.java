package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.bootcamp.demo.data.game.MilitaryGearSlot;
import lombok.Getter;
import lombok.Setter;

public class TacticalSaveData implements Json.Serializable {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int level;
    @Getter @Setter
    private int count;


    @Override
    public void write (Json json) {
        json.writeValue("l", level);
        json.writeValue("c", count);
    }

    @Override
    public void read (Json json, JsonValue jsonValue) {
        level = jsonValue.getInt("l");
        count = jsonValue.getInt("c");
    }
}
