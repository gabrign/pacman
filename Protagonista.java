 

import java.awt.Image;
import java.awt.event.KeyEvent;

import java.util.ArrayList;


public class Protagonista extends Personaje {
    private boolean KeyLeft;
    private boolean KeyRight;
    private boolean KeyUp;
    private boolean KeyDown;
    
    private String craft = "./graficos/pacman_izquierda.png";

    private ArrayList missiles;


    public Protagonista(int x, int y){
        super (x,y,"pacman_izquierda.png","pacman_derecha.png","pacman_arriba.png","pacman_abajo.png");
        
        missiles = new ArrayList();
    }
    
    public void keyPressed(KeyEvent e) 
    {
        //System.out.println("test");
        int key = e.getKeyCode();
       
        
       if (key==KeyEvent.VK_LEFT)
            {
                dx = -1;
            }
            
       if (key==KeyEvent.VK_RIGHT)
       {
                dx = 1;
       }
       
       if (key==KeyEvent.VK_UP)
       {
                dy = -1;
       }
       
       if (key==KeyEvent.VK_DOWN)
       {
                dy = 1;
       }
 
    }
    
    public void cambiarDireccion()
    {
        if ((ultimaIzquierda && dx==-1)||(ultimaDerecha && dx==1))
        {
            dy=0;
        }
        if ((ultimaArriba && dy==-1)||(ultimaAbajo && dy==1))
        { 
            dx=0; 
        }
        
    }
    
    public void vueltaInicio ()
    {
        x=inicioX;
        y=inicioY;
    }
}