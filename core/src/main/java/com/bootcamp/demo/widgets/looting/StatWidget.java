package com.bootcamp.demo.widgets.looting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bootcamp.demo.data.game.MStat;
import com.bootcamp.demo.engine.Labels;
import com.bootcamp.demo.localization.GameFont;
import lombok.Getter;

public class StatWidget extends Table {

    private Label statLabel;
    private Label valueLabel;
    @Getter
    private MStat stat;

    public StatWidget (MStat stat) {
        this.statLabel = Labels.make(GameFont.BOLD_22, Color.BLACK);
        this.valueLabel = Labels.make(GameFont.BOLD_22, Color.WHITE);

        add(statLabel).expand().left();
        add(valueLabel).expand().right();

        this.stat = stat;
    }

    public void setData (MStat stat, float value) {
        statLabel.setText(stat.name());
        if (stat.equals(MStat.HP) || stat.equals(MStat.ATK)) {
            valueLabel.setText(String.valueOf((int) value));
        } else {
            valueLabel.setText(String.valueOf(value) + "%");
        }
    }

}
