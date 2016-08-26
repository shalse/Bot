/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;




/**```````````````````````````````
 *
 * @author Shane
 */
public class TwitchBot {

    /**
     * @param args the command line arguments
     */
    private static WordWatcherHandler wh = new WordWatcherHandler();
    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
        //PlaySound snd = new PlaySound();
        //snd.beep();
        List list = new List();
        list.setVisible(true);
        // The server to connect to and our details.
        String server = "irc.chat.twitch.tv";
        
        String nick = ""+args[0]; //<---- ENTER TWITCH USERNAME
        String login = ""+args[0]; //<---- ENTER TWITCH USERNAME
        String pass = ""+args[1]; //<----- ENTER TWITCH OATH

        // The channel which the bot will join.
        String channel = ""; //<----- ENTER CHANNEL NAME
        
        // Connect directly to the IRC server.
        Socket socket = new Socket(server, 6667);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream( )));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream( )));
        //set the writer
        wh.setWriter(writer);
        wh.setChannel(channel);
        // Log on to the server.
        writer.write("PASS " + pass + "\r\n");
        writer.write("NICK " + nick + "\r\n");
        writer.write("USER " + login + "\r\n");
        
        writer.flush( );
        
        // Read lines from the server until it tells us we have connected.
        String line = null;
        while ((line = reader.readLine( )) != null) {
            System.out.println("line: "+line);
            if (line.indexOf("004") >= 0) {
                // We are now logged in.
                break;
            }
            else if (line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
            }
        }
        
        // Join the channel.
        writer.write("JOIN " + channel + "\r\n");
        writer.write("CAP REQ :twitch.tv/commands\r\n");
        writer.flush( );

       

        // Keep reading lines from the server.
        while ((line = reader.readLine( )) != null) {
            
            if (line.startsWith("PING ")) {
                // We must respond to PINGs to avoid being disconnected.
                writer.write("PONG " + line.substring(5) + "\r\n");
                //writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
                writer.flush( );
                System.out.println("PONG " + line.substring(5) + "\r\n");
                System.out.println("PINGED");
                
            }
            else {
                // Print the raw line received by the bot.
                //System.out.printl```````````````````````n(line);
                //writer.write("PRIVMSG " + channel + " :hi\r\n");
                //System.out.println("PRIVMSG " + channel + " :hi\r\n");
                wh.addLine(line,list);
            }
        }
    }

}

 
