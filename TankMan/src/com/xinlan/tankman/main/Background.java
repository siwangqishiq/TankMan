package com.xinlan.tankman.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	public static final float width = GameScreen.SCREEN_WIDTH;
	public static final float height = GameScreen.SCREEN_HEIGHT;
	public static final int WIDTH_NUM = 25;
	public static final int HEIGHT_NUM = 15;

	private GameScreen context;
	private SpriteCache mSpriteCache;
	private int cacheIndex;
	private Texture cubeTexture;
	private TextureRegion region;

	public Background(GameScreen context) {
		this.context = context;
		mSpriteCache = new SpriteCache();
		cubeTexture = new Texture(Gdx.files.internal("bg_cube.png"));
		region = new TextureRegion(cubeTexture);

		float cube_width = width / WIDTH_NUM;
		float cube_height = height / HEIGHT_NUM;
		mSpriteCache.beginCache();
		for (int i = 0; i < HEIGHT_NUM; i++) {
			for (int j = 0; j < WIDTH_NUM; j++) {
				mSpriteCache.add(region, j * cube_width, i * cube_height,
						cube_width, cube_height);
			}// end for j
		}// end for i
		cacheIndex = mSpriteCache.endCache();
	}

	public void draw(SpriteBatch batch) {
		mSpriteCache.setProjectionMatrix(context.guiCamera.combined);
		mSpriteCache.begin();
		mSpriteCache.draw(cacheIndex);
		mSpriteCache.end();
	}

	public void dispose() {
		mSpriteCache.dispose();
	}
}// end class
