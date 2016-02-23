
package dionakra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Bola implements Runnable {
        
    public int x,y,xdir,ydir;
    public int vidas;
    
    Image img;
        
    private LinkedList<Bloco> c = BlocoControlador.getBlocoBounds();
    
    
    PowerControlador pc = new PowerControlador();
    private LinkedList<Power> lpc = pc.getPowerBounds();
    
    Rectangle bola;
    private int speed = 5;
    private int countBloco = 0;
    
    
    boolean init = false;
    boolean paused = false;
    boolean glue = false;
    
    Level lvl = new Level();
    Score sc = new Score();
    
    Sound s = new Sound();
    
    public Bola(int x, int y){
    
        this.x = x;
        this.y = y;
        bola = new Rectangle(this.x, this.y, 8, 8);
        
        vidas = 4;
        
        setYdir(0);
        setXdir(0);
                
    }
    
    public void setInit(boolean init) {
        this.init = init;
    }
    
    public boolean isInit() {
        return init;
    }
        
    public void setPaused(boolean paused) {
        this.paused = paused;
    }   
    
    public Rectangle getBounds(){
        return bola;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.MAGENTA);
        img = ResourceLoader.getImage("bola.png");        
        g.drawImage(img, bola.x, bola.y, null);
        desenhaVidas(g, img);
    }
    
    public void desenhaVidas(Graphics g, Image a){
         for(int i = 1; i <= this.vidas; i++){
            g.drawImage(a, 445+15*i, 47, null);
        }
    }
    
    public void setXdir(int d){
        xdir = d;
    }
    
    public void setYdir(int d){
        ydir = d;
    }
    
    public void setGlue(boolean glue) {
        this.glue = glue;
    }
    
    public void collision(Raquete rqt){
        
        Rectangle rec = new Rectangle();
        rec.setBounds(this.bola);
        int vx, vy, vrx;
        vrx = rqt.getXdir()/5;
        vx = 2*this.xdir - vrx;
        
        //a direção da raquete influencia na saída do vetor x
        if(vrx == 0){
            vx = this.xdir;
        } else {
            if(vx*vrx > 0){
                vx = this.xdir - 2;
            } else{
                vx = this.xdir + 2;
            }    
        }

        //randomiza uma velocidade para o y
        Random rnd = new Random();
        int rnum = rnd.nextInt(2)+ 1;        
        vy = -rnum*this.ydir;        
        
        if(rec.intersects(rqt.r)){
            s.collision.play();
            setXdir(vx);
            setYdir(vy);
            if(this.glue){
                setXdir(0);
                setYdir(0);
                setInit(false);                
                bola.y = bola.y - 2;
            }
        }   
    }
    
    public void collisionBlocks(){
        int xb,yb; //x,y da bola
        int xm,ym; // x,y do bloco
        int w,h; //width e height do bloco
        
        for(int i = 0; i < c.size(); i++){
            if(getBounds().intersects(c.get(i).getBounds())){
                s.collision.play();
                sc.addPoints(1);
                pc.randomGenerate(c.get(i)); //gera ou não os powers
                
                xb = bola.x + (int)bola.width/2;
                yb = bola.y + (int)bola.height/2;
                xm = c.get(i).r.x;
                ym = c.get(i).r.y;
                w = c.get(i).r.width;
                h = c.get(i).r.height;
                xm = xm + (int)w/2;
                ym = ym + (int)h/2;                

                
                if(yb - ym == 16 || yb - ym == 15){
                    setYdir(+1);
                }
                
                if(yb - ym == -15 || yb - ym == -16){
                    setYdir(-1);
                }
                
                if(xb - xm == 28 || xb - xm == 25){
                    setXdir(+1);
                }
                if(xb - xm == -28 || xb - xm == -25){
                    setXdir(-1);
                }
                
                c.remove(i); //remove o bloco do jogo e do controlador
                
                if(c.size() == 0){//próximo nível
                    restartBola();
                    lvl.setNextLevel();
                    lvl.fillLevel();
                    Dionakra.getFundo().setNextTipo();
                }
                
                //rotina para ir aumentando a velocidade da bola
                countBloco++;
                if(countBloco > 10){
                    this.updateSpeed();
                    countBloco = 0;
                }
                
            }
        }
    }
    
    public void move(){
        
        if(!init){
            
            bola.x = Dionakra.r.getX() + Dionakra.r.r.width/2 - 4;
            
        } else {

            if(bola.y < 500){
                collision(Dionakra.r);
                collisionBlocks();
            }

            bola.x += xdir;
            bola.y += ydir;

            if(bola.x <= 50){
                setXdir(+1);            
            }
            if(bola.x >= 730 - bola.width){
                setXdir(-1);            
            }
            if(bola.y <= 80){
                setYdir(+1);            
            }
            if(bola.y >= 544){
                this.perdeVidas();                
                restartBola();
            }
        }       
        
    }
    
    public void restartBola(){
        setXdir(0);
        setYdir(0);
        speed = 6;
        bola.x = Dionakra.r.getX() + Dionakra.r.r.width/2 - 4;
        bola.y = Dionakra.r.getY() - bola.height;
        setInit(false);
        sc.setF(1);
        this.setGlue(false);
        s.restartbola.play();
    }
    
    public void updateSpeed(){
        this.speed++;
        if(this.speed > 3)
            this.speed = 3;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
    public void ganhaVidas() {
        this.vidas++;
    }
    
    public void perdeVidas() {
        this.vidas--;
        if(this.vidas == 0){
            Dionakra.setGameOver(true);
        }
    }
    
    
    
    public void reset(){
        vidas = 4;        
        setYdir(0);
        setXdir(0);
        
        sc.reset();
        pc.removeAll();
        Dionakra.r.reset();
        
        lvl.reset();
        
        restartBola();
    }
    
    
    @Override    
    public void run(){
        try{
            while(true){
                move();
                for(int i = 0; i < lpc.size(); i++){
                    lpc.get(i).move();
                }
                Thread.sleep(speed);  //mínimo de 3;
            }                
        }
        catch(Exception e){System.out.println("System error: "+e);}
    }
}
