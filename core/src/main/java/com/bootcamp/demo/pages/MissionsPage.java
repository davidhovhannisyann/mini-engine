package com.bootcamp.demo.pages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bootcamp.demo.engine.Squircle;
import com.bootcamp.demo.engine.widgets.OffsetButton;
import com.bootcamp.demo.pages.core.APage;

public class MissionsPage extends APage {

    @Override
    protected void constructContent (Table content) {
        final Table militarySegment = new Table();

        final Table header = new Table();
        header.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.WHITE));
        final Table power = new Table();
        power.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("d6d6d6")));
        header.add(power).grow().pad(20);

        final Table militaryContent = constructMilitarySegmentContent();

        // TODO: 08.04.25 Get header squircle from project
        militarySegment.add(header).size(400, 200).padBottom(-100);
        militarySegment.row();
        militarySegment.add(militaryContent).growX().height(1150).pad(0, 50, 0, 50);

        content.add(militarySegment).growX();
        debug();
    }

    public Table constructMilitarySegmentContent () {
        final Table segment = new Table();
        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.WHITE));

        final Table statSegment = constructStatSegment();
        final Table gearSegment = constructGearSegment();
        final Table buttonSegment = constructButtonSegment();

        segment.pad(20);
        segment.defaults().space(20).growX();
        segment.add(statSegment).height(300);
        segment.row();
        segment.add(gearSegment).grow();
        segment.row();
        segment.add(buttonSegment).height(200);
        return segment;
    }


    public Table constructStatSegment () {
        final Table segment = new Table();
        segment.pad(20);
        segment.defaults().space(20);
        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("d6d6d6")));

        final Table stats = new Table();
        stats.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("989898")));
        stats.pad(20);
        stats.defaults().grow().space(10);
        for (int i = 0; i < 3; i++) {
            final Table column = new Table();
            column.defaults().space(10).grow();
            for (int j = 0; j < 3; j++) {
                final Table row = new Table();
                row.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("797979")));
                column.add(row);
                column.row();
            }
            stats.add(column);
        }

        final Table futureButton = new Table();
        futureButton.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("989898")));

        segment.add(stats).grow();
        segment.add(futureButton).width(200).growY();

        return segment;
    }

    public Table constructGearSegment () {
        final Table segment = new Table();
        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("d6d6d6")));
        segment.pad(20);
        segment.defaults().space(20);

        final Table gearContainer = new Table();
        gearContainer.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("989898")));
        gearContainer.pad(20);
        gearContainer.defaults().space(20).grow();
        for (int i = 1; i < 7; i++) {
            final Table gear = new Table();
            gear.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("797979")));
            gearContainer.add(gear);
            if (i % 3 == 0) gearContainer.row();
        }

        final Table extraContainer = new Table();
        extraContainer.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("989898")));
        extraContainer.pad(20);
        final Table column1 = new Table();
        for (int i = 0; i < 2; i++) {
            final Table row = new Table();
            row.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("797979")));
            column1.add(row).grow().space(20);
            column1.row();
        }
        final Table column2 = new Table();
        column2.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("797979")));

        extraContainer.add(column1).grow().space(20);
        extraContainer.add(column2).grow();

        segment.add(gearContainer).grow();
        segment.add(extraContainer).width(500).growY();
        return segment;
    }

    public Table constructButtonSegment () {
        final Table segment = new Table();
        segment.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("d6d6d6")));
        segment.pad(20, 50, 20, 50);
        for (int i = 0; i < 3; i++) {
            final Table button = new Table();
            button.setBackground(Squircle.SQUIRCLE_35.getDrawable(Color.valueOf("989898")));
            segment.add(button).grow().space(40);
        }

        return segment;
    }
}
