package ru.geekbrains.stargame.engine.pool;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ru.geekbrains.stargame.engine.Sprite;

public abstract class SpritesPool <T extends Sprite> {

    // список активных объектов
    protected final List<T> activeObjects = new LinkedList<T>();

    // список свободных объектов
    protected final List<T> freeObjects = new ArrayList<T>();

    // инициализация объекта нужного класса
    protected abstract T newObject();

/*     метод который запрашивает из пула новые объекты
     метод проверяет есть ли в списке свободных объектов свободный объект,
     возвращает этот объект, этот объект перемещается в список активных объектов,
     если свободного объекта нет, то метод создает новый объект и
     добавляет его в список*/
    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        }else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        return object;
    }

    // обновление всех объектов
    public void updateActiveObjects(float delta) {
        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).update(delta);
        }
    }

    // отрисовка объектов из пула
    public void drawActiveObjects(SpriteBatch batch) {
        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).draw(batch);
        }
    }

    // удалить все объекты помеченные "на удаление"
    public void freeAllDestroyedObjects() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.setDestroyed(false);
            }
        }
    }

    // метод помещает из списка активных объектов в список ожидаемых
    public void free(T object) {
        if (!activeObjects.remove(object)) {
            throw new RuntimeException("попытка удаления несуществующего объекта");
        }
        freeObjects.add(object);
    }

    // очищение ссылок на объекты
    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }
}
