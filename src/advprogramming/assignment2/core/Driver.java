/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogramming.assignment2.core;
import advprogramming.assignment2.core.Student;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Rija
 */
public class Driver 
{
    public static void main(String[] args)
    {
        ORMapperImpl O = new ORMapperImpl();
        System.out.println("Testing initializeStorageForClass(Class classForStorage)");
        int a = O.initializeStorageForClass(Student.class);
        
        /*System.out.println("initializeStorageForClass(Object object)");
        Student S1 =  new Student();
        O.initializeStorageForClass(S1);
        
        O.initializeStorageForClass("advprogramming.assignment2.core.Student");*/
        /*Student S2 = new Student();
        S2.setId("56");
        S2.setName("Reeja");
        S2.setAge(24);
        
        Student S3 = new Student();
        S3.setId("2");
        S3.setName("zoooo");
        S3.setAge(569);
        
        Student[] list = new Student[2];
        list[0] = S2;
        list[1] = S3;
        O.storeObjects(list);*/
        String class_name_new = "Student";
        
                        String SQL = "select * from "+ class_name_new+ " where ";
                for (int i= 0; i<5; i++)
                {
                    String index = Integer.toString(i);
                    if (i==5-1)
                    {
                        SQL  = SQL + "queryField["+ index +"] = values[" + index + "]";
                    }
                    else
                    {
                        SQL  = SQL + "queryField["+ index +"] = values[" + index + "] AND ";
                    }
                }
                System.out.println(SQL);
                
        
    }
    
}

