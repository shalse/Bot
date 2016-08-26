/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitchbot;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shane
 */
public class WordWatcherHandler {
    ArrayList<UserWord> chatMessages = new ArrayList();
    BufferedWriter outWriter = null;
    String outChannel = null;
    PlaySound sound = new PlaySound();
    ArrayList<String> timedOutUsers = new ArrayList();
    public void addLine(String line, List list) throws IOException, FileNotFoundException, InterruptedException
    {
        //split line
        if(chatMessages.size() > 5000)
        {
            chatMessages.remove(0);
        }
        if(line.contains(":") && line.contains("!"))
        {
            String user = line.split(":")[1].split("!")[0];
            String message = line.split(":")[line.split(":").length-1];
            //check if user/keyphrase exists
          //  System.out.println("User: " + user);
         //   System.out.println("Message: " + message);
            
            for(int cnt = 0; cnt < chatMessages.size(); cnt++)
            {
                if(chatMessages.get(cnt).user.equals(user) && chatMessages.get(cnt).keyphrase.equals(message))
                {
                    chatMessages.get(cnt).count++;
                    if(chatMessages.get(cnt).count >= 3)
                    {
                        if(!chatMessages.get(cnt).keyphrase.toLowerCase().startsWith("Twitch") && !chatMessages.get(cnt).keyphrase.toLowerCase().contains(")") && !chatMessages.get(cnt).keyphrase.toLowerCase().startsWith("grat") && !chatMessages.get(cnt).keyphrase.toLowerCase().contains(")") && !chatMessages.get(cnt).keyphrase.toLowerCase().startsWith("grat") && !chatMessages.get(cnt).keyphrase.startsWith("D") && !chatMessages.get(cnt).keyphrase.toLowerCase().contains("3") && !chatMessages.get(cnt).keyphrase.toLowerCase().contains(")")&& !chatMessages.get(cnt).keyphrase.toLowerCase().contains("(") && !chatMessages.get(cnt).keyphrase.toLowerCase().startsWith("friendship"))
                        {
                         //   outWriter.write("PRIVMSG " + outChannel + " :@"+chatMessages.get(cnt).user+" you have entered the message \""+chatMessages.get(cnt).keyphrase+"\", "+chatMessages.get(cnt).count+" times!!\r\n");
                          //  outWriter.flush( );``````````
                        }
                        System.out.println(">>>>>"+chatMessages.get(cnt).keyphrase+" x "+chatMessages.get(cnt).count);
                        System.out.println("@"+chatMessages.get(cnt).user+" has entered the keyword multiple times.");                       
                        PlaySound snd = new PlaySound();
                        if(timedOutUsers.size() > 1)
                        {
                            Boolean userExists = false;
                            for(int cnt2 = 0; cnt2 < timedOutUsers.size(); cnt2++)
                            {
                                if(timedOutUsers.get(cnt2).equals(chatMessages.get(cnt).user))
                                {
                                    userExists = true;
                                    break;
                                }
                            }
                            if(userExists)
                            {
                                
                            }
                            else
                            {
                                timedOutUsers.add(chatMessages.get(cnt).user);
                                list.add("@"+chatMessages.get(cnt).user);
                            }
                        }
                        else
                        {
                            timedOutUsers.add(chatMessages.get(cnt).user);
                            list.add("@"+chatMessages.get(cnt).user);
                        }
                        snd.beep();
                        
                    }
                }
            }
            if(message.length() < 20)
            {
                UserWord uw = new UserWord();
                uw.user = user;
                uw.keyphrase = message;
                uw.count++;
                chatMessages.add(uw);
             //   System.out.println("SIZE = "+chatMessages.size());
            }
            
        }
    }
    public void checkUserKeyPhrase()
    {
        
    }

    void setWriter(BufferedWriter writer) {
        outWriter = writer;
    }

    void setChannel(String channel) {
        outChannel = channel;
    }
}
