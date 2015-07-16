package com.jodelahithit.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jodelahithit.main.GameObject;
import com.jodelahithit.main.Handler;
import com.jodelahithit.main.ID;
import com.jodelahithit.main.Main;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 5;
		velY = 5;
	}

	public void tick() {
		x += velX;
		y += velY;

		if (y < 0 || y >= Main.HEIGHT - 32)velY *= -1;
		if (x < 0 || x >= Main.WIDTH - 16)velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 0.05f, true, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 16, 16);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
}
