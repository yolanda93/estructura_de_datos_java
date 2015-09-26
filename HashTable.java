package hash;

import java.lang.Iterable;
import net.datastructures.Map;
import net.datastructures.Entry;
import net.datastructures.NodePositionList;
import java.lang.reflect.Array;

/**
 * Short version: modify the three methods hashValue(K key),
 * findEntry(K key) and findEmptyCell(K key) below.
 * 
 * Long version:
 * this class implements a HashTable using a Java array with
 * "open addressing" and "linear probing".
 * See course notes, or the course literature,
 * on what "open addressing" and "linear probing" means if you don't remember.
 *
 * Each cell in the hash table stores an Entry<K,V>, a key and a value.
 * A null cell indicates that the cell is empty. Also, a null key inside a
 * cell indicates that the cell is empty. The operations get, put and remove
 * use a cell with a null key as a placeholder ("disponible" according
 * to Pablo Noguiera's notes).
 * 
 * Your task is to complete the implementation of
 * the three methods hashValue(key), findEntry(key) and 
 * findEmptyCell(key) below.
 *
 * You may not modify other methods than the three above, but you may of 
 * course add new auxilliary methods.
 *
 * 1: hashValue(K key) is called with a key, and should return an int
 * calculating a hash value for the key, which is in the range 0..tableSize-1.
 * (tableSize is a variable in the class which records the size of the table).
 * Hints: use the method hashCode() to first compute a hash value and then
 * adjust the hash value to the size of the table (see course notes).
 *
 * 2: findEntry(key) looks for the key in the hash table, and if found,
 * returns its index, otherwise (if not found) it should return a negative
 * value. This method is called from numerous other methods, i.e., get(K key),
 * and put(K key).
 *
 * Hints:
 * Use your method hashValue(key) to find the cell where an entry with
 * the key should be located. Then do a linear search in the table (array)
 * from that entry until the key is found, or a null cell is found (failure),
 * or until the whole array has been searched (failure). If a cell with a null
 * key is found, the search should continue.
 *
 * 3: findEmptyPlace(K key) searches for an empty cell in the table,
 * starting from the index where the key should ideally be located.
 * If there is no space in the array a negative int should be returned.
 *
 * Hints: if the ideal cell is occupied, search continues linearly.
 * Seach is successful if an empty cell is found (with a null cell, 
 * or a non-null cell with a null key).
 */

public class HashTable<K,V> implements Map<K,V> 
{
	/*
	 * Numero de Entrys validas, no tiene porque coincidir 
	 * con la cantidad de Entrys inicializadas en la tabla
	 */
    private int numElements;
	
	/*
	 * Tamaño de la tabla, coincide siempre con table.length
	 * (Siempre que table este inicializada) se usa para no estar
	 * todo el rato:
	 *	if(table != null)
	 *		table.length;
	 */
    private int tableSize;
	
    private Entry<K,V> table[];
    
    /**
     * Creates an empty hash table. The table will grow when its inherit
     * limit (the size of the array used to store entries) is reached.
     */
    public HashTable() 
	{
		numElements = 0;
		tableSize = 0;
		table = null;
    }

	/*
	 * Esta funcion sera llamada cuando nos quedemos sin espacio en la tabla para incrementarla 
	 * (Nota: la tabla nunca decrementa, solo incrementa)
	 */
    @SuppressWarnings("unchecked")
    void increaseTableSize(K key,V value) 
	{
		if (size()+1 > tableSize) // size vale exactamente lo mismo que numElement. Tambien se podia haber puesto (size() >= tableSize)
		{
			Entry<K,V> oldTable[] = table;
			int oldTableSize = tableSize;

			if (tableSize == 0)
				tableSize = 5;
			else 
				tableSize = tableSize*2;

			Entry<K,V> entry = new HashTableEntry<K,V>(key,value); // se crea una nueva entrada, unicamente para en la linea de abajo sacar la clase en: entry.getClass()
			table = (Entry<K,V>[]) Array.newInstance(entry.getClass(), tableSize); //creacion de arrays para datos genericos.
			numElements = 0; // <------ No entiendo.
			for (int i=0; i<oldTableSize; i++) 
			{
				Entry<K,V> copyEntry = oldTable[i];
				if (copyEntry != null && copyEntry.getKey() != null)
					put(copyEntry.getKey(), copyEntry.getValue());
				//importante observacion, solo se copian aquellos entrys que tengan
				//una llave inicialzada, ya que en la oldTable, pueden haber entrys 
				//con key = null y value inicializado.
			}
		}
    }

    /**
     * Returns an iterable over the set of entries in the hash table.
     * 
     * @return an iterable over the set of entries in the hash table
     */
	
	/*
	 * Crea un iterable solo de las los entrys existentes 
	 * (organizados de la posicion mayor a la menor).
	 */
    public Iterable<Entry<K,V>> entrySet() 
	{
		NodePositionList<Entry<K,V>> nl = new NodePositionList<Entry<K,V>>();
		for (int i=0; i<size(); i++) 
		{
			Entry<K,V> entry = table[i];
			if (entry != null && entry.getKey() != null)
				nl.addFirst(entry);
		}
		return nl;
    }

    
    /**
     * Computes a hash value for the key argument.
     * YOU SHOULD MODIFY THIS METHOD.
     * @param key the key to compute a hash value for
     * @return a hash value in the range 0..tableSize-1
     */
    int hashValue(K key) 
	{
    	 return (int) (Math.abs(key.hashCode())% tableSize);
    }

    /**
     * Locates an entry with the corresponding key in the hash table.
     * YOU SHOULD MODIFY THIS METHOD.
     * @param the key to search for
     * @return the index for the array cell where the key was found,
     * or a negative value if no key was found.
     */
	 
	/*
	 * Busca la posicion del array donde key tiene que estar,
	 * sino, da una vuelta desde ideal hasta ideal para encontrarlo.
	 * Teoricamente, hay muchas posibilidades de que key este en 
	 * la posicion ideal, y por tanto O(1).
	 */
    int findEntry(K key) { 
    	Entry<K,V> e;
    	int ideal = hashValue(key);
    	int i = ideal;
    	do
    	{
    		e = table[i];
    		if(e != null)
    		{
    			if (key.equals(e.getKey()))
        			return i;
    		}
    		i = (i+1)%tableSize;
    	}
    	while(i != ideal);
    	return -1;
    }

    /**
     * Locates an empty cell where an entry with the key argument
     * should be stored in the hash table.
     * YOU SHOULD MODIFY THIS METHOD.
     * @param the key to use for the search
     * @return the index for the array cell where an empty cell was found,
     * or a negative value if no empty cell was found.
     */
	 
	/*
	 * busca la celda vacia ideal para la key dada por parametro, 
	 * si no se encuentra ninguna idea, se da una vuelta en busca 
	 * de la que mas lo sea. Hay muchas posibilidades de que la celda
	 * libre para key este vacia.
	 */
    int findEmptyCell(K key) {
    	int ideal = hashValue(key);
    	int i = ideal;
    	do
    	{
    		if(table[i] == null || table[i].getKey() == null)
    		{
    			return i; //posicion libre.
    		}
    		i = (i+1)%tableSize;
    	}
    	while(i != ideal);
    	return -1;
    }

    /**
     * Return the value corresponding to the key argument, or <code>null</code>
     * if no such key exists in the table.
     * @param key the key to search for
     * @return <code>null</code> if no key is found, 
     * otherwise the value associated (in the same entry) with the key.
     */
	 
	/*
	 * Este metodo es muy lento si no se encuentra la key del parametro.
	 */
    public V get(K key) 
	{
		if (size() == 0) 
			return null;
		int pos = findEntry(key);
		if (pos < 0) 
			return null;
		return table[pos].getValue();
    }

    /**
     * Determines whether the hash table is empty.
     * @return true if the hash table is empty, false otherwise.
     */
    public boolean isEmpty() 
	{
		return size() == 0;
    }

    /**
     * Returns an iterable over the set of keys in the hash table.
     * @return an iterable over the set of keys in the hash table
     */
	 
	/*
	 * Crea un iterable solo de las llaves existentes 
	 * (organizadas de la posicion mayor a la menor).
	 */
    public Iterable<K> keySet() 
	{
		NodePositionList<K> nl = new NodePositionList<K>();
		for (int i=0; i<size(); i++) 
		{
			Entry<K,V> entry = table[i];
			if (entry != null && entry.getKey() != null)
				nl.addFirst(entry.getKey());
		}
		return nl; //gracias a que NodePositionList hereda de Iterable
    }

    /**
     * Replaces the old value associated with key with the parameter
     * <code>value</code>, and returns the old value. If the key is
     * not present in the hash table, a new entry with the key and value
     * is stored in the table, and <code>null</code> is returned.
     * @param key the key to search for
     * @param value the new value
     * @return <code>null</code> if key is not present in the table, 
     * otherwise returns the old value associated
     * with the key.
     */
	 
	/*
	 * Tratara de encontrar una Entry con la misma key para sobreescribirla. 
	 * Sino la encuentra se creara sin sobreescribir (en una nueva posicion /posicion vacia)
	 */
    public V put(K key, V value)
	{
		if (tableSize == 0) //solo sera true la primera vez que se llame a put.
			increaseTableSize(key,value);
		int pos = findEntry(key);
		V returnValue = null;
		if (pos < 0) //es decir, no se ha encontrado una Entry para key. Caso de O(n), raro.
		{
			if (size() >= tableSize) //si esta lleno, incrementamos
				increaseTableSize(key,value); 
			pos = findEmptyCell(key); // se busca una posicion vacia para key.
			if (pos < 0) //caso erroneo, que no deberia ocurrir nunca.
			{
				System.out.println("findEmptyCell found no free cell although there " + "should be space?");
				System.out.println(this);
				throw new RuntimeException();
			}
			else 
				++numElements;
		} 
		else 
			returnValue = table[pos].getValue(); //caso O(1), tipico.
		table[pos] = new HashTableEntry<K,V>(key,value); // se crea la nueva Entry en la posicion elegida mediante todo el lio de arriba
		return returnValue; //devolvemos el valor antiguo, en el caso de que hayamos sobreecrito.
    } 

    /**
     * Removes an entry identified with key in the table.
     * @param key the key to search for
     * @return <code>null</code> if key is not present in the table, 
     * otherwise returns the old value associated
     * with the key.
     */
	 
	/*
	 * Se busca, y si se encuentra, se elimina. Se elimina no igualando a null,
	 * sino sustituyendolo por un nuevo objeto sin key, y conservando el antiguo valor.
	 * Importante: la tabla va incrementando de tamaño siempre que se necesite mas espacio,
	 * pero nunca decrementa.
	 */
    public V remove(K key) 
	{
		if (size() == 0) 
			return null;
		int pos = findEntry(key);
		if (pos < 0) 
			return null;
		Entry<K,V> entry = table[pos];
		table[pos] = new HashTableEntry<K,V>(null,entry.getValue()); //se sobreescribe por uno nuevo.
		--numElements;
		return entry.getValue();
    }

    /**
     * Returns the number of elements in the hash table.
     * @return the number of elements in the hash table
     */
	 
    public int size() 
	{
		return numElements;
    }

    /**
     * Returns an iterable over the set of values in the hash table.
     * @return an iterable over the set of values in the hash table
     */
	 
	 /*
	 * Crea un iterable solo de los valores de los entrys
	 * (organizados de la posicion mayor a la menor).
	 * Aunque haya Entrys con valores definidos, solo se utilizaran
	 * los que tengan asociados una key
	 */
    public Iterable<V> values() 
	{
		NodePositionList<V> nl = new NodePositionList<V>();
		for (int i=0; i<size(); i++) 
		{
			Entry<K,V> entry = table[i];
			if (entry != null && entry.getKey() != null)
				nl.addFirst(entry.getValue());
		}
		return nl;
    }

    /**
     * Returns a string representation of the hash table
     * (useful for debugging).
     * @return a string representation of the hash table.
     */
    public String toString() 
	{
		String retString = "";
		for (int i=0; i<tableSize; i++) 
		{
			if (!retString.equals("")) 
				retString = retString + ", ";
			Entry<K,V> entry = table[i];
			if (entry != null) 
				retString = retString + i + ":" + entry.toString();
			else
				retString = retString + i + ":" + entry;
		}
		return "Hashtable {size=" + size() + "," + "contents=" + retString + "}";
    }
}