package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.ship.Ship;
import ru.geekbrains.stargame.star.Star;


public class GameScreen extends Base2DScreen{

    private Background background;
    private Texture backgroundTexture;
    private static final int STAR_COUNT = 56;
    private static final float STAR_HEIGHT = 0.01f;
    private TextureAtlas atlas;
    private Array<Star> starfield;
    private Ship ship;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("space.png");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("mainAtlas.tpack");
        starfield = new Array<Star>();
        for (int i = 0; i < STAR_COUNT; i++) {
            starfield.add(new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), STAR_HEIGHT));
        }
        ship = new Ship(atlas);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for(Star star: starfield) {
            star.draw(batch);
        }
        ship.draw(batch);
        batch.end();
    }

    public void update(float delta) {
        for(Star star: starfield) {
            star.update(delta);
        }
        ship.update(delta);
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        ship.resize(worldBounds);
        for(Star star: starfield) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) { // перемещение корабля по клику
        ship.touchDown(touch, pointer);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        ship.touchUp(touch, pointer);
    }

    @Override
    protected void touchDragged(Vector2 touch, int pointer) { // перемещение корабля по драгу
        ship.touchDragged(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) { // перемещение корабля по кнопкам
        ship.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ship.keyUp(keycode);
        return false;
    }
}
