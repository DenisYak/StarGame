package ru.geekbrains.stargame.star;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class Star extends Sprite{

    private final Vector2 v = new Vector2(); // скорость звезды
    private Rect worldBounds; // для определения звезды относительно пересечения границ экрана

    public Star(TextureAtlas atlas, float vx, float vy, float height) {
        super(atlas.findRegion("star"));
        v.set(vx, vy); // вектор скорости по х и у
        setHeightProportion(Rnd.nextFloat(height, height * 3)); // пропорции звезды
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
    }

    protected void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight()); // случайная координита появления по оси Х
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop()); // случайная координита появления по оси Y
        pos.set(posX, posY); // установка вектора позиции

    }
}
