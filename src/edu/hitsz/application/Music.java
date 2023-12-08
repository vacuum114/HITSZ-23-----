package edu.hitsz.application;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.text.RuleBasedCollator;

public class Music implements Runnable {
    private Clip clip;
    private boolean flag = false;
    private int count;

    @Override
    public void run() {
        start();
    }

    public Music(String path, int count, float voice) {
        this.count = count;
        try {
            File file = new File(path);
            if (file.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                floatControl.setValue(voice);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        clip.loop(this.count);
    }

    public void stop() {
        clip.stop();
    }
}
