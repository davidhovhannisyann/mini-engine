package com.bootcamp.demo.data.save;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.bootcamp.demo.data.game.MStat;
import lombok.Getter;

import java.util.Locale;

public class StatsSaveData implements Json.Serializable {

    @Getter
    private ObjectMap<MStat, StatSaveData> statsMap = new ObjectMap<>();

    @Override
    public void write (Json json) {
        MStat[] mStats = MStat.values();
        for (MStat mStat : mStats) {
            if (!statsMap.containsKey(mStat)) statsMap.put(mStat, new StatSaveData(mStat));
            json.writeValue(mStat.name(), statsMap.get(mStat).value);
        }
    }

    @Override
    public void read (Json json, JsonValue jsonValue) {
        statsMap.clear();
        for (JsonValue value : jsonValue) {
            StatSaveData data = json.readValue(StatSaveData.class, value);
            statsMap.put(data.getStat(), data);
        }
    }

    public class StatSaveData implements Json.Serializable{
        @Getter
        private MStat stat;
        @Getter
        private Float value;

        private StatSaveData(MStat stat) {
            this.stat = stat;
            this.value = 0f;
        }

        @Override
        public void write (Json json) {
            json.writeValue("s", value);
            json.writeValue("v", value);
        }

        @Override
        public void read (Json json, JsonValue jsonValue) {
            stat = MStat.valueOf(jsonValue.getString("s").toUpperCase(Locale.ENGLISH));
            value = jsonValue.getFloat("v");
        }
    }
}
