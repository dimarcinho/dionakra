
package dionakra;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;


public class Power extends Thread {

    int tipo;
    int x, y, ydir;
    Image img;
    
    public Rectangle r = new Rectangle(0,0,0,0);
    
    public Power(Bloco bloco){
        
        this.x = bloco.x;
        this.y = bloco.y;
        r.setBounds(this.x, this.y, 32, 32);
        
        this.setYdir(+1);
        //System.out.println("Power criado!");
        this.start();
        //System.out.println("Power start!");
        //this.run();
    }
    
    public void init(){}
        
    public Rectangle getBounds(){
        return this.r;
    }
    
    public void draw(Graphics g){        
        img = ResourceLoader.getImage("mod"+tipo+".png");        
        g.drawImage(img, r.x, r.y, 32, 32, null);
    }

    public int getTipo(){
        return tipo;
    }

    public void setTipo(int tipo){
        this.tipo = tipo;
    }
    
    public int randomTipo(){
        Random rnd = new Random();
        int rndTipo = rnd.nextInt(19) + 1;
        return rndTipo;
    }
    
    private void setYdir(int ydir){
        this.ydir = ydir;
    }
    
    public void move(){
        this.y += ydir;
        this.r.y += ydir;
        
        if(this.y > Dionakra.HEIGHT){
            setYdir(0);
            img = null;            
            this.stop();            
        }
    }

    @Override
    public void run(){
        try{}
        catch(Exception e){System.out.println("Erro no Power: "+e);}
    }
    
}
