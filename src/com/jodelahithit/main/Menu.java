package com.jodelahithit.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jodelahithit.main.Main.STATE;
import com.jodelahithit.main.objects.Player;

public class Menu extends MouseAdapter {

	private Main main;
	private Handler handler;
	private HUD hud;
	private int OldHighScore = 0;
	private int HighScore = 0;
	private boolean played = false;
	

	public Menu(Main main, Handler handler, HUD hud) {
		this.main = main;
		this.handler = handler;
		this.hud = hud;
	}

	public void setHighScore(int highScore) {
		HighScore = highScore;
	}

	public void played() {
		played = true;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (main.GameState == STATE.Menu) {
			// play button
			if (mouseOver(mx, my, Main.WIDTH / 2 - 100, 100, 200, 64)) {
				played = true;
				main.GameState = STATE.Game;
				handler.addObject(new Player(Main.WIDTH / 2 - 32, Main.HEIGHT / 2 - 32, ID.Player, handler));
			}
			// help button
			if (mouseOver(mx, my, Main.WIDTH / 2 - 100, 200, 200, 64)) {
				main.GameState = STATE.Help;
			}
			// quit button
			if (mouseOver(mx, my, Main.WIDTH / 2 - 100, 300, 200, 64)) {
				System.exit(1);
			}
		} else if (main.GameState == STATE.Help) {
			if (mouseOver(mx, my, Main.WIDTH / 2 - 100, 300, 200, 64)) {
				main.GameState = STATE.Menu;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void render(Graphics g) {
		if (main.GameState == STATE.Menu) {
			Font fnt = new Font("courier new", 1, 50);
			Font fnt2 = new Font("courier new", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			if (!played) {
				g.drawString("Menu", 260, 60);
			} else {
				g.drawString("" + OldHighScore, 260, 60);
			}

			g.setFont(fnt2);
			
			g.setColor(Color.BLACK);
			g.fillRect(Main.WIDTH / 2 - 100, 100, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(Main.WIDTH / 2 - 100, 100, 200, 64);
			g.drawString("Play", 280, 140);
			
			g.setColor(Color.BLACK);
			g.fillRect(Main.WIDTH / 2 - 100, 200, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(Main.WIDTH / 2 - 100, 200, 200, 64);
			g.drawString("Help", 280, 240);
			
			g.setColor(Color.BLACK);
			g.fillRect(Main.WIDTH / 2 - 100, 300, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(Main.WIDTH / 2 - 100, 300, 200, 64);
			g.drawString("Quit", 280, 340);
		} else if (main.GameState == STATE.Help) {
			Font fnt = new Font("courier new", 1, 50);
			Font fnt2 = new Font("courier new", 1, 30);
			Font fnt3 = new Font("courier new", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 260, 60);
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 40, 200);
			g.setFont(fnt2);
			
			g.setColor(Color.BLACK);
			g.fillRect(Main.WIDTH / 2 - 100, 300, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(Main.WIDTH / 2 - 100, 300, 200, 64);
			g.drawString("Back", 280, 340);
		}
	}

	public void tick() {
		if (HighScore > OldHighScore) {
			OldHighScore = HighScore;
		}
	}

}
