package dionakra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Fundo extends JFrame{
    
    int Width, Height;
    int tipo;
    Image img, wallImg;

    private boolean c = false;
    
    
    public Fundo(int W, int H){
        
        Width = W;
        Height = H;
        setTipo(1);
    }
    
    public void update(Graphics g){
        
    }
    
    public void draw(Graphics g){
        
        //Colore o fundo do frame
        if(getTipo() <= 5){
            img = ResourceLoader.getImage("fundo0"+tipo+".png");

            int i=0;
            int j=0;

            while(i < Width){
                while(j < Height){
                    g.drawImage(img, i, j, 50, 50, null);
                    j = j + 50;
                }
                i = i + 50;
                j = 0;
            }
        }
        
        //Colore o muro lateral e superior
        //=================================

        //Muro lateral esquerdo
        int i = 0;
        int j = 0;
        wallImg = ResourceLoader.getImage("wall.png");
        while(i < 50){
            while(j < Height){
                g.drawImage(wallImg, i, j, 50, 50, null);
                j = j + 50;
            }
            i = i + 50;
            j = 0;
        }
        //Muro lateral direito
        i = Width - 50;
        j = 0;
        while(i < Width){
            while(j < Height){
                g.drawImage(wallImg, i, j, 50, 50, null);
                j = j + 50;
            }
            i = i + 50;
            j = 0;
        }
        //Muro superior        
        for(int k = 0; k <= Width; k+=50){
            g.drawImage(wallImg, k, 30, 50, 50, null);
        }
        g.setColor(Color.cyan);
        g.drawRect(50, 80, Width-100, Height-50);
        
        
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public void setNextTipo() {
        this.tipo++;
    }
    
    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }
    
}

