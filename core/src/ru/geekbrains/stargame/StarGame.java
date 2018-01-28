package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Texture background;
	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("nebula.jpg");
		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img, 20, 20, 100, 100);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(background, 0, 0);
//		for (int i = 0; i < 640; i++) {
//            batch.draw(img, i, 0);
//        }
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
