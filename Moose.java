import java.awt.*;

public class Moose {

	private int xPos, yPos, daysLeft = 0, heading = (int) (Math.random() * 360), id, lifeTime = 0;
	private Forest forest;
	private Image mooseImage, clockImage, heartImage, goldImage;
	private Picture home;
	private boolean reproducing = false, fertile = true, beingKilled = false, bestMoose = false,
			bestSurvivalMoose = false;

	public Moose(Forest f) {
		this((int) (Math.random() * 500 + 100), (int) (Math.random() * 500 + 100), f);
	}

	public Moose(int x, int y, Forest f) {
		xPos = x;
		yPos = y;
		forest = f;
		Picture m = new Picture("resources/moose.jpg");
		Picture g = new Picture("resources/goldMoose.jpg");
		Picture c = new Picture("resources/clock.jpg");
		Picture h = new Picture("resources/heart.jpg");
		mooseImage = m.getImage();
		goldImage = g.getImage();
		clockImage = c.getImage();
		heartImage = h.getImage();
		home = forest.getDisplay();
		draw();
	}

	public void draw() {

		Graphics g = home.getGraphics();
		if (bestMoose && bestSurvivalMoose)
			g.drawImage(goldImage, xPos - 25, yPos - 25, null);
		else
			g.drawImage(mooseImage, xPos - 25, yPos - 25, null);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.drawString("" + id, xPos + 7, yPos + 5);
		if (bestMoose)
			g.drawImage(heartImage, xPos + 14, yPos + 4, null);
		if (bestSurvivalMoose)
			g.drawImage(clockImage, xPos + 14, yPos + 14, null);
		bestMoose = false;
		bestSurvivalMoose = false;
	}

	public void move() {
		heading += (int) (Math.random() * 20 - 10);
		Graphics g = home.getGraphics();
		g.setColor(Color.white);
		g.fillRect(xPos - 25, yPos - 25, 50, 50);
		xPos += Math.sin(Math.toRadians(heading)) * 10;
		yPos -= Math.cos(Math.toRadians(heading)) * 10;

		if (xPos > 674) {
			xPos = 674;
			heading = 270;
		}
		if (yPos > 674) {
			yPos = 674;
			heading = 0;
		}
		if (xPos < 20) {
			xPos = 20;
			heading = 90;
		}
		if (yPos < 20) {
			yPos = 20;
			heading = 180;
		}
		draw();
	}

	public void reproduce(Moose m) {
		reproducing = true;
		fertile = false;
		daysLeft = 60;
		m.setReproduction(true);
		m.setFertility(false);
		m.setDaysLeft(60);
	}

	public void setReproduction(boolean flag) {
		reproducing = flag;
	}

	public void setFertility(boolean flag) {
		fertile = flag;
	}

	public boolean isReproducing() {
		return reproducing;
	}

	public boolean isFertile() {
		return fertile;
	}

	public int getDistance(Moose m) {
		int dx = xPos - m.getXPos();
		int dy = yPos - m.getYPos();
		return (int) (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int i) {
		daysLeft = i;
	}

	public void passDay() {
		setDaysLeft(daysLeft - 1);
	}

	public void hump(int i) {
		Graphics g = home.getGraphics();
		g.setColor(Color.white);
		g.fillRect(xPos - 25, yPos - 25, 50, 50);
		yPos += (i % 2) * 20 - 10;
		draw();
	}

	public void setBeingKilled() {
		beingKilled = true;
	}

	public boolean isBeingKilled() {
		return beingKilled;
	}

	public String toString() {
		return "A moose at (" + xPos + ", " + yPos + ")";
	}

	public void setId(int i) {
		id = i;
	}

	public int getId() {
		return id;
	}

	public void live() {
		lifeTime++;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public void setBestMoose() {
		bestMoose = true;
	}

	public void setBestSurvivalMoose() {
		bestSurvivalMoose = true;
	}

}