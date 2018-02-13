package ru.geekbrains.stargame.ship;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.pool.SpritesPool;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final TextureAtlas enemyShipAtlas;
    private BulletPool bulletPool;


    public EnemyShipPool(TextureAtlas atlas, BulletPool bulletPool) {
        this.enemyShipAtlas = atlas;
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(enemyShipAtlas, bulletPool);
    }

    @Override
    protected void debugLog() {
        System.out.println("EnemyShipPool change activ/free:" + activeObjects.size() + "/" + freeObjects.size());
    }
}
