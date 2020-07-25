
/*import java.awt.Image;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;*/

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
/**
 * Write a description of class objetoNivel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjetoNivel
{
    final String DIRECTORIO_GRAFICOS = "./graficos/";
    // instance variables - replace the example below with your own
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible=true;
    protected Image imagen;

    /**
     * Constructor for objects of class objetoNivel
     */
    public ObjetoNivel(int inicialX,int inicialY,String nombreImagen)
    {
        // initialise instance variables
        imagen=cargarImagen(nombreImagen);
        width = imagen.getWidth(null);
        height = imagen.getHeight(null);
        //width=16;
        //height = 16;
        x = inicialX;
        y = inicialY;
    }
    
    public Image cargarImagen(String imagenStr)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(DIRECTORIO_GRAFICOS + imagenStr));
        
        return ii.getImage();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return imagen;
    }
    
    public Rectangle getBounds() {
        int tempX=x+ ((width-16)/2);
        int tempY=y+ ((height-16)/2);
        return new Rectangle(tempX, tempY, 16, 16);
    }
    public boolean visible() {
        return visible;
    }
}
