
/**
 * El "main" del juego. crea el objeto juego al comenzar.
 * 
 */
import javax.swing.JFrame;

public class pacman extends JFrame
{
    public pacman()
    {
        add(new Tablero());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(448, 570);
        setLocationRelativeTo(null);
        setTitle("PACMAN");
        setResizable(false);
        setVisible(true);
    }
    /**
     * Main
     */
    public static void main(String [] args)
    {
       new pacman();
            
    }
}
