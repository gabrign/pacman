import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("unchecked")
public class Tablero extends JPanel implements ActionListener {
    final int ESCALA_PIXELES = 16;
    private int puntuacion = 0;
    private Timer timer;
    private int vidas = 1;
    private boolean pausa = false;
    private boolean cheat = false;
    private Protagonista protagonista;
    private ObjetoNivel nivel;
    private Font fuente;
    private String msg;
    private int tableroX=0;
    private int tableroY=0;
    private int snacksRestantes=0;
    private FontMetrics metr;
    private Graphics2D g2d;
    private boolean moverFantasmas=true;
    private boolean jugando=true;
    private int[][] listaSnacks ;
    private boolean[] salidas;
    private ArrayList muros=new ArrayList<ObjetoNivel>();
    private ArrayList snacks=new ArrayList<ObjetoNivel>();
    private ArrayList powerups= new ArrayList<ObjetoNivel>();
    private ArrayList fantasmas= new ArrayList<Fantasma>();
    
    //Array con el nivel codificado
    private int[][] nivelArray = {
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,1,1,1,1,1,1,1,1,1,1,1,-1},
        {-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1},
        {-1,2,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,2,-1},
        {-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1},
        {-1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1},
        {-1,1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,1,-1,-1},
        {-1,1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,1,-1,-1},
        {-1,1,1,1,1,1,1,-1,-1,1,1,1,1,-1,-1,1,1,1,1,-1,-1,1,1,1,1,1,1,-1,-1},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1},
        {0,0,0,0,0,-1,1,-1,-1,-1,-1,-1,0,-1,-1,0,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1},
        {0,0,0,0,0,-1,1,-1,-1,0,0,0,4,0,5,0,6,0,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {0,0,0,0,0,-1,1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {0,0,0,0,0,-1,0,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,0,0,0,0,0},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,0,0,0,0,0,0,0,0,0,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,1,-1,-1,-1,-1,-1,-1},
        {-1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,1,1,1,1,1,1,1,1,1,1,1,-1},
        {-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1},
        {-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1},
        {-1,2,1,1,-1,-1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,-1,-1,1,1,2,-1},
        {-1,-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,-1},
        {-1,-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,-1},
        {-1,1,1,1,1,1,1,-1,-1,1,1,1,1,-1,-1,1,1,1,1,-1,-1,1,1,1,1,1,1,-1},
        {-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1},
        {-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1},
        {-1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    };
   
    public Tablero() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        fuente = new Font("Arial", Font.BOLD, 14);

        nivel = new ObjetoNivel(0,0,"nivel.png");

        poblar(tableroX,tableroY);
        timer = new Timer(5, this);
        timer.start();
    }
    private void pintarLista (ArrayList lista)
    {
        for (int i = 0; i < lista.size(); i++ ) {
                ObjetoNivel m = (ObjetoNivel) lista.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
    }
    public void paint(Graphics g) {
        super.paint(g);
        
        g2d = (Graphics2D)g;
        
        
        metr = this.getFontMetrics(fuente);
        g.setColor(Color.white);
        g.setFont(fuente);
        
        //Si esta jugando muestra todo
        if (jugando) 
        {
            
            g2d.drawImage(nivel.getImage(),nivel.getX(), nivel.getY(), this);
            
            
            pintarLista (powerups);
            pintarLista (snacks);
            
            if (cheat)
            {
                pintarLista (muros);
            }

                g2d.drawImage(protagonista.getImage(), protagonista.getX(), protagonista.getY(), this);

            
            //imagen de asustado o imagen normal para los fantasmas
            for (int i = 0; i < fantasmas.size(); i++ ) {
                Fantasma m = (Fantasma) fantasmas.get(i);
                if (m.getAsustado())
                {
                    g2d.drawImage(m.getImagenAsustado(), m.getX(), m.getY(), this);
                }
                 else
                 {
                    g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
                }
            }
  
        }
        else
        {
            pausa=true;
         //Mesajes de felicitacion y juego terminado
           Font small = new Font("Helvetica", Font.BOLD, 28);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (getWidth()- metr.stringWidth(msg)) / 2,
                         getHeight() / 2);
        }
        //Marcador 
        Font small = new Font("Helvetica", Font.BOLD, 24);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString("Puntuacion:"+Integer.toString(puntuacion), 4,528);
            
            g.drawString("Vidas:" + Integer.toString(vidas), 240,528);
            
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
            
    }
    private void calcularProtagonista()
    {
            salidas=calcularSalidas(protagonista);
            if (protagonista.encontradaSalida(salidas))
            {
                protagonista.cambiarDireccion();
            }
            //colision protagonista con escenario
            Rectangle testX=protagonista.getBounds();
            testX.x += protagonista.getDx();
            if (pruebaColisionList (testX,muros)==-1)
            {   
                protagonista.mueveX();
            }
            
            Rectangle testY=protagonista.getBounds();
            testY.y += protagonista.getDy();
            
            if (pruebaColisionList (testY,muros)==-1){

                protagonista.mueveY();
            }
    }
    public void actionPerformed(ActionEvent e) {
        requestFocus();
        if (!pausa)
        {

            calcularProtagonista();
            calcularFantasmas();
            
            cogerSnacks();
            cogerPowerUp();

            
            //Condiciones para victoria/derrota
            
            if (snacksRestantes<=0)
            {
                jugando = false;
                msg = "¡Felicidades!";
            }
            if (vidas<0)
            {
                jugando = false;
                msg = "Juego Terminado";
            }
            
            //muerte por colision con fantasta (ignorado si esta en modo cheat), sino, muerte del propio fantasma si esta "asustado"
            //si el fantasma muere, vuelve a casa, sino el jugador pierde una vida
            pruebaColisionProtagonistaFantasmas();

            
            //repintamos JFrame...
            repaint();  
        }
    }
    
    private void calcularFantasmas()
    {
        //colision fantasmas con escenario y ellos mismos
            ArrayList fantasmasTemp;
            
            for (int i = 0; i < fantasmas.size(); i++ ) {
                {
                    fantasmasTemp = new ArrayList<Fantasma>(fantasmas);
                    Fantasma m = (Fantasma) fantasmas.get(i);
                    /*quitamos el actual durante la comprobacion en otra lista para no colisionar consigo
                    mismo*/
                    fantasmasTemp.remove(i);
                    
                     
                    //miramos salidas, por defecto todas están cerradas y las abrimos según encontramos
                    //ATENCIÓN: para evitar el retroceso, se comprueban todas menos la dirección de
                    // la que prócede el fantasma, evita loops en el movimiento.

                    
                    salidas=calcularSalidas(m);

                    //finalmente comprobamos que si hay una salida y movemos al fantasma por un metodo comun
                    if (m.encontradaSalida(salidas))
                    {
                       m.perseguir(protagonista.getX(),protagonista.getY());
                    }
                    
                    //aqui se comprueba que no colisiona ni con el escenario ni con otros fantasmas 
                    //haciéndolos retroceder 
                    
                    //miramos colisiones en X
                    Rectangle testX =m.getBounds();
                        testX.x += m.getDx();
                        
                        if (pruebaColisionList (testX,muros)==-1)
                        {
                            if (pruebaColisionList (testX,fantasmasTemp)==-1)
                            {
                            //
                                m.mueveX();
                            }
                            else
                            {
                                m.invierteDireccion();    
                            }
                        }else
                        {
                            //m.perseguir (protagonista.getX(),protagonista.getY());
                        }

                   
                   //miramos colisiones en Y
                    Rectangle testY=m.getBounds();
                    testY.y += m.getDy();    

                        if (pruebaColisionList (testY,fantasmasTemp)==-1)
                        {
                            m.mueveY();
                        }
                        else
                        {
                            m.invierteDireccion();
                        }
                    
                }
            }

    }
    private void cogerSnacks()
    {
        //coger snacks
            int cogeSnack = pruebaColisionList (protagonista.getBounds(),snacks);
            
            if (cogeSnack>-1){
                snacks.remove(cogeSnack);
                puntuacion+=10;
                snacksRestantes--;
            }
    }
    private void cogerPowerUp ()
    {
                    //coger powerup
            int cogePowerUp = pruebaColisionList (protagonista.getBounds(),powerups);
            if (cogePowerUp>-1){
                powerups.remove(cogePowerUp);
                puntuacion+=50;
                for (int i = 0; i < fantasmas.size(); i++ ) 
                {
                    Fantasma m = (Fantasma) fantasmas.get(i);
                    m.setAsustado();
                }
            }
        
    }
    private boolean[] calcularSalidas(Personaje m)
    {
          //coger dx y dy para ver la direccion por la que ya circula
          boolean salidaIzquierda=false;
          boolean salidaDerecha=false;
          boolean salidaArriba=false;
          boolean salidaAbajo=false;
          Rectangle testX;
          boolean salidas[] = new boolean[4]; 
          int dx=m.getDx();
          int dy=m.getDy();
          //mira salida izquierda
          if (dx!=1)
          {
               testX =m.getBounds();
               testX.x -= 1;
               if (pruebaColisionList (testX,muros)==-1)
               {
                    salidas[0]=true;
               }
          }
          //mira salida derecha
           if (dx!=-1){
                 testX =m.getBounds();
                 testX.x += 1;
                 if (pruebaColisionList (testX,muros)==-1)
                 {
                     salidas[1]=true;
                 }
           }
           //mira salida arriba
           if (dy!=1){
                testX =m.getBounds();
                testX.y -= 1;
                if (pruebaColisionList (testX,muros)==-1)
                {
                     salidas[2]=true;
                }
           }
                    //mira salida abajo
           if (dy!=-1)
                    {
               testX =m.getBounds();
               testX.y += 1;
               if (pruebaColisionList (testX,muros)==-1)
                {
                     salidas[3]=true;
               }
           }
        return salidas;
    }
    private void perderUnaVida()
    {
        protagonista.vueltaInicio();
        for (int i = 0; i < fantasmas.size(); i++ ) 
        {
            Fantasma m = (Fantasma) fantasmas.get(i);
            m.vueltaInicio();
        }
        vidas--;
    }
    private void pruebaColisionProtagonistaFantasmas ()
    {
            int fantasmaTocado = pruebaColisionList (protagonista.getBounds(),fantasmas);
            if (fantasmaTocado>-1){
                    Fantasma m = (Fantasma) fantasmas.get(fantasmaTocado);
                 if (!cheat && !m.getAsustado())
                 {       
                         perderUnaVida();
                 }
                 
                 if (m.getAsustado())
                 {
                     puntuacion+=100;
                     m.vueltaInicio();
                 }
            }
        
    }
    private void poblar (int x, int y)
    {
        int convertidaX;
        int convertidaY;
        int[][] ArrayList;
        int arrayCount =0;
        
        
            
            for (y=0;y<nivelArray.length;y++)
            {
                for (x=0;x<nivelArray[y].length;x++)
                {
                    convertidaX=x * ESCALA_PIXELES;
                    convertidaY=y * ESCALA_PIXELES;
                    
                    switch (nivelArray[y][x])
                    {
                        
                        case -1:
                            muros.add (new ObjetoNivel(convertidaX,convertidaY,"muro.png"));
                            break;
                        case 1:
                            snacks.add (new ObjetoNivel(convertidaX,convertidaY,"snack.png"));
                            snacksRestantes++;
                            break;
                        case 2:
                            powerups.add (new ObjetoNivel(convertidaX,convertidaY, "powerup.png"));
                            break;
                        case 3:
                            protagonista = new Protagonista(convertidaX-8,convertidaY-8);
                            break;
                        case 4:
                            fantasmas.add ( new Blinky (convertidaX-8,convertidaY-8));
                            break;
                        case 5:
                            fantasmas.add ( new Pinky (convertidaX-8,convertidaY-8));
                            break;
                        case 6:
                            fantasmas.add ( new Clyde (convertidaX-8,convertidaY-8));
                            break;
                }
            }
        }
    }
    public boolean pruebaColisionRect(Rectangle a,Rectangle b) {
    
        if (a.intersects(b)) 
        {
           return true;
        }
        else{
            return false;
        }
    }
    
    
    public int pruebaColisionList(Rectangle a,ArrayList lista) {
        
        for (int i = 0; i < lista.size(); i++ ) {
                ObjetoNivel m = (ObjetoNivel) lista.get(i);
                if (pruebaColisionRect (a,m.getBounds()))
                {
                    return i;
                }
        }
        return -1;

    }

    private class TAdapter extends KeyAdapter {


        public void keyPressed(KeyEvent e) {
            
            protagonista.keyPressed (e);
            int key=e.getKeyCode();
                if (key==KeyEvent.VK_P)
                {
                    if (pausa)
                    {
                        pausa = false;
                    } else
                    {
                        pausa = true;
                    }
                }
                if (key==KeyEvent.VK_C)
                {
                    if (cheat)
                    {
                        cheat = false;
                    } else
                    {
                        cheat = true;
                    }
                }
        }
    }
}
