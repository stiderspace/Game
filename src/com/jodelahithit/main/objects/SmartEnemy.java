package com.jodelahithit.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.jodelahithit.main.GameObject;
import com.jodelahithit.main.Handler;
import com.jodelahithit.main.ID;
import com.jodelahithit.main.Main;

public class SmartEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	private int speed;

	public SmartEnemy(float x, float y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}
		
		velX = 0;
		velY = 0;
	}

	public void tick(){
		x += velX;
		y += velY;
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		velX = (((-1/distance) * diffX)*speed);
		velY = (((-1/distance) * diffY)*speed);
		
		if (y < 0 || y >= Main.HEIGHT - 32)
			velY *= -1;
		if (x < 0 || x >= Main.WIDTH - 16)
			velX *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 8, 0.05f, false, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

}
