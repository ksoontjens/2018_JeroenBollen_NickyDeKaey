/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;
import java.util.ArrayList;
import org.havi.ui.HScene;


/**
 *
 * @author student
 */
public class LaserManager {
    ArrayList arrayEnemyLasers=new ArrayList();
    ArrayList arrayPlayerLasers=new ArrayList();
    HScene scene=null;
    
    public void setScene(HScene sc)
    {
        scene=sc;
    }
           
    public void addEnemyLaser(EnemyLaser l)
    {
        arrayEnemyLasers.add(l);       
    }
    public void addPlayerLaser (PlayerLaser l)
    {
        arrayPlayerLasers.add(l);
    }
    public void moveAllLasers()
    {
        
        for (int i=0;i<arrayEnemyLasers.size();i++)
        {
            EnemyLaser tempE=(EnemyLaser)arrayEnemyLasers.get(i);
            tempE.moverel(0, 2);
            tempE.repaint();  
            if (tempE.getY()<0) {scene.remove(tempE); arrayEnemyLasers.remove(i);}
         }
        for (int i=0;i<arrayPlayerLasers.size();i++)
        {
            PlayerLaser tempP=(PlayerLaser)arrayPlayerLasers.get(i);
            tempP.moverel(0, -3);
            tempP.repaint();        
            if (tempP.getY()<0) {scene.remove(tempP); arrayPlayerLasers.remove(i);}
         }
    }

    public boolean testPlayerCollision(Player s)
    {
        for (int i=0;i<arrayEnemyLasers.size();i++)
        {
            if (((EnemyLaser)arrayEnemyLasers.get(i)).getBounds().intersects(s.getBounds())) return true;         
        }
        return false;
    }
    public boolean testEnemyCollision(Enemy s)
    {
        for (int i=0;i<arrayPlayerLasers.size();i++)
        {
            if (((PlayerLaser)arrayPlayerLasers.get(i)).getBounds().intersects(s.getBounds())) return true;         
         }
        return false;
    }
    public void removeLazers()
    {
      arrayEnemyLasers=new ArrayList();
     arrayPlayerLasers=new ArrayList();
    }

}

