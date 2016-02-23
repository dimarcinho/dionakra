
package dionakra;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class PowerControlador {
    
    static LinkedList<Power> pc = new LinkedList<Power>();
    
    Power tempPower;

    public PowerControlador() {

        removeAll();

    }
    
    public static LinkedList<Power> getPowerBounds(){
        return pc;
    }
    
    public void draw(Graphics g){
        for(int i = 0; i < pc.size(); i++){
            tempPower = pc.get(i);
            tempPower.draw(g);
        }
    }
    
    public void update(){
        for(int i = 0; i < pc.size(); i++){
            if(pc.get(i)!= null){
                if(pc.get(i).y > 510){
                    this.removePower(pc.get(i));
                }
            }
        }
    }
    
    public void addPower(Power power){
        pc.add(power);
    }
    
    public void removePower(Power power){
        pc.remove(power);
    }
    
    public void removeAll() {
        for(int i = 0; i < pc.size(); i++){
            pc.remove(i);            
        }
    }
    
    public void randomGenerate(Bloco bloco){
        Random rnd = new Random();
        int rndTipo = rnd.nextInt(34)+ 1; //probabilidade de 4 em NextInt(x)+1
        if(rndTipo <= 7){
            Power power = new Power(bloco);
            power.setTipo(rndTipo);
            this.addPower(power);
        }
        this.update();
    }
}
