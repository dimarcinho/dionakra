
package dionakra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Raquete implements Runnable {
    
    public int x,y,xdir,ydir;
    Image img;
    
    private LinkedList<Power> pc = PowerControlador.getPowerBounds();
    
    Rectangle r;
    
    Score pontos = new Score();
    
    Sound s = new Sound();    
        
    public Raquete(){
    
        x = 315;
        y = 500;
        r = new Rectangle(this.x, this.y, 75, 15);        
                
    }
    
    public void reset(){
        x = 315;
        y = 500;
        r = new Rectangle(this.x, this.y, 75, 15);        
    }
    
    public Rectangle getBounds(){
        return r;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.red);
        //g.fillRect(r.x, r.y, r.width, r.height);
        img = ResourceLoader.getImage("raquete.png");
        g.drawImage(img, r.x, r.y, null); 
        
        //desenha(g);
        
    }
    
    public void desenha(Graphics g){
        int x1, x2, x3, x4;        
        Image leftRqt, rightRqt, meioRqt;
        leftRqt = ResourceLoader.getImage("raquete_esquerda.png");
        rightRqt = ResourceLoader.getImage("raquete_direita.png");
        meioRqt = ResourceLoader.getImage("raquete_meio.png");
        int xBasis = 100;
        x1 = xBasis + leftRqt.getWidth(null);
        x2 = x1 + 5;
        x3 = x2 + meioRqt.getWidth(null);
        x4 = x3 + meioRqt.getWidth(null);
        
        g.drawImage(leftRqt, xBasis, 500, null);
        g.drawImage(meioRqt, xBasis+leftRqt.getWidth(null), 500, null);        
        g.drawImage(meioRqt, xBasis+leftRqt.getWidth(null)+meioRqt.getWidth(null), 500, null);
        g.drawImage(meioRqt, xBasis+leftRqt.getWidth(null)+2*meioRqt.getWidth(null), 500, null);
        g.drawImage(rightRqt, xBasis+leftRqt.getWidth(null)+3*meioRqt.getWidth(null), 500, null);
        
        
        
    }
    
    public void setXdir(int d){
        xdir = d;
    }
    
    public int getXdir() {
        return xdir;
    }
    
    public int getX(){
        return r.x;
    }
    
    public int getY(){
        return r.y;
    }
    
    public void move(){
        
        collisionPowers();
        
        r.x += xdir;
        r.y += ydir;
        
        if(r.x <= 51){
            r.x = 51;
            
        }
        if(r.x >= 730 - r.width){
            r.x = 730 - r.width;
            
        }
    }
    
    public void collisionPowers(){        
        for(int i = 0; i < pc.size(); i++){
            if(getBounds().intersects(pc.get(i).getBounds())){
                int k = pc.get(i).getTipo();                
                pc.remove(i);
                this.bonusPower(k);
            }
        }
        
    }
    
    public void bonusPower(int tipo){
        switch(tipo){
            default:
                System.out.println("Tipo de power inválido no switch do bonusPower()!");
            case 1:
                Dionakra.bola.perdeVidas();                
                s.pup1.play();
                break;
            case 2:
                pontos.addPoints(10);
                pontos.addF();                
                s.pup2.play();
                break;
            case 3:
                pontos.addPoints(10);
                Dionakra.bola.setGlue(true);
                s.pup3.play();
                break;
            case 4:
                pontos.addPoints(-10);
                Dionakra.bola.ganhaVidas();
                s.pup4.play();
                break;
        }
    }
    
    public void keyPressed(KeyEvent e){        
            int keyCode = e.getKeyCode();
            
            if(keyCode == e.VK_LEFT)
                setXdir(-5);
            
            if(keyCode == e.VK_RIGHT)
                setXdir(+5);
           
    }
    
    public void keyReleased(KeyEvent e){
            int keyCode = e.getKeyCode();
            
            if(keyCode == e.VK_LEFT)
                setXdir(0);
            if(keyCode == e.VK_RIGHT)
                setXdir(0);
            
            if(keyCode == e.VK_SPACE){
                if(!Dionakra.bola.isInit()){                    
                    Dionakra.bola.setYdir(-1);
                    if(this.xdir != 0)
                        Dionakra.bola.setXdir(this.xdir/2);
                    else
                        Dionakra.bola.setXdir(1);                    
                    Dionakra.bola.setInit(true);
                }
            }
           
        }
    
    @Override    
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(15);           
            }                
        }
        catch(Exception e){System.out.println("System error: "+e);}
    }
    
}
