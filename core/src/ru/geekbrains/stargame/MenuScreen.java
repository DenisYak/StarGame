package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Base2DScreen;

/**
 * Created by Denis on 28.01.2018.
 */

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture background;
    private Texture ship;
    private Vector2 shipLocation;

    public MenuScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
//        background = new Texture("space.png");
//        background = new Texture("nebula.jpg");
        background = new Texture("bg.png");
        ship = new Texture("spaceship.png");
        shipLocation = new Vector2(50, 50);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            shipLocation.x = Gdx.input.getX() - ship.getWidth() / 2;
            shipLocation.y = Gdx.graphics.getHeight() - Gdx.input.getY() - ship.getHeight() / 2;
        }
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ship, shipLocation.x, shipLocation.y);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        background.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
