
/**
 * Write a description of class Blinky here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pinky extends Fantasma
{
        public Pinky(int x, int y)
    {
        
        super (x,y,"pinky_izquierda1.png","pinky_derecha1.png","pinky_arriba1.png","pinky_abajo1.png");
    }
    public void perseguir (int pacX,int pacY)
    {
        
        dy = 0;
        dx=0;
        if (x>pacX && ultimaIzquierda)
            {
                dx=-1;
            }else
            if (x<pacX && ultimaDerecha)
            {
                    dx=1;
            }
            else
            {
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
                           if (ultimaIzquierda)
                           {
                              dx=-1;
                                        
                           }else{
                           if (ultimaDerecha){
                               dx=1;
                           }
                           else{
                           if (ultimaArriba)
                           {
                               dy=-1;
                           }else{

                               dy=1; 
                            }
                          }
                        }
                   }
               }
        }
    }
}
