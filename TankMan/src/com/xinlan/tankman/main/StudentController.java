package com.xinlan.tankman.main;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xinlan.tankman.util.Common;

public class StudentController {
	public static final int CUBE_TEXTURE = 141;
	public static final int MAX_NUM = 20;
	public GameScreen context;

	protected LinkedList<Student> studentList = new LinkedList<Student>();
	protected Texture texture;
	protected TextureRegion face1Texture, face2Texture, face3Texture,
			face4Texture, face5Texture, face6Texture;
	protected TextureRegion deadTexture;

	public StudentController(GameScreen context) {
		this.context = context;
		texture = new Texture(Gdx.files.internal("student.png"));

		face1Texture = new TextureRegion(texture, 0, 0, CUBE_TEXTURE,
				CUBE_TEXTURE);
		face2Texture = new TextureRegion(texture, CUBE_TEXTURE, 0,
				CUBE_TEXTURE, CUBE_TEXTURE);
		face3Texture = new TextureRegion(texture, 2 * CUBE_TEXTURE, 0,
				CUBE_TEXTURE, CUBE_TEXTURE);
		face4Texture = new TextureRegion(texture, 3 * CUBE_TEXTURE, 0,
				CUBE_TEXTURE, CUBE_TEXTURE);
		face5Texture = new TextureRegion(texture, 4 * CUBE_TEXTURE, 0,
				CUBE_TEXTURE, CUBE_TEXTURE);
		face6Texture = new TextureRegion(texture, 5 * CUBE_TEXTURE, 0,
				CUBE_TEXTURE, CUBE_TEXTURE);
		deadTexture = new TextureRegion(texture, 6 * CUBE_TEXTURE, 0,
				CUBE_TEXTURE, CUBE_TEXTURE);
	}

	public void initStudents() {
		for (int i = 0; i < MAX_NUM; i++) {
			Student student = null;
			do{
				student=new Student(this, Common.genRand(0, 6), Common.genRand(0, GameScreen.SCREEN_WIDTH),
						Common.genRand(0, GameScreen.SCREEN_HEIGHT));
			}while(Common.overlapRectangles(student.rect, context.mTiananmen.sprite.getBoundingRectangle())
					||Common.overlapRectangles(student.rect, context.tank.tempRect));
			studentList.add(student);
		}// end for i
	}

	public void logic(float delta) {
		for (Student student : studentList) {
			student.logic(delta);
		}// end for
		for (int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			if (student.state == Student.CAN_REMOVE) {
				studentList.remove(student);
				System.gc();
			}
		}// end for i
		
		if(studentList.size()<MAX_NUM){
			//TODO add
			Student student = null;
			do{
				student=new Student(this, Common.genRand(0, 6), Common.genRand(0, GameScreen.SCREEN_WIDTH),
						Common.genRand(0, GameScreen.SCREEN_HEIGHT));
			}while(Common.overlapRectangles(student.rect, context.mTiananmen.sprite.getBoundingRectangle())
					||Common.overlapRectangles(student.rect, context.tank.tempRect));
			studentList.add(student);
		}
	}

	public void draw(SpriteBatch batch) {
		for (Student student : studentList) {
			student.draw(batch);
		}// end for
	}

	public void dispose() {
		texture.dispose();
	}
}// end class
