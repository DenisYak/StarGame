package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.explosion.Explosion;
import ru.geekbrains.stargame.explosion.ExplosionPool;
import ru.geekbrains.stargame.ship.EnemyShip;
import ru.geekbrains.stargame.ship.EnemyShipPool;
import ru.geekbrains.stargame.ship.MainShip;
import ru.geekbrains.stargame.star.TrackingStar;


public class GameScreen extends Base2DScreen{

    private Background background;
    private Texture backgroundTexture;
    private static final int STAR_COUNT = 56;
    private static final float STAR_HEIGHT = 0.01f;
    private TextureAtlas atlas;
    private MainShip mainShip;
    private EnemyShip enemyShip;
    private Array<TrackingStar> starfield;

    private final BulletPool bulletPool = new BulletPool();
    private ExplosionPool explosionPool;
    private EnemyShipPool enemyShipPool;

    private Sound soundExplosion;
    private Music music;

    protected float reloadInterval = 3f; // время перезарядки
    protected float reloadTimer; // таймер для стрельбы

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        soundExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        backgroundTexture = new Texture("textures/space.png");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        mainShip = new MainShip(atlas, bulletPool);
//        enemyShip = new EnemyShip(atlas);
        starfield = new Array<TrackingStar>();
        for (int i = 0; i < STAR_COUNT; i++) {
            starfield.add(new TrackingStar(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), STAR_HEIGHT, mainShip.getV()));
        }
        this.explosionPool = new ExplosionPool(atlas, soundExplosion);
        this.enemyShipPool = new EnemyShipPool(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        deleteAllDestoyed();
        update(delta);
        draw();
    }

    public void deleteAllDestoyed() {
        bulletPool.freeAllDestroyedObjects();
        explosionPool.freeAllDestroyedObjects();
        enemyShipPool.freeAllDestroyedObjects();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for(TrackingStar o: starfield) {
            o.draw(batch);
        }
        mainShip.draw(batch);
//        enemyShip.draw(batch);
        enemyShipPool.drawActiveObjects(batch);
        bulletPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        batch.end();
    }

    public void update(float delta) {
        for(TrackingStar o: starfield) {
            o.update(delta);
        }
        bulletPool.updateActiveObjects(delta);
        explosionPool.updateActiveObjects(delta);
        enemyShipPool.updateActiveObjects(delta);
        mainShip.update(delta);
//        enemyShip.update(delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            enemyShipArrive();
        }
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        mainShip.resize(worldBounds);
//        enemyShip.resize(worldBounds);
        for(TrackingStar o: starfield) {
            o.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        enemyShipPool.dispose();
        soundExplosion.dispose();
        music.dispose();
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) { // перемещение корабля по клику
        mainShip.touchDown(touch, pointer);
        Explosion explosion = explosionPool.obtain();
        explosion.set(0.1f, touch);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) { // перемещение корабля по кнопкам
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    protected void enemyShipArrive() {
        EnemyShip enemyShip = enemyShipPool.obtain();
        enemyShip.set(0.15f);


    }
}
