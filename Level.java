/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dionakra;

public class Level {

    private static int level = 1;
    
    Bloco tempBloco;
    BlocoControlador bc = new BlocoControlador();
    
    public Level(){}
    
    public void fillLevel(){
        
        if(level == 1){
            int k = 1;
            for(int j = 105; j < 330; j+=50){            
                for(int i = 70; i < 705; i+=50){
                    tempBloco = new Bloco(i,j);
                    tempBloco.setTipo(k);
                    bc.addBloco(tempBloco);
                }
                if(k >= 9)
                    k = 2;
                else
                    k+=1;            
            }   
        }
        
        if(level == 2){
            int k = 1;
            boolean jump = false;
            for(int j = 105; j < 330; j+=25){            
                for(int i = 70; i < 705; i+=50){
                    if(!jump){
                        tempBloco = new Bloco(i,j);
                        tempBloco.setTipo(k);
                        bc.addBloco(tempBloco);
                        jump = true;
                    } else {
                        jump = false;
                    }
                    
                }
                if(k >= 9)
                    k = 2;
                else
                    k+=1;            
            }   
        }
        
        if(level == 3){
            int k = 1;
            int cont = 0;
            for(int j = 105; j < 330; j+=25){            
                for(int i = 70; i < 705; i+=50){
                    if(cont < 3){
                        tempBloco = new Bloco(i,j);
                        tempBloco.setTipo(k);
                        bc.addBloco(tempBloco);
                        cont++;
                    } else {
                        cont = 0;
                    }
                    
                }
                if(k >= 9)
                    k = 2;
                else
                    k+=1;            
            }   
        }
        
        if(level == 4){
            int k = 1; //para colorir os blocos

            for(int j = 105; j < 330; j+=25){            
                for(int i = 70; i < 705; i+=50){
                    tempBloco = new Bloco(i,j);
                    tempBloco.setTipo(k);
                    bc.addBloco(tempBloco);
                }
                if(k == 9)
                    k = 1;
                else
                    k++;            
            }
        }
        
        if(level == 5){
            int k = 10;
            
            for(int j = 105; j < 330; j+=25){ //330
                for(int i = 70; i < 705; i+=50){ //705
                    
                        tempBloco = new Bloco(i,j);
                        tempBloco.setTipo(k);
                        bc.addBloco(tempBloco);
                
                    
                }
                
            }   
        }
        
        if(level > 5){
            setLevel(5);
            Dionakra.endGame = true;
            Dionakra.setGameOver(true);
        }
        

    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setNextLevel() {
        this.level++;
    }
    
    public void reset(){
        setLevel(1);
        bc.removeAll();
        fillLevel();
        Dionakra.endGame = false;
        Dionakra.setGameOver(false);
    }
    
}
