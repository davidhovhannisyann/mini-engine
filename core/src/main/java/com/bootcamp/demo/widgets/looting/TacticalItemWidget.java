package com.bootcamp.demo.widgets.looting;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.data.game.GameData;
import com.bootcamp.demo.data.game.TacticalItemData;
import com.bootcamp.demo.data.save.TacticalSaveData;
import com.bootcamp.demo.engine.ColorLibrary;
import com.bootcamp.demo.data.game.Rarity;
import com.bootcamp.demo.engine.Squircle;
import com.bootcamp.demo.engine.widgets.BorderedTable;
import com.bootcamp.demo.managers.API;

import java.util.Locale;

public class TacticalItemWidget extends BorderedTable {
    private final Image icon;

    public TacticalItemWidget () {
        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.GRAY_BROWN.getColor()));

        icon = new Image();
        icon.setScaling(Scaling.fit);

        add(icon).pad(10);
    }

    // Fake setData
    public void setData (TacticalSaveData data) {
        ObjectMap<String, TacticalItemData> tacticalsArray = API.get(GameData.class).getTacticalsGameData().getTacticalsMap();
        TacticalItemData tacticalItemData = tacticalsArray.get(data.getName());
        Rarity rarity =  tacticalItemData.getRarity();

        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
        setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));

        icon.setDrawable(tacticalItemData.getIcon());
    }
}
