package com.bootcamp.demo.pages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.data.game.MStat;
import com.bootcamp.demo.data.game.MilitaryGearSlot;
import com.bootcamp.demo.data.game.Rarity;
import com.bootcamp.demo.data.save.MilitaryGearSaveData;
import com.bootcamp.demo.data.save.SaveData;
import com.bootcamp.demo.data.save.TacticalSaveData;
import com.bootcamp.demo.engine.*;
import com.bootcamp.demo.engine.widgets.*;
import com.bootcamp.demo.localization.GameFont;
import com.bootcamp.demo.managers.API;
import com.bootcamp.demo.pages.core.APage;
import com.bootcamp.demo.widgets.looting.MilitaryGearWidget;
import com.bootcamp.demo.widgets.looting.StatWidget;
import com.bootcamp.demo.widgets.looting.TacticalItemWidget;

public class LootingPage extends APage {
    public Label powerLabel;
    // stat segment
    public WidgetsContainer<StatWidget> statsContainer;
    private TacticalItemContainer tacticalContainer;
    private MilitaryGearContainer militaryGearContainer;

    @Override
    protected void constructContent (Table content) {

        final BorderedTable header = constructHeader();
        final Table militaryContent = constructMilitarySegmentContent();

        final Table militarySegment = new Table();
        militarySegment.add(header).size(600, 100).padBottom(-10);
        militarySegment.row();
        militarySegment.add(militaryContent).grow();

        content.add(militarySegment).growX().expandY().bottom();
    }

    public BorderedTable constructHeader () {
        final Table powerWrapper = new Table();
        final Image powerIcon = new Image(Resources.getDrawable("gear/fist"));
        powerIcon.setScaling(Scaling.fit);
        powerLabel = Labels.make(GameFont.BOLD_32, Color.WHITE, "420");
        powerWrapper.add(powerIcon).size(100);
        powerWrapper.add(powerLabel).padLeft(15);

        final BorderedTable header = new BorderedTable();
        header.setTouchable(Touchable.disabled);
        header.setBackground(Squircle.SQUIRCLE_35_TOP.getDrawable(ColorLibrary.COLORS.DARK_BROWN.getColor()));
        header.setBorderDrawable(Squircle.SQUIRCLE_35_BORDER_TOP.getDrawable(ColorLibrary.COLORS.WHITE.getColor()));
        header.add(powerWrapper).expand();
        return header;
    }

    public Table constructMilitarySegmentContent () {

        final Table statSegment = constructStatSegment();
        final Table gearSegment = constructGearSegment();
        final Table buttonSegment = constructButtonSegment();

        final Table segment = new Table();
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
        statsContainer = new WidgetsContainer<>(300, 30, 3, 30, 70);
        statsContainer.pad(20);

        MStat[] stats = MStat.values();
        for (MStat stat : stats) {
            final StatWidget statWidget = new StatWidget(stat);
            statsContainer.add(statWidget);
        }

        final Image infoImage = new Image(Resources.getDrawable("gear/list"));
        infoImage.setScaling(Scaling.fit);

        final BorderedTable allStatsButton = new BorderedTable();
        allStatsButton.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.WHITE.getColor()));
        allStatsButton.setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
        allStatsButton.add(infoImage);
        allStatsButton.setOnClick(() -> {
        });

        final Table segment = new Table();
        segment.pad(20);
        segment.defaults().space(20);
        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.BROWN.getColor()));
        segment.add(statsContainer).expand();
        segment.add(allStatsButton).size(150).expand();

        return segment;
    }

    public Table constructGearSegment () {

        militaryGearContainer = new MilitaryGearContainer();
        final Table extraSegment = constructExtraSegment();

        final Table segment = new Table();
        segment.add(militaryGearContainer);
        segment.add(extraSegment).growX().padLeft(20);

        return segment;
    }


    private Table constructExtraSegment () {

        tacticalContainer = new TacticalItemContainer();
        final FlagContainer flagContainer = new FlagContainer();

        final Table column1 = new Table();
        column1.add(tacticalContainer).space(30);
        column1.row();
        column1.add(flagContainer).size(tacticalContainer.getPrefWidth());

        final PetContainer petContainer = new PetContainer();

        final Table segment = new Table();
        segment.add(column1).space(30);
        segment.add(petContainer).grow();

        return segment;
    }

    public Table constructButtonSegment () {
        final ImageTextOffsetButton shovelButton = new ImageTextOffsetButton(OffsetButton.Style.YELLOW_DARK, GameFont.BOLD_24, Color.WHITE, "Shovel");
        shovelButton.setIconDrawable("gear/shovel");

        final ImageTextOffsetButton lootButton = new ImageTextOffsetButton(OffsetButton.Style.GREEN_35, GameFont.BOLD_24, Color.WHITE, "LOOT");
        lootButton.setIconDrawable("gear/shovel");

        final ImageTextOffsetButton autoLootButton = new ImageTextOffsetButton(OffsetButton.Style.YELLOW_DARK, GameFont.BOLD_24, Color.WHITE, "Auto Loot");
        autoLootButton.setIconDrawable("gear/autoloot");
        autoLootButton.getLabelCell().padLeft(30);

        final Table segment = new Table();
        segment.pad(10, 20, 0, 20);
        segment.defaults().space(60).width(400).growY();
        segment.add(shovelButton);
        segment.add(lootButton);
        segment.add(autoLootButton);

        return segment;
    }


    public static class MilitaryGearContainer extends Table {
        final private WidgetsContainer<MilitaryGearWidget> gearContainer;

        public MilitaryGearContainer () {
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
            for (int i = 0; i < MilitaryGearSlot.values().length; i++) {
                final MilitaryGearWidget gear = new MilitaryGearWidget();
                gearContainer.add(gear);
            }

            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.GRAY.getColor()));
            pad(20);
            defaults().space(20);
            add(skinsetInfoTable).height(70).grow();
            row();
            add(gearContainer);
        }

        public void setData () {
            ObjectMap<MilitaryGearSlot, MilitaryGearSaveData> equippedMilitaryGears = API.get(SaveData.class).getEquippedMilitaryGearsSaveData().getEquippedMilitaryGears();
            Array<MilitaryGearWidget> widgets = gearContainer.getWidgets();
            MilitaryGearSlot[] values = MilitaryGearSlot.values();

            for (int i = 0; i < values.length; i++) {
                MilitaryGearSaveData militaryGearSaveData = equippedMilitaryGears.get(values[i]);
                if (militaryGearSaveData == null) {
                    widgets.get(i).setDefault(values[i]);
                    continue;
                }
                widgets.get(i).setData(militaryGearSaveData);
            }
        }
    }

    public static class TacticalItemContainer extends PressableTable {
        private final WidgetsContainer<TacticalItemWidget> container;
        private final Array<TacticalItemWidget> items;

        public TacticalItemContainer () {
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

            setPressedScale(0.93f);
            add(container);
        }

        public void setData () {
            Array<TacticalSaveData> equippedTacitcals = API.get(SaveData.class).getEquippedTacitcalsSaveData().getEquippedTacitcals();
            Array<TacticalItemWidget> widgets = container.getWidgets();
            for (int i = 0; i < equippedTacitcals.size; i++) {
                widgets.get(i).setData(equippedTacitcals.get(i));
            }
        }
    }

    public static class FlagContainer extends BorderedTable {
        private Image icon;

        public FlagContainer () {
            icon = new Image();
            icon.setScaling(Scaling.fit);

            setPressedScale(0.93f);
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.LIGHT_GRAY.getColor()));
            setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
            pad(30);

            add(icon);
            //leaving setData in UI constructor as there is no setData logic at all, so ok for now,
            // will separate logic later
            setData(Rarity.COMMON);
        }

        public void setData (Rarity rarity) {
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
            icon.setDrawable(Resources.getDrawable("gear/flag"));
        }
    }

    public static class PetContainer extends BorderedTable {
        private final Image icon;

        public PetContainer () {
            icon = new Image();
            icon.setScaling(Scaling.fit);

            final ImageTextOffsetButton petButton = new ImageTextOffsetButton(OffsetButton.Style.YELLOW_DARK);
            petButton.setIconDrawable("gear/peticon");

            setPressedScale(1);
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.COLORS.LIGHT_GRAY.getColor()));
            setBorderDrawable(Squircle.SQUIRCLE_35_BORDER.getDrawable(ColorLibrary.COLORS.BORDER.getColor()));
            add(icon).expandY().bottom().padBottom(-120);
            row();
            add(petButton).expand().bottom().growX().height(170).pad(0, -10, -10, -10);

            //same here with logic
            setData(Rarity.LEGENDARY);
        }

        public void setData (Rarity rarity) {
            setBackground(Squircle.SQUIRCLE_35.getDrawable(ColorLibrary.getRarityColors().get(rarity)));
            icon.setDrawable(Resources.getDrawable("gear/chicken"));

        }
    }

    public void setStatsData () {
        API.get(SaveData.class).calculateCurrentStats();
        ObjectMap<MStat, Float> statMap = SaveData.getStatMap();
        for (StatWidget widget : statsContainer.getWidgets()) {
            widget.setData(widget.getStat(), statMap.get(widget.getStat()));
        }
    }

    public void reEvaluateContainers () {
        tacticalContainer.setData();
        militaryGearContainer.setData();
        setStatsData();
    }

    @Override
    public void show (Runnable onComplete) {
        super.show(onComplete);
        reEvaluateContainers();
    }
}
