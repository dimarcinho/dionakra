
package dionakra;

import java.awt.Graphics;
import java.util.LinkedList;

public class BlocoControlador {
    
    static LinkedList<Bloco> c = new LinkedList<Bloco>();
    
    Bloco tempBloco;
    
    public BlocoControlador(){
      
    }
    
    public static LinkedList<Bloco> getBlocoBounds(){
        return c;
    }
    
    public static void setC(LinkedList<Bloco> c) {
        BlocoControlador.c = c;
    }
    
    public void draw(Graphics g){
        for(int i = 0; i < c.size(); i++){
            tempBloco = c.get(i);
            tempBloco.draw(g);
        }
    }

    public void addBloco(Bloco blk){
        c.add(blk);
    }
    
    public void removeBloco(Bloco blk) {
        c.remove(blk);
    }
    
    public void removeAll() {
        while(c.size() > 0){
            for(int i = 0; i < c.size(); i++){
                c.remove(i);            
            }
        }
    }
}
