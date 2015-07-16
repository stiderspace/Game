package com.jodelahithit.main.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.jodelahithit.main.GameObject;
import com.jodelahithit.main.Handler;
import com.jodelahithit.main.ID;

public class Trail extends GameObject {

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private double size;
	private int originalSize;
	private float life;
	private boolean resize;

	public Trail(int x, int y, ID id, Color color, int size, float life, boolean resize, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.size = size;
		this.originalSize = size;
		this.life = life;
		this.resize = resize;
	}

	public void tick() {
		if (alpha > life) {
			alpha -= (life - 0.001);
		} else {
			handler.removeObject(this);
		}
		if (resize == true) {
			if (size > 0) {
				size = (size - originalSize * 0.05);
			} else {
				handler.removeObject(this);
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect(x, y, (int) size, (int) size);

		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
