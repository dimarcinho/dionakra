
package dionakra;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Dionakra extends JFrame {

    private Image dbImage;
    private Graphics dbg;     
    
    public static int WIDTH = 780, HEIGHT = 540;
    
    int mx, my;
    int i, j = 0;
    
    public static boolean startGame = true;
    public static boolean gameOver = false;
    public static boolean endGame = false;
    public static boolean paused = false;
        
    public static Fundo fundo = new Fundo(WIDTH, HEIGHT);

    static Raquete r = new Raquete();
    static Bola bola = new Bola(r.x+37,r.y-8);
        
    public BlocoControlador bc = new BlocoControlador();
    public PowerControlador pc = new PowerControlador();
    
    public Level level = new Level();
    public Score score = new Score();
    
    static Thread t = new Thread(bola);
    static Thread t2 = new Thread(r); 
    //static Thread t3 = new Thread(pc); 

    public Dionakra(){
    
        this.setLocation(500, 150);
        
        //Game properties        
        addKeyListener(new AL());
        addMouseMotionListener(new MouseHandler());
        addMouseListener(new MouseHandler());
        setTitle("Dionakra v1.0");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        level.fillLevel();        
    }
    
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
    }
    
    public void paintComponent(Graphics g){        
        fundo.draw(g);
        drawTopMenu(g);
        if(!isGameOver()){
            bc.draw(g);
            pc.draw(g);        
            bola.draw(g);
            r.draw(g);
        } else {
            if(!endGame){
                g.setFont(new Font("Arial", Font.BOLD, 72));
                g.setColor(Color.yellow);
                g.drawString("Game Over", 215, 320);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.setColor(Color.magenta);
                g.drawString("Pressione Enter para recomeçar", 290, 360);    
            } else {
                g.setFont(new Font("Arial", Font.BOLD, 72));
                g.setColor(Color.yellow);
                g.drawString("Parabéns!", 215, 320);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.setColor(Color.magenta);
                g.drawString("Você terminou o Dionakra!", 290, 360);
                g.setColor(Color.cyan);
                g.drawString("Desenvolvido por Márcio Brito", 280, 425);
                g.drawString("dimarcinho@gmail.com", 300, 450);
                g.setColor(Color.green);
                g.drawString("Pressione Enter para recomeçar", 265, 490);
            }
            
        }
        
        
        
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.yellow);
        g.drawString("("+mx+","+my+")", 685, 60);
        
        repaint();
    }
    
    public void drawTopMenu(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 25, WIDTH, 40);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.yellow);
        g.drawString("Level: "+level.getLevel(), 18, 55);        
        g.drawString("Pontos: ", 105, 55);
        g.drawString(""+Score.pts, 175, 55);
        g.drawString("Recorde: ", 245, 55);
        g.drawString(""+Score.max, 335, 55);
        g.drawString("Vidas: ", 405, 55);
        
    }
    
    public void mouseX(int d){
        mx = d;
    }
    public void mouseY(int d){
        my = d;
    }
    
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            r.keyPressed(e);
            if(isGameOver() && e.getKeyCode() == e.VK_ENTER){
                setGameOver(false);
                setStartGame(true);
                bola.reset();
                fundo.setTipo(1);
            }
            
            if(e.getKeyCode() == e.VK_P){
                if(!paused){
                    
                }
            }
        }
            
        @Override
        public void keyReleased(KeyEvent e){
            r.keyReleased(e);
        }
    }
    
    public class MouseHandler extends MouseAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e){
            mouseX(e.getX());
            mouseY(e.getY());
        }
        @Override
        public void mousePressed(MouseEvent e){
            
        }
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
    }
    
    public static boolean isGameOver() {
        return gameOver;
    }

    public static boolean isStartGame() {
        return startGame;
    }
    
    public static void setGameOver(boolean gameOver) {
        Dionakra.gameOver = gameOver;
    }

    public static void setStartGame(boolean startGame) {
        Dionakra.startGame = startGame;
    }
    
    public static Fundo getFundo() {
        return fundo;
    }
    
    public static void main(String[] args) {
        Dionakra game = new Dionakra();        
       
        t.start();
        t2.start();
        //t3.start();
    }
}
