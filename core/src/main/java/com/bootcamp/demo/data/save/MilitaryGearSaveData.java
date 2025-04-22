package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.bootcamp.demo.data.game.Rarity;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

public class MilitaryGearSaveData implements Json.Serializable {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private int level;
    @Getter
    private Rarity rarity = Rarity.EPIC;

    @Override
    public void write (Json json) {
        json.writeValue("n", name);
        json.writeValue("l", level);
        json.writeValue("r", rarity.name());
    }

    @Override
    public void read (Json json, JsonValue jsonValue) {
        name = jsonValue.getString("n");
        level = jsonValue.getInt("l");
        rarity = Rarity.valueOf(jsonValue.getString("r").toUpperCase(Locale.ENGLISH));
    }
}
