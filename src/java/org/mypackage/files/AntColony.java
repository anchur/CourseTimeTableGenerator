/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.*;

/**
 *
 * @author akhanc
 */
public class AntColony extends TakeInput {

  public static double ALPHA = 1.0;
  public static double BETA = 5.0;
  public static double RHO = 0.5;
  public static double QVAL = 100;
  public static AntTSCD[] anttscd = new AntTSCD[1000];
  public static int anttscd_count = 0;
  public static double[][] dist = new double[1000][1000];
  public static double[][] pheromone = new double[1000][1000];
  public static Chromosome[]chromosome=new Chromosome[100];
  public static int chromosome_count=0;

  public static void AntC() {
    int i, j;
    for (i = 0; i < anttscd.length; i++) {
      anttscd[i] = new AntTSCD();
    }
    for (i = 0; i < 1000; i++) {
      for (j = 0; j < 1000; j++) {
        pheromone[i][j] = 99;//check initialisation.............1/
        //pheromone s made the highest for every edge so that ant can choose any path
      }
    }
    for (i = 0; i < 1000; i++) {
      for (j = 0; j < 1000; j++) {
        dist[i][j] = 1;//check initialisation.............1/
        //distance is made the least for every edge so that ant has max n equal probability to choose any edge
      }
    }
    init_anttscd();


    System.out.println("atc" + anttscd_count);
    System.out.println("tc" + tscd_count);
  }

  public static void init_anttscd() {
    int k = 0;
    for (int i = 0; i < tscd_count; i++) {//take each tscd and allot a separate 
      //anttscd for each hour tscd combination.
      //This forms the index for pheromone and 
      //distance of ants for a day for all depts
      for (int j = 0; j < 7; j++) {
        anttscd[k].TSCD_index = i;
        anttscd[k].hour = j + 1;//1st hour,2nd hour 
        k++;

      }
    }
    anttscd_count = k;
    init_phero();//avoids teacher and class clash
    init_dist();//no two same subs appear continuously
  }
  public static void Display_dist()
  {
    for(int i=0;i<classtscd_count;i++)
    {
      for(int j=0;j<classtscd[i].num_cls_tscd;j++)
      {
        for(int k=0;k<6;k++)//check
        {
          int c=k+1;
          System.out.println("sub: "+tscd[classtscd[i].cls_tscd[j]].sub+"\t"+"hour: "+c+"\t class: "+tscd[classtscd[i].cls_tscd[j]].cls);
          for(int l=0;l<classtscd[i].num_cls_tscd;l++)
          {
            int h=k+2;
            System.out.println("sub: "+tscd[classtscd[i].cls_tscd[l]].sub+"\t"+"hour: "+h+"\tclass: "+tscd[classtscd[i].cls_tscd[l]].cls);
            System.out.print(dist[classtscd[i].cls_tscd[j]*7+k][classtscd[i].cls_tscd[l]*7+k+1]);
          }

        }
      }
    }
  }
  public static void set_chromo(int i)
  {
    for(int x=0;x<100;x++)
    {
      chromosome[x]=new Chromosome();
    }
    int k;
    for(k=0;k<40;k++)
    {
      // for(int i=0;i<classtscd_count;i++)
      //{
      for(int l=0;l<7;l++){
        int index=(int)(Math.random()*classtscd[i].num_cls_tscd);
        System.out.println("NUMBER:"+classtscd[i].num_cls_tscd+"index: "+index);
        //chromosome[k].gene[l]
        int h=classtscd[i].cls_tscd[index];
        chromosome[k].gene[l]=h;

      }
      //}
      chromosome[k].cls=classtscd[0].cls;
      chromosome[k].day=0;
    }
    chromosome_count=k;
  }
  public static void Display_phero()
  {
    for(int i=0;i<classtscd_count;i++)
    {
      for(int j=0;j<classtscd[i].num_cls_tscd;j++)
      {
        for(int k=0;k<6;k++)//check
        {
          int c=k+1;
          System.out.println("sub: "+tscd[classtscd[i].cls_tscd[j]].sub+"\t"+"hour: "+c+"\t class: "+tscd[classtscd[i].cls_tscd[j]].cls);
          for(int l=0;l<classtscd[i].num_cls_tscd;l++)
          {
            int h=k+2;
            System.out.println("sub: "+tscd[classtscd[i].cls_tscd[l]].sub+"\t"+"hour: "+h+"\tclass: "+tscd[classtscd[i].cls_tscd[l]].cls);
            System.out.print(pheromone[classtscd[i].cls_tscd[j]*7+k][classtscd[i].cls_tscd[l]*7+k+1]);
          }

        }
      }
    }
  }
  public static void init_dist() {
    int i, j;
    for (i = 0; i < 1000; i++) {
      for (j = 0; j < 1000; j++) {
        if (anttscd[i].TSCD_index == anttscd[j].TSCD_index) {//distance between same subjects in a class
          dist[i][j] = 50;
        } else if ((tscd[anttscd[i].TSCD_index].teacher_code.equals(tscd[anttscd[j].TSCD_index].teacher_code))) {
          if (!(tscd[anttscd[i].TSCD_index].cls.equals(tscd[anttscd[j].TSCD_index].cls))) {//SIC ANT Biju sir same class
            if (anttscd[i].hour + 1 == anttscd[j].hour) {
              dist[i][j] = 25;
            }

          } else {
            if (anttscd[i].hour + 1 == anttscd[j].hour) {
              dist[i][j] = 75;
            }

          }
        } else {
          dist[i][j] = 1;
        }
      }
    }
    for (i = 0; i < 1000; i++) {
      for (j = 0; j < 1000; j++) {
        if (anttscd[i].hour + 1 != anttscd[j].hour) {
          dist[i][j] = 99;//if its not the next hour of currently considered index antsshould not take that path
        }
      }
    }

  }

  public static void init_phero() {//considers the preassigned slots for the first day and then stores the constraints for other anttscds
    int hour;
    int ind1, ind2;
    for (int i = 0; i < tscd_count; i++) {
      if (!tscd[i].lab) {
        if (tscd[i].hr != 9 && tscd[i].day == 1) {//assuming all assigned hours are clash-free
          System.out.println("Taken:\t" + tscd[i].sub + "\t" + tscd[i].teacher_code + "\t" + tscd[i].cls);

          hour = tscd[i].hr - 1;
          for (int j = 0; j < tscd_count; j++) { //taken non lab and checked for teacher clash in tscds
            if (tscd[j].teacher_code.equals(tscd[i].teacher_code) && tscd[j].hr == 9 && i != j) {
              System.out.println("Not alloted to class coz of teacher clash\t" + tscd[j].teacher_code + "\t" + tscd[j].sub + "\t" + tscd[j].cls + "\t" + tscd[i].hr);
              ind2 = j * 7 + hour;//teacher.......
              for (ind1 = 0; ind1 < anttscd_count; ind1++) {//no ant can take path to clashed-tscds for that hour
                if (anttscd[ind1].TSCD_index != i) {
                  pheromone[ind1][ind2] = 0;
                }

              }
            }
            if (tscd[j].cls.equals(tscd[i].cls) && tscd[j].hr == 9 && i != j) {
              System.out.println("Not alloted to class coz of class clash\t" + tscd[j].teacher_code + "\t" + tscd[j].sub + "\t" + tscd[j].cls + "\t" + tscd[i].hr);
              ind2 = j * 7 + hour;//sep&inter make 0 for other subjects..........
              for (ind1 = 0; ind1 < anttscd_count; ind1++) {
                if (anttscd[ind1].TSCD_index != i) {
                  pheromone[ind1][ind2] = 0;//only 2nd index has significance
                }

              }
            }
          }

        }//all written as hour- is 1 less than actual hour: indexing
      } else {
        for (int k = 0; k < lab_count; k++) {
          if (tscd[i].sub.equals(lab[k].sub)) {
            if (lab[k].day[0] == 1) {
              System.out.println("Taken \t" + lab[k].sub + "\t" + lab[k].cls);
              int hour0 = lab[k].hr[0] - 1;
              int hour1 = hour0 + 1;
              int hour2 = hour1 + 1;
              int h0 = hour0 + 1;//just for printing
              int h1 = hour1 + 1;
              int h2 = hour2 + 1;
              //System.out.println("LAB");
              for (int j = 0; j < tscd_count; j++) {
                if (((lab[k].teacher[0] != null && lab[k].teacher[0].equals(tscd[j].teacher_code)) ||
                      (lab[k].teacher[1] != null && lab[k].teacher[1].equals(tscd[j].teacher_code)) || 
                      (lab[k].teacher[2] != null && lab[k].teacher[2].equals(tscd[j].teacher_code))|| 
                      (lab[k].teacher[3] != null && lab[k].teacher[3].equals(tscd[j].teacher_code))||
                      (lab[k].teacher[4] != null && lab[k].teacher[4].equals(tscd[j].teacher_code))) && tscd[j].hr == 9 && i != j) {

                  System.out.println("Not alloted to class coz of teacher clash\t" + tscd[j].teacher_code + "\t" + tscd[j].sub + "\t" + tscd[j].cls + "\t" + h0 + "\t" + h1 + "\t" + h2);
                  ind2 = j * 7 + hour0;
                  int ind3 = j * 7 + hour1;
                  int ind4 = j * 7 + hour2;//teacher.......
                  for (ind1 = 0; ind1 < anttscd_count; ind1++) {
                    if (anttscd[ind1].TSCD_index != i) {//if added now
                      pheromone[ind1][ind2] = 0;
                      pheromone[ind1][ind3] = 0;
                      pheromone[ind1][ind4] = 0;
                    }
                    //System.out.print(tscd[anttscd[j].TSCD_index].sub+"\t"+anttscd[j].hour);

                  }
                }
                if (tscd[j].cls.equals(tscd[i].cls) && tscd[j].hr == 9 && i != j) {
                  System.out.println("Not alloted to class coz of class clash\t" + tscd[j].teacher_code + "\t" + tscd[j].sub + "\t" + tscd[j].cls + "\t" + h0 + "\t" + h1 + "\t" + h2);
                  ind2 = j * 7 + hour0;//sep&inter make 0 for other subjects..........
                  int ind3 = j * 7 + hour1;
                  int ind4 = j * 7 + hour2;//teacher.......
                  for (ind1 = 0; ind1 < anttscd_count; ind1++) {
                    if (anttscd[ind1].TSCD_index != i) {//if added now
                      pheromone[ind1][ind2] = 0;
                      pheromone[ind1][ind3] = 0;
                      pheromone[ind1][ind4] = 0;
                    }
                    //System.out.print(tscd[anttscd[j].TSCD_index].sub+"\t"+anttscd[j].hour);

                  }
                }
              }


            }

          }

        }
      }
    }
  }

  public static void init() {
    //Initialising ants................
    for (int i = 0; i < classtscd_count; i++) {
      for (int j = 0; j < 6; j++) {
        for (int k = 0; k < 7; k++) {
          for (int l = 0; l < 6; l++) {
            classtscd[i].cls_ants[j][k].path[l] = -1;
          }
          for (int m = 0; m < tscd_count; m++) {
            classtscd[i].cls_ants[j][k].tabu[m] = 0;
          }

          classtscd[i].cls_ants[j][k].next_anttscd = -1;
          classtscd[i].cls_ants[j][k].path_fitnes = 0;
        }
      }
    }//all previously alloted are clash free
    for (int i = 0; i < lab_count; i++)//lab details to ant.....................
    {
      for (int j = 0; j < classtscd_count; j++) {
        if (classtscd[j].cls.equals(lab[i].cls)) {
          if ((lab[i].day[0] != 9) && (lab[i].hr[0] != 9))//Initialising tabu and path of ant.....
          {
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0] - 1].current_anttscd = lab[i].tscd_index*7+lab[i].hr[0]-1;
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0] - 1].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0] - 1].tabu[lab[i].tscd_index] = 1;

            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0]].current_anttscd = lab[i].tscd_index*7+lab[i].hr[0];
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0]].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0]].tabu[lab[i].tscd_index] = 1;

            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0] + 1].current_anttscd = lab[i].tscd_index*7+lab[i].hr[0] + 1;
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0] + 1].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[0] - 1][lab[i].hr[0] + 1].tabu[lab[i].tscd_index] = 1;
          }
          if ((lab[i].day[1] != 9) && (lab[i].hr[1] != 9)) {
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1] - 1].current_anttscd = lab[i].tscd_index*7+lab[i].hr[1] - 1;
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1] - 1].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1] - 1].tabu[lab[i].tscd_index] = 1;

            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1]].current_anttscd = lab[i].tscd_index*7+lab[i].hr[1];
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1]].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1]].tabu[lab[i].tscd_index] = 1;

            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1] + 1].current_anttscd = lab[i].tscd_index*7+lab[i].hr[1]+1;
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1] + 1].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[1] - 1][lab[i].hr[1] + 1].tabu[lab[i].tscd_index] = 1;

          }
          if ((lab[i].day[2] != 9) && (lab[i].hr[2] != 9)) {
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2] - 1].current_anttscd = lab[i].tscd_index*7+lab[i].hr[2]-1;
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2] - 1].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2] - 1].tabu[lab[i].tscd_index] = 1;

            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2]].current_anttscd = lab[i].tscd_index*7+lab[i].hr[2];
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2]].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2]].tabu[lab[i].tscd_index] = 1;

            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2] + 1].current_anttscd = lab[i].tscd_index*7+lab[i].hr[2]+1;
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2] + 1].path[0] = lab[i].tscd_index;
            classtscd[j].cls_ants[lab[i].day[2] - 1][lab[i].hr[2] + 1].tabu[lab[i].tscd_index] = 1;

          }
        }
      }
    }
    for (int i = 0; i < tscd_count; i++)//SEP& InterDEpartment allocation given to ants........
    {
      if ((tscd[i].day != 9) && (tscd[i].hr != 9)) {
        for (int j = 0; j < classtscd_count; j++) {
          if ((classtscd[j].cls.equals(tscd[i].cls))) {
            classtscd[j].cls_ants[tscd[i].day - 1][tscd[i].hr - 1].current_anttscd = i*7+tscd[i].hr-1;
            classtscd[j].cls_ants[tscd[i].day - 1][tscd[i].hr - 1].path[0] = i;
            classtscd[j].cls_ants[tscd[i].day - 1][tscd[i].hr - 1].tabu[i] = 1;
          }
        }
      }
    }
    for (int i = 0; i < classtscd_count; i++) {
      //List<Integer> lst = new ArrayList<Integer>();

      L_add(classtscd[i].lst, i);
      if (classtscd[i].lst.isEmpty()) {
        continue;
      }
      System.out.println(classtscd[i].cls);//+"\t"+"List"+classtscd[i].lst);
      for (int j = 0; j < 6; j++) {

        for (int k = 0; k < 7; k++) {
          try {
            if (classtscd[i].cls_ants[j][k].current_anttscd == -1) {
              int index = new Random().nextInt(classtscd[i].lst.size());
              classtscd[i].cls_ants[j][k].current_anttscd = classtscd[i].lst.remove(index)*7+k;//check
              classtscd[i].cls_ants[j][k].path[0] = classtscd[i].cls_ants[j][k].current_anttscd/7;
              classtscd[i].cls_ants[j][k].tabu[classtscd[i].cls_ants[j][k].path[0]] = 1;//look
            }
          } catch (Exception e) {
            k--;
            L_add(classtscd[i].lst, i);
          }
        }
        //  System.out.println(classtscd[i].cls_ants[j][0].path[0]);
        //try
        //{
        System.out.println(tscd[anttscd[classtscd[i].cls_ants[j][0].current_anttscd].TSCD_index].sub + "\t" + tscd[anttscd[classtscd[i].cls_ants[j][1].current_anttscd].TSCD_index].sub+ "\t" + tscd[anttscd[classtscd[i].cls_ants[j][2].current_anttscd].TSCD_index].sub+ "\t" + tscd[anttscd[classtscd[i].cls_ants[j][3].current_anttscd].TSCD_index].sub+ "\t" + tscd[anttscd[classtscd[i].cls_ants[j][4].current_anttscd].TSCD_index].sub+ "\t" + tscd[anttscd[classtscd[i].cls_ants[j][5].current_anttscd].TSCD_index].sub+ "\t" + tscd[anttscd[classtscd[i].cls_ants[j][6].current_anttscd].TSCD_index].sub      );
        //}
        //catch(Exception e)
        //{
        //  System.out.println(e);
        //}
      }
    }

  }

  public static void L_add(List lst, int i) {
    for (int j = 0; j < classtscd[i].num_cls_tscd; j++) {
      if ((tscd[classtscd[i].cls_tscd[j]].day == 9) && (tscd[classtscd[i].cls_tscd[j]].hr == 9) && !tscd[classtscd[i].cls_tscd[j]].lab) {
        lst.add(classtscd[i].cls_tscd[j]);
      }
    }
  }

  public static void restartAnts() {
    int i, j, k, l;
    for (i = 0; i < classtscd_count; i++) //each class
    {
      for (j = 0; j < 6; j++)//each day
      {
        for (k = 0; k < 7; k++)//each hour
        {
          if (classtscd[i].bestpath_fitness[j] < classtscd[i].cls_ants[j][k].path_fitnes) {
            classtscd[i].bestpath_fitness[j] = classtscd[i].cls_ants[j][k].path_fitnes;
            classtscd[i].bestant_index[j] = k;//find tour length and best path indexof each day for a class
          }

          for (l = 0; l < tscd_count; l++) {
            classtscd[i].cls_ants[j][k].tabu[l] = 0;
          }
          for (l = 0; l < 6; l++) {
            classtscd[i].cls_ants[j][k].path[l] = -1;
          }

        }
      }
    }
    init();
  }

  public static double antProduct(int from, int to) {
    try
    {
      return ((Math.pow(pheromone[from][to], ALPHA)) * (Math.pow((1 / dist[from][to]), BETA)));
    }
    catch(Exception e)
    {
      System.out.println(e);
      return 0;
    }
  }

  public static int selectNextCity(int i, int j, int k) {
    int from, to;
    double denom = 0.00001;//check,,,,,,,,,,,,modified..............................

    from = classtscd[i].cls_ants[j][k].current_anttscd;//check current_tscd or current_anttscd
    //for each class index in anttscd
    for (to = classtscd[i].cls_tscd[0]*7; to <classtscd[i].cls_tscd[classtscd[i].num_cls_tscd]*7; to++) {
      if (classtscd[i].cls_ants[j][k].tabu[anttscd[to].TSCD_index] == 0) {
        denom += antProduct(from, to);
      }

    }
    //do {
    int rand;
    double p;
    rand = (int) (Math.random() * classtscd[i].num_cls_tscd);
    System.out.println(rand);
    try
    {
      to = classtscd[i].cls_tscd[rand]*7+anttscd[from].hour;
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    if (classtscd[i].cls_ants[j][k].tabu[anttscd[to].TSCD_index] == 0) {
      p = antProduct(from, to) / denom;

    }
    //} while (true);
    return to;
  }

  public static int SimulateAnts() {
    int moving = 0;
    int k;
    for (int i = 0; i < classtscd_count; i++) {
      for (int j = 0; j < 6; j++) {
        for (k = 0; k < 7; k++) {
          if (classtscd[i].cls_ants[j][k].path_index < 7) {
            classtscd[i].cls_ants[j][k].next_anttscd = selectNextCity(i, j, k);
            classtscd[i].cls_ants[j][k].tabu[anttscd[classtscd[i].cls_ants[j][k].next_anttscd].TSCD_index] = 1;
            classtscd[i].cls_ants[j][k].path[classtscd[i].cls_ants[j][k].path_index++] = anttscd[classtscd[i].cls_ants[j][k].next_anttscd].TSCD_index;
            classtscd[i].cls_ants[j][k].path_fitnes += dist[classtscd[i].cls_ants[j][k].current_anttscd][classtscd[i].cls_ants[j][k].next_anttscd];

            classtscd[i].cls_ants[j][k].current_anttscd = classtscd[i].cls_ants[j][k].next_anttscd;
            moving++;
          }

        }
      }
    }



    return moving;



  }

  public static void UpdateTrails() {
    int from, to, i, ant;
    //pheromone evaluation........................

    for (from = 0; from < anttscd_count; from++) {
      for (to = 0; to < anttscd_count; to++) {
        if (from != to) {
          pheromone[from][to] *= (1 - RHO);
          if (pheromone[from][to] < 0) {
            pheromone[from][to] = 1 / 99;//check.........................
          }
        }
      }
    }

    for (i = 0; i < classtscd_count; i++) {
      for (int j = 0; j < 6; j++) {
        for (int k = 0; k < 7; k++) {
          for (int pathindex = 0; pathindex < 7; pathindex++) {
            from = classtscd[i].cls_ants[j][k].path[pathindex];//index wrong
            int rand;

            rand = (int) (Math.random() * classtscd[i].num_cls_tscd);
            to = classtscd[i].cls_tscd[rand];
            pheromone[from][to] += (QVAL / classtscd[i].cls_ants[j][k].path_fitnes);
            //one step deleted
          }
        }
      }
    }

    for (from = 0; from < anttscd_count; from++) {
      for (to = 0; to < anttscd_count; to++) {
        pheromone[from][to] *= RHO;
      }
    }


  }

  public static void start() {
    for(int i=0;i<7;i++)
      SimulateAnts();
    try
    {
      System.out.println(classtscd[0].cls_ants[0][0].path[0]+"\t"+classtscd[0].cls_ants[0][0].path[1]+"\t"+classtscd[0].cls_ants[0][0].path[2]+"\t"+classtscd[0].cls_ants[0][0].path[3]+"\t"+classtscd[0].cls_ants[0][0].path[4]+"\t"+classtscd[0].cls_ants[0][0].path[5]+"\t"+classtscd[0].cls_ants[0][0].path[6]);
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    //UpdateTrails();
    //restartAnts();
  }
}
