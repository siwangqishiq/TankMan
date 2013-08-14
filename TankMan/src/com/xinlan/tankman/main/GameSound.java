package com.xinlan.tankman.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameSound {
	protected Music bgMusic;
	
	protected Music turnSound;
	protected Music runSound;
	
	public GameSound(GameScreen context){
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/bg.mp3")); 
		turnSound =  Gdx.audio.newMusic(Gdx.files.internal("sound/tank_turn.ogg"));
		runSound = Gdx.audio.newMusic(Gdx.files.internal("sound/tank_run.ogg"));
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.4f);
		turnSound.setVolume(0.3f);
		runSound.setVolume(0.3f);
		turnSound.setLooping(true);
		runSound.setLooping(true);
	}
	
	
	public void dispose(){
		bgMusic.dispose();
		turnSound.dispose();
		runSound.dispose();
	}
}//end class
