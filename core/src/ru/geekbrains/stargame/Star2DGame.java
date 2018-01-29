package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;

/**
 * Created by Denis on 28.01.2018.
 */

public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
