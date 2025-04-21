package com.bootcamp.demo.data.game;

import com.badlogic.gdx.utils.XmlReader;
import lombok.Getter;

import java.util.Locale;

public class TacticalItemData extends MItemData{
    @Getter
    private Rarity rarity;
    @Getter
    private String title;
    @Override
    public void load (XmlReader.Element rootXml) {
        super.load(rootXml);
        String rarity = rootXml.getAttribute("rarity");
        this.rarity = Rarity.valueOf(rarity.toUpperCase(Locale.ENGLISH));
        this.title = rootXml.getAttribute("title");
    }
}
