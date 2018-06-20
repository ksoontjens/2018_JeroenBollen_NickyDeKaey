/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimerTask extends TimerTask{
    
    HelloTVXlet mijnXLet;
    
    public void setCallBack(HelloTVXlet xlet)
    {
        mijnXLet = xlet;
    }

    public void run() {
        //timer wordt hier uitgevoerd
        if (mijnXLet != null) { mijnXLet.callback(); }
    }

}
