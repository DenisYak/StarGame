package ru.geekbrains.stargame.ship;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class EnemyShip extends Ship{

    private Vector2 v0 = new Vector2(0.0f, -0.1f);


    public EnemyShip(TextureRegion region, int rows, int cols, int frame) {
        super(region, rows, cols, frame);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v0, delta);
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
