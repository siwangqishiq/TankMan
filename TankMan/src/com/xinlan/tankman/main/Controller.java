package com.xinlan.tankman.main;

import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Controller {
	private GameScreen context;
	private Stage stage;
	private Touchpad touchpad;
	private TextureRegionDrawable mBottomTexture;
	private TextureRegionDrawable mTopTexture;
	private TouchpadStyle touchpadStyle;
	Texture texture;

	public Controller(GameScreen gamescreen) {
		this.context = gamescreen;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		texture = new Texture(Gdx.files.internal("controller_texture.png"));
		mBottomTexture = new TextureRegionDrawable(new TextureRegion(texture,
				0, 0, 132, 128));
		mTopTexture = new TextureRegionDrawable(new TextureRegion(texture, 132,
				0, 128, 128));
		touchpadStyle = new TouchpadStyle(mBottomTexture, mTopTexture);
		touchpad = new Touchpad(50, touchpadStyle);
		touchpad.setBounds(50, 10, 150, 150);
		stage.addActor(touchpad);
	}

	public void logic(float delta) {
		Tank tank = context.tank;
		float x = touchpad.getKnobPercentX();
		float y = touchpad.getKnobPercentY();
		float velocity = tank.velocity;
		tank.dx = velocity * x;
		tank.dy = velocity * y;
		stage.act();
	}

	public void draw(SpriteBatch batch) {
		stage.draw();
	}

	public void dispose() {
		stage.dispose();
	}
}// end class
