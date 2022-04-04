import java.awt.*;

public class Hero{
  
  private int xPos = (int)(Math.random()*500 + 100), yPos = (int)(Math.random()*500 + 100), defCON = 0,
                     killCount, heading = (int)(Math.random()*360), range = 80, delay = 11, reloadDelay = 20;
  private Forest forest;
  public Image heroImage, ramboImage, arnoldImage;  
  private Picture home;
  private boolean firing = false, reloading = false;
  
  public Hero(Forest f){
    forest = f;
    Picture p = new Picture("resources/hero.jpg");
    Picture r = new Picture("resources/rambo.jpg");
    Picture a = new Picture("resources/arnold.jpg");
    heroImage = p.getImage();
    ramboImage = r.getImage();
    arnoldImage = a.getImage();
    home = forest.getDisplay();
    draw();
  }
  
  public void draw(){
    
    Graphics g = home.getGraphics();
    if(defCON == 0)
    g.drawImage(heroImage, xPos - 20, yPos - 27, null);
    if(defCON == 1)
    g.drawImage(ramboImage, xPos - 20, yPos - 27, null);
    if(defCON == 2)
    g.drawImage(arnoldImage, xPos - 20, yPos - 27, null);
    g.setColor(Color.black);
      
    
  }
  
  public void move(){
    heading += (int)(Math.random()*20 - 10);
    Graphics g = home.getGraphics();
    g.setColor(Color.white);
    g.fillRect(xPos - 20, yPos - 27, 40, 55);
   
    xPos += Math.sin(Math.toRadians(heading)) * 10;
    yPos -= Math.cos(Math.toRadians(heading)) * 10;
    
    if(xPos > 679){
      xPos = 679;
      heading = 270;
    }
    if(yPos > 672){
      yPos = 672;
      heading = 0;
    }
    if(xPos < 20){
      xPos = 20;
      heading = 90;
    }
    if(yPos < 20){
      yPos = 20;
      heading = 180;
    }
    draw();
  }
  
  public boolean targetAquired(Moose m){
    int dx = xPos - m.getXPos();
    int dy = yPos - m.getYPos();
    if(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) < range)
      return true;
    else 
      return false;
  }
  
  public void kill(Moose m){
    m.setBeingKilled();
    firing = true;
    delay = 0;
    
  }
  public boolean isReadyToFire(){
    if(!reloading && !firing)
      return true;
    else 
      return false;
  }
  
  public boolean isFiring(){
    return firing;
  }
  
  public int getDelay(){
    return delay;
  }
  
  public void passDelay(){
    delay++;
  }
  
  public void addKill(){
    killCount++;
  }
  
  public int getKills(){
    return killCount;
  }
  
  public void setFiring(boolean flag){
    firing = flag;
  }
  public void setReloading(boolean flag){
    reloading = flag;
  }
  
  public void passReloadDelay(){
    reloadDelay++;
  }
  
  public int getReloadDelay(){
    return reloadDelay;
  }
  
  public void setReloadDelay(int i){
    reloadDelay = i;
  }
  
  public int getXPos(){
    return xPos;
  }
  
  public int getYPos(){
    return yPos;
  }
  
  public void setDefCON(int i){
    defCON = i;
  }
}

