
package dionakra;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {
    
    public static final AudioClip collision = Applet.newAudioClip(Sound.class.getResource("../audio/collision.wav"));
    public static final AudioClip morte = Applet.newAudioClip(Sound.class.getResource("../audio/morte.wav"));
    public static final AudioClip restartbola = Applet.newAudioClip(Sound.class.getResource("../audio/restartbola.wav"));
    public static final AudioClip pup1 = Applet.newAudioClip(Sound.class.getResource("../audio/powerup01.wav"));
    public static final AudioClip pup2 = Applet.newAudioClip(Sound.class.getResource("../audio/powerup02.wav"));
    public static final AudioClip pup3 = Applet.newAudioClip(Sound.class.getResource("../audio/powerup03.wav"));
    public static final AudioClip pup4 = Applet.newAudioClip(Sound.class.getResource("../audio/powerup04.wav"));
}
/*
    private static Sound staticSound = new Sound();
    
    public String name;
    public AudioClip sound;
    
    private Sound(){}
    
    public Sound(String name, URL url){
        this.name = name;
        try{
            sound = Applet.newAudioClip(url);
        } catch(Exception e){System.out.println("System error: "+e);}
    }
    
    public void play(){
        new Thread(new Runnbale(){
            @Override
            public void run(){
                if(sound != null)
                    sound.play();
            }
        }).start();
    }
    
    public void loop(){
        new Thread(new Runnbale(){
            @Override
            public void run(){
                if(sound != null)
                    sound.loop();
            }
        }).start();
    }
    
    public void stop(){
        if(sound != null)
            sound.stop();
    }
    
    public static URL getURL(String fileName){
        return staticSound.getClass().getResource("../audio/"+fileName);        
    }
}
 * 
 */
