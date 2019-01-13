/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.files;

import java.util.Random;

/**
 *
 * @author akhilaanchu
 */
public class GeneticAlgorithm extends AntColony 
{

   public static int pool_count = 0;
    public static int newpool_count = 0;
    public static int chromoLen = 7;
    public static double crossRate = .7;
    public static double mutRate = .001;
    public static Random rand = new Random();
    public static int poolSize = chromosome_count;	// Must be even
    public static Pool newPool = new Pool();
    
    // public static Chromosome chromosome[]=new Chromosome[40];
    public static Pool pool = new Pool();//pool of chromosomes...............

    public static void doIt(int target)
    {
        int count=100;
        int gen = 0, i;
        for (i = 0; i < chromosome_count; i++)
        {
            pool.chromo[i] = chromosome[i];
        }
        pool_count = i;
        
       for(i=0;i<chromosome_count;i++)//Initialising score of each chromosome..................
        {
            scoreChromo(chromosome[i]);
            
        }
        
        //insert create chromosomes.............from ant colony...
        //insert Create the pool............

        //add chromosomes to pool....


        // Loop until solution is found
        while (count>0) 
        {count--;
            // Clear the new pool
            //newPool.clear();
            newpool_count = 0;
            // Add to the generations
            gen++;

            // Loop until the pool has been processed
            for (int x = 40 - 1; x >= 0; x -= 2) 
            {
                // Select two members
                Chromosome n1 = selectMember();
                Chromosome n2 = selectMember();

                // Cross over and mutate
                 System.out.println("N1:"+n1.gene[0]+"\t"+n1.gene[1]+"\t"+n1.gene[2]+"\t"+n1.gene[3]+"\t"+n1.gene[4]+"\t"+n1.gene[5]+"\t"+n1.gene[6]);
                  System.out.println("N2:"+n2.gene[0]+"\t"+n2.gene[1]+"\t"+n2.gene[2]+"\t"+n2.gene[3]+"\t"+n2.gene[4]+"\t"+n2.gene[5]+"\t"+n2.gene[6]);
                   if (n2.score == target && isValid(n2)) 
                {

                    System.out.println("Satisfied......" + n2.gene[0]+"\t"+n2.gene[1]+"\t"+n2.gene[2]+"\t"+n2.gene[3]+"\t"+n2.gene[4]+"\t"+n2.gene[5]+"\t"+n2.gene[6]);//check............
                    
                    return;
                }
                    if (n1.score == target && isValid(n1)) 
                {

                    System.out.println("Satisfied......" + n1.gene[0]+"\t"+n1.gene[1]+"\t"+n1.gene[2]+"\t"+n1.gene[3]+"\t"+n1.gene[4]+"\t"+n1.gene[5]+"\t"+n1.gene[6]);//check............
                    
                    return;
                }
    
                crossOver(n1, n2);
                mutate(n1);
                mutate(n2);

                // Rescore the nodes
                scoreChromo(n1);
                scoreChromo(n2);

                // Check to see if either is the solution
                if (n1.score == target && isValid(n1))
                {
                    System.out.println("Satisfied......" + n1.gene[0]+"\t"+n1.gene[1]+"\t"+n1.gene[2]+"\t"+n1.gene[3]+"\t"+n1.gene[4]+"\t"+n1.gene[5]+"\t"+n1.gene[6]);
                    for(int k=0;k<classtscd_count;k++)
                    {
                        //code mapping
                    }
                    return;
                }
                if (isValid(n1)) 
                {
                    chromosome[chromosome_count++] = n1;
                    //check...........................
                }
                if (isValid(n2)) 
                {
                    chromosome[chromosome_count++] = n2;//check...........................
                } 
                if (n2.score == target && isValid(n2)) 
                {

                    System.out.println("Satisfied......" + n2.gene[0]);//check............
                    
                    return;
                }

                // Add to the new pool
                newPool.chromo[newpool_count++] = n1;
                newPool.chromo[newpool_count++] = n2;
                //newPool[n++].chromo=n1;
                //newPool[n++].chromo=n2;
            }


            // Add the newPool back to the old pool
            //pool.addAll(newPool);
        }



    }

    public static void scoreChromo(Chromosome c)
    {
        
       for(int j=0;j<6;j++)
        {   
            if (tscd[c.gene[j]].lab == false) {
                if (c.gene[j] == c.gene[j + 1]) {
                    
                    c.score=c.score-4;//same adjacent hrs
                }
            }
            
        }
        c.score=10;
        for (int j = 0; j < classtscd_count; j++) 
        {
            for(int k=0;k<=c.day;k++)
            {
                 if(classtscd[j].mapping[k][0]!=-1)
                 {
                        if (classtscd[j].mapping[k][0]==c.gene[0])
                        {
                            c.score=c.score-3;///same first hr...................
                    
                         }
                         if (classtscd[j].mapping[k][6]==c.gene[6])
                        {
                            c.score=c.score-3;///same last hr...................
                    
                         }
                 }
            }
            for (int i = 0; i < 7; i++) {

                if (classtscd[j].mapping[c.day][i] != -1) {
                    if (classtscd[j].mapping[c.day][i] != c.gene[i])//prealloc not done
                    {
                        c.score = 0;

                    }
                }
                if (!classtscd[j].cls.equalsIgnoreCase(c.cls)) {


                    //teacher clash..............
                    if (classtscd[j].mapping[c.day][i] != -1) {
                        
                        if (tscd[classtscd[j].mapping[c.day][i]].lab==true)//teacher clash of lab..........
                        {
                            
                            for(int h=0;h<lab_count;h++)
                            {
                                if(lab[h].sub.equals(tscd[classtscd[j].mapping[c.day][i]].sub))
                                {
                                    
                                    if(lab[h].teacher[0].equals(tscd[c.gene[i]].teacher_code)||lab[h].teacher[1].equals(tscd[c.gene[i]].teacher_code)||lab[h].teacher[2].equals(tscd[c.gene[i]].teacher_code)||lab[h].teacher[3].equals(tscd[c.gene[i]].teacher_code)||lab[h].teacher[4].equals(tscd[c.gene[i]].teacher_code))
                                    {
                                        c.score=0;// lab teacher clash
                                    }
                                }
                            }
                        }
                        if (tscd[classtscd[j].mapping[c.day][i]].elective==true){
                            
                            if(tscd[classtscd[j].mapping[c.day][i]].elective_teacher_id2==tscd[c.gene[i]].teacher_id)
                                c.score=0;//teacher elective clash
                            
                        }
                        
                        if (tscd[classtscd[j].mapping[c.day][i]].teacher_code.equals(tscd[c.gene[i]].teacher_code))//tscd in same hr of same day equal for two diff classes
                        {
                            c.score = 0;//teacher clash

                        }
                    }
                }
                if ((i == 3 && (tscd[c.gene[i]].HOD == 1)) || (i == 1 && (tscd[c.gene[i]].HOD == 1)) || (i == 5 && (tscd[c.gene[i]].HOD == 1)))//HOD not in first third and fifth hr
                {
                    c.score = 0;//hod 

                }

                if (i < 6 && (c.gene[i] == c.gene[i + 1]) && tscd[c.gene[i]].lab == false)//no two subject adjacent....
                {
                    if (c.score > 0) {
                        c.score = c.score - 2;//adjacent.........
                    }
                }

            }
        }
        
    }

    public static boolean isValid(Chromosome c) 
    {
        
       //check validity of   chromosomes after mutation and cross over ................ 
        //if valid return true
        
        for (int j = 0; j < classtscd_count; j++)
        {
            
            
           
            for (int i = 0; i < 7; i++) 
            {
               if(classtscd[j].mapping[c.day][i]!=-1)
                {
                 if (classtscd[j].mapping[c.day][i]!=c.gene[i])//not preallocated
                {
                    return false;
                    
                }
                }
                if(!classtscd[j].cls.equalsIgnoreCase(c.cls))
                {
                //teacher clash..............
                if(classtscd[j].mapping[c.day][i]!=-1)
                {
                 if (tscd[classtscd[j].mapping[c.day][i]].teacher_code.equals(tscd[c.gene[i]].teacher_code))//teachr clash
                {
                    return false;
                    
                }
                }
            }
            }
        }
        
        
        return true;
    }

    public static Chromosome selectMember()
    {
        
        //selecting chromosomes for cross over and mutation.......
        
        Chromosome node = new Chromosome();
        // Get the total fitness		
        double tot = 0.0;
        int index = 0;
        for (int x = 40 - 1; x >= 0; x--) 
        {
            double score = chromosome[x].score;
            if (score > tot&&chromosome[x].flag==false)
            {
                index = x;
                tot = score;
            }
        }
        node = chromosome[index];
        chromosome[index].flag=true;
        return node;


    }

    public static void crossOver(Chromosome n1, Chromosome n2)
    {
        //temporary for swapping...............
        Chromosome temp = new Chromosome();
        
        // Should we cross over?
        // Should we cross over?
			if (rand.nextDouble() > crossRate) return;
			
			// Generate a random position
			int pos = rand.nextInt(n1.length);
			
			// Swap all chars after that position
			for (int x=pos;x<n1.length;x++)
                        {
				// Get our character
                            //
                            if(tscd[n1.gene[x]].lab==false&&tscd[n2.gene[x]].lab==false&&!tscd[n1.gene[x]].sub.equals("SEP")&&!tscd[n2.gene[x]].sub.equals("SEP"))
                            {	temp.gene[x] =n1.gene[x];
				
				// Swap the chars
				n1.gene[x]=n2.gene[x];
                                n2.gene[x]=temp.gene[x];
                            }
			}
                        
                        System.out.println("After cross over N1:"+n1.gene[0]+"\t"+n1.gene[1]+"\t"+n1.gene[2]+"\t"+n1.gene[3]+"\t"+n1.gene[4]+"\t"+n1.gene[5]+"\t"+n1.gene[6]);
    
                        System.out.println("After cross over N2:"+n2.gene[0]+"\t"+n2.gene[1]+"\t"+n2.gene[2]+"\t"+n2.gene[3]+"\t"+n2.gene[4]+"\t"+n2.gene[5]+"\t"+n2.gene[6]);
    }
    

    public static void mutate(Chromosome n1)
    {
        int i = 0;
        
        //choosing classtscd of the chromosomes class
        for (int j = 0; j < classtscd_count; j++)
        {
            if (classtscd[j].cls.equals(n1.cls)) 
            {
                i = j;//i index of class tscd
                break;
            }
        }

        for (int x = 0; x < 7; x++)///for each gene mutate...........
        {
            //change only if not lab or sep
            if (tscd[n1.gene[x]].lab == false && !(tscd[n1.gene[x]].sub.equals("SEP"))) 
            {
                int index = (int) (Math.random() * classtscd[i].num_cls_tscd);
                n1.gene[x] = classtscd[i].cls_tscd[index];
            }
            // n1.gene[x]=
            //if (rand.nextDouble()<=mutRate) 
            //chromo.setCharAt(x, (chromo.charAt(x)=='0' ? '1' : '0'));
        }
         System.out.println("After mutate N1:"+n1.gene[0]+"\t"+n1.gene[1]+"\t"+n1.gene[2]+"\t"+n1.gene[3]+"\t"+n1.gene[4]+"\t"+n1.gene[5]+"\t"+n1.gene[6]);
    
    }
    
}
