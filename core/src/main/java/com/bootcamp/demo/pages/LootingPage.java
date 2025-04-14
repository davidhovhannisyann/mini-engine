package com.bootcamp.demo.pages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.engine.*;
import com.bootcamp.demo.engine.widgets.*;
import com.bootcamp.demo.localization.GameFont;
import com.bootcamp.demo.pages.core.APage;
import com.bootcamp.demo.widgets.looting.MilitaryGearWidget;
import com.bootcamp.demo.widgets.looting.StatWidget;
import com.bootcamp.demo.widgets.looting.TacticalItemWidget;

import java.util.Random;

public class LootingPage extends APage {

    private static final int MAX_STATS = 9;
    private static final int STATS_PER_ROW = 3;


    // stat segment
    public WidgetsContainer<StatWidget> statsContainer;
    public Label powerLabel;

    @Override
    protected void constructContent (Table content) {
        final Table militarySegment = new Table();

        final BorderedTable header = constructHeader();
        final Table militaryContent = constructMilitarySegmentContent();


        // TODO: 09.04.25 Get header squircle from proj
        // TODO: 13.04.25 poxuy

        militarySegment.add(header).size(600, 200).padBottom(-100);
        militarySegment.row();
        militarySegment.add(militaryContent).grow();

        content.add(militarySegment).growX().expandY().bottom();
    }

    public BorderedTable constructHeader () {
        final BorderedTable header = new BorderedTable();
        header.setTouchable(Touchable.disabled);
        header.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.DARK_BROWN.getColor()));
        header.setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.WHITE.getColor()));

        final Table powerWrapper = new Table();
        final Image powerIcon = new Image(Resources.getDrawable("gear/fist"));
        powerIcon.setScaling(Scaling.fit);
        powerLabel = Labels.make(GameFont.BOLD_32, Color.WHITE, "420");
        powerWrapper.add(powerIcon).size(100);
        powerWrapper.add(powerLabel).padLeft(15);

        header.add(powerWrapper).expand().top();
        return header;
    }

    public Table constructMilitarySegmentContent () {
        final Table segment = new Table();

        final Table statSegment = constructStatSegment();
        final Table gearSegment = constructGearSegment();
        final Table buttonSegment = constructButtonSegment();

        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.WHITE.getColor()));
        segment.pad(20);
        segment.defaults().space(20).growX();
        segment.add(statSegment).grow();
        segment.row();
        segment.add(gearSegment).grow();
        segment.row();
        segment.add(buttonSegment).height(200);
        return segment;
    }


    public Table constructStatSegment () {
        final Table segment = new Table();

        statsContainer = new WidgetsContainer<>(300, 30, 3, 30, 70);
        statsContainer.pad(20);

        StatWidget.MStat[] stats = StatWidget.MStat.values();
        Random randomValue = new Random();
        for (int i = 0; i < stats.length; i++) {
            final StatWidget stat = new StatWidget();
            float value = randomValue.nextInt(0, 10);
            stat.setData(stats[i], value);
            statsContainer.add(stat);
        }

        final Image infoImage = new Image(Resources.getDrawable("gear/list"));
        infoImage.setScaling(Scaling.fit);

        final BorderedTable allStatsButton = new BorderedTable();
        allStatsButton.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.WHITE.getColor()));
        allStatsButton.setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
        allStatsButton.add(infoImage);
        allStatsButton.setOnClick(() -> {});


        segment.pad(20);
        segment.defaults().space(20);
        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.BROWN.getColor()));
        segment.add(statsContainer).expand();
        segment.add(allStatsButton).size(150).expand();

        return segment;
    }

    public Table constructGearSegment () {
        final Table segment = new Table();

        final MilitaryGearContainer militaryGearContainer = new MilitaryGearContainer();
        final Table extraSegment = constructExtraSegment();

        segment.add(militaryGearContainer).width(850);
        segment.add(extraSegment).growX().padLeft(20);

        return segment;
    }


    private Table constructExtraSegment () {
        final Table segment = new Table();

        TacticalItemContainer tacticalContainer = new TacticalItemContainer();
        final FlagContainer flagContainer = new FlagContainer();

        final Table column1 = new Table();
        column1.add(tacticalContainer).space(30);
        column1.row();
        column1.add(flagContainer).size(tacticalContainer.getPrefWidth());

        final PetContainer petContainer = new PetContainer();

        segment.add(column1).space(30);
        segment.add(petContainer).growY().size(column1.getPrefWidth(), column1.getPrefHeight());

        return segment;
    }

    public Table constructButtonSegment () {
        final Table segment = new Table();
        segment.pad(10, 20, 0, 20);
        segment.defaults().space(60).width(400).growY();

        final ImageTextOffsetButton shovelButton = new ImageTextOffsetButton(OffsetButton.Style.YELLOW_DARK, GameFont.BOLD_24, Color.WHITE, "Shovel");
        shovelButton.setIconDrawable("gear/shovel");

        final ImageTextOffsetButton lootButton = new ImageTextOffsetButton(OffsetButton.Style.GREEN_35, GameFont.BOLD_24, Color.WHITE, "LOOT");
        lootButton.setIconDrawable("gear/shovel");

        final ImageTextOffsetButton autoLootButton = new ImageTextOffsetButton(OffsetButton.Style.YELLOW_DARK, GameFont.BOLD_24, Color.WHITE, "Auto Loot");
        autoLootButton.setIconDrawable("gear/autoloot");
        autoLootButton.getLabelCell().padLeft(30);

        segment.add(shovelButton);
        segment.add(lootButton);
        segment.add(autoLootButton);

        return segment;
    }


    public class MilitaryGearContainer extends Table {
        final private WidgetsContainer<MilitaryGearWidget> gearContainer;

        public MilitaryGearContainer () {
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.GRAY.getColor()));
            padTop(20).padBottom(20);
            defaults().space(20);

            final Label skinSetLabel = Labels.make(GameFont.BOLD_22, Color.BLACK, "Incomplete Set");
            final Image skinSetInfoIcon = new Image(Resources.getDrawable("gear/info"));
            skinSetInfoIcon.setScaling(Scaling.fit);

            final BorderedTable skinSetInfoButton = new BorderedTable();
            skinSetInfoButton.setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
            skinSetInfoButton.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.WHITE.getColor()));
            skinSetInfoButton.add(skinSetInfoIcon).pad(5);

            final Table skinsetInfoTable = new Table();
            skinsetInfoTable.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.GRAY_BROWN.getColor()));
            skinsetInfoTable.add(skinSetLabel).expandX().right();
            skinsetInfoTable.add(skinSetInfoButton).expandX().right().size(100, 80);

            gearContainer = new WidgetsContainer<>(250, 250, 3, 30, 30);
            for (int i = 0; i < 6; i++) {
                MilitaryGearWidget.GEAR_PARTS[] values = MilitaryGearWidget.GEAR_PARTS.values();
                final MilitaryGearWidget gear = new MilitaryGearWidget();
                gear.setData(Rarity.COMMON, values[i]);
                gearContainer.add(gear);
            }

            add(skinsetInfoTable).height(70).width(gearContainer.getPrefWidth());
            row();
            add(gearContainer);
        }
    }

    public class TacticalItemContainer extends PressableTable {
        private final WidgetsContainer<TacticalItemWidget> container;
        private final Array<TacticalItemWidget> items;

        public TacticalItemContainer () {
            setPressedScale(0.93f);

            items = new Array<>();

            container = new WidgetsContainer<>(110, 110, 2, 10, 10);
            container.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.LIGHT_GRAY.getColor()));
            container.pad(10);

            for (int i = 0; i < 4; i++) {
                final TacticalItemWidget gear = new TacticalItemWidget();
                gear.setTouchable(Touchable.disabled);
                container.add(gear);
                items.add(gear);
            }

            for (int i = 0; i < items.size/2; i++) {
                items.get(i).setData(Rarity.EXOTIC);
            }

            add(container);
        }
    }

    public class FlagContainer extends BorderedTable {
        private Image flagIcon;

        public FlagContainer () {
            setPressedScale(0.93f);
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.LIGHT_GRAY.getColor()));
            setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
            pad(30);

            flagIcon = new Image();
            flagIcon.setScaling(Scaling.fit);

            add(flagIcon);
            //leaving setData in UI constructor as there is no setData logic at all, so ok for now,
            // will separate logic later
            setData(Rarity.COMMON);
        }

        public void setData (Rarity rarity) {
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
            flagIcon.setDrawable(Resources.getDrawable("gear/flag"));
        }
    }

    public class PetContainer extends BorderedTable {
        private final Image petIcon;

        public PetContainer () {
            setPressedScale(1);
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.LIGHT_GRAY.getColor()));
            setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));

            petIcon = new Image();
            petIcon.setScaling(Scaling.fit);

            final ImageTextOffsetButton petButton = new ImageTextOffsetButton(OffsetButton.Style.YELLOW_DARK);
            petButton.setIconDrawable("gear/peticon");

            //strange shit here with petIcon
            add(petIcon).height(350).expandY().bottom().padBottom(-120);
            row();
            add(petButton).expand().bottom().growX().height(170).pad(0, -10, -10, -10);

            //same here with logic
            setData(Rarity.LEGENDARY);
        }

        public void setData (Rarity rarity) {
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
            petIcon.setDrawable(Resources.getDrawable("gear/chicken"));

        }
    }
}
