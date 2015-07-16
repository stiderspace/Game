package com.jodelahithit.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.jodelahithit.main.GameObject;
import com.jodelahithit.main.Handler;
import com.jodelahithit.main.ID;
import com.jodelahithit.main.Main;

public class MenuParticle extends GameObject {

	private Handler handler;
	Random r = new Random();
	private Color col;

	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
		if(velX == 0) velX = 1;
		if(velY == 0) velY = 1;

		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

	}

	public void tick() {
		x += velX;
		y += velY;

		if (y < 0 || y >= Main.HEIGHT - 32)
			velY *= -1;
		if (x < 0 || x >= Main.WIDTH - 16)
			velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, col, 8, 0.05f, false, handler));
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int) x, (int) y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 8, 8);
	}
}