package com.jodelahithit.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jodelahithit.main.GameObject;
import com.jodelahithit.main.Handler;
import com.jodelahithit.main.ID;
import com.jodelahithit.main.Main;

public class StraightEnemy extends GameObject {

	private Handler handler;

	public StraightEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 1;
		velY = 7;
	}

	public void tick() {
		x += velX;
		y += velY;

		if (y < 0 || y >= Main.HEIGHT - 32)
			velY *= -1;
		if (x < 0 || x >= Main.WIDTH - 16)
			velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 8, 0.05f, false, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}
}