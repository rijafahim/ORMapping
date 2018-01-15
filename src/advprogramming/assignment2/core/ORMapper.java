

package advprogramming.assignment2.core;
import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public abstract class ORMapper 
{

	/**
	 * represents the connection to the database 
	 */
	protected Connection con;
	
	
	/**
	 * represents the hashmap containing mappings between the classes and their corresponding table names in the database  
	 */
	protected HashMap<Class, String> mapping;
	
/**
 * 
 * @param classForStorage: the class for which database should be initialized.
 * Initialization process creates a new table for the class in the database, 
 * if such a table does not exists.
 * 
 * @return int: 0 - if table was created, 
 * -1 - if table could not be created (e.g., in case of exceptions,  
 * +1 - if table was successfully created
 */
public abstract int initializeStorageForClass(Class classForStorage);

/**
 * 
 * @param object: The object which provides the class for which database should
 * be initialized.
 * @return int: 0 - if table was created, 
 * -1 - if table could not be created (e.g., in case of exceptions,  
 * +1 - if table was successfully created
 *  */
public abstract int initializeStorageForClass(Object object);

/**
 * 
 * @param className: The class name of the class for which database should be initialized.
 * @return int: 0 - if table was created, 
 * -1 - if table could not be created (e.g., in case of exceptions,  
 * +1 - if table was successfully created
 *  */
public abstract int initializeStorageForClass(String className);

/**
 * Given an object obj, the method will store all the data corresponding to the attributes of 
 * the object in the initialized database. Each object should be stored as a separate tuple.
 * @param obj: the object to store
 * @return: whether or not the object was stored
 */
public abstract boolean storeObject(Object obj);

/**
 * Given a list of objects 'list', the method will store all the data corresponding to each of the objects of the list 
 * @param list: List of objects to store
 * @return int: the number of objects that were successfully stored
 */
public abstract int storeCollectionOfObjects(List<Object> list);



/**
 * Given a array of objects 'list', the method will store all the data corresponding to each of the objects of the list 
 * @param list: Array of objects to store
 * @return int: the number of objects that were successfully stored
 */
public abstract int storeObjects(Object[] list);



/**
 * The method populated objects representing all the tuples from the table corresponding 
 * to the classForRetrieval
 *  
 * @param classForRetrieval: The class for which the objects should be returned
 * @return an array of objects belonging to the classForRetrieval
 */
public abstract Object[] retrieveAllObjects(Class classForRetrieval);

/**
 * The method populates objects representing all the tuples from the table corresponding 
 * to the class of Object obj
 *  
 * @param obj: Object representing the class for retrieval
 * @return an array of objects belonging to the classForRetrieval
 */
public abstract Object[] retrieveAllObjects(Object obj);



/**
 * The method populates objects representing all the tuples from the table corresponding 
 * to the class with the name: className
 *  
 * @param className: className of the class for retrieval
 * @return an array of objects belonging to the classForRetrieval
 */
public abstract Object[] retrieveAllObjects(String className);

/**
 * The method retrieves objects from the database for the container class of the queryFiled where 
 * value in the table corresponding to the column representing queryField is equal to the queryValue parameter.
 * This is equivalent to the query  'Select * from Table where queryField = queryValue' 
 * 
 * @param queryField: The field (or column) that is to be queried
 * @param queryValue: The value of the field
 * @return the objects retrieved
 */
public abstract Object[] retrieveObjectsByAttribute(Field queryField, Object queryValue);


/**
 * The method retrieves objects from the database for the container class of any of the queryField 
 * where values in the table corresponding to the queryFields are equal to the queryValues parameter.
 * This is equivalent to the query  
 * 'Select * from Table where queryField[0] = queryValue[0] AND
 * queryField[1] = queryValue[1] and .... queryField[n] = queryValue[n]
 * ' where n = queryField.length-1
 * @param queryFields: Represents an array of query fields that are to be queried
 * @param values: represents an array of values
 * @return the objects retrieved
     * @throws advprogramming.assignment2.core.ORMappingException
 * @throws ORMappingException Exception thrown in the following cases
 * Size of queryFields[]  is not equal to values[]
 * Any of the queryField belong to some other class
 * value do not match to the datatype of the corresponding queryField
 */
public abstract Object[] retrieveObjectsByAttribute(Field[] queryFields, Object[] values) throws ORMappingException;

/**
 * The method retrieves objects from the database for the container class of the queryFiled where 
 * value in the table corresponding to the column representing fieldName is equal to the queryValue parameter.
 * This is equivalent to the query  'Select * from Table where fieldName = queryValue' 
 * 
 * @param fieldName Represents the name of the field to query
 * @param value represents a value corresponding to the fieldName
 * @return the objects retrieved
 * @throws ORMappingException Exception thrown in the following cases:
 * Size of queryFields[]  is not equal to values[]
 * Any of the queryField belong to some other class
 * value do not match to the datatype of the corresponding queryField
 */
public abstract Object[] retrieveObjectsByAttribute(String fieldName, Object value) throws ORMappingException;

/**
 * The method retrieves objects from the database for the class of the Object obj. 
 * Query fields and Query values are obtained from the NOT NULL fields of  obj.
 * For example, if obj has five attributes and only two have values, then the query is
 *  formed based on these fields and their values
 * 
 * @param obj containing values to be queried
 * @return all objects fitting the criteria
 */
public abstract Object[] retrieveObjectsByAttribute(Object obj);


/**
 * The method retrieves objects from the database for the container class of the queryFiled where 
 * value in the table corresponding to the column representing fieldName is equal to the queryValue parameter.
 * This is equivalent to the query  'Select * from Table where fieldName = queryValue'. The only difference being
 * that since the fieldName corresponds to Primary key, therefore only one object should be returned and an exception otherwise
 * 
 * @param fieldName Represents the primary key field
 * @param value the value of the primary key
 * @throws ORMappingException incase more than one objects are obtained (i.e., the field is not unique)
 * @return the object retrieved
 */

//public abstract Object retrieveObjectsByID(String fieldName, Object value) throws ORMappingException;



/**
 * The method retrieves objects from the database for the container class of the queryFiled where 
 * value in the table corresponding to the columns representing fieldNames are equal to the queryValues parameter.
 * This is equivalent to the query  'Select * from Table where fieldName[0] = queryValue[0] and .... '.
 *  The only difference being that since the fieldNames corresponds to Composite key, 
 *  therefore only one object should be returned and an exception otherwise
 * 
 * @param fieldNames Represent the primary keys field
 * @param values the values of the composite key
 * @throws ORMappingException incase more than one objects are obtained (i.e., the field is not unique)
 * @return the object retrieved
 */

//public abstract Object retrieveObjectsByID(String fieldNames[], Object values[]) throws ORMappingException;




}
