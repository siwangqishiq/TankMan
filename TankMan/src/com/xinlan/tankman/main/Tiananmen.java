package com.xinlan.tankman.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tiananmen {
	public static final float width = 500;
	public static final float height = 100;
	private GameScreen context;
	protected Sprite sprite;
	private Texture texture;
	protected float x, y;

	public Tiananmen(GameScreen context) {
		this.context = context;
		x = GameScreen.SCREEN_WIDTH / 2 - width / 2;
		y = GameScreen.SCREEN_HEIGHT-height;
		texture = new Texture(Gdx.files.internal("tiananmen.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(x, y, width, height);
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	public void dispose() {
		texture.dispose();
	}
}//end class
