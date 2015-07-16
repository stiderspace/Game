package com.jodelahithit.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jodelahithit.main.GameObject;
import com.jodelahithit.main.HUD;
import com.jodelahithit.main.Handler;
import com.jodelahithit.main.ID;
import com.jodelahithit.main.Main;

public class Player extends GameObject {

	private Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Main.clamp(x, 0, Main.WIDTH - 35);
		y = Main.clamp(y, 0, Main.HEIGHT - 60);

		collision();
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 0.05f, false, handler));
	}

	public void render(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}

			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
