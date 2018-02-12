package ru.geekbrains.stargame.ship;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class EnemyShip extends Ship{

    private Vector2 v0 = new Vector2(0.0f, -0.1f);


    public EnemyShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("enemy1"), 1, 2, 3);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0, -0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 0.2f;
        this.bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v0, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
//        if (isOutside(worldBounds)) {
//            setDestroyed(true);
//        }
    }

    public void set(float height,
                    Rect worldBounds) {
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight()); // случайная координита появления по оси Х
        float posY = worldBounds.getTop() + this.getHeight(); // появление сверху за кадром
        pos.set(posX, posY); // установка вектора позиции
    }
}
