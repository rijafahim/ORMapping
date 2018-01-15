package advprogramming.assignment2.core;

import java.lang.reflect.Field;
import java.util.List;

import advprogramming.assignment2.core.ORMapper;
import advprogramming.assignment2.core.ORMappingException;
import advprogramming.assignment2.core.Student;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ORMapperImpl extends ORMapper {

	public ORMapperImpl() 
        {

	}

	@Override
	public int initializeStorageForClass(Class classForStorage) 
        {
            //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                String table_name_old = classForStorage.getName();
                Package class_package = classForStorage.getPackage();
                String package_name = class_package.getName();
                
                String table_name_new = table_name_old.replace(package_name, "");
                table_name_new = table_name_new.replace(".", "");
                System.out.println(table_name_new );
                String SQL = "CREATE TABLE " + table_name_new;
                Field[] class_fields = classForStorage.getDeclaredFields();
                System.out.println(class_fields.length);
                String column = null;
                for (int i=0; i<class_fields.length; i++)
                {
                    String fieldName = class_fields[i].getName();
                    Class type = class_fields[i].getType();
                    String field_type = type.getName();
                    if (field_type.equals("java.lang.String"))
                    {
                        field_type = field_type.replace("java.lang.String"," varchar(255)");
                    }
                    if (i == 0)
                    {
                        column = " ( " + fieldName + " " + field_type + ",";
                    }
                    else if (i == class_fields.length-1)
                    {
                        column = fieldName + " " + field_type + ")";
                    }
                    else 
                    {
                        column = fieldName + " " + field_type + ",";
                    }
                    SQL = SQL + column;
                } 
                System.out.println(SQL);
                
                /*executing the SQL statement */
                smt = con.createStatement();
                int a = smt.executeUpdate(SQL);
                System.out.println("Table Created");
                con.close();

            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
 
            return 0;
	}

	@Override
	public int initializeStorageForClass(Object object) 
        {
            //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class class_name = object.getClass();
                String table_name_old = class_name.getName();
                Package class_package = class_name.getPackage();
                String package_name = class_package.getName();
                
                String table_name_new = table_name_old.replace(package_name, "");
                table_name_new = table_name_new.replace(".", "");
                System.out.println(table_name_new );
                String SQL = "CREATE TABLE " + table_name_new;
                Field[] class_fields = class_name.getDeclaredFields();
                System.out.println(class_fields.length);
                String column = null;
                for (int i=0; i<class_fields.length; i++)
                {
                    String fieldName = class_fields[i].getName();
                    Class type = class_fields[i].getType();
                    String field_type = type.getName();
                    if (field_type.equals("java.lang.String"))
                    {
                        field_type = field_type.replace("java.lang.String"," varchar(255)");
                    }
                    if (i == 0)
                    {
                        column = " ( " + fieldName + " " + field_type + ",";
                    }
                    else if (i == class_fields.length-1)
                    {
                        column = fieldName + " " + field_type + ")";
                    }
                    else 
                    {
                        column = fieldName + " " + field_type + ",";
                    }
                    SQL = SQL + column;
                } 
                System.out.println(SQL);
                
                /*executing the SQL statement */
                smt = con.createStatement();
                int a = smt.executeUpdate(SQL);
                System.out.println("Table Created");
                con.close();

            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
 
            return 0;            
	}


	@Override
	public int initializeStorageForClass(String className) 
        {
            //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                Class class_name = Class.forName(className);
                /* Determining the class info and making SQL create statement */
                String table_name_old = class_name.getName();
                Package class_package = class_name.getPackage();
                String package_name = class_package.getName();
                
                String table_name_new = table_name_old.replace(package_name, "");
                table_name_new = table_name_new.replace(".", "");
                System.out.println(table_name_new );
                String SQL = "CREATE TABLE " + table_name_new;
                Field[] class_fields = class_name.getDeclaredFields();
                System.out.println(class_fields.length);
                String column = null;
                for (int i=0; i<class_fields.length; i++)
                {
                    String fieldName = class_fields[i].getName();
                    Class type = class_fields[i].getType();
                    String field_type = type.getName();
                    if (field_type.equals("java.lang.String"))
                    {
                        field_type = field_type.replace("java.lang.String"," varchar(255)");
                    }
                    if (i == 0)
                    {
                        column = " ( " + fieldName + " " + field_type + ",";
                    }
                    else if (i == class_fields.length-1)
                    {
                        column = fieldName + " " + field_type + ")";
                    }
                    else 
                    {
                        column = fieldName + " " + field_type + ",";
                    }
                    SQL = SQL + column;
                } 
                System.out.println(SQL);
                
                /*executing the SQL statement */
                smt = con.createStatement();
                int a = smt.executeUpdate(SQL);
                System.out.println("Table Created");

            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
 
            return 0;    
	}

	@Override
	public boolean storeObject(Object obj) 
        {
           //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class class_name = obj.getClass();
                String table_name_old = class_name.getName();
                Package class_package = class_name.getPackage();
                String package_name = class_package.getName();
                
                String table_name_new = table_name_old.replace(package_name, "");
                table_name_new = table_name_new.replace(".", "");
                table_name_new = table_name_new.toLowerCase();
                 
                String SQL = "INSERT INTO " + table_name_new + " VALUES (";
                Field[] functionFields = class_name.getDeclaredFields();
                for (int i=0; i<functionFields.length; i++)
                {
                    functionFields[i].setAccessible(true);
                    String value = null;
                    String fieldName = functionFields[i].getName();
                    if (i == 0)
                    {
                        value = "'" + (String) functionFields[i].get(obj).toString() + "'" + ",";
                    }
                    else if (i == functionFields.length-1)
                    {
                        value = "'" + (String) functionFields[i].get(obj).toString() + "'" + ")";
                    }
                    else
                    {
                        value = "'" + (String) functionFields[i].get(obj).toString() + "'"+ ",";
                    }
                    SQL  = SQL + value;
                }
                System.out.println(SQL);
                
                /*executing the SQL statement */
                smt = con.createStatement();
                int a = smt.executeUpdate(SQL);
                System.out.println("Object created");

            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
 	
            return false;
	}

	@Override
	public int storeCollectionOfObjects(List<Object> list) 
        {
           //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */

                for (int i=0; i<list.size(); i++)
                {
                    Object obj = list.get(i);
                    Class class_name = obj.getClass();
                    String table_name_old = class_name.getName();
                    Package class_package = class_name.getPackage();
                    String package_name = class_package.getName();
                    
                    String table_name_new = table_name_old.replace(package_name, "");
                    table_name_new = table_name_new.replace(".", "");
                    table_name_new = table_name_new.toLowerCase();

                    String SQL = "INSERT INTO " + table_name_new + " VALUES (";
                    Field[] functionFields = class_name.getDeclaredFields();
                    for (int j=0; j<functionFields.length; j++)
                    {
                        functionFields[j].setAccessible(true);
                        String value = null;
                        String fieldName = functionFields[j].getName();
                        if (j == 0)
                        {
                            value = "'" + (String) functionFields[j].get(obj).toString() + "'" + ",";
                        }
                        else if (j == functionFields.length-1)
                        {
                            value = "'" + (String) functionFields[j].get(obj).toString() + "'" + ")";
                        }
                        else
                        {
                            value = "'" + (String) functionFields[j].get(obj).toString() + "'"+ ",";
                        }
                        SQL  = SQL + value;
                    }
                    System.out.println(SQL);
                
                    /*executing the SQL statement */
                    smt = con.createStatement();
                    int a = smt.executeUpdate(SQL);
                    System.out.println("Object created");
                }

            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
 	
            return 0;
	}


	@Override
	public int storeObjects(Object[] list) 
        {
            for (int i=0; i<list.length; i++)
            {
                storeObject(list[i]);
            }
            return 0;
	}

	@Override
	public Object[] retrieveAllObjects(Class classForRetrieval) 
        {
            //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            List <Object> store = new ArrayList<>();

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                String class_name = classForRetrieval.getName();
                System.out.println(class_name);
                Package class_package = classForRetrieval.getPackage();
                String package_name = class_package.getName();
                
                String class_name_new = class_name.replace(package_name, "");
                class_name_new = class_name_new.replace(".", "");
                System.out.println(class_name_new );
                
                Field[] fields = classForRetrieval.getDeclaredFields();
                String[] type = new String[fields.length];
                
                for (int i = 0; i<fields.length; i++)
                {
                    Class class_type = fields[i].getType();
                    type[i] = class_type.getName();
                }

                String SQL = "select * from" + class_name_new;
                smt = con.createStatement();
                ResultSet rs = smt.executeQuery(SQL);
                int counter = 0;
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    Object obj = (Object)classForRetrieval.newInstance();
                    Class class_var = obj.getClass();
                    Field[] fields_new = class_var.getDeclaredFields();
                    
                    
                    for (int i=0; i<fields_new.length; i++)
                    {
                        fields_new[i].setAccessible(true);
                    }
                    
                    for(int j=0; j<fields_new.length; j++)
                    {
                        if(type[j].equals("java.lang.String"))
                        {
                            String temp = rs.getString(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("int"))
                        {
                            int temp = rs.getInt(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("float"))
                        {
                            float temp = rs.getFloat(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        
                        else if(type[j].equals("double"))
                        {
                            double temp = rs.getDouble(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("boolean"))
                        {
                            boolean temp = rs.getBoolean(j+1);
                            fields_new[j].set(obj, temp);
                        }                               
                    }
                    store.add(obj);
                }    
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            
            int length = store.size();
            Object[] count = new Object[length];
            Iterator count2 = store.iterator();
            for (int k=0; count2.hasNext();k++ )
            {
                count[k] = count2.next();
            }
            return count;
	}

	/* (non-Javadoc)
	 * @see advprogramming.assignment2.core.ORMapper#retrieveAllObjects(java.lang.Object)
	 */
	@Override
	public Object[] retrieveAllObjects(Object obj) 
        {
            //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            List <Object> store = new ArrayList<>();

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class classForRetrieval = obj.getClass();
                String class_name = classForRetrieval.getName();
                System.out.println(class_name);
                Package class_package = classForRetrieval.getPackage();
                String package_name = class_package.getName();
                
                String class_name_new = class_name.replace(package_name, "");
                class_name_new = class_name_new.replace(".", "");
                System.out.println(class_name_new );
                
                Field[] fields = classForRetrieval.getDeclaredFields();
                String[] type = new String[fields.length];
                
                for (int i = 0; i<fields.length; i++)
                {
                    Class class_type = fields[i].getType();
                    type[i] = class_type.getName();
                }

                String SQL = "select * from" + class_name_new;
                smt = con.createStatement();
                ResultSet rs = smt.executeQuery(SQL);
                int counter = 0;
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    Object obj2 = (Object)classForRetrieval.newInstance();
                    Class class_var = obj2.getClass();
                    Field[] fields_new = class_var.getDeclaredFields();
                    
                    
                    for (int i=0; i<fields_new.length; i++)
                    {
                        fields_new[i].setAccessible(true);
                    }
                    
                    for(int j=0; j<fields_new.length; j++)
                    {
                        if(type[j].equals("java.lang.String"))
                        {
                            String temp = rs.getString(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("int"))
                        {
                            int temp = rs.getInt(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("float"))
                        {
                            float temp = rs.getFloat(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        
                        else if(type[j].equals("double"))
                        {
                            double temp = rs.getDouble(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("boolean"))
                        {
                            boolean temp = rs.getBoolean(j+1);
                            fields_new[j].set(obj, temp);
                        }                               
                    }
                    store.add(obj);
                }    
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            
            int length = store.size();
            Object[] count = new Object[length];
            Iterator count2 = store.iterator();
            for (int k=0; count2.hasNext();k++ )
            {
                count[k] = count2.next();
            }
            return count;
	}

	/* (non-Javadoc)
	 * @see advprogramming.assignment2.core.ORMapper#retrieveAllObjects(java.lang.String)
	 */
	@Override
	public Object[] retrieveAllObjects(String className) 
        {
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            List <Object> store = new ArrayList<>();

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class classForRetrieval = Class.forName(className);
                String class_name = classForRetrieval.getName();
                System.out.println(class_name);
                Package class_package = classForRetrieval.getPackage();
                String package_name = class_package.getName();
                
                String class_name_new = class_name.replace(package_name, "");
                class_name_new = class_name_new.replace(".", "");
                System.out.println(class_name_new );
                
                Field[] fields = classForRetrieval.getDeclaredFields();
                String[] type = new String[fields.length];
                
                for (int i = 0; i<fields.length; i++)
                {
                    Class class_type = fields[i].getType();
                    type[i] = class_type.getName();
                }

                String SQL = "select * from" + class_name_new;
                smt = con.createStatement();
                ResultSet rs = smt.executeQuery(SQL);
                int counter = 0;
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    Object obj2 = (Object)classForRetrieval.newInstance();
                    Class class_var = obj2.getClass();
                    Field[] fields_new = class_var.getDeclaredFields();
                    
                    
                    for (int i=0; i<fields_new.length; i++)
                    {
                        fields_new[i].setAccessible(true);
                    }
                    
                    for(int j=0; j<fields_new.length; j++)
                    {
                        if(type[j].equals("java.lang.String"))
                        {
                            String temp = rs.getString(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("int"))
                        {
                            int temp = rs.getInt(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("float"))
                        {
                            float temp = rs.getFloat(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        
                        else if(type[j].equals("double"))
                        {
                            double temp = rs.getDouble(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("boolean"))
                        {
                            boolean temp = rs.getBoolean(j+1);
                            fields_new[j].set(obj2, temp);
                        }                               
                    }
                    store.add(obj2);
                }    
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            
            int length = store.size();
            Object[] count = new Object[length];
            Iterator count2 = store.iterator();
            for (int k=0; count2.hasNext();k++ )
            {
                count[k] = count2.next();
            }
            return count;
            
	}

	@Override
	public Object[] retrieveObjectsByAttribute(Field queryField, Object queryValue) 
        {

            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            List <Object> store = new ArrayList<>();

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class classForRetrieval = queryField.getDeclaringClass();
                String class_name = classForRetrieval.getName();
                System.out.println(class_name);
                Package class_package = classForRetrieval.getPackage();
                String package_name = class_package.getName();
                
                String class_name_new = class_name.replace(package_name, "");
                class_name_new = class_name_new.replace(".", "");
                System.out.println(class_name_new );
                
                Field[] fields = classForRetrieval.getDeclaredFields();
                String[] type = new String[fields.length];
               
                for (int i = 0; i<fields.length; i++)
                {
                    Class class_type = fields[i].getType();
                    type[i] = class_type.getName();
                }

                String SQL = "select * from "+ class_name_new+ " where " + queryField.getName() + "=" + queryValue;
                smt = con.createStatement();
                ResultSet rs = smt.executeQuery(SQL);
                int counter = 0;
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    Object obj2 = (Object)classForRetrieval.newInstance();
                    Class class_var = obj2.getClass();
                    Field[] fields_new = class_var.getDeclaredFields();
                    
                    
                    for (int i=0; i<fields_new.length; i++)
                    {
                        fields_new[i].setAccessible(true);
                    }
                    
                    for(int j=0; j<fields_new.length; j++)
                    {
                        if(type[j].equals("java.lang.String"))
                        {
                            String temp = rs.getString(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("int"))
                        {
                            int temp = rs.getInt(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("float"))
                        {
                            float temp = rs.getFloat(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        
                        else if(type[j].equals("double"))
                        {
                            double temp = rs.getDouble(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("boolean"))
                        {
                            boolean temp = rs.getBoolean(j+1);
                            fields_new[j].set(obj2, temp);
                        }                               
                    }
                    store.add(obj2);
                }    
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            
            int length = store.size();
            Object[] count = new Object[length];
            Iterator count2 = store.iterator();
            for (int k=0; count2.hasNext();k++ )
            {
                count[k] = count2.next();
            }
            return count;
	}

	@Override
	public Object[] retrieveObjectsByAttribute(Field[] queryFields, Object[] values) throws ORMappingException 
        {        
            
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            List <Object> store = new ArrayList<>();

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class classForRetrieval = queryFields.getClass();
                String class_name = classForRetrieval.getName();
                System.out.println(class_name);
                Package class_package = classForRetrieval.getPackage();
                String package_name = class_package.getName();
                
                String class_name_new = class_name.replace(package_name, "");
                class_name_new = class_name_new.replace(".", "");
                System.out.println(class_name_new );
                
                Field[] fields = classForRetrieval.getDeclaredFields();
                String[] type = new String[fields.length];
               
                for (int i = 0; i<fields.length; i++)
                {
                    Class class_type = fields[i].getType();
                    type[i] = class_type.getName();
                }
                
                int value_size = values.length;
                
                
                String SQL = "select * from "+ class_name_new+ " where ";
                for (int i= 0; i<values.length; i++)
                {
                    String index = Integer.toString(i);
                    if (i==values.length-1)
                    {
                        SQL  = SQL + "queryField["+ index +"] = values[" + index + "]";
                    }
                    else
                    {
                        SQL  = SQL + "queryField["+ index +"] = values[" + index + "] AND ";
                    }
                }
                System.out.println(SQL);
                smt = con.createStatement();
                ResultSet rs = smt.executeQuery(SQL);
                int counter = 0;
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    Object obj2 = (Object)classForRetrieval.newInstance();
                    Class class_var = obj2.getClass();
                    Field[] fields_new = class_var.getDeclaredFields();
                    
                    for (int i=0; i<fields_new.length; i++)
                    {
                        fields_new[i].setAccessible(true);
                    }
                    
                    for(int j=0; j<fields_new.length; j++)
                    {
                        if(type[j].equals("java.lang.String"))
                        {
                            String temp = rs.getString(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("int"))
                        {
                            int temp = rs.getInt(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("float"))
                        {
                            float temp = rs.getFloat(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        
                        else if(type[j].equals("double"))
                        {
                            double temp = rs.getDouble(j+1);
                            fields_new[j].set(obj2, temp);
                        }
                        else if(type[j].equals("boolean"))
                        {
                            boolean temp = rs.getBoolean(j+1);
                            fields_new[j].set(obj2, temp);
                        }                               
                    }
                    store.add(obj2);
                }    
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            
            int length = store.size();
            Object[] count = new Object[length];
            Iterator count2 = store.iterator();
            for (int k=0; count2.hasNext();k++ )
            {
                count[k] = count2.next();
            }
            return count;
	}

	@Override
	public Object[] retrieveObjectsByAttribute(String fieldName, Object value) throws ORMappingException 
        {
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            try
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);
           
                DatabaseMetaData md = con.getMetaData();
                ResultSet rs = md.getTables(null, "ormapper", "%", null);
            
                ArrayList tables = new ArrayList();
                while(rs.next())
                {
                    System.out.println(": " + rs.getString(3));
                    String S = rs.getString(3);
                    S = S.substring(0,1).toUpperCase() + S.substring(1);
                    tables.add(S);
                }
                
                
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            return null;
	}


	@Override
	public Object[] retrieveObjectsByAttribute(Object obj) 
        {
           //declaring info for db connection
            String userName = "root";
            String password = "1qazxcde32";
            String serverName = "localhost";
            int portNumber = 3306;
            String dbName = "ormapper";
            Statement smt = null;
            List <Object> store = new ArrayList<>();

            try 
            {
                /* Creating a DB connection */
                con = null;
                Properties connectionProps = new Properties();
                connectionProps.put("user", userName);
                connectionProps.put("password", password);
                con = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);

                /* Determining the class info and making SQL create statement */
                Class classForRetrieval = obj.getClass();
                String class_name = classForRetrieval.getName();
                System.out.println(class_name);
                Package class_package = classForRetrieval.getPackage();
                String package_name = class_package.getName();
                
                String class_name_new = class_name.replace(package_name, "");
                class_name_new = class_name_new.replace(".", "");
                System.out.println(class_name_new );
                
                Field[] fields = classForRetrieval.getDeclaredFields();
                String[] type = new String[fields.length];
                
                for (int i = 0; i<fields.length; i++)
                {
                    Class class_type = fields[i].getType();
                    type[i] = class_type.getName();
                }

                String SQL = "select * from" + class_name_new;
                smt = con.createStatement();
                ResultSet rs = smt.executeQuery(SQL);
                int counter = 0;
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    Object obj2 = (Object)classForRetrieval.newInstance();
                    Class class_var = obj2.getClass();
                    Field[] fields_new = class_var.getDeclaredFields();
                    
                    
                    for (int i=0; i<fields_new.length; i++)
                    {
                        fields_new[i].setAccessible(true);
                    }
                    
                    for(int j=0; j<fields_new.length; j++)
                    {
                        if(type[j].equals("java.lang.String"))
                        {
                            String temp = rs.getString(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("int"))
                        {
                            int temp = rs.getInt(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("float"))
                        {
                            float temp = rs.getFloat(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        
                        else if(type[j].equals("double"))
                        {
                            double temp = rs.getDouble(j+1);
                            fields_new[j].set(obj, temp);
                        }
                        else if(type[j].equals("boolean"))
                        {
                            boolean temp = rs.getBoolean(j+1);
                            fields_new[j].set(obj, temp);
                        }                               
                    }
                    store.add(obj);
                }    
            }
            catch(SQLException se)
            {
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(smt!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                }
                try
                {
                    if(con!=null)
                    {
                        con.close();
                    }
                }
                catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }
            
            int length = store.size();
            Object[] count = new Object[length];
            Iterator count2 = store.iterator();
            for (int k=0; count2.hasNext();k++ )
            {
                count[k] = count2.next();
            }
            return count;
	}

	/* (non-Javadoc)
	 * @see advprogramming.assignment2.core.ORMapper#retrieveObjectsByID(java.lang.String, java.lang.Object)
	 */
	/*@Override
	public Object retrieveObjectsByID(String fieldName, Object value) throws ORMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see advprogramming.assignment2.core.ORMapper#retrieveObjectsByID(java.lang.String[], java.lang.Object[])
	 */
	/*@Override
	public Object retrieveObjectsByID(String[] fieldNames, Object[] values) throws ORMappingException {
		// TODO Auto-generated method stub
		return null;
	}*/

}
