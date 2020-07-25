
/**
 * Enemigos, persiguen o se alejan del protagonista. Le mandan la señal de "muerto" si lo atrapan
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Fantasma extends Personaje implements ActionListener 
{
    final String IMAGEN_ASUSTADO = "asustado1.png";



    protected Timer timerAsustado;
    protected boolean asustado;
    protected Image imagenAsustado;
    /**
     * Constructor for objects of class fantasma
     */
    public Fantasma(int x, int y,String imageIzquierda,String imageDerecha,String imageArriba,String imageAbajo)
    {
        super (x,y,imageIzquierda,imageDerecha,imageArriba, imageAbajo);
        
        imagenAsustado = cargarImagen (IMAGEN_ASUSTADO);
    }

    public void vueltaInicio ()
    {
        x=inicioX;
        y=inicioY;
        asustado=false;
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
    
    public void invierteDireccion()
    {
        dy=-(dy);
        dx=-(dx);
    }
    public Image getImagenAsustado()
    {
        return imagenAsustado;
        
    }
    public boolean getAsustado()
    {
        return asustado;
    }
    public void setAsustado ()
    {
        asustado=true;
        timerAsustado = new Timer(5000, this);
        timerAsustado.start();
    }
    public void actionPerformed(ActionEvent e) 
    {
        asustado=false;
        timerAsustado.stop();
    }    
}
