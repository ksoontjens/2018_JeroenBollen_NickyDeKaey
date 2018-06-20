/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class Sprite extends HComponent {
    
    private Image sprite;
    int x,y;
        
    public Sprite(String imagename)
    {
        sprite = this.getToolkit().getImage(imagename);
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(sprite, 0);

        try {
            mt.waitForAll();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
    
        }

    }
    
    public void move(int x,int y)
    {        
        this.x=x; this.y=y;
        this.setBounds(this.x,this.y,sprite.getWidth(null),sprite.getHeight(null));                
    }
    public void moverel(int xr, int yr)
    {
        
        if(this.x>0 && this.x < 670)
         {
          this.x+=xr;
         }
         if(this.x == 0 && xr>0)
         {
          this.x+=xr;
         }
          if(this.x >= 670 && xr <0)
         {
          this.x+=xr;
         }
         this.y+=yr;
         this.setBounds(this.x, this.y, sprite.getWidth(null), sprite.getHeight(null));
    }
       public void paint (Graphics g){
        g.drawImage(sprite, 0, 0, this);

    }
}

