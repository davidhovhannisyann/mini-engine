package com.bootcamp.demo.widgets.looting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.data.game.GameData;
import com.bootcamp.demo.data.game.MilitaryGearGameData;
import com.bootcamp.demo.data.game.MilitaryGearSlot;
import com.bootcamp.demo.data.game.Rarity;
import com.bootcamp.demo.data.save.MilitaryGearSaveData;
import com.bootcamp.demo.engine.*;
import com.bootcamp.demo.engine.widgets.BorderedTable;
import com.bootcamp.demo.localization.GameFont;
import com.bootcamp.demo.managers.API;

import java.util.Locale;

public class MilitaryGearWidget extends BorderedTable {

    private final Label levelLabel;
    private final Image gearIcon;
    private final Cell<Image> iconCell;
    private final Image tierIcon;
    private final Image starIcon;
    private final Table overlay;
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

        this.overlay = new Table();
        overlay.add(starIcon).expand().left().top().size(50).pad(15);
        overlay.row();
        overlay.add(levelLabel).expand().left().bottom().padBottom(15).padLeft(20);
        overlay.add(tierIcon).expand().right().bottom().padRight(20).padBottom(15).size(50);
        overlay.setFillParent(true);

        iconCell = add(gearIcon).grow().pad(35);
        addActor(overlay);

        overlay.setVisible(false);
    }

    public void setData (MilitaryGearSaveData gearData) {
        overlay.setVisible(true);

        this.rarity = gearData.getRarity();
        setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
        levelLabel.setText("Lv. " + gearData.getLevel());

        ObjectMap<String, MilitaryGearGameData> militaryGearMap = API.get(GameData.class).getMilitaryGearsGameData().getMilitaryGearMap();
        MilitaryGearGameData militaryGearGameData = militaryGearMap.get(gearData.getName());
        gearIcon.setDrawable(militaryGearGameData.getIcon());
    }

    public void setDefault (MilitaryGearSlot slot) {
        String file = slot.name().toLowerCase(Locale.ENGLISH);
        gearIcon.setDrawable(Resources.getDrawable("gear/default/" + file, ColorLibrary.COLORS.GRAY_BROWN.getColor()));
    }
}
