package com.xinlan.tankman.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.xinlan.tankman.util.Common;

public class Student {
	public static final int DEAD = 0;
	public static final int MOVE = 1;

	private StudentController context;
	private TextureRegion texture, textureDead;
	public int state;
	public float x, y;
	public float dx, dy;
	public float width = 30;
	public float height = 30;
	public Rectangle rect = new Rectangle(0, 0, width, height);

	public Student(StudentController context,int type,float init_x,float init_y) {
		this.context = context;
		textureDead = context.deadTexture;
		
		x = init_x;
		y = init_y;
		
		state = MOVE;
		
		switch(type){
		case 0:
			texture = context.face1Texture;
			break;
		case 1:
			texture = context.face2Texture;
			break;
		case 2:
			texture = context.face3Texture;
			break;
		case 3:
			texture = context.face4Texture;
			break;
		case 4:
			texture = context.face5Texture;
			break;
		case 5:
			texture = context.face6Texture;
			break;
		default:
			texture = context.face1Texture;
		}//end switch
		rect.x = x;
		rect.y = y;
	}

	public void logic(float delta) {
		x+=(float)Common.genRand(0, 10)/20f;
		y+=(float)Common.genRand(0, 5)/20f;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
	}

	public void dispose() {
	}
}// end class
