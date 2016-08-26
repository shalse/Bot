/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Shane
 */
public class PlaySound {
    public void beep() throws FileNotFoundException, IOException, InterruptedException
    {
        InputStream in = new FileInputStream("Four.wav");
        // Create an AudioStream object from the input stream.
        AudioStream as = new AudioStream(in);         
        // Use the static class member "player" from class AudioPlayer to play
        // clip.
        AudioPlayer.player.start(as);            
        // Similarly, to stop the audio.
        //wait(1000);
        //AudioPlayer.player.stop(as); 
    }
}
