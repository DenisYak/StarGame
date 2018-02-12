package ru.geekbrains.stargame.ship;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.engine.pool.SpritesPool;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final TextureRegion enemyShipRegion;

    public EnemyShipPool(TextureAtlas atlas) {
        enemyShipRegion = atlas.findRegion("enemy1");
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(enemyShipRegion, 1, 2, 3);
    }
}
