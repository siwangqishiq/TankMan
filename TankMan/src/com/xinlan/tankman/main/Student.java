package com.xinlan.tankman.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.xinlan.tankman.util.Common;

public class Student {
	public static final int DEAD = 0;
	public static final int MOVE = 1;
	public static final int CAN_REMOVE = 3;

	public StudentController context;
	private TextureRegion texture, textureDead;
	public int state;
	public float x, y;
	public float dx, dy;
	public float width = 30;
	public float height = 30;
	public Rectangle rect = new Rectangle(0, 0, width, height);
	private long dead_time;

	public Student(StudentController context, int type, float init_x,
			float init_y) {
		this.context = context;
		textureDead = context.deadTexture;

		x = init_x;
		y = init_y;

		state = MOVE;

		switch (type) {
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
		}// end switch
		rect.x = x;
		rect.y = y;
	}

	public void logic(float delta) {
		// x++;
		if (state == DEAD) {
			if (System.currentTimeMillis() - dead_time >= 5000) {
				state = CAN_REMOVE;
			}
			return;
		}

		if (Common.overlapRectangles(context.context.tank.hitRect, rect)) {
			state = DEAD;
			
			context.context.mSound.haulSound.play();
			dead_time = System.currentTimeMillis();
		}
	}

	public void draw(SpriteBatch batch) {
		if (state == MOVE) {
			batch.draw(texture, x, y, width, height);
		} else if (state == DEAD) {
			batch.draw(textureDead, x, y, width, height);
		}
	}

	public void dispose() {
	}
}// end class
