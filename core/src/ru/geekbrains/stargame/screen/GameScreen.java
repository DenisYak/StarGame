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
    private TextureAtlas atlas;
    private TextureAtlas atlas2;
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
        atlas = new TextureAtlas("menuAtlas.tpack");
        atlas2 = new TextureAtlas("mainAtlas.tpack");
        starfield = new Array<Star>();
        for (int i = 0; i < 40; i++) {
            starfield.add(new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.02f));
        }
        ship = new Ship(atlas2);
        ship.setHeightProportion(0.1f);
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
        super.touchDown(touch, pointer);
        ship.pos.set(touch);
    }

    @Override
    protected void touchDragged(Vector2 touch, int pointer) { // перемещение корабля по драгу
        super.touchDragged(touch, pointer);
        ship.pos.set(touch);
    }

    @Override
    public boolean keyDown(int keycode) { // перемещение корабля по кнопкам
        Vector2 incrementX = new Vector2(0.02f, 0);
        Vector2 incrementY = new Vector2(0, 0.02f);
        if(keycode == Input.Keys.LEFT)
            ship.pos.set(ship.pos.cpy().sub(incrementX));
        if(keycode == Input.Keys.RIGHT)
            ship.pos.set(ship.pos.cpy().add(incrementX));
        if(keycode == Input.Keys.UP)
            ship.pos.set(ship.pos.cpy().add(incrementY));
        if(keycode == Input.Keys.DOWN)
            ship.pos.set(ship.pos.cpy().sub(incrementY));
        return true;
    }
}
