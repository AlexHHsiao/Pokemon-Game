import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BGM {
	AudioInputStream audioInputStream;
	AudioInputStream audioInputStream2;
	Clip clip;
	public void playSound(String name) {
	    try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(name + ".wav").getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
	    } catch(Exception ex) {
	      //  System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	public void playSound2(String name) {
	    try {
	    	clip.stop();
	    	audioInputStream2 = AudioSystem.getAudioInputStream(new File(name + ".wav").getAbsoluteFile());
	       
	        Clip clip2 = AudioSystem.getClip();
	        clip2.open(audioInputStream2);
	       
	        clip2.start();
	    } catch(Exception ex) {
	      //  System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}
