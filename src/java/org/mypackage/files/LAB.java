/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.files;

/**
 *
 * @author anchu
 */
public class LAB {
    LAB()
    {
            for(int i=0;i<teacher.length;i++)
                teacher[i]=null;
            for(int i=0;i<3;i++)
            {
                day[i]=9;
                hr[i]=9;
            }
    }
    public String cls;
    public String sub;
    public int[] day=new int[3];
    public int[] hr=new int[3];
    public String[] teacher=new String[5];
    public int tscd_index;
    
}
