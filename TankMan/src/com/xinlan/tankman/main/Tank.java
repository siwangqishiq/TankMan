package com.xinlan.tankman.main;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.xinlan.tankman.util.Common;

public class Tank {
	private GameScreen context;
	public static final float pad=5;
	public final int width = 50;
	public final int height = 50;
	public final int hit_width=40;
	public final int hit_height=40;
	public float x, y;
	protected Sprite bottomSprite;
	protected Sprite headSprite;
	private float bodyAngle = 0f;
	private float headAngle = 0f;

	private Texture texture;
	private Texture headTexture;
	public float dx, dy;
	public float velocity = 2f;
	public float rotate_velocity=5.5f;

	private ParticleEffectPool mParticleEffectPool;// 粒子池
	private ParticleEffect mParticleEffect;
	private ParticleEffect tempParticle;
	private ArrayList<ParticleEffect> mParticleList;

	public Rectangle tempRect = new Rectangle(0, 0, width, height);
	public Rectangle hitRect = new Rectangle(pad, pad, hit_width, hit_height);
	private Vector2 dirVector = new Vector2();
	private Music turnSound;
	
	public Tank(GameScreen context) {
		this.context = context;

		x = GameScreen.SCREEN_WIDTH / 2;
		y = GameScreen.SCREEN_HEIGHT / 2;

		texture = new Texture(Gdx.files.internal("tank_bottom.png"));
		bottomSprite = new Sprite(texture);
		bottomSprite.setBounds(x, y, width, height);
		headTexture = new Texture(Gdx.files.internal("tank_head.png"));
		headSprite = new Sprite(headTexture);
		headSprite.setBounds(x, y, width, height);
		bottomSprite.setOrigin(width / 2, height / 2);
		bottomSprite.rotate(90);
		
		mParticleEffect = new ParticleEffect();
		mParticleEffect.load(Gdx.files.internal("particle/particle.p"),
				Gdx.files.internal("particle/"));
		mParticleEffectPool = new ParticleEffectPool(mParticleEffect, 5, 10);
		mParticleList = new ArrayList<ParticleEffect>();

		turnSound = context.mSound.turnSound;
	}

	public void logic(float delta) {
		headSprite.setOrigin(width / 2, height / 2);
		headSprite.rotate(0.2f);

		float origin_x = bottomSprite.getX(), origin_y = bottomSprite.getY();
		origin_x += dx;
		if (origin_x < 0 || origin_x > GameScreen.SCREEN_WIDTH - width) {
			dx = 0;
		}
		origin_y += dy;
		if (origin_y > GameScreen.SCREEN_HEIGHT - height || origin_y < 0) {
			dy = 0;
		}

		tempRect.x = origin_x;
		tempRect.y = origin_y;
		
		hitRect.x = tempRect.x+pad;
		hitRect.y = tempRect.y+pad;
		
		if (Common.overlapRectangles(tempRect,
				context.mTiananmen.sprite.getBoundingRectangle())) {
			dx = 0;
			dy = 0;
		}

		if(dx==0 && dy==0){
			turnSound.pause();
			return;
		}
		
		if(!turnSound.isPlaying()){
			turnSound.play();
		}
		
		float cur_head =  headAngle;//当前朝向角度
		dirVector.set(dx, dy);
		float toAngle = dirVector.angle();//转向角度
		float deltaAngle = Math.abs(cur_head-toAngle);
		if(deltaAngle<1f){//移动
			bottomSprite.setOrigin(width / 2, height / 2);
			bottomSprite.setRotation(headAngle+90);
			headSprite.translate(dx, dy);
			bottomSprite.translate(dx, dy);
		}else{//旋转
			if(cur_head<=toAngle){
				if(deltaAngle<=180){
					float rotate = Math.min(deltaAngle, rotate_velocity);
					headAngle+=rotate;
					bottomSprite.rotate(rotate);
				}else{
					float rotate = Math.min(deltaAngle, rotate_velocity);
					headAngle-=rotate;
					bottomSprite.rotate(-rotate);
				}
			}else{
				if(deltaAngle<=180){
					float rotate = Math.min(deltaAngle, rotate_velocity);
					headAngle-=rotate;
					bottomSprite.rotate(-rotate);
				}else{
					float rotate = Math.min(deltaAngle, rotate_velocity);
					headAngle+=rotate;
					bottomSprite.rotate(rotate);
				}
			}
			if(headAngle>360 ){
				headAngle=0;
			}
			if(headAngle<0){
				headAngle=360;
			}
		}

		// tempParticle = mParticleEffectPool.obtain();
		// tempParticle.setPosition(bottomSprite.getX() + width / 2,
		// bottomSprite.getY() + height / 2);
		// mParticleList.add(tempParticle);

		// ParticleEffect tempparticle;
		// for (int i = 0; i < mParticleList.size(); i++) {
		// tempparticle = mParticleList.get(i);
		// if (tempparticle.isComplete()) {
		// mParticleList.remove(i);
		// }
		// }// end for i
	}

	public void draw(SpriteBatch batch) {
		bottomSprite.draw(batch);
		headSprite.draw(batch);
		// for (ParticleEffect particle : mParticleList) {
		// particle.draw(batch, Gdx.graphics.getDeltaTime());
		// }// end for
	}

	public void dispose() {
		texture.dispose();
		headTexture.dispose();
		mParticleEffectPool.clear();
		mParticleEffect.dispose();
	}
}// end class
