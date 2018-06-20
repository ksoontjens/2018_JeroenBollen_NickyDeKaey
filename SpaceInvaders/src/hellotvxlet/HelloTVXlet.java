package hellotvxlet;


import java.awt.event.*;
import java.util.*;
import javax.tv.xlet.*;
import org.dvb.event.*;
import org.dvb.ui.*;
import org.havi.ui.*;
import org.havi.ui.event.*;
import java.awt.Color;



public class HelloTVXlet implements Xlet, UserEventListener, HActionListener{
    
    MijnComponent mc;

    HScene scene;
     HTextButton start;

     
    Random rgen=new Random();
    
    Player speler;
    
    Enemy enemy=new Enemy();
    int deler=0;  
    
    Timer t;
        
    public HelloTVXlet() {
        
    }
 public void initXlet(XletContext context) 
 {
        scene = HSceneFactory.getInstance().getDefaultHScene(); 
        
        start=new HTextButton("start",200,250,320,50);
        start.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        start.setBackground(Color.BLUE);
        scene.add(start);
        scene.validate();
        start.setActionCommand("start");
        start.requestFocus();
        start.addHActionListener(this);
        
        
        
        scene.setVisible(true);
     
      
 
 }
    public void startSpel() {
        mc = new MijnComponent(); 
        scene.add(mc);
        
        UserEventRepository uev = new UserEventRepository("my collection");
        uev.addAllArrowKeys();
        EventManager.getInstance().addUserEventListener(this, uev);
        speler=new Player();
        speler.move(300,500);
        scene.add(speler);
        scene.popToFront(speler);
        enemy=new Enemy();
        enemy.move(300,10);
        scene.add(enemy);
        scene.popToFront(enemy);
        t = new Timer();
        MijnTimerTask mtt = new MijnTimerTask();
        mtt.setCallBack(this); 
        t.scheduleAtFixedRate(mtt, 0, 10); 
    }
    
    
    void callback() {
     
        
        mc.verplaatsSterren(0,1);
        if (enemy!=null) enemy.verplaatsEnemy(1);
        

        
    }
    
    public void startXlet() {
    }

    public void pauseXlet() {
     
    }
    
    public void destroyXlet(boolean unconditional) {
     
    }
    public void actionPerformed(ActionEvent arg0) {
    if(arg0.getActionCommand().equals("start"))
        {
           scene.remove(start);
           this.startSpel();
           scene.repaint();
           return;
        }
     
    }
    public void userEventReceived(UserEvent e) {
        
        if (e.getType()==HRcEvent.KEY_PRESSED){ //enkel indrukken, niet loslaten
            switch (e.getCode())
            {
                case HRcEvent.VK_LEFT:
                    System.out.println ("links") ;
                    speler.moverel(-10,0);
                    scene.validate();
                    scene.setVisible(true);
                    scene.repaint();
                    break;
                    
                case HRcEvent.VK_RIGHT:
                    System.out.println ("rechts");
                    speler.moverel(+10,0);
                    scene.validate();
                    scene.setVisible(true);
                    scene.repaint();
                    break;
                    
                
            }
        }
    }
}
