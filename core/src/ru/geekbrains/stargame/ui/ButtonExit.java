package ru.geekbrains.stargame.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.engine.ActionListener;
import ru.geekbrains.stargame.engine.ScaledTouchUpButton;
import ru.geekbrains.stargame.engine.math.Rect;


public class ButtonExit extends ScaledTouchUpButton{

    public ButtonExit(TextureAtlas atlas, float pressScale, ActionListener actionListener) {
        super(atlas.findRegion("btExit"), pressScale, actionListener);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
