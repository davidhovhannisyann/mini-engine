package com.bootcamp.demo.engine.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.bootcamp.demo.engine.Labels;
import com.bootcamp.demo.engine.Resources;
import com.bootcamp.demo.localization.GameFont;
import lombok.Getter;

public class ImageTextOffsetButton extends OffsetButton {
    private Image icon;
    private Label textLabel;
    @Getter
    private Cell<Label> labelCell;
    @Getter
    private Cell<Image> iconCell;

    public ImageTextOffsetButton (Style style, GameFont font, Color color, String text) {
        //THIS CLASS GOOFY AHHHH, just made to use real quick
        // Will fix this shit later
        textLabel = Labels.make(font, color, text);
        build(style);
    }

    @Override
    protected void buildInner (Table container) {
        super.buildInner(container);
        icon = new Image();
        icon.setScaling(Scaling.fit);
        labelCell = frontTable.add(textLabel).expand().right();
        iconCell = frontTable.add(icon).pad(20,0,20,0);
    }

    public ImageTextOffsetButton (Style style) {
        super(style);
    }

    public void setText (String text) {
        textLabel.setText(text);
    }

    public void setFont (GameFont font) {
        textLabel.setStyle(Resources.getLabelStyle(font));
    }

    public void setLabelColor (Color color) {
        textLabel.setColor(color);
    }

    public void setIconDrawable(String drawablePath) {
        icon.setDrawable(Resources.getDrawable(drawablePath));
    }
}
