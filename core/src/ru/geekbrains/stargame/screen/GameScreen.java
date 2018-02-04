package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import ru.geekbrains.stargame.engine.Base2DScreen;


public class GameScreen extends Base2DScreen{

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
