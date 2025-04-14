package com.bootcamp.demo.presenters;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bootcamp.demo.engine.Labels;
import com.bootcamp.demo.engine.Resources;
import com.bootcamp.demo.engine.widgets.PressableTable;
import com.bootcamp.demo.events.core.EventListener;
import com.bootcamp.demo.events.core.EventModule;
import com.bootcamp.demo.localization.GameFont;
import com.bootcamp.demo.managers.API;
import com.bootcamp.demo.pages.LootingPage;
import com.bootcamp.demo.pages.core.APage;
import com.bootcamp.demo.pages.core.PageManager;
import lombok.Getter;
import lombok.Setter;

public class GameUI extends ScreenAdapter implements Disposable, EventListener {

    @Getter
    private final Stage stage;
    @Getter
    private final Table rootUI;
    @Getter
    private final Cell<APage> mainPageCell;

    @Getter
    @Setter
    private boolean buttonPressed;

    public GameUI (Viewport viewport) {
        API.Instance().register(GameUI.class, this);
        API.get(EventModule.class).registerListener(this);

        rootUI = new Table();
        rootUI.setFillParent(true);

        // init stage
        stage = new Stage(viewport);
        stage.addActor(rootUI);

        // construct
        mainPageCell = rootUI.add().grow();

//        final Table grid = constructGrid(3, 4);
//        rootUI.add(grid).padLeft(50).padRight(50);

        API.get(PageManager.class).show(LootingPage.class);

    }

    private Table constructGrid (int rows, int cols) {
        final Table grid = new Table();
        grid.setBackground(Resources.getDrawable("basics/white-squircle-35"));
        grid.defaults().size(300).space(20);
        grid.pad(20);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                PressableTable cell = new PressableTable();
                cell.setBackground(Resources.getDrawable("basics/white-squircle-35", Color.valueOf("74b151")));

                final Image icon = new Image(Resources.getDrawable("brainrot/bombordilo-crocodilo"));
                icon.setScaling(Scaling.fit);

                final Label label = Labels.make(GameFont.BOLD_40, Color.BLACK, String.valueOf(i * cols + j + 1));

                cell.add(label);
                cell.setOnClick(() -> {
                    cell.clearChildren();
                    cell.add(icon).grow();
                    cell.setPressedScale(1f);
                });

                grid.add(cell);
            }
            grid.row();
        }

        return grid;
    }


    @Override
    public void render (float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose () {
        stage.dispose();
    }
}
