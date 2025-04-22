package com.bootcamp.demo.data.game;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import lombok.Getter;

import java.util.Locale;

public class TacticalItemData extends MItemData{
    @Getter
    private final static ObjectMap<Rarity, Integer> rarityPassiveStatMap = new ObjectMap<>();
    private static final float DELTA_ACTIVE_LEVEL = 0.5f;
    private static final float DELTA_PASSIVE_LEVEL = 15f;

    @Getter
    private Rarity rarity;
    @Getter
    private String title;
    @Getter
    private ObjectMap<MStat, Float> stats = new ObjectMap<>();

    @Override
    public void load (XmlReader.Element rootXml) {
        super.load(rootXml);
        String rarity = rootXml.getAttribute("rarity");
        this.rarity = Rarity.valueOf(rarity.toUpperCase(Locale.ENGLISH));

        int childCount = rootXml.getChildCount();
        for (int i = 0; i < childCount; i++) {
            XmlReader.Element child = rootXml.getChild(i);
            MStat stat = MStat.valueOf(child.getAttribute("stat").toUpperCase(Locale.ENGLISH));
            float initialStatValue = child.getFloat("value");
            stats.put(stat, initialStatValue);
        }
        stats.put(MStat.HP, (float) rarityPassiveStatMap.get(this.rarity));
        stats.put(MStat.ATK, (float) rarityPassiveStatMap.get(this.rarity));
    }

    // Passive stats are based on Rarity
    static {
        rarityPassiveStatMap.put(Rarity.COMMON, 50);
        rarityPassiveStatMap.put(Rarity.RARE, 100);
        rarityPassiveStatMap.put(Rarity.EPIC, 150);
        rarityPassiveStatMap.put(Rarity.LEGENDARY, 200);
        rarityPassiveStatMap.put(Rarity.EXOTIC, 250);
    }

    public float calculateStatBasedOnLevel (MStat stat, int level) {
        if (!stats.containsKey(stat)) return 0;
        float v = stats.get(stat);
        if (stat == MStat.HP || stat == MStat.ATK) {
            return v + (level * DELTA_PASSIVE_LEVEL);
        }
        return v + (level + DELTA_ACTIVE_LEVEL);
    }

}
