package com.bootcamp.demo.data.game;

import com.badlogic.gdx.utils.XmlReader;
import lombok.Getter;

public class MilitaryGearGameData extends MItemData{
    @Getter
    private Rarity rarity;
    @Override
    public void load (XmlReader.Element rootXml) {
        super.load(rootXml);
    }
}
