package Tetris;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {
    
 HScene scene;
  HStaticText feedback;
  HTextButton hulplijn;
  HTextButton button1;
  HTextButton button2;
  HTextButton button3;
  HTextButton button4;
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
    scene=HSceneFactory.getInstance().getDefaultHScene();
       
      
      HStaticText hst=new HStaticText("WHAT DO WE WANT",100,100,200,200);
      hst.setBackgroundMode(HVisible.BACKGROUND_FILL);
      hst.setBackground(Color.blue);
      scene.add(hst);
      
      button3=new HTextButton("FREE MONEY",20,500,200,50);
      button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button3.setBackground(Color.blue);
      scene.add(button3);
      
      button2=new HTextButton("waifu",370,420,200,50);
      button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button2.setBackground(Color.blue);
      scene.add(button2);
      
      button4=new HTextButton("party",370,500,200,50);
      button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button4.setBackground(Color.blue);
      scene.add(button4);
      
      button1=new HTextButton("FREE asuna",20,420,200,50);
      button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button1.setBackground(Color.blue);
      scene.add(button1);
      
      hulplijn=new HTextButton("hulplijn",370,50,320,50);
      hulplijn.setBackgroundMode(HVisible.BACKGROUND_FILL); 
      hulplijn.setBackground(Color.BLUE);
      scene.add(hulplijn);
      
      
      
      // up down left right
      button1.setFocusTraversal(hulplijn, button3, button2, button2);
     button2.setFocusTraversal(hulplijn, button4, button1, button3);
      button3.setFocusTraversal(button1, null, button4, button4);
      button4.setFocusTraversal(button2, null, button3, button4);
      hulplijn.setFocusTraversal(button1,button1,button1,button1);
      
      button1.setActionCommand("knop1");
      button2.setActionCommand("knop2");
      button3.setActionCommand("knop3");
      button4.setActionCommand("knop4");
      hulplijn.setActionCommand("hulplijn");
      
      button1.addHActionListener(this);
      button2.addHActionListener(this);
      button3.addHActionListener(this);
      button4.addHActionListener(this);
      hulplijn.addHActionListener(this);
      
      
      scene.validate();
      scene.setVisible(true);
      button1.requestFocus();
     
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
           
        if(arg0.getActionCommand().equals("hulplijn"))
        {
           scene.remove(button1);
           scene.remove(button3);
           scene.repaint();
           return;
           
        }
        if(arg0.getActionCommand().equals("knop2"))
        {
            feedback=new HStaticText("nice1",20,200,700,100);
            feedback.setBackgroundMode(HVisible.BACKGROUND_FILL);
            feedback.setBackground(Color.RED);
            
            
        }
        else 
        {
            feedback=new HStaticText("nope",20,200,700,100);
            feedback.setBackground(Color.CYAN);
        }
           feedback.setBackgroundMode(HVisible.BACKGROUND_FILL);
           if(feedback!=null) scene.remove(feedback);
           scene.add(feedback);
        scene.add(feedback);
        scene.popToFront(feedback);
        scene.repaint();
    }
}
