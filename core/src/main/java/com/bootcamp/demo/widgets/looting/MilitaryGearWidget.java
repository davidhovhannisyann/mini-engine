package com.bootcamp.demo.widgets.looting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.data.game.Rarity;
import com.bootcamp.demo.engine.*;
import com.bootcamp.demo.engine.widgets.BorderedTable;
import com.bootcamp.demo.localization.GameFont;
import lombok.Getter;

public class MilitaryGearWidget extends BorderedTable {

    private final Label levelLabel;
    private final Image gearIcon;
    private final Image tierIcon;
    private final Image starIcon;
    private Rarity rarity;

    public MilitaryGearWidget () {
        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.LIGHT_GRAY.getColor()));
        setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
        setPressedScale(0.95f);

        gearIcon = new Image();
        gearIcon.setScaling(Scaling.fit);

        levelLabel = Labels.make(GameFont.BOLD_20, Color.WHITE, "Lv. 1");
        tierIcon = new Image(Resources.getDrawable("gear/tier"));
        starIcon = new Image(Resources.getDrawable("gear/star"));

        final Table overlay = new Table();
        overlay.add(starIcon).expand().left().top().size(50).pad(15);
        overlay.row();
        overlay.add(levelLabel).expand().left().bottom().padBottom(15).padLeft(20);
        overlay.add(tierIcon).expand().right().bottom().padRight(20).padBottom(15).size(50);
        overlay.setFillParent(true);

        add(gearIcon).grow().pad(25);
        addActor(overlay);
    }

    // Fake setData
    public void setData (Rarity rarity, GEAR_PARTS gear) {
        this.rarity = rarity;
        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
        gearIcon.setDrawable(Resources.getDrawable(gear.getDrawablePath()));
    }

    // Fake enum just to make this work
    public enum GEAR_PARTS {
        WEAPON("gear/ak47"),
        MELEE("gear/melee"),
        HEAD("gear/head"),
        BODY("gear/body"),
        GLOVES("gear/gloves"),
        FEET("gear/foot");

        @Getter
        private final String drawablePath;

        GEAR_PARTS (String drawablePath) {
            this.drawablePath = drawablePath;
        }
    }
}
