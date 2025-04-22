package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.*;
import com.bootcamp.demo.data.game.MStat;
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
    @Getter
    private ObjectFloatMap<MStat> statsMap = new ObjectFloatMap<>();


    @Override
    public void write (Json json) {
        json.writeValue("n", name);
        json.writeValue("l", level);
        json.writeValue("r", rarity.name());
        json.writeValue("s", statsMap);
    }

    @Override
    public void read (Json json, JsonValue jsonValue) {
        name = jsonValue.getString("n");
        level = jsonValue.getInt("l");
        rarity = Rarity.valueOf(jsonValue.getString("r").toUpperCase(Locale.ENGLISH));
        statsMap.clear();
        JsonValue stats = jsonValue.getChild("s");
        for (JsonValue stat : stats) {
            String[] stringArray = stat.asStringArray();
            statsMap.put(MStat.valueOf(stringArray[0]), Float.valueOf(stringArray[1]));
        }
    }
}
