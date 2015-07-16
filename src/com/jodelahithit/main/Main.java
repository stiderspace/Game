package com.jodelahithit.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.jodelahithit.main.objects.MenuParticle;
import com.jodelahithit.main.objects.Player;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = -1206509343160705101L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Random r;
	private Spawn spawner;
	private Menu menu;

	public enum STATE {
		Menu, Game, Help
	};

	public STATE GameState = STATE.Menu;
	private STATE oldState;

	public Main() {

		handler = new Handler();
		menu = new Menu(this, handler, hud);
		hud = new HUD(this);
		spawner = new Spawn(handler, hud);

		this.addKeyListener(new KeyInput(handler, this, spawner, menu));
		this.addMouseListener(menu);

		new Window(WIDTH, HEIGHT, "Game", this);

		r = new Random();

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();

	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		if (GameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		} else if (GameState == STATE.Menu) {
			menu.tick();
		}
		if (GameState != oldState) {
			if (!(GameState == STATE.Help)) {
				if (!(oldState == STATE.Help)) {
					handler.object.clear();

					if (GameState == STATE.Menu || GameState == STATE.Help) {
						for (int i = 0; i < 20; i++) {
							handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
						}
					} else if (GameState == STATE.Game) {
						handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
					}
				}
			}
			oldState = GameState;
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		if (GameState == STATE.Game) {
			hud.render(g);
		} else if (GameState == STATE.Menu || GameState == STATE.Help) {
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;

	}

	public static void main(String[] args) {
		new Main();
	}

	public void goToMenu() {
		menu.setHighScore(hud.getScore());
		HUD.resetHealth();
		spawner.reset();
		GameState = STATE.Menu;
		for (int i = 0; i < 20; i++) {
			handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
		}
	}
}
