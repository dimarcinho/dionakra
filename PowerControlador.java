
package dionakra;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class PowerControlador {
    
    static LinkedList<Power> powers = new LinkedList<Power>();
    
    Power tempPower;

    public PowerControlador() {

        removeAll();
        powers = new LinkedList<Power>();

    }
    
    public static LinkedList<Power> getPowers(){
        return powers;
    }
    
    public void draw(Graphics g){
        for(int i = 0; i < powers.size(); i++){
            tempPower = powers.get(i);
            tempPower.draw(g);
        }
    }
    
    public void update(){
        for(int i = 0; i < powers.size(); i++){
            if(powers.get(i)!= null){
                if(powers.get(i).y > 510){
                    this.removePower(powers.get(i));
                }
            }
        }
    }
    
    public void addPower(Power power){
        powers.add(power);
    }
    
    public void removePower(Power power){
        powers.remove(power);
    }
    
    public void removeAll() {
        for(int i = 0; i < powers.size(); i++){
            powers.remove(i);            
        }
    }
    
    public void randomGenerate(Bloco bloco){
        Random rnd = new Random();
        int rndTipo = rnd.nextInt(34)+ 1; //probabilidade em NextInt(x)
        if(rndTipo <= 7){
            Power power = new Power(bloco);
            power.setTipo(rndTipo);
            addPower(power);
        }
        this.update();
        
    }
}
