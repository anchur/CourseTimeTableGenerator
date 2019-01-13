package org.mypackage.files;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @akhanch
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.io.*;


import java.util.*;
import java.util.Random;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TakeInput {

    public static int lab_count;
    public static int num_of_teachers;
    public static int num_of_subjects;
    public static int tscd_count;
    public static TSCD[] tscd = new TSCD[500];
    public static TEACHER[] tchr = new TEACHER[500];
    public static Sub_Details[] sub = new Sub_Details[500];
    public static LAB[] lab = new LAB[500];
    public static ClassTSCD[] classtscd = new ClassTSCD[100];
    public static int classtscd_count;

    public static void readTchrDetails(String S) throws IOException {

        for (int i = 0; i < tchr.length; i++) {
            tchr[i] = new TEACHER();//this will call constructor.
        }
        InputStream ExcelFileToRead = new FileInputStream(S);
        XSSFWorkbook wb2 = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();
        XSSFSheet sheet2 = wb2.getSheetAt(0);
        XSSFRow row2;
        XSSFCell cell2;

        Iterator rows = sheet2.rowIterator();
        int i = 0;
        int t = 0;
//TEACHER ID
        //System.out.println("\nColumn1:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(0); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    //System.out.print("\n" + cell2.getStringCellValue());
                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    tchr[t++].teacher_id = (int) cell2.getNumericCellValue();
                    //System.out.print("\n" + tchr[t - 1].teacher_id);
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        t = 0;
//TEACHER NAME
        //System.out.println("\nColumn2:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(1); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    tchr[t++].teacher_name = cell2.getStringCellValue();
                    //  System.out.print("\n" + tchr[t - 1].teacher_name);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        t = 0;
//TEACHER CODE
        //System.out.println("\nColumn3:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(2); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    tchr[t++].teacher_code = cell2.getStringCellValue();
                    //System.out.print("\n" + tchr[t - 1].teacher_code);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        t = 0;
 //TEACHER DEPARTMENT
        //System.out.println("\nColumn4:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(3); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    tchr[t++].dept = cell2.getStringCellValue();
                   // System.out.print("\n" + tchr[t - 1].dept);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        t=0;
        //HOD
        //System.out.println("\nColumn5:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++,t++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(4); //change 1 to get required column
                if(cell2==null)
                {
                    tchr[t].HOD=0;
                }
                else if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    
                    if("y".equals(cell2.getStringCellValue())){
                        tchr[t].HOD=1;
                        System.out.print("@@@" + j+tchr[t].teacher_name+tchr[t].HOD);
                    }
                    else
                        tchr[t].HOD=0;
                   // System.out.print("\n" + tchr[t - 1].dept);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        
        num_of_teachers = t;
        System.out.println("TEACHER DETAILS...............................");
        for (int k = 0; k < t; k++) {
            System.out.println(tchr[k].teacher_id + "\t" + tchr[k].teacher_name + "\t" + tchr[k].teacher_code + "\t" +tchr[k].HOD+"\t"+ tchr[k].dept);
        }

    }

    public static void writeXLSXFile() throws IOException {

        String excelFileName = "D\\lab1.xlsx";//name of excel file 
        String sheetName = "Sheet1";//name of sheet
        XSSFWorkbook wb3 = new XSSFWorkbook();
        XSSFSheet sheet3 = wb3.createSheet(sheetName);

//iterating r number of rows



        String[] cls = new String[500];
        String cls1;
        int c = 0, j;
        String sub1;
        String[] sub = new String[500];
        int f = 0;

        int r = 0;
        XSSFRow row3 = sheet3.createRow(r++);
        XSSFCell cell3 = row3.createCell(0);
        cell3.setCellValue("CLASS");
        XSSFCell cell4 = row3.createCell(1);
        cell4.setCellValue("SUBJECT");
        XSSFCell cell5 = row3.createCell(2);
        cell5.setCellValue("DAY1");
        XSSFCell cell6 = row3.createCell(3);
        cell6.setCellValue("HOUR1");
        XSSFCell cell7 = row3.createCell(4);
        cell7.setCellValue("DAY2");
        XSSFCell cell8 = row3.createCell(5);
        cell8.setCellValue("HOUR2");
        XSSFCell cell9 = row3.createCell(6);
        cell9.setCellValue("DAY3");
        XSSFCell cell10 = row3.createCell(7);
        cell10.setCellValue("HOUR3");
        int s = 0;

        for (int i = 0; (i < TakeInput.tscd_count); i++) {

            if (TakeInput.tscd[i].lab == true) {

                sub1 = TakeInput.tscd[i].sub;
                cls1 = TakeInput.tscd[i].cls;
                for (j = 0; j < s; j++) {
                    if (sub1.equals(sub[j])) {
                        if (cls1.equals(cls[j])) {
                            f = 1;
                        }
                    }
                }
                if (f == 0) {
                    sub[s++] = sub1;

                    cls[c++] = cls1;
                    XSSFRow row2 = sheet3.createRow(r++);
                    XSSFCell cell1 = row2.createCell(0);
                    cell1.setCellValue(cls1);
                    XSSFCell cell2 = row2.createCell(1);
                    cell2.setCellValue(sub1);



                } else {
                    f = 0;
                }




            }
        }



//iterating c number of columns



        FileOutputStream fileOut = new FileOutputStream(excelFileName);

//write this workbook to an Outputstream.
        wb3.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public static void readSubDetails(String S) throws IOException {
        for (int i = 0; i < sub.length; i++) {
            sub[i] = new Sub_Details();//this will call constructor.
        }
        InputStream ExcelFileToRead = new FileInputStream(S);
        XSSFWorkbook wb2 = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();
        XSSFSheet sheet2 = wb2.getSheetAt(0);
        XSSFRow row2;
        XSSFCell cell2;

        Iterator rows = sheet2.rowIterator();
        int i = 0;
        int sl = 0;
//CLASS
        //System.out.println("\nColumn1:");
//column wise extraction

        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(0); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    sub[sl++].cls = cell2.getStringCellValue();
                    //System.out.print("\n" + sub[sl - 1].cls);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        int num1 = sl;

        sl = 0;
//SUBJECT NAME
        //System.out.println("\nColumn2:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(1); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    sub[sl++].sub_name = cell2.getStringCellValue();
                    //System.out.print("\n" + sub[sl - 1].sub_name);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        sl = 0;
//MINIMUM SLOTS
        //System.out.println("\nColumn3:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(2); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    sub[sl++].sub_hours = (int) cell2.getNumericCellValue();
                   // System.out.print("\n" + sub[sl - 1].sub_hours);
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        sl = 0;
//IS ELECTIVE
        //System.out.println("\nColumn4:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(3); //change 1 to get required column
                if (cell2 == null) {
                    sub[sl++].elective = false;
                    //System.out.print("\n" + sub[sl - 1].elective);
                }
                // System.out.println("\nfalse");
                //if(cell2.)
                // System.out.println("aHERE       :"+cell2.getStringCellValue());
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    if (cell2.getNumericCellValue() == 0) {
                        sub[sl++].elective = false;
                    } else {
                        sub[sl].elective = true;
                        sub[sl++].elective_id = (int) cell2.getNumericCellValue();
                    }
                   // System.out.print("****************************************************\n" + sub[sl - 1].sub_name + "\t" + sub[sl - 1].elective_id);

                } else {
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }


        sl = 0;
//IS LAB
        //System.out.println("\nColumn5:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(4); //change 1 to get required column
                if (cell2 == null) {
                    sub[sl++].lab = false;
                    sub[sl - 1].room = "Nil";
                    //System.out.print("\n" + sub[sl - 1].lab + "\n" + sub[sl - 1].room);
                }
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {

                    sub[sl++].lab = true;
                    sub[sl - 1].room = cell2.getStringCellValue();

                   // System.out.print("\n" + sub[sl - 1].lab + "\n" + sub[sl - 1].room);
                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        sl = 0;
 //ROOM CAPACITY
        //System.out.println("\nColumn6:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(5); //change 1 to get required column
                if (cell2 == null) {
                    sub[sl++].capacity = 60;
                    //System.out.print("\n" + sub[sl - 1].capacity);
                }

                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    sub[sl++].capacity = (int) cell2.getNumericCellValue();
                    //System.out.print("\n" + sub[sl - 1].capacity);
                } else {
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        System.out.println("SUBJECT DETAILS..........................................................");
        num_of_subjects = num1;
        for (int k = 0; k < num1; k++) {
            System.out.println(sub[k].cls + "\t" + sub[k].sub_name + "\t" + sub[k].sub_hours + "\t" + sub[k].elective + "\t" + sub[k].elective_id + "\t" + sub[k].lab + "\t" + sub[k].room + "\t" + sub[k].capacity);
        }

    }

    public static void display() {
        System.out.println("\nCLASS\tSUB\tTID\tTCODE\tTDEPT\tLAB\tELECTIVE\tELECTIVEID\tROOM\tDAY\tHOUR\n");

        for (int k = 0; k < tscd_count; k++) {
            System.out.println("\n" + k + "\t" + tscd[k].cls + "\t" + tscd[k].sub +"\t"+tscd[k].HOD+ "\t" + tscd[k].teacher_id + "\t" + tscd[k].teacher_code + "\t" + tscd[k].teacher_dept + "\t" + tscd[k].lab + "\t" + tscd[k].elective + "\t" + tscd[k].elective_id + "\t" + tscd[k].room + "\t" + tscd[k].day + "\t" + tscd[k].hr);
        }
        System.out.println("\n------SEP ALLOCATION------\n");
        System.out.println("\nCLASS\tSUB\tTID\tTCODE\tTDEPT\tLAB\tROOM\tDAY\tHOUR\n");

        for (int k = 0; k < tscd_count; k++) {
            if (tscd[k].sub.equals("SEP")) {
                System.out.println("\n" + tscd[k].cls + "\t" + tscd[k].sub + "\t" + tscd[k].teacher_id + "\t" + tscd[k].teacher_code + "\t" + tscd[k].teacher_dept + "\t" + tscd[k].lab + "\t" + tscd[k].room + "\t" + tscd[k].day + "\t" + tscd[k].hr);
            }
        }

        System.out.println("\n------LAB ALLOCATION------\n");
        System.out.println("\nCLASS\tSUB\tTCODE1\tTCODE2\tTCODE3\tDAY1\tHOUR1\tDAY2\tHOUR2\tDAY3\tHOUR3\n");

        for (int k = 0; k < lab_count; k++) {
            //if (tscd[k].lab == true) {
            System.out.println("\n" + lab[k].cls + "\t" + lab[k].sub + "\t" + lab[k].teacher[0] + "\t" + lab[k].teacher[1] + "\t" + lab[k].teacher[2] + "\t" + lab[k].day[0] + "\t" + lab[k].hr[0] + "\t" + lab[k].day[1] + "\t" + lab[k].hr[1] + "\t" + lab[k].day[2] + "\t" + lab[k].hr[2]);
        }
       // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(tscd_count);
        for (int k = 0; k < classtscd_count; k++) {
            System.out.println(classtscd[k].cls);
            for (int m = 0; m < 6; m++) {
                // for(int l=0;l<7;l++)
                //{
                System.out.print(classtscd[k].mapping[m][0] + "\t" + classtscd[k].mapping[m][1] + "\t" + classtscd[k].mapping[m][2] + "\t" + classtscd[k].mapping[m][3] + "\t" + classtscd[k].mapping[m][4] + "\t" + classtscd[k].mapping[m][5] + "\t" + classtscd[k].mapping[m][6]);
                //}
                System.out.println("");
            }
            /*for(int m=0;m<classtscd[k].num_cls_tscd;m++)
            {
            //  System.out.println(tscd[classtscd[k].cls_tscd[m]].sub);
            }*/
        }
    }

    public static void readTSCD(String S) throws IOException {

        /*
         * The function fills tscd class 
         * it takes input from Input file1
         * and teacher class
         *
         */
        for (int i = 0; i < tscd.length; i++) {
            tscd[i] = new TSCD();//this will call constructor.
        }
        InputStream ExcelFileToRead = new FileInputStream(S);
        XSSFWorkbook wb2 = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();
        XSSFSheet sheet2 = wb2.getSheetAt(0);
        XSSFRow row2;
        XSSFCell cell2, cell3, cell1;

        Iterator rows = sheet2.rowIterator();
        int i = 0;
        int sl = 0, s_col = 4;
        int ts = 0;
        boolean elective = false;
        int elective_id = 0;
        boolean lab = false;
        String str;
        String tcode;
        int tid,thod;
        String tdept;
       // System.out.println("\nColumn1:");
//column wise extraction
        

        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                tcode = tchr[j - 1].teacher_code;
                tid = tchr[j - 1].teacher_id;
                tdept = tchr[j - 1].dept;
                thod=tchr[j - 1].HOD;
                lab = false;
                row2 = sheet2.getRow(j);
                //1st subject of teacher
                s_col = 5;
                for (int z = 0; z < 5; z++) {
                    elective = false;
                    cell2 = row2.getCell(s_col);
                    if (cell2 == null) {
                        break;//change 1 to get required column
                    }
                    if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        str = cell2.getStringCellValue();//subject name
                        //System.out.print("\n" + str);
                        if (str.equals(null) || str.equals("")) {
                            break;
                        }


                        cell1 = row2.getCell(s_col + 1);
                        if (cell1 == null) {
                            lab = false;
                        }
                        if (cell1.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                            if (cell1.getStringCellValue().equals("y")) {
                                lab = true;

                            } else {
                                lab = false;
                            }
                        }
                        for (int u = 0; u < 4; u++) {
                            elective = false;
                            cell3 = row2.getCell(s_col + 2 + u);
                            if (cell3 == null) {
                                break;
                            }
                            if (cell3.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                                tscd[ts].cls = cell3.getStringCellValue();
                                tscd[ts].sub = str;
                                tscd[ts].teacher_id = tid;
                                tscd[ts].teacher_code = tcode;
                                tscd[ts].teacher_dept = tdept;
                                tscd[ts].lab = lab;
                                tscd[ts].HOD=thod;

                                tscd[ts].room = tscd[ts].cls;


                                for (int k = 0; k < num_of_subjects; k++) {
                                    if (str.equals(sub[k].sub_name) && sub[k].elective == true) {
                                        elective = true;

                                        elective_id = sub[k].elective_id;
                                        tscd[ts].elective_id = elective_id;
                                        //System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + str + elective + sub[k].elective_id);
                                        break;

                                    }
                                }
                                tscd[ts].elective = elective;
                                //elective=false;


                                if (lab == true) {
                                    // System.out.println("In lab-----------------------------------------");
                                    for (int h = 0; h < num_of_subjects; h++) {
                                        if (str.equals(sub[h].sub_name)) {
                                            tscd[ts].room = sub[h].room;
                                        }


                                    }
                                }
                                ts++;


                            }
                        }
                        lab = false;


                    } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    } else {
                        //System.out.print("\n");
                    }
                    s_col = s_col + 6;
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
            tscd_count = ts;

        }
        int j;
        TSCD temp = new TSCD();
        for (i = 0; i < ts; i++) {
            for (j = 0; j < ts - 1; j++) {
                if (tscd[j + 1].cls.compareTo(tscd[j].cls) > 0) {
                    temp = tscd[j];
                    tscd[j] = tscd[j + 1];
                    tscd[j + 1] = temp;
                }
            }
        }

    }

    public static void laballoc(String s) throws IOException {
        for (int i = 0; i < lab.length; i++) {
            lab[i] = new LAB();//this will call constructor.
        }
        InputStream ExcelFileToRead = new FileInputStream(s);
        XSSFWorkbook wb2 = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();
        XSSFSheet sheet2 = wb2.getSheetAt(0);
        XSSFRow row2;
        XSSFCell cell2, cell1;

        Iterator rows = sheet2.rowIterator();
        int i = 0;
        int t = 0;
        //System.out.println("\nColumn1:");
//column wise extraction
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(0); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    lab[t++].cls = cell2.getStringCellValue();
                    //System.out.print("\n" + lab[t - 1].cls);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        lab_count = t;

        t = 0;
//SUBJECT
        //System.out.println("\nColumn2:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);
                cell2 = row2.getCell(1); //change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    lab[t++].sub = cell2.getStringCellValue();
                    ///System.out.print("\n" + lab[t - 1].sub);

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                } else {
                    //System.out.print("\n");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

        t = 0;
        //System.out.println("\nColumn3:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);

                cell2 = row2.getCell(2);//change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                   // System.out.print("\n" + cell2.getStringCellValue());

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    lab[t++].day[0] = (int) cell2.getNumericCellValue();
                   // System.out.print("\n" + lab[t - 1].day[0]);
                } else {
                    //System.out.print("\n");
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        t = 0;
        //System.out.println("\nColumn4:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);

                cell2 = row2.getCell(3);//change 1 to get required column
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    //System.out.print("\n" + cell2.getStringCellValue());

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    lab[t++].hr[0] = (int) cell2.getNumericCellValue();
                    //System.out.print("\n" + lab[t - 1].hr[0]);
                } else {
                   // System.out.print("\n");
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        t = 0;
        //System.out.println("\nColumn5:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);

                cell2 = row2.getCell(4);//change 1 to get required column
                if (cell2 == null) {
                    t++;
                }
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    //System.out.print("\n" + cell2.getStringCellValue());

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    lab[t++].day[1] = (int) cell2.getNumericCellValue();
                    //System.out.print("\n" + lab[t - 1].day[1]);
                } else {
                    //System.out.print("\n");
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        t = 0;
       // System.out.println("\nColumn6:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);

                cell2 = row2.getCell(5);
                if (cell2 == null) {
                    t++;//change 1 to get required column
                }
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                   // System.out.print("\n" + cell2.getStringCellValue());

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    lab[t++].hr[1] = (int) cell2.getNumericCellValue();
                   // System.out.print("\n" + lab[t - 1].hr[1]);
                } else {
                   // System.out.print("\n");
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        t = 0;
        //System.out.println("\nColumn7:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);

                cell2 = row2.getCell(6);
                if (cell2 == null) {
                    t++;//change 1 to get required column
                }
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    //System.out.print("\n" + cell2.getStringCellValue());

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    lab[t++].day[2] = (int) cell2.getNumericCellValue();
                    //System.out.print("\n" + lab[t - 1].day[2]);
                } else {
                    //System.out.print("\n");
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        t = 0;
       // System.out.println("\nColumn8:");
        for (int j = 1; j < sheet2.getPhysicalNumberOfRows(); j++) {
            try {
                row2 = sheet2.getRow(j);

                cell2 = row2.getCell(7);//change 1 to get required column
                if (cell2 == null) {
                    t++;
                }
                if (cell2.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    //System.out.print("\n" + cell2.getStringCellValue());

                } else if (cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

                    lab[t++].hr[2] = (int) cell2.getNumericCellValue();
                   // System.out.print("\n" + lab[t - 1].hr[2]);
                } else {
                    //System.out.print("\n");
                }

            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        for (i = 0; i < lab_count; i++) {
            for (int j = 0; j < tscd_count; j++) {
                if (((lab[i].sub).equals(tscd[j].sub)) && ((lab[i].cls).equals(tscd[j].cls))) {
                    for (int k = 0; k < lab[i].teacher.length; k++) {
                        if ((lab[i].teacher[k]) == null) {
                            lab[i].teacher[k] = tscd[j].teacher_code;
                            // tscd[j].hr1=lab[]
                            break;
                        }
                    }

                }

            }
        }

        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      
        System.out.println(lab_count);
        for (i = 0; i < lab_count; i++) {
            //System.out.print("Lab Details");
            System.out.println(lab[i].cls + "\t" + lab[i].sub + "\t" + lab[i].teacher[0] + "\t" + lab[i].teacher[1] + "\t" + lab[i].teacher[2] + "\t" + lab[i].day[0] + "\t" + lab[i].hr[0] + "\t" + lab[i].day[1] + "\t" + lab[i].hr[1] + "\t" + lab[i].day[2] + "\t" + lab[i].hr[2]);
        }
        int j;
        for (i = 0; i < tscd_count; i++) {
            if (tscd[i].lab == true) {
                /* for(int h=0;h<lab_count;h++)
                {
                if((lab[h].sub.equals(tscd[i].sub))&&lab[h].cls.equals(tscd[i].cls))
                {
                lab[h].tscd_index=i;
                }
                }*/
                for (j = 0; j < tscd_count; j++) {
                    if ((tscd[j].sub.equals(tscd[i].sub)) && tscd[j].cls.equals(tscd[i].cls) && (j != i)) {

                        if(tscd[j].HOD==1)
                        {tscd[i].teacher_code=tscd[j].teacher_code;
                        tscd[i].teacher_dept=tscd[j].teacher_dept;
                        tscd[i].teacher_id=tscd[j].teacher_id;
                        tscd[i].HOD=tscd[j].HOD;
                        
                        }
                        for (int k = j; k < tscd_count - 1; k++) {
                            tscd[k] = tscd[k + 1];
                        }
                        tscd_count = tscd_count - 1;
                    }
                }

            }
           
        }
	 for (i = 0; i < tscd_count; i++) {
            if(tscd[i].elective==true)
            {
               int elective_id=tscd[i].elective_id;
               for (j = 0; j < tscd_count; j++) {
                   if(i!=j&&(tscd[j].elective==true)&&(tscd[j].elective_id==elective_id)&&(tscd[i].cls.equals(tscd[j].cls)))
                   {
                       
                      tscd[i].elective_sub=tscd[j].sub; 
                      tscd[i].elective_teacher_id2=tscd[j].teacher_id;
                      System.out.println("\nELECTIVE"+tscd[i].cls+"\t"+tscd[i].sub+"\t"+tscd[i].elective_sub);
                      for (int k = j; k < tscd_count - 1; k++) {
                            tscd[k] = tscd[k + 1];
                        }
                        tscd_count = tscd_count - 1;
                   } 
               }  
            }
         }	
		
        for (int h = 0; h < lab_count; h++) {
            for (i = 0; i < tscd_count; i++) {
                if ((lab[h].sub.equals(tscd[i].sub)) && lab[h].cls.equals(tscd[i].cls)) {
                    lab[h].tscd_index = i;
                }
            }
        }


    }

    public static void sepallocate() throws IOException {
        for (int i = 0; i < tscd_count; i++) {
            List<Integer> lst = new ArrayList<Integer>();
            lst.add(1);
            lst.add(2);
            lst.add(3);
            lst.add(4);
            lst.add(5);
            lst.add(6);
            //lst.add(7);
            int ind;

            if (tscd[i].sub.equals("SEP")) {
                if (tscd[i].day == 9) {
                    tscd[i].hr = 7;
                    System.out.println("tscd" + tscd[i].cls);

                    int day1, day2;
                    for (int y = 0; y < lab_count; y++) {
                        System.out.println("list" + lst);
                        if ((lab[y].cls.equals(tscd[i].cls)) || ((lab[y].teacher[0] != null) && lab[y].teacher[0].equals(tscd[i].teacher_code)) || ((lab[y].teacher[1] != null) && lab[y].teacher[1].equals(tscd[i].teacher_code)) || ((lab[y].teacher[2] != null) && lab[y].teacher[2].equals(tscd[i].teacher_code))) {
                            System.out.println("class" + lab[y].cls + "tchr" + lab[y].teacher[0] + "\t" + lab[y].teacher[1] + "\t" + lab[y].teacher[2]);
                            if (lab[y].hr[0] == 5) {
                                day1 = lab[y].day[0];
                                if (day1 != 9) {
                                    ind = lst.indexOf(day1);
                                    try {
                                        lst.remove(ind);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            if (lab[y].hr[1] == 5) {
                                day1 = lab[y].day[1];
                                if (day1 != 9) {
                                    ind = lst.indexOf(day1);
                                    try {
                                        lst.remove(ind);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            if (lab[y].hr[2] == 5) {
                                day1 = lab[y].day[2];
                                if (day1 != 9) {
                                    ind = lst.indexOf(day1);
                                    try {
                                        lst.remove(ind);
                                    } catch (Exception e) {
                                    }
                                }
                            }

                        }

                    }


                }
                System.out.println(lst);
                int index = new Random().nextInt(lst.size());
                tscd[i].day = lst.get(index);
                /* for (int y = 0; y < tscd_count; y++) {
                if ((tscd[y].cls.equals(tscd[i].cls)) && (tscd[y].sub.equals(tscd[i].sub))) {
                if (i != y) {
                tscd[y].hr = 5;
                tscd[y].hr1 = 6;
                tscd[y].hr2 = 7;
                tscd[y].day = tscd[i].day;
                System.out.println(tscd[y].day + "\t" + tscd[y].cls + "\t" + tscd[y].sub);
                }
                }
                }*/
                System.out.println(tscd[i].day + "\t" + tscd[i].cls + "\t" + tscd[i].sub);
            }




        }

    }

    public static void classTSCDallocate() {
        classtscd_count = 0;
        int i, j, k = 0;
        for (i = 0; i < classtscd.length; i++) {
            classtscd[i] = new ClassTSCD();//this will call constructor.
        }
        String cls;
        for (i = 0; i < tscd_count;) {
            cls = tscd[i].cls;
            k = 0;
            classtscd[classtscd_count].cls = cls;
            for (j = i; j < tscd_count; j++) {
                if (tscd[j].cls.equals(cls)) {
                    classtscd[classtscd_count].cls_tscd[k++] = j;

                } else {
                    classtscd[classtscd_count].num_cls_tscd = k;
                    //j=j+1;
                    break;
                }

            }
            i = j;
            System.out.println("Class" + cls + "\ttscd_count" + classtscd_count + "\t\ti" + i);
            classtscd_count++;

        }
        classtscd[classtscd_count - 1].num_cls_tscd = k;
    }

    public static void initial_mapping() {
        for (int i = 0; i < lab_count; i++) {
            for (int j = 0; j < classtscd_count; j++) {
                if (classtscd[j].cls.equals(lab[i].cls)) {
                    //int x=lab[i].day[]
                    if (lab[i].day[0] != 9 && lab[i].hr[0] != 9) {
                        classtscd[j].mapping[lab[i].day[0] - 1][lab[i].hr[0] - 1] = lab[i].tscd_index;
                        classtscd[j].mapping[lab[i].day[0] - 1][lab[i].hr[0]] = lab[i].tscd_index;
                        classtscd[j].mapping[lab[i].day[0] - 1][lab[i].hr[0] + 1] = lab[i].tscd_index;
                    }
                    if (lab[i].day[1] != 9 && lab[i].hr[1] != 9) {
                        classtscd[j].mapping[lab[i].day[1] - 1][lab[i].hr[1] - 1] = lab[i].tscd_index;
                        classtscd[j].mapping[lab[i].day[1] - 1][lab[i].hr[1]] = lab[i].tscd_index;
                        classtscd[j].mapping[lab[i].day[1] - 1][lab[i].hr[1] + 1] = lab[i].tscd_index;
                    }
                    if (lab[i].day[2] != 9 && lab[i].hr[2] != 9) {
                        classtscd[j].mapping[lab[i].day[2] - 1][lab[i].hr[2] - 1] = lab[i].tscd_index;
                        classtscd[j].mapping[lab[i].day[2] - 1][lab[i].hr[2]] = lab[i].tscd_index;
                        classtscd[j].mapping[lab[i].day[2] - 1][lab[i].hr[2] + 1] = lab[i].tscd_index;
                    }
                }
            }
        }
        for (int i = 0; i < tscd_count; i++) {
            if ((tscd[i].hr != 9) && !(tscd[i].lab)) {
                for (int j = 0; j < classtscd_count; j++) {
                    if (classtscd[j].cls.equals(tscd[i].cls)) {
                        classtscd[j].mapping[tscd[i].day - 1][tscd[i].hr - 1] = i;
                        break;
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
//writeXLSXFile();
        // TakeInput TI = new TakeInput();
    }
}
