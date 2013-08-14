package com.xinlan.tankman.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {
	// 屏幕高宽
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 480;
	
	Game game;
	// 摄像机
	OrthographicCamera guiCamera;
	//画笔工具
	SpriteBatch batch;
	Texture tankTexture;
	TextureRegion region;
	
	Animation animation;
	public float stateTime = 0;
	
	protected GameSound mSound;
	protected Background mBackground;
	protected Tank tank;
	protected StudentController mStudentController;
	protected Tiananmen mTiananmen;
	protected Controller mController;
	
	
	public GameScreen(Game game) {
		this.game = game;
		batch = new SpriteBatch();
		guiCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		guiCamera.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);	
		
		init();
	}
	Sprite bottomSprite;
	private void init(){
		mSound = new GameSound(this);
		mBackground = new Background(this);
		mController = new Controller(this);
		tank = new Tank(this);
		mStudentController = new StudentController(this);
		mTiananmen = new Tiananmen(this);
		
		mStudentController.initStudents();
		mSound.bgMusic.play();
	}

	@Override
	public void render(float delta) {
		logic(delta);
		draw(batch);
	}

	public void logic(float delta) {
		stateTime+=delta;
		mController.logic(delta);
		tank.logic(delta);
		mStudentController.logic(delta);
//		guiCamera.position.set(tank.bottomSprite.getX(), 
//				tank.bottomSprite.getY(), 0);
	}

	public void draw(SpriteBatch batch) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// 清屏
		mBackground.draw(batch);
		// 设置摄像机
		batch.setProjectionMatrix(guiCamera.combined);
		guiCamera.update();

		batch.begin();
		// TODO
		mTiananmen.draw(batch);
		mStudentController.draw(batch);
		tank.draw(batch);
		batch.end();
		mController.draw(batch);
	}

	@Override
	public void dispose() {
		tank.dispose();
		mSound.dispose();
		mBackground.dispose();
		mTiananmen.dispose();
		mController.dispose();
		mStudentController.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {

	}

}// end class
