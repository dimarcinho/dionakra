
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
    Image leftRqt, rightRqt, meioRqt;
    
    boolean paused = false;
    private int centerSize = 3;
    
    private LinkedList<Power> pc = PowerControlador.getPowerBounds();
    
    Rectangle r;
    
    Score pontos = new Score();    
    Sound s = new Sound();    
        
    public Raquete(){
    
        x = 315;
        y = 500;
        r = new Rectangle(this.x, this.y, 85, 15);        
                
    }
    
    public void reset(){
        x = 315;
        y = 500;
        r = new Rectangle(this.x, this.y, 85, 15);
        centerSize = 3;
    }
    
    public Rectangle getBounds(){
        return r;
    }
    
    public void draw(Graphics g){

        leftRqt = ResourceLoader.getImage("raquete_esquerda.png");
        rightRqt = ResourceLoader.getImage("raquete_direita.png");
        meioRqt = ResourceLoader.getImage("raquete_meio.png");
        
        int xref = this.getX();
        
        g.drawImage(leftRqt, xref, this.r.y, null);
        xref = xref + leftRqt.getWidth(null);        
        for(int i = 1; i <= centerSize; i++){
            g.drawImage(meioRqt, xref, this.r.y, null);
            xref = xref + meioRqt.getWidth(null);            
        }        
        g.drawImage(rightRqt, xref, this.r.y, null);
        xref += rightRqt.getWidth(null);
        xref -= this.getX();
        r.setBounds(r.x, r.y, xref, r.height); //atualiza a raquete para o tamanho correto
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

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setCenterSize(int centerSize) {
        this.centerSize = centerSize;
    }    
    
    
    public void updateCenterSize(int k){
        centerSize += k;
        if(centerSize == 0){
            Dionakra.bola.perdeVidas();
            Dionakra.bola.restartBola();            
        }
        if(centerSize > 5){
            centerSize = 5;
        }
        Dionakra.bola.setGlue(false);
        Dionakra.bola.setSlowBall(false);
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
            case 1: //tira uma vida
                Dionakra.bola.perdeVidas();                
                s.pup1.play();
                break;
            case 2: //aumenta o fator de multiplicação de pontos
                pontos.addPoints(10);
                pontos.addF();                
                s.pup2.play();
                break;
            case 3: //introduz a "cola" na raquete                
                Dionakra.bola.setGlue(true);
                Dionakra.bola.setSlowBall(false);
                this.setCenterSize(3);
                pontos.addPoints(10);
                s.pup3.play();
                break;
            case 4: //aumenta uma vida
                Dionakra.bola.ganhaVidas();
                pontos.addPoints(10);
                s.pup4.play();
                break;
            case 5: //aumenta a raquete                           
                updateCenterSize(+1);                
                pontos.addPoints(10);
                s.pup5.play();
                break;
            case 6: //diminui a raquete/tira uma vida
                updateCenterSize(-1);                
                s.pup6.play();
                break;
            case 7: //deixa a bola lenta
                Dionakra.bola.setSpeed(7);
                Dionakra.bola.setSlowBall(true);
                this.setCenterSize(3);
                Dionakra.bola.setGlue(false);
                pontos.addPoints(10);
                s.pup7.play();
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
        catch(Exception e){System.out.println("Erro na Raquete: "+e);}
    }
    
}
