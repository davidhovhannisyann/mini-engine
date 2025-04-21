package com.bootcamp.demo.widgets.looting;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.engine.ColorLibrary;
import com.bootcamp.demo.data.game.Rarity;
import com.bootcamp.demo.engine.Resources;
import com.bootcamp.demo.engine.Squircle;
import com.bootcamp.demo.engine.widgets.BorderedTable;

public class TacticalItemWidget extends BorderedTable {
    private final Image itemIcon;
    private Rarity rarity;

    public TacticalItemWidget () {
        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.GRAY_BROWN.getColor()));

        itemIcon = new Image();
        itemIcon.setScaling(Scaling.fit);

        add(itemIcon).pad(10);
    }

    // Fake setData
    public void setData (Rarity rarity) {
        this.rarity = rarity;
        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
        setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));

        itemIcon.setDrawable(Resources.getDrawable("gear/grenade"));
    }
}
