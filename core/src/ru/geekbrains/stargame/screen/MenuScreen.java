package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
//    private Texture logo;
//    private Sprite logosprite;
    private Background background;
//    private Texture ship;
//    private Vector2 shipLocation;
//    private Vector2 shipVelocity;
    private static final float BUTTON_HEIGHT = 0.15f;
    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private TextureAtlas atlas;
    private Star star;

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
        atlas = new TextureAtlas("menuAtlas.tpack");
        buttonExit = new ButtonExit(atlas, BUTTON_PRESS_SCALE, this);
        buttonExit.setHeightProportion(BUTTON_HEIGHT);
        buttonPlay = new ButtonPlay(atlas, BUTTON_PRESS_SCALE, this);
        buttonPlay.setHeightProportion(BUTTON_HEIGHT);
        star = new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.02f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();

//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//            shipLocation.x = Gdx.input.getX() - ship.getWidth() / 2;
//            shipLocation.y = Gdx.graphics.getHeight() - Gdx.input.getY() - ship.getHeight() / 2;
//        }
//        batch.draw(background, 0, 0);
//        batch.draw(ship, shipLocation.x, shipLocation.y);
//        logosprite.draw(batch);
//        batch.draw(logo, -1f, -1f, 2f, 2f);

    }

    public void update(float delta) {
        star.update(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        star.draw(batch);
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        star.resize(worldBounds);
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
