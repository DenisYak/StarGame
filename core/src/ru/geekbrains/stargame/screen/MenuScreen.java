package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.engine.ActionListener;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.star.Star;
import ru.geekbrains.stargame.ui.ButtonExit;
import ru.geekbrains.stargame.ui.ButtonPlay;


public class MenuScreen extends Base2DScreen implements ActionListener{

    private Texture backgroundTexture;
    private Background background;
    private static final int STAR_COUNT = 256;
    private static final float STAR_HEIGHT = 0.01f;
    private static final float BUTTON_HEIGHT = 0.15f;
    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private TextureAtlas atlas;
    private Array<Star> starfield;
    private Star star;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("menuAtlas.tpack");
        starfield = new Array<Star>();
        for (int i = 0; i < STAR_COUNT; i++) {
            starfield.add(new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), STAR_HEIGHT));
        }
        buttonExit = new ButtonExit(atlas, BUTTON_PRESS_SCALE, this);
        buttonExit.setHeightProportion(BUTTON_HEIGHT);
        buttonPlay = new ButtonPlay(atlas, BUTTON_PRESS_SCALE, this);
        buttonPlay.setHeightProportion(BUTTON_HEIGHT);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for(Star star: starfield) {
            star.update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        background.draw(batch);
        for(Star star: starfield) {
            star.draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for(Star star: starfield) {
            star.resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
        buttonExit.touchUp(touch, pointer);
        buttonPlay.touchUp(touch, pointer);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        buttonExit.touchDown(touch, pointer);
        buttonPlay.touchDown(touch, pointer);
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonExit) {
            Gdx.app.exit();
        } else if (src == buttonPlay) {
            game.setScreen(new GameScreen(game));
        } else {
            throw new RuntimeException("Unknown src " + src);
        }
    }

}
