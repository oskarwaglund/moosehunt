import java.util.*;
import java.awt.*;

public class MooseHunt{
   
  public static void main(String[] args) throws InterruptedException{
    
    Sound tjena = new Sound("resources/tjena.wav");
    Sound snygging = new Sound("resources/snygging.wav");
    Sound puss = new Sound("resources/puss.wav");
    Sound gumman = new Sound("resources/gumman.wav");
    Sound laget = new Sound("resources/laget.wav");
    Sound vad = new Sound("resources/vad.wav");
    Sound gun = new Sound("resources/gun.wav");
    Sound moo1 = new Sound("resources/moo1.wav");
    Sound moo2 = new Sound("resources/moo2.wav");
    Sound doub = new Sound("resources/double.wav");
    Sound trip = new Sound("resources/triple.wav");
    Sound over = new Sound("resources/over.wav");
    Sound invi = new Sound("resources/invincible.wav");
    Sound yakety = new Sound("resources/yakety.wav");
    
    yakety.play();   
    
    int humpCount = 0, target = 0, killsInRow = 0, timer = 40, killPhrase = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    int bestMooseId = 1, oldestMooseId = 1;
    Forest f = new Forest();
    Picture home = f.getDisplay();
    Hero h = new Hero(f);
    ArrayList<Moose> mooses = new ArrayList<Moose>();
    ArrayList<String> actions = new ArrayList<String>();
    ArrayList<Color> colors = new ArrayList<Color>();
    ArrayList<Integer> population = new ArrayList<Integer>();
    ArrayList<Integer> kills = new ArrayList<Integer>();
    ArrayList<Integer> timesReproduced = new ArrayList<Integer>();
    ArrayList<Integer> lifeTime = new ArrayList<Integer>();
    ArrayList<Integer> timeOfBirth = new ArrayList<Integer>();
    Font font1 = new Font("Arial", Font.PLAIN,  30);
    Font font2 = new Font("Arial", Font.PLAIN,  12);
    
    for(int i = 0; i < 6; i++){
      mooses.add(new Moose(f));
      mooses.get(i).setId(i+1);
      timeOfBirth.add(0);
      timesReproduced.add(0);
      lifeTime.add(0);
      
    }
    Graphics u = home.getGraphics();
    u.setColor(Color.white);
    u.setFont(font1);
    u.drawString("" + mooses.size(), 720, 280); 
    u.drawString("" + h.getKills(), 720, 168);
    
    for(int i = 0; i < 1200 && mooses.size() < 100 && mooses.size() > 0; i++){
    
      
      
      //Working Code
      int bestMoose1 = 0, bestSurvivalMoose1 = 0;
      for(int n = 1; n < mooses.size(); n++){
        if(timesReproduced.get(mooses.get(n).getId()-1) > timesReproduced.get(mooses.get(bestMoose1).getId()-1))
          bestMoose1 = n;
      }
      mooses.get(bestMoose1).setBestMoose();
      mooses.get(0).setBestSurvivalMoose();
      
      
      if(mooses.get(bestMoose1).getId() != bestMooseId){
        bestMooseId = mooses.get(bestMoose1).getId();
        actions.add("Moose " + bestMooseId + " is the most reproductive moose!");
        colors.add(Color.cyan);
        Graphics g = home.getGraphics();
        g.setColor(Color.black);
        g.fillRect(718, 367, 266, 312);
        g.setFont(font2);
        g.setColor(Color.white);
        if(actions.size() > 15){
            colors.remove(0);
            actions.remove(0);
        }
        for(int l = 0; l < actions.size(); l++){
          g.setColor(colors.get(l));
          g.drawString(actions.get(l), 720, 380 + l*20);
        }
      }
   
      if(mooses.get(0).getId() != oldestMooseId){
        oldestMooseId = mooses.get(0).getId();
        actions.add("Moose " + bestMooseId + " is oldest living moose!");
        colors.add(Color.cyan);
        Graphics g = home.getGraphics();
        g.setColor(Color.black);
        g.fillRect(718, 367, 266, 312);
        g.setFont(font2);
        g.setColor(Color.white);
        if(actions.size() > 15){
            colors.remove(0);
            actions.remove(0);
        }
        for(int l = 0; l < actions.size(); l++){
          g.setColor(colors.get(l));
          g.drawString(actions.get(l), 720, 380 + l*20);
        }
      }
      
      
      if(!h.isFiring())
        h.move();
      if(h.getReloadDelay() < 20)
        h.passReloadDelay();
      if(h.getReloadDelay() == 10){
        if(killPhrase == 0)
          laget.play();
        if(killPhrase == 2)
          snygging.play();
        if(killPhrase == 3)
          puss.play();
      }
      if(h.getReloadDelay() == 19)
        h.setReloading(false);
      if(h.getDelay() < 11){
        h.passDelay();
        h.draw();
        if(h.getDelay() == 10){
          Graphics g = home.getGraphics();
          g.setColor(Color.white);
          g.drawLine(x1, y1, x2, y2); 
        }
        if(h.getDelay() == 9){
          gun.play();
          Graphics g = home.getGraphics();
          g.setColor(Color.white);
          g.fillRect(mooses.get(target).getXPos()-25, mooses.get(target).getYPos()-25, 50, 50);
          g.setColor(Color.black);
          x1 = h.getXPos();
          y1 = h.getYPos();
          x2 = mooses.get(target).getXPos();
          y2 = mooses.get(target).getYPos();
          g.drawLine(x1, y1, x2, y2); 
          h.addKill();
          h.setFiring(false);
          lifeTime.set(mooses.get(target).getId() - 1, mooses.get(target).getLifeTime());
          
          actions.add("Hero killed moose " + mooses.get(target).getId() + "!");
          colors.add(Color.red);
          if(timer < 40)
            killsInRow++;
          else 
            killsInRow = 0;
          if(killsInRow == 1){
            doub.play();
            actions.add("Double Kill!");
            colors.add(Color.yellow);
          }
          if(killsInRow == 2){
            trip.play();
            actions.add("Triple Kill!");
            colors.add(Color.yellow);
          }
          if(killsInRow == 3){
            over.play();
            actions.add("Overkill!");
            colors.add(Color.yellow);
          }
          if(killsInRow == 4){
            invi.play();
            actions.add("Invincible!");
            colors.add(Color.yellow);
          }
          timer = 0;
          if(actions.size() > 15){
            colors.remove(0);
            actions.remove(0);
          }
          if(actions.size() > 15){
            colors.remove(0);
            actions.remove(0);
          }
          mooses.remove(target);
          h.setReloading(true);
          if(mooses.size() < 20){
            h.setDefCON(0);
            h.setReloadDelay(0);
          }
          else if(mooses.size()<50){
            h.setDefCON(1);
            h.setReloadDelay(10);
          }
          else {
            h.setDefCON(2);
            h.setReloadDelay(18);
          }
          //Update Fields
          g.setColor(Color.black);
          g.fillRect(718, 137, 154, 33);
          g.fillRect(718, 250, 153, 32);
          g.fillRect(718, 367, 266, 312);
          g.setColor(Color.white);
          g.setFont(font1);
          g.drawString("" + mooses.size(), 720, 280); 
          g.drawString("" + h.getKills(), 720, 168);
          g.setFont(font2);
          for(int l = 0; l < actions.size(); l++){
            g.setColor(colors.get(l));
            g.drawString(actions.get(l), 720, 380 + l*20);
          }
          
        }
      }
      
      
      humpCount++;
      if(humpCount % 10 == 0){
        int secs = ((humpCount%600)/10);
        int mins = (humpCount/600);
        Graphics g = home.getGraphics();
        g.setColor(Color.black);
        g.fillRect(900, 138, 80, 30);
        g.setColor(Color.white);
        g.setFont(font1);
        if(secs < 10)
          g.drawString(mins + ":0" + secs, 900, 168);
        else 
          g.drawString(mins + ":" + secs, 900, 168);
      }
      
      if(timer < 40)
        timer++;
      else 
        killsInRow = 0;
      
      for(int j = 0; j < mooses.size(); j++){
        //Hunt
        mooses.get(j).live();
        if(h.isReadyToFire()){
          if(h.targetAquired(mooses.get(j))){
            if(!mooses.get(j).isReproducing()){
              h.kill(mooses.get(j));
              killPhrase = (int)(Math.random() * 5);
              if(killPhrase == 0)
                tjena.play();
              if(killPhrase == 1)
                gumman.play();
              if(killPhrase == 4)
                vad.play();
              target = j;
            }
            
          }
        }
        
        //Fertilty
        if(mooses.get(j).getDaysLeft() > 0)
          mooses.get(j).passDay();
        if(!mooses.get(j).isReproducing()){
          mooses.get(j).move();
        } else if(mooses.get(j).getDaysLeft() > 40){
          mooses.get(j).hump(humpCount);
        } else {
          mooses.get(j).setReproduction(false);
        }
      }
      //New for-loop
      for(int j = 0; j < mooses.size() - 1; j++){
        if(!mooses.get(j).isReproducing() && mooses.get(j).getDaysLeft() == 0 && !mooses.get(j).isBeingKilled()){      
          for(int k = j + 1; k < mooses.size(); k++){
            if(mooses.get(j).getDistance(mooses.get(k)) < 50 && !mooses.get(k).isReproducing() && mooses.get(k).getDaysLeft() == 0){
              mooses.get(j).reproduce(mooses.get(k));
              if(Math.round(Math.random())==1)
                moo1.play();
              else 
                moo2.play();
              mooses.add(new Moose(f));
              timesReproduced.add(0);
              lifeTime.add(0);
              timesReproduced.set(mooses.get(j).getId()-1, timesReproduced.get(mooses.get(j).getId()-1) + 1);
              timesReproduced.set(mooses.get(k).getId()-1, timesReproduced.get(mooses.get(k).getId()-1) + 1);
              timeOfBirth.add(humpCount);
              mooses.get(mooses.size()-1).setId(h.getKills()+mooses.size());
              actions.add("Moose " + mooses.get(j).getId() + " and " + mooses.get(k).getId() + " gave birth to moose " + mooses.get(mooses.size()-1).getId() + "!");
              colors.add(Color.white);
              if(actions.size() > 15){
                colors.remove(0);
                actions.remove(0);
              }
              
              //Update Fields
              Graphics g = f.getDisplay().getGraphics();
              g.setColor(Color.black);
              g.fillRect(718, 250, 153, 32);
              g.fillRect(718, 367, 266, 312);
              g.setColor(Color.white);
              
              g.setFont(font1);
              g.drawString("" + mooses.size(), 720, 280);
              g.setFont(font2);
              for(int l = 0; l < actions.size(); l++){
                g.setColor(colors.get(l));
                g.drawString(actions.get(l), 720, 380 + l*20);
              }
              
              
              
            }
          }
        }
      }
      if(humpCount % 10 == 0){
        population.add(mooses.size());
        kills.add(h.getKills());
      }
      home.repaint();
      Thread.sleep(100);
    }
    for(int i = 0; i < mooses.size(); i++){
      lifeTime.set(mooses.get(i).getId() - 1, mooses.get(i).getLifeTime());
    }
    double xStep =  (500. / (population.size() - 1));
    int bestMoose = 0, bestSurvivalMoose = 0;
    for(int i = 1; i < timesReproduced.size(); i++){
      if(timesReproduced.get(i) > timesReproduced.get(bestMoose))
        bestMoose = i;
      if(lifeTime.get(i) > lifeTime.get(bestSurvivalMoose))
        bestSurvivalMoose = i;
    }
    
    
    Graphics g = home.getGraphics();
    
    g.setColor(Color.black);
    g.fillRect(0, 0, 700, 700);
    g.setColor(Color.red);
    g.drawLine(85, 190, 100, 190);
    g.setColor(Color.white);
    g.setFont(font1);
    g.drawString("Statistics", 50, 50);
    g.drawString("MVPs", 300, 50);
    
    g.drawLine(50 + (int)(xStep * timeOfBirth.get(bestSurvivalMoose) / 10), 300, 50 + (int)(xStep / 10 * (timeOfBirth.get(bestSurvivalMoose) + lifeTime.get(bestSurvivalMoose) - 10)), 300);
    
    
    g.setFont(font2);
    g.drawString("Moose " + (bestSurvivalMoose + 1) + "'s lifespan", 52 + (int)(xStep * timeOfBirth.get(bestSurvivalMoose) / 10), 298); 
    g.drawRect(80, 150, 120, 50);
    g.drawLine(85, 160, 100, 160);
    g.drawString("Population", 110, 166);
    g.drawString("Kills", 110, 196);
    g.drawString("Moose " + (bestMoose + 1) + " reproduced " + timesReproduced.get(bestMoose) + " times.", 300, 70); 
    g.drawString("Moose " + (bestSurvivalMoose + 1) + " survived " + (lifeTime.get(bestSurvivalMoose) * 0.1) + " seconds.", 300, 90);
    
    g.drawString("Time / seconds", 500, 680);
    g.drawLine(50, 150, 50, 650);
    g.drawLine(50, 650, 550, 650);
    for(int i = 0; i <= 100; i++){
      
      if(i % 10 == 0){
        g.drawLine(50, 150 + i*5, 55, 150 + i*5);
        g.drawString("" + i, 30, 656 - 5*i);
      }
    }
    for(int i = 0; i < population.size(); i++){
      
      if(i % 5 == 0){
        g.drawLine((int)(50 + i*xStep), 650, (int)(50 + i*xStep), 645);
        g.drawString("" + i, (int)(50 + i*xStep), 665);
      }
    }
    for(int i = 0; i < population.size() - 1; i++){
      g.setColor(Color.white);
      g.drawLine((int)(50 + i*xStep), 650 - population.get(i) * 5, 50 + (int)((i+1)*xStep), 650 - population.get(i+1) * 5);
      g.setColor(Color.red);
      g.drawLine(50 + (int)(i*xStep), 650 - kills.get(i) * 5, 50 + (int)((i+1)*xStep), 650 - kills.get(i+1) * 5);
    }
    home.repaint();
  }
  
  
}
