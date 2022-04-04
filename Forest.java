import java.awt.*;

public class Forest{
  
  private Picture display;
 
  
  public Forest(){
    display = new Picture("resources/forest.jpg");
    display.show();
   
  }
  
  public Picture getDisplay(){
    return display;
  }
  
 
  
}