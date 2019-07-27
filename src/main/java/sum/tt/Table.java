package sum.tt;

import java.net.*;
import javax.swing.*;

import java.awt.Polygon;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;

public class Table extends JComponent implements GameConstants {
	int x = TABLE_MID_X;
	int y = TABLE_MID_Y;
	int ball_mid_x = BALL_START_X;
	int ball_mid_y = 320;// ---BALL_START_Y+BALL_MAX_SIZE/2;
	int size = BALL_MAX_SIZE;
	int rackx = RACKET_X_START;
	int racky = RACKET_Y_START;
	int comp_rack_x = COMPUTER_RACKET_X_START;
	int comp_rack_y = COMPUTER_RACKET_Y;
	private int MyRacket_x = RACKET_X_START;
	private int MyRacket_y = COMPUTER_RACKET_Y;
	String MoveUp = "true";
	int my_score = 0;
	int comp_score = 0;
	String player = "PLAYER.jpg";
	int ball_hit = 0;
	String info = "Press 's' to start";
	Image image;

	Dimension prefferedSize = new Dimension(700, 600);

	public Dimension getPrefferedSize() {
		return prefferedSize;
	}

	Table() {
		//image = new ImageIcon(new URL("classpath:org/my/package/resource.extension")).getImage();
		URL res = getClass().getClassLoader().getResource("PLAYER.JPG");
		image = new ImageIcon(res).getImage();
		GameEngine gameEngine = new GameEngine(this);
		// Listen to mouse movements to move the rackets
		addMouseMotionListener(gameEngine);
		// Listen to the keyboard events
		addKeyListener(gameEngine);
		repaint();
	}

	void addPaneltoFrame(Container container) {
		container.add(this);
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		GradientPaint ball = new GradientPaint(ball_mid_x, ball_mid_y, Color.white, ball_mid_x + size,
				ball_mid_y + size, Color.gray);
		GradientPaint rack = new GradientPaint(comp_rack_x, comp_rack_y, Color.gray,
				comp_rack_x + COMPUTER_RACKET_LENGTH + 30, comp_rack_y + COMPUTER_RACKET_WIDTH + 30, Color.gray);

		// Image court=new ImageIcon("court.jpg").getImage();
		g.setColor(Color.pink);
		g.fillRect(0, 0, 700, 600);

		// -------------------------------paint wall------
		/*
		 * 
		 * g.setColor(Color.green); Polygon wall=new Polygon(); wall.addPoint(0,80);
		 * wall.addPoint(60,40); wall.addPoint(60,100); wall.addPoint(0,160);
		 * g.fillPolygon(wall);
		 * 
		 * 
		 * g.setColor(Color.green); Polygon wall1=new Polygon(); wall1.addPoint(60,40);
		 * wall1.addPoint(640,40); wall1.addPoint(640,100); wall1.addPoint(60,100);
		 * g.fillPolygon(wall1);
		 * 
		 * g.setColor(Color.green); Polygon wall2=new Polygon(); wall2.addPoint(640,40);
		 * wall2.addPoint(700,80); wall2.addPoint(700,160); wall2.addPoint(640,100);
		 * g.fillPolygon(wall2);
		 */
		g.drawImage(image, comp_rack_x - 10, comp_rack_y - 40 - 2, this);

		// ------------------------------set table border--------
		g.setColor(new Color(220, 220, 220));
		Polygon p = new Polygon();
		p.addPoint(x - 261, y + 145);
		p.addPoint(x - 255, y + 150);
		p.addPoint(x + 255, y + 150);
		p.addPoint(x + 261, y + 145);
		p.addPoint(x + 80, y - 140);
		p.addPoint(x - 80, y - 140);

		g.fillPolygon(p);

		// ------------------------------set table--------

		g.setColor(new Color(0, 93, 0));
		Polygon p2 = new Polygon();
		p2.addPoint(x + 2, y + 140);
		p2.addPoint(x + 255, y + 140);
		p2.addPoint(x + 79, y - 138);
		p2.addPoint(x + 2, y - 138);
		g.fillPolygon(p2);

		Polygon p3 = new Polygon();
		p3.addPoint(x - 255, y + 140);
		p3.addPoint(x - 2, y + 140);
		p3.addPoint(x - 2, y - 138);
		p3.addPoint(x - 79, y - 138);
		g.fillPolygon(p3);

		// ------------------------------net---------------
		g.setColor(new Color(225, 0, 0, 50));
		Polygon p5 = new Polygon();
		p5.addPoint(x - 78 * 2, y - 60);
		p5.addPoint(x - 78 * 2, y - 30);
		p5.addPoint(x + 78 * 2, y - 30);
		p5.addPoint(x + 78 * 2, y - 60);
		g.fillPolygon(p5);
		g.setColor(Color.black);
		Polygon pp = new Polygon();
		pp.addPoint(x - 78 * 2, y - 60);
		pp.addPoint(x - 78 * 2, y - 30);
		pp.addPoint(x + 78 * 2, y - 30);
		pp.addPoint(x + 78 * 2, y - 60);
		g.drawPolygon(pp);

		g.setColor(Color.black);
		Polygon p4 = new Polygon();
		p4.addPoint(x - 78 * 2, y - 20);
		p4.addPoint(x + 78 * 2, y - 20);
		p4.addPoint(x + 78 * 2, y - 18);
		p4.addPoint(x - 78 * 2, y - 18);
		g.fillPolygon(p4);
		g.drawLine(x - 78 * 2, y - 20, x - 78 * 2, y - 60);
		g.drawLine(x + 78 * 2, y - 20, x + 78 * 2, y - 60);

		// ------------------------------------Table footer----------
		g.setColor(new Color(0, 93, 0));
		Polygon p6 = new Polygon();
		p6.addPoint(x - 255, y + 150);
		p6.addPoint(x - 235, y + 150);
		p6.addPoint(x - 235, y + 400);
		p6.addPoint(x - 255, y + 400);
		g.fillPolygon(p6);

		Polygon p7 = new Polygon();
		p7.addPoint(x + 255, y + 150);
		p7.addPoint(x + 235, y + 150);
		p7.addPoint(x + 235, y + 400);
		p7.addPoint(x + 255, y + 400);
		g.fillPolygon(p7);

		/*
		 * g.setColor(Color.gray); g.fillOval(comp_rack_x,comp_rack_y,34,40); Polygon
		 * p9=new Polygon(); p9.addPoint(comp_rack_x+17-3,comp_rack_y+39);
		 * p9.addPoint(comp_rack_x+17-3,comp_rack_y+56);
		 * p9.addPoint(comp_rack_x+17-2,comp_rack_y+57);
		 * p9.addPoint(comp_rack_x+17+2,comp_rack_y+57);
		 * p9.addPoint(comp_rack_x+17+3,comp_rack_y+56);
		 * p9.addPoint(comp_rack_x+17+3,comp_rack_y+39); g.fillPolygon(p9);
		 */

		/*
		 * g.setColor(Color.gray); //g2d.setPaint(rack);
		 * g.fillOval(comp_rack_x,comp_rack_y,COMPUTER_RACKET_LENGTH,
		 * COMPUTER_RACKET_WIDTH); Polygon p9=new Polygon();
		 * p9.addPoint(comp_rack_x+17-3,comp_rack_y+39);
		 * p9.addPoint(comp_rack_x+17-3,comp_rack_y+56);
		 * p9.addPoint(comp_rack_x+17-2,comp_rack_y+57);
		 * p9.addPoint(comp_rack_x+17+2,comp_rack_y+57);
		 * p9.addPoint(comp_rack_x+17+3,comp_rack_y+56);
		 * p9.addPoint(comp_rack_x+17+3,comp_rack_y+39); g.setColor(Color.black);
		 * g.fillPolygon(p9);
		 */

		// g.setColor(Color.white);
		g2d.setPaint(ball);
		g2d.fillOval(ball_mid_x, ball_mid_y, size, size);

		g.setColor(Color.black);
		g.drawOval(MyRacket_x, MyRacket_y, RACKET_WIDTH, RACKET_LENGTH);
		g.drawOval(MyRacket_x - 1, MyRacket_y, RACKET_WIDTH + 1, RACKET_LENGTH + 1);
		g.drawOval(MyRacket_x, MyRacket_y + 1, RACKET_WIDTH + 1, RACKET_LENGTH + 1);
		g.drawOval(MyRacket_x + 1, MyRacket_y, RACKET_WIDTH + 1, RACKET_LENGTH + 1);
		g.setColor(new Color(0, 0, 255, 180)); // 50% transparent
		g.fillOval(MyRacket_x, MyRacket_y - 1, RACKET_WIDTH + 1, RACKET_LENGTH + 1);

		g.setColor(Color.black);
		Polygon p8 = new Polygon();
		p8.addPoint(MyRacket_x + RACKET_WIDTH / 2 - 7, MyRacket_y + RACKET_LENGTH - 1);
		p8.addPoint(MyRacket_x + RACKET_WIDTH / 2 - 7, MyRacket_y + RACKET_LENGTH + 37);
		p8.addPoint(MyRacket_x + RACKET_WIDTH / 2 - 4, MyRacket_y + RACKET_LENGTH + 40);
		p8.addPoint(MyRacket_x + RACKET_WIDTH / 2 + 4, MyRacket_y + RACKET_LENGTH + 40);
		p8.addPoint(MyRacket_x + RACKET_WIDTH / 2 + 8, MyRacket_y + RACKET_LENGTH + 37);
		p8.addPoint(MyRacket_x + RACKET_WIDTH / 2 + 8, MyRacket_y + RACKET_LENGTH - 1);
		g.fillPolygon(p8);

		Font info_font = new Font("Comic Sans MS", Font.BOLD, 20);
		Font score = new Font("Comic Sans MS", Font.BOLD, 15);
		g.setFont(score);
		// g.drawString(info,20,20);
		// g.drawString("ball_mid_y="+Integer.toString(ball_mid_y),10,20);
		// g.drawString(Integer.toString(ball_hit),10,30);
		// g.drawLine(TABLE_MID_X-100,141,TABLE_MID_X+100,141);
		// g.drawString("COMPUTER"+Integer.toString(ball_mid_x),10,10);
		// g.drawLine(TABLE_MID_X-215,290,TABLE_MID_X+215,290);
		g.drawString("COMPUTER :", 20, 20);
		g.drawString(Integer.toString(comp_score), 140, 20);
		g.drawString("YOU :", 20, 40);
		g.drawString(Integer.toString(my_score), 140, 40);
		g.setFont(info_font);
		g.drawString(info, 230, 210);
		requestFocus();
	}

	public void setMyRacket(int xCoordinate, int yCoordinate) {
		this.MyRacket_x = xCoordinate;
		this.MyRacket_y = yCoordinate;
		// repaint();
	}

	// Return current posiition of the kid's racket
	// public int getMyRacket_Y()
	// {
	// return MyRacket_y;
	// }

	// Set the current position of the computer's racket
	public void setComputerRacket(int xCoordinate) {
		this.comp_rack_x = xCoordinate;
		// repaint();
	}

	// Set the game's message
	public void setMessageText(String text) {

		this.info = text;
		// repaint();
	}

	// Set the ballposition
	public void setBallPosition(int xPos, int yPos, int ballSize) {
		ball_mid_x = xPos;
		ball_mid_y = yPos;
		size = ballSize;
		repaint();
		System.out.println("repaint");
	}

	public void setScore(int MyScore, int CompScore) {
		my_score = MyScore;
		comp_score = CompScore;
	}

	public void setInfo(int BallHit) {
		ball_hit = BallHit;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("table");
		Table table = new Table();
		table.addPaneltoFrame(f.getContentPane());
		f.setBounds(0, 0, 700, 600);
		f.setVisible(true);
	}
}
