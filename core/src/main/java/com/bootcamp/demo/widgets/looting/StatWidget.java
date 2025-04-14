package com.bootcamp.demo.widgets.looting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bootcamp.demo.engine.Labels;
import com.bootcamp.demo.localization.GameFont;
import lombok.Getter;

public class StatWidget extends Table {

    private Label statLabel;
    private Label valueLabel;
    private MStat stat;

    public StatWidget () {
        this.statLabel = Labels.make(GameFont.BOLD_22, Color.BLACK);
        this.valueLabel = Labels.make(GameFont.BOLD_22, Color.WHITE);

        add(statLabel).expand().left();
        add(valueLabel).expand().right();
    }

    // Fake setData
    public void setData (MStat stat, float value) {
        this.stat = stat;
        statLabel.setText(stat.getTitle());
        valueLabel.setText(String.valueOf(value));
    }

    // Fake enum just to make this work
    public enum MStat {
        HP("HP"),
        ATK("ATK"),
        STUN("STUN"),
        REGEN("REGEN"),
        CRIT("CRIT"),
        POISON("POISON"),
        STEAL("STEAL"),
        COMBO("COMBO"),
        DODGE("DODGE");

        @Getter
        private String title;

        MStat (String title) {
            this.title = title;
        }
    }

}
