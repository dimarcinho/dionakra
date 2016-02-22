
package dionakra;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Bloco {
    
    int tipo;
    int x,y;        
    Image img;
    
    public Rectangle r = new Rectangle(0,0,0,0);
    
    public Bloco(int mx, int my){
        
        this.x = mx;
        this.y = my;                        
        r.setBounds(this.x, this.y , 50, 25);
        
        setTipo(1);
    }
    
    public Rectangle getBounds(){
        return r;
    }
    
    public void draw(Graphics g){     
        img = ResourceLoader.getImage("bloco0"+tipo+".png");
        g.drawImage(img, r.x, r.y, 50, 25, null);
    }
    
    public void update(){                     
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
