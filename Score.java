
package dionakra;

public class Score {

    public static int pts;
    public static int max;
    public static int f;
    
    public Score(){
        
        pts = 0;
        f = 1;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    public void addF(){
        this.f++;
    }
    
    public void addPoints(int x){
        this.pts = this.pts + x*this.f;
        
        if(max < pts)
            max = pts;
    }
    
    public void reset(){
        this.pts = 0;
        this.f = 1;
    }
    
}
