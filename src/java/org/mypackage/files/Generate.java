/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.files;

import java.util.*;
import java.io.*;

/**
 *
 * @author anchu
 */
public class Generate {
    //public TakeInput Ti=new TakeInput();
    //public static int i=TakeInput.tscd[1].teacher_id;

    public static void interDept() {

        for (int i = 0; (i < TakeInput.tscd_count); i++) {
            String cls;
            String dept;
            cls = TakeInput.tscd[i].cls;
            dept = TakeInput.tscd[i].teacher_dept;
            cls = cls.substring(2, cls.length());
            dept = dept.substring(0, 2);
            if (!cls.equals(dept)) {
               //System.out.println(cls+"\n"+dept);
                List<Integer> lst = new ArrayList<Integer>();
                lst.add(1);
                lst.add(2);
                lst.add(3);
                lst.add(4);
                lst.add(5);
                lst.add(6);
                int ind;
                List<Integer> lsth = new ArrayList<Integer>();
                lsth.add(1);
                lsth.add(2);
                lsth.add(3);
                lsth.add(4);
                lsth.add(5);
                lsth.add(6);
                lsth.add(7);
                int inh;
                int index = new Random().nextInt(lst.size());
                int randay = lst.get(index);
                boolean flag = true;
                while (true) {
                   // System.out.println("lst"+lst);
                   // System.out.println("lsth"+lsth);
               //     flag = true;
                    for (int y = 0; y < TakeInput.tscd_count; y++) {
                        if ((y != i)&&((TakeInput.tscd[y].cls.equals(TakeInput.tscd[i].cls))||(TakeInput.tscd[y].teacher_code.equals(TakeInput.tscd[i].teacher_code)))) {
                            if ((TakeInput.tscd[y].hr != 9) && (TakeInput.tscd[y].day == randay)) {
                                ind = lsth.indexOf(TakeInput.tscd[y].hr);
                                //System.out.println(ind+"\t"+TakeInput.tscd[y].hr);
                                try {
                                    lsth.remove(ind);
                                   // System.out.println(lsth);
                                } catch (Exception e) {
                                   // System.out.println("Am hr");
                                   flag = false;
                                    break;
                                }
                            }
                        }
                    }
                     for (int y = 0; y < TakeInput.lab_count; y++) {
                        if ((y != i)&&((TakeInput.lab[y].cls.equals(TakeInput.tscd[i].cls))||((TakeInput.lab[y].teacher[0]!=null)&&TakeInput.lab[y].teacher[0].equals(TakeInput.tscd[i].teacher_code))||((TakeInput.lab[y].teacher[1]!=null)&&TakeInput.lab[y].teacher[1].equals(TakeInput.tscd[i].teacher_code))||((TakeInput.lab[y].teacher[2]!=null)&&TakeInput.lab[y].teacher[2].equals(TakeInput.tscd[i].teacher_code)))) {
                            if ((TakeInput.tscd[y].hr != 9) && (TakeInput.tscd[y].day == randay)) {
                                ind = lsth.indexOf(TakeInput.tscd[y].hr);
                                //System.out.println(ind+"\t"+TakeInput.tscd[y].hr);
                                try {
                                    lsth.remove(ind);
                                   // System.out.println(lsth);
                                } catch (Exception e) {
                                   // System.out.println("Am hr");
                                   flag = false;
                                    break;
                                }
                            }
                        }
                    }
                            */
                    
                    if (flag==false) {
                        ind = lst.indexOf(randay);
                        try {
                            lst.remove(ind);
                            //System.out.println("LST Removed");
                        } catch (Exception ex) {
                            break;
                        }
                        index = new Random().nextInt(lst.size());
                        randay = lst.get(index);
                        continue;

                    } 
                    else {
                        index = new Random().nextInt(lsth.size());
                        int randhr = lsth.get(index);
                        if(!TakeInput.tscd[i].lab)
                        {
                        TakeInput.tscd[i].hr = randhr;
                        TakeInput.tscd[i].day=randay;
                        }
                        //System.out.println("here"+randhr+"\t"+randay+"\t"+lst+"\t"+lsth+"\t"+TakeInput.tscd[i].hr+"\t"+TakeInput.tscd[i].sub);
                                
                        break;
                    }
                }


            }

        }

    }

    //public static void main(String args[]) {
        //interDept();
    //}
}
