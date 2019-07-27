package sum.tt;

import java.awt.*;
import java.applet.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.net.*;
import java.util.*;

public class GameEngine implements Runnable, MouseMotionListener, KeyListener, GameConstants {

	private Table table;
	private int MyRacket_x = RACKET_X_START;
	private int MyRacket_y = RACKET_Y_START;
	private int ComputerRacket_x = COMPUTER_RACKET_X_START;
	private int Ball_x;
	private int Ball_y = 320;
	private double ball_size1 = 30;
	private int ball_size;
	private boolean MovingUp = false;
	private boolean BallServed = false;
	private boolean start = false;
	private double float_ball = RACKET_X_START + 45;
	private double b = 0; // --stands for slide
	private double c = 0;// --stands for motion
	private int ComputerScore = 0;
	private int MyScore = 0;
	private boolean Bounce = false;
	private boolean KeepPlaying = false;
	int f = 0;
	float rkt = 0;
	Random rnd = new Random();
	int rndgen = 0;
	int my_score = 0;
	int comp_score = 0;
	int ball_hit = 0;
	int speed = SLEEP_TIME;
	String info = " ";

	public GameEngine(Table table2) {
		table = table2;
		Thread worker = new Thread(this);
		worker.start();
		
	}

	// ------------------move MyRacket------------------------------------------
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		int mouse_x = e.getX();
		int mouse_y = e.getY();
		if (mouse_y < MyRacket_y && MyRacket_y > 10) {
			MyRacket_y = mouse_y - 120;
		} else if (MyRacket_y < 500) {
			MyRacket_y = mouse_y - 120;
		}
		if (mouse_x < MyRacket_x && MyRacket_x > 5) {
			MyRacket_x = mouse_x - 50;
		} else if (MyRacket_x < 700) {
			MyRacket_x = mouse_x - 50;
		}
		table.setMyRacket(MyRacket_x, MyRacket_y);
	}
	// ------------------take key
	// input---------------------------------------------------------

	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed : "+e.getKeyChar());
		char key = e.getKeyChar();
		if ('n' == key || 'N' == key) {
			startNewGame();

		} else if ('q' == key || 'Q' == key) {
			endGame();
		} else if ('s' == key || 'S' == key) {
			if (KeepPlaying == false) {
				KeepPlaying = true;
				Serve();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void startNewGame() {
		comp_score = 0;
		my_score = 0;
		Serve();
	}

	// End the game
	public void endGame() {
		System.exit(0);
	}

	// ------------------run_start---------------------------------------------------

	public void run() {
		while (true) {
			System.out.println("while true");
			if (KeepPlaying == true) {
				if (MovingUp == true) {
					Move_Up();
				} else {
					Move_Down();
				}
				// ----------------------------change ball speed-------
				// ----------------------------change ball speed-------
				table.setComputerRacket(Ball_x - 20);
				table.setScore(my_score, comp_score);
				table.setMessageText(info);
				try {
					Thread.sleep(speed);
					table.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}// -------------------------------------------------------------------run colse

	public void Move_Up() {
		System.out.println("moving up");
		if (Ball_y > 140) {
			if (Ball_y < 145) {
				if (Ball_x < TABLE_MID_X - 115 || Ball_x > TABLE_MID_X + 105) {
					KeepPlaying = false;
					comp_score++;
					info = "Press 's' to serve";
					table.setMessageText(info);
					table.setScore(my_score, comp_score);
				}
			}

			if (b > 0)
				c = 0.09;
			else
				c = -0.09;

			Ball_y -= 4;
			float_ball += b;
			Ball_x = (int) float_ball;
			b += c;
			ball_size1 -= BALL_MOVE;
			ball_size = (int) ball_size1;
		} else if (Ball_y > BALL_MAX_UP) {

			// c=0.50;
			// b=0;
			Ball_y -= 8;
			float_ball += b;
			Ball_x = (int) float_ball;
			b = (b + c) * 2 / 3;
			ball_size1 -= BALL_MOVE;
			ball_size = (int) ball_size1;
		} else {
			// (Ball_x>ComputerRacket_x && Ball_x<ComputerRacket_x+COMPUTER_RACKET_LENGTH &&
			// Ball_y)
			MovingUp = false;
		}
		table.setBallPosition(Ball_x, Ball_y, ball_size);
	}

	// ---------------------------------------move down--------
	public void Move_Down() {
		System.out.println("moving down");
		rkt = (TABLE_MID_X - MyRacket_x - 45) / 100f;
		b = rndgen;
		c = 0.50;
		// Bounce=false;
		// c=0.05;
		if (ball_size < BALL_MAX_SIZE) {
			if (Ball_y < BOUNCE_DOWN && Bounce == false) {
				if (Ball_y > BOUNCE_DOWN - 6) {
					if (Ball_x < TABLE_MID_X - 215 || Ball_x > TABLE_MID_X + 200) {
						KeepPlaying = false;
						my_score++;
						info = "Press 's' to serve";
						table.setMessageText(info);
					}
				}
				ball_size1 += 0.15;
				Ball_y += 5;
			} else {
				Bounce = true;
				ball_size1 += 0.40;
				if (f == 1) {
					Ball_y = Ball_y - 1;
					f = 0;
				}
				f++;
			}
			float_ball += b;
			Ball_x = (int) float_ball;
			b -= c;
			ball_size = (int) ball_size1;
		} else if (Ball_x + ball_size / 2 > MyRacket_x && Ball_y + ball_size / 2 > MyRacket_y
				&& Ball_x < MyRacket_x + RACKET_WIDTH && Ball_y < MyRacket_y + RACKET_LENGTH) {
			if (Ball_x < MyRacket_x + 10)
				b = rkt + 2.50;
			else if (Ball_x < MyRacket_x + 25)
				b = rkt + 1;
			else if (Ball_x < MyRacket_x + 40)
				b = rkt + 0.5;
			else if (Ball_x < MyRacket_x + 50)
				b = rkt + 0;
			else if (Ball_x < MyRacket_x + 65)
				b = rkt - 0.5;
			else if (Ball_x < MyRacket_x + 80)
				b = rkt - 1;
			else// if(Ball_x<MyRacket_x+90)
				b = rkt - 2.50;
			MovingUp = true;
			Bounce = false;
			rndgen = rnd.nextInt(7) - 3;
			ball_hit++;
			table.setInfo(Ball_y);
		} else {
			KeepPlaying = false;
			comp_score++;
			info = "Press 's' to serve";
			table.setMessageText(info);

		}
		table.setBallPosition(Ball_x, Ball_y, ball_size);

	}

	// ---------------------------------serve()---------
	private void Serve() {
		info = " ";
		KeepPlaying = true;
		MyRacket_x = RACKET_X_START;
		MyRacket_y = RACKET_Y_START;
		float_ball = BALL_START_X;
		Ball_y = BALL_START_Y;
		ball_size1 = 30;
		b = 0;
		c = 0;
		rkt = 0;
		rndgen = 0;

		MovingUp = true;
		Bounce = false;

	}

	// ---------------------------------served()-------------

}// --------------class close
