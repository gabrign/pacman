
/**
 * Write a description of class Blinky here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;

public class Clyde extends Fantasma
{
        public Clyde(int x, int y)
    {
        super (x,y,"clyde_izquierda1.png","clyde_derecha1.png","clyde_arriba1.png", "clyde_abajo1.png");
    }
    public void perseguir (int pacX,int pacY)
    {
        dy = 0;
        dx=0;
        Random generadorAleatorio = new Random();
        boolean noencontrado=true;
        while (noencontrado)
        {
            int numeroAleatorio = generadorAleatorio.nextInt(4);
            switch (numeroAleatorio)
            {
                case 0: 

                    if (ultimaArriba)
                    {
                        dy=-1;
                        noencontrado=false;
                    }
                    break;

                case 1:
                
                    if (ultimaAbajo)
                    {
                        
                        dy=1;
                        noencontrado=false;
                    }
                break;
                case 2:
                
                    if (ultimaIzquierda)
                    {
                          dx=-1;
                             noencontrado=false;           
                    }
                break;
                case 3:
                    if (ultimaDerecha)
                    {
                          dx=1;
                          noencontrado=false;
                     }
                 break;
            }
        }
    }

 
        
            
        
   
    
}
