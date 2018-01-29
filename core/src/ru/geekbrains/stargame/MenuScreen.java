package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.geekbrains.stargame.engine.Base2DScreen;

/**
 * Created by Denis on 28.01.2018.
 */

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture background;

    public MenuScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
//        background = new Texture("space.png");
        background = new Texture("bg.png");
//        background = new Texture("nebula.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        background.dispose();
    }
}
