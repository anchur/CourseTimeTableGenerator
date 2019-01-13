/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.files;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anchu
 */
public class ClassTSCD {
    public String cls;
    public int num_cls_tscd;
    public int[] cls_tscd=new int[100];
    public int[][] mapping=new int[6][7];
    //public double[][] dist=new double[1000][1000];
    //public double[][] pheromone=new double[1000][1000];
    public Ant[][] cls_ants=new Ant[6][7];
    public List<Integer> lst = new ArrayList<Integer>();
    public int[] bestant_index=new int[6];
    public double[] bestpath_fitness=new double[6];
    public ClassTSCD() {
       //Initialisation..........
       //Constructor.............
        int i,j;
        for(i=0;i<100;i++)
        {
            cls_tscd[i]=-1;
        }
        for(i=0;i<6;i++)
        {
            for(j=0;j<7;j++)
                mapping[i][j]=-1;
        }
      /*  for(i=0;i<1000;i++)
        {
            for(j=0;j<1000;j++)
                pheromone[i][j]=1/9999;//check initialisation.............1/
        }
        for(i=0;i<1000;i++)
        {
            for(j=0;j<1000;j++)
                dist[i][j]=1;
        }*/
        for(j=0;j<6;j++)
           {
               for(int k=0;k<7;k++)
               {
                   cls_ants[j][k]=new Ant();
                   cls_ants[j][k].current_anttscd=-1;
               }
           }
        for(i=0;i<6;i++)
        {
            bestpath_fitness[i]=9999;
        }
    }
    
}
