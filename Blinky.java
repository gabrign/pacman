
/**
 * Write a description of class Blinky here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blinky extends Fantasma
{
        public Blinky(int x, int y)
    {
        super (x,y,"blinky_izquierda1.png","blinky_derecha1.png","blinky_arriba1.png","blinky_abajo1.png");
    }
    public void perseguir (int pacX,int pacY)
    {
        dy = 0;
        dx=0;
        if (y>pacY && ultimaArriba)
        {
            dy=-1;
        }else
        {
            if (y<pacY && ultimaAbajo)
            {
                dy=1;
            }else{
            if (x>pacX && ultimaIzquierda)
            {
                dx=-1;
            }else
            {
                if (x<pacX && ultimaDerecha)
                {
                    dx=1;
                }
                else
                {
                    if (ultimaArriba)
                    {
                        dy=-1;
                    }else{
                    if (ultimaAbajo)
                    {
                        dy=1;
                    }else{
                        if (ultimaIzquierda)
                        {
                            dx=-1;
                            
                        }else{
                                dx=1;
                        }
                    }
                }
            }
            
        }
        
    }
    }
        
            
  }
}
