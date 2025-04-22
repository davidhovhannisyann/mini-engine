package com.bootcamp.demo.data.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.bootcamp.demo.managers.API;
import lombok.Getter;

public class GameData {

    private final XmlReader xmlReader = new XmlReader();
    @Getter
    private final MilitaryGearsGameData militaryGearsGameData = new MilitaryGearsGameData();
    @Getter
    private final TacticalsGameData tacticalsGameData = new TacticalsGameData();
    public GameData () {

    }

    public void load () {
        militaryGearsGameData.load(xmlReader.parse(Gdx.files.internal("data/military-gear.xml")));
        tacticalsGameData.load(xmlReader.parse(Gdx.files.internal("data/tacticals.xml")));
    }
}
