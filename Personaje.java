import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * Un objeto de nivel que permite movimiento y graficos cambiantes
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Personaje extends ObjetoNivel
{
    protected int dx = 0;
    protected int dy = 0;
    protected boolean bloqueadoX ;
    protected boolean bloqueadoY ;
    protected Image imagenIzquierda;
    protected Image imagenDerecha;
    protected Image imagenArriba;
    protected Image imagenAbajo;
    protected int inicioX;
    protected int inicioY;
    protected boolean ultimaIzquierda=false;
    protected boolean ultimaDerecha=false;
    protected boolean ultimaArriba=false;
    protected boolean ultimaAbajo=false;
    
    
    /**
     * Constructor for objects of class entidad
     */
    public Personaje(int x, int y,String imagenIzquierda,String imagenDerecha,String imagenArriba,String imagenAbajo)
    {
        super(x,y,imagenAbajo);
        this.x=x;
        this.y=y;
        inicioX=x;
        inicioY=y;
        this.imagenIzquierda = cargarImagen(imagenIzquierda);
        this.imagenDerecha = cargarImagen(imagenDerecha);
        this.imagenArriba = cargarImagen(imagenArriba);
        this.imagenAbajo = cargarImagen(imagenAbajo);
        this.imagen=this.imagenAbajo;
    }
    public int getDx ()
    {
        return dx;
        
    }
    public int getDy ()
    {
        return dy;
        
    }
        public void setDx (int valor)
    {
        dx= valor;
        
    }
        public void setDy (int valor)
    {
        dy=valor;
        
    }

    public boolean encontradaSalida ( boolean[] salidas)
    {
        boolean salidaIzquierda= salidas[0];
        boolean salidaDerecha= salidas[1];
        boolean salidaArriba= salidas[2];
        boolean salidaAbajo= salidas[3];
        boolean test=false;
        if (((!ultimaIzquierda)&&salidaIzquierda) || ((!ultimaDerecha)&&salidaDerecha) ||  ((!ultimaArriba)&&salidaArriba) || ((!ultimaAbajo)&&salidaAbajo))
        {
            test=true;
        }

        ultimaIzquierda=salidaIzquierda;
        ultimaDerecha=salidaDerecha;
        ultimaArriba=salidaArriba;
        ultimaAbajo=salidaAbajo;
        return test;
    }
    
    public void mueveX() {
        if (!bloqueadoX){
            if (dx<0)
            {
                imagen=imagenIzquierda;
            }
            else
            {
                if (dx>0)
                {
                imagen=imagenDerecha;
                }
            }
            
            x += dx;
        }
 
        bloqueadoX = false;
    }
    
    public void mueveY() {

        if (!bloqueadoY)
             {
                if (dy<0)
                {
                  imagen=imagenArriba;
                }
                else
                {
                    if (dy>0)
                    {
                        imagen = imagenAbajo;
                    }
                }
                y += dy;
        }
 
        bloqueadoY = false;
    }
    
}