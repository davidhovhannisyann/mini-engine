package com.bootcamp.demo.data.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import lombok.Getter;

public class TacticalsGameData implements IGameData {
    @Getter
    private final ObjectMap<String, TacticalItemData> tacticalsMap = new ObjectMap<>();
    public TacticalsGameData () {

    }

    @Override
    public void load (XmlReader.Element rootXml) {
        Array<XmlReader.Element> tacticals = rootXml.getChildrenByName("tactical");
        for (XmlReader.Element tactical : tacticals) {
            final TacticalItemData item = new TacticalItemData();
            item.load(tactical);
            tacticalsMap.put(item.getName(), item);
        }
    }


}
