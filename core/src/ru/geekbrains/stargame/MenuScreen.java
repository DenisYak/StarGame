package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

/**
 * Created by Denis on 28.01.2018.
 */

public class MenuScreen extends Base2DScreen {

    private Texture backgroundTexture;
//    private Texture logo;
//    private Sprite logosprite;
    private Background background;

    private Texture ship;
    private Vector2 shipLocation;
    private Vector2 shipVelocity;

    public MenuScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();
//        logo = new Texture("badlogic.jpg");
//        logosprite = new Sprite(new TextureRegion(logo));
//        logosprite.setSize(1f, 1f);
//        background = new Texture("space.png");
//        background = new Texture("nebula.jpg");
        backgroundTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(backgroundTexture));
//        ship = new Texture("spaceship.png");
//        shipLocation = new Vector2(50, 50);
//        shipVelocity = new Vector2(50, 50);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//            shipLocation.x = Gdx.input.getX() - ship.getWidth() / 2;
//            shipLocation.y = Gdx.graphics.getHeight() - Gdx.input.getY() - ship.getHeight() / 2;
//        }
        batch.begin();
//        batch.draw(background, 0, 0);
//        batch.draw(logo, -0.5f, -0.5f, 1f, 1f);
//        batch.draw(ship, shipLocation.x, shipLocation.y);
//        logosprite.draw(batch);
        background.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
    }
}
