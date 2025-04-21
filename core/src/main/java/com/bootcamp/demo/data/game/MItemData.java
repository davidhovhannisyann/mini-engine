package com.bootcamp.demo.data.game;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.XmlReader;
import com.bootcamp.demo.engine.Resources;
import lombok.Getter;

public class MItemData implements IGameData{

    @Getter
    private String name;
    @Getter
    private Drawable icon;
    @Getter
    private String title;

    @Override
    public void load (XmlReader.Element rootXml) {
        this.name = rootXml.getAttribute("name");
        this.icon = Resources.getDrawable(rootXml.getAttribute("icon"));
        this.title = rootXml.getAttribute("title");
    }
}
