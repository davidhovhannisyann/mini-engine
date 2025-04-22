package com.bootcamp.demo.data.game;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import lombok.Getter;

public class MilitaryGearsGameData implements IGameData {

    @Getter
    private ObjectMap<String, MilitaryGearGameData> militaryGearMap = new ObjectMap<>();

    @Override
    public void load (XmlReader.Element rootXml) {
        // loop through each category (weapon, melee, head, etc.)
        final int categoryCount = rootXml.getChildCount();
        for (int i = 0; i < categoryCount; i++) {
            final XmlReader.Element categoryElement = rootXml.getChild(i);
            final String categoryName = categoryElement.getName();
            // iterate over each item inside the category
            final int itemCount = categoryElement.getChildCount();
            for (int j = 0; j < itemCount; j++) {
                final XmlReader.Element itemElement = categoryElement.getChild(j);
                MilitaryGearGameData gear = new MilitaryGearGameData();
                gear.load(itemElement);
                militaryGearMap.put(gear.getName(), gear);
            }
        }
    }
}
