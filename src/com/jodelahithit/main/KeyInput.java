package com.jodelahithit.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.jodelahithit.main.Main.STATE;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private Main main;
	private Menu menu;
	private Spawn spawner;
	
	private boolean[] KeyDown = new boolean[4];

	public KeyInput(Handler handler, Main main, Spawn spawner, Menu menu) {
		this.handler = handler;
		this.main = main;
		this.spawner = spawner;
		this.menu = menu;

		KeyDown[0] = false;
		KeyDown[1] = false;
		KeyDown[2] = false;
		KeyDown[3] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(main.GameState == STATE.Game){
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					KeyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
					KeyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					KeyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					KeyDown[3] = true;
				}

			}
		}
		if (key == KeyEvent.VK_ESCAPE){
			main.goToMenu();


		}
		}else if(main.GameState == STATE.Menu){
			if (key == KeyEvent.VK_ESCAPE){
				System.exit(1);

			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					KeyDown[0] = false;
				}
				if (key == KeyEvent.VK_S) {
					KeyDown[1] = false;
				}
				if (key == KeyEvent.VK_D) {
					KeyDown[2] = false;
				}
				if (key == KeyEvent.VK_A) {
					KeyDown[3] = false;
				}

				if (!KeyDown[0] && !KeyDown[1]) {
					tempObject.setVelY(0);
				}
				if (!KeyDown[2] && !KeyDown[3]) {
					tempObject.setVelX(0);
				}
			}
		}
	}
}
