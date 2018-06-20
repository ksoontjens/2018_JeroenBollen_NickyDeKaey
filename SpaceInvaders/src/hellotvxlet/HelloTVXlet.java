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
    LaserManager laserman=new LaserManager();
    HScene scene;
    HTextButton start;
    HTextButton retry;
     
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
        
        retry =new HTextButton("retry",200,250,320,50);
        retry.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        retry.setBackground(Color.BLUE);
        retry.setActionCommand("retry");
        retry.requestFocus();
        retry.addHActionListener(this);
        
        scene.setVisible(true);
     
      
 
 }
    public void startSpel() {
        mc = new MijnComponent(); 
        scene.add(mc);
        laserman.setScene(scene);
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
        

        deler++;
        if (rgen.nextInt(50)==10)
        {
        EnemyLaser eLaser=new EnemyLaser();
        eLaser.move(enemy.getX()+32, enemy.getY()+64);
        laserman.addEnemyLaser(eLaser);        
        scene.add(eLaser);
        scene.popToFront(eLaser);
        deler=0;
        }        

        laserman.moveAllLasers();
        
        
        if (laserman.testPlayerCollision(speler)){
        scene.remove(mc);
        laserman.removeLazers();
        scene.dispose();
        t.cancel();
        scene = HSceneFactory.getInstance().getDefaultHScene();  
        retry =new HTextButton("game over : retry",200,250,320,50);
        retry.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        retry.setBackground(Color.BLUE);
        scene.add(retry);
        scene.validate();
        retry.setActionCommand("retry");
        retry.requestFocus();
        retry.addHActionListener(this);
        scene.setVisible(true);
        }
        
        if (laserman.testEnemyCollision(enemy)){   
        laserman.removeLazers();
        scene.remove(mc);
        scene.dispose();
        t.cancel();
        scene = HSceneFactory.getInstance().getDefaultHScene();  
        retry =new HTextButton("play again",200,250,320,50);
        retry.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        retry.setBackground(Color.BLUE);
        scene.add(retry);
        scene.validate();
        retry.setActionCommand("retry");
        retry.requestFocus();
        retry.addHActionListener(this);
        scene.setVisible(true);
        }
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
     if(arg0.getActionCommand().equals("retry"))
        {
           scene.remove(retry);
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
                    
                case HRcEvent.VK_UP:                    
                    System.out.println("shots fired");
                    PlayerLaser pLaser=new PlayerLaser();
                    pLaser.move(speler.getX()+21, speler.getY()-20);
                    laserman.addPlayerLaser(pLaser);                     
                    scene.add(pLaser);
                    scene.popToFront(pLaser);
                    break;

            }
        }
    }
}
