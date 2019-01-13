/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.files;

/**
 *
 * @author project1
 */
public class Ant {
    //Datastructure to store values of each ant:(per day 7 hours)
    public int current_anttscd;
    public int next_anttscd=-1;
    public int[] tabu=new int[500];
    public int[] path=new int[7];
    public double path_fitnes=0;
    public int path_index=1;
    public Ant()
    {
        for(int i=0;i<TakeInput.tscd_count;i++)
        {
            tabu[i]=0;
        }
        for(int i=0;i<6;i++)
        {
            path[i]=-1;
        }
    }
    
    
}
