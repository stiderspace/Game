package com.jodelahithit.main;

import java.util.Random;

import com.jodelahithit.main.objects.BasicEnemy;
import com.jodelahithit.main.objects.SmartEnemy;
import com.jodelahithit.main.objects.StraightEnemy;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();

	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {
		scoreKeep++;

		if (scoreKeep >= 200) {

			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), ID.BasicEnemy, handler));
			switch (hud.getLevel()) {
			case 3:
				handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), 2, ID.SmartEnemy, handler));
				break;
			case 5:
				handler.addObject(new StraightEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), ID.StraightEnemy, handler));
				break;
			case 7:
				handler.addObject(new StraightEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), ID.StraightEnemy, handler));
				break;
			case 9:
				handler.addObject(new StraightEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), ID.StraightEnemy, handler));
				break;

			}

		}
	}
}
