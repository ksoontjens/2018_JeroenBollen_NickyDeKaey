package hellotvxlet;

import java.awt.*;
import java.util.ArrayList;
import org.havi.ui.*;

public class MijnComponent extends HComponent{
    
    private Image player, sterren, enemy, playerLaser;
    int playerX = 300, playerY = 500, sterrenX = 0, sterrenY = 0, 
        playerLaserX = 0, playerLaserY = 0, enemyLaserX = 0, enemyLaserY = 0;
    int enemyX = 300, enemyY= 10;

    
    public MijnComponent(){
        this.setBounds(0,0,720,576);
        
        sterren = this.getToolkit().getImage("sterren.png");
        player = this.getToolkit().getImage("schip.png");
        enemy = this.getToolkit().getImage("enemy.png");
        playerLaser = this.getToolkit().getImage("playerLaser.png");

        
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(sterren, 0);

        try {
            mt.waitForAll();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void verplaatsSterren(int x, int y){
        sterrenX += x;
        sterrenY += y;
        if (sterrenY>570) sterrenY-=570;
        this.repaint();
        //maar 1 repaint nodig
    }    
    
    public void paint (Graphics g){
        g.drawImage(sterren, sterrenX, sterrenY, this);
        g.drawImage(sterren, sterrenX, sterrenY-570, this);

    }
}
