package heaps;

import java.lang.reflect.Array;
import java.util.Comparator;
import net.datastructures.Entry;
import net.datastructures.PriorityQueue;
import net.datastructures.EmptyPriorityQueueException;

/**
 * Short version: modify the upHeap and downHeap methods below.
 * (CAMBIA los metodos upHeap y downHeap!)
 *
 * Long version:
 *
 * This class implements a PriorityQueue using a Heap (Monticulo). 
 * The Heap is implemented using an array. 
 * Consider the following Heap:
 * <pre>
 * {@code
 *
 *        1
 *      /   \
 *     3     5
 *    / \   / \
 *   8   9  20 11
 *  /
 * 7
 * }
 * </pre>
 *
 * Ordering requirement on a heap
 * ==============================
 *
 * The key in a child must be greater than (or equal to) the key in
 * the parent. Or vice versa, the key of a parent must be less than
 * (or equal to) the keys of its children.
 *
 * Your task
 * =========
 *
 * Your task is to restore the proper ordering of the heap after a
 * method may have caused the heap to not be properly ordered,
 * possibly swapping nodes to restore order.
 * 
 * The heap can have become non-ordered due to the insertion of
 * a new element as the rightmost leaf - by the method insert,
 * or due to removing the root element and replacing it with the rightmost 
 * leaf - by the method removeMin.
 * 
 * The methods you have to MODIFY to restore the proper ordering
 * are upHeap (called at the end of insert), 
 * and downHeap (called at the end of removeMin).
 *
 * downHeap:
 * =========
 *
 * downHeap(position) should check if any child of position
 * has a key less than position, if so swap the entries of the parent node
 * with the smallest child node. Then continue with the smallest child node.
 *
 * EXAMPLE:
 *
 * If removeMin is called on the above heap, the heap looks like:
 * <pre>
 * {@code
 *        7
 *      /   \
 *     3     5
 *    / \   / \
 *   8   9  20 11
 * }
 * </pre>
 * which is NOT a properly ordered heap since 7>3 (also because 7>5 as well).
 *
 * The method downHeap that you should implement is responsible for restoring 
 * the ordering by moving entries. It will be called
 * with the root position, and the entry corresponding to the key 7.
 *
 * The result should be a heap of the following shape:
 * <pre>
 * {@code
 *        3
 *      /   \
 *     7     5
 *    / \   / \
 *   8   9  20 11
 * }
 * </pre>
 * First 7 was swapped with 3, but since 7<8 and 7<9 the downHeap methods
 * then finishes
 * (note that downHeap starts at the root of the heap, and continues downwards
 * to the leafs).
 *
 * upHeap:
 * =========
 *
 * upHeap(position) should check if the parent of position
 * has a key greater than the key of the position, if so swap the entries
 * of the parent node with the child node. Then continue with the parent node.
 *
 * EXAMPLE:
 *
 * If insert(2) has been called on the above heap, to insert
 * the new key 2, the heap looks like:
 * <pre>
 * {@code
 *        1
 *      /   \
 *     3     5
 *    / \   / \
 *   8   9  20 11
 *  /\
 * 7  2
 * }
 * </pre>
 * which is NOT a properly ordered heap since 2<8. 
 * The method upHeap that you should implement is responsible for restoring 
 * the ordering by moving entries. It will be called
 * with the new leaf position, and the entry corresponding to the key 2.
 *
 * The result should be a heap of the following shape:
 * <pre>
 * {@code
 *        1
 *      /   \
 *     2     5
 *    / \   / \
 *   3   9  20 11
 *  /\
 * 7  8
 * }
 * </pre>
 * First 2 and 8 were swapped, then 2 and 3, but since 1<2 the upHeap
 * method terminates
 * (note that upHeap starts at the root of the heap, and continues upwards
 * to the root).
 *
 * Representation of the Heap
 * ===========================
 *
 * The Heap in this class is stored in a normal Java array.
 * For example, the elements (keys) for the heap in the first figure
 * are stored in an array a[] in the following manner:
 * a[0]=empty, a[1]=1, a[2]=3, a[3]=5, a[4]=8, a[5]=9,
 * a[6]=20, a[7]=11, a[8]=7. 
 * The position of an entry is the index in this array.
 *
 * Note that the parent of node i is ALWAYS at position i/2
 * (for example the a[7]=11 has the parent at position a[7/2]=a[3]=5).
 * Moreover the children of a node at position i are ALWAYS located at 2*i
 * and 2*i+1
 * (for example the children of a[2]=3 are located at a[2*2]=a[4]=8
 * and a[2*2+1]=a[5]=9.
 *
 * Note also that the first element of the heap, its root, if it exists, 
 * is stored at a[1], NOT a[0]. The a[0] element should NEVER be used.
 */
public class Heap<K,V> implements PriorityQueue<K,V>
{
    protected Entry<K,V> a[];
    protected int heapDepth;
    protected int numElements;
    protected Comparator<K> comp;

    /**
     * Creates a priorityqueue where elements are ordered 
     * using <code>comp</code>.
     * @param comp the ordering relation.
     */
    public Heap(Comparator<K> comp) {
	this.comp = comp;
	heapDepth = 0;
	numElements = 0;
	a = null;
    }

    /**
     * YOU SHOULD MODIFY THIS METHOD.
     *
     * Restores the proper ordering of the heap which may be wrongly
     * ordered after an insert (a new entry was added as the last leaf).
     * Upon completion of the method all entries in the tree should be ordered
     * correctly. That is, parent entries have values smaller (or equal)
     * to their children entries.
     *
     * Given a position corresponding to a node, this method
     * ensures that the key at the parent of the node
     * is less than (or equal) to the key of the node argument,
     * if necessary by swapping the node entry and the entry at the parent.
     * The procedure should then continue recursively with the parent.
     */
    protected void upHeap(int pos) {
		if(a[pos] == null) //comprobamos que haya un elemento en la posicion dada
			return;
		int p = pos;
		Entry<K,V> aux;
		while(p > 1 && comp.compare(a[p].getKey(), a[p/2].getKey()) < 0 )
		{
			aux = a[p];
			a[p] = a[p/2];
			a[p/2] = aux;
			p = p/2;
		}
    }

    /**
     * YOU SHOULD MODIFY THIS METHOD.
     *
     * Restores the proper ordering of the tree that may be wrongly
     * ordered after an call to removeMin 
     * (the old root entry was removed, and a 
     * previous last leaf entry was inserted at the root).
     * Due to this change the entries in the tree may no longer be 
     * correctly ordered.
     * Upon completion of the method all tree nodes should be ordered
     * correctly.
     *
     * Given a position, the method ensures that both its children
     * have keys less than its parent (the position argument).
     * If necessary, the entry at smallest child node is swapped with the 
     * entry of the parent node.
     * The downHeap operation then continues at the child position that
     * was updated.
     */
    protected void downHeap(int pos) {
		
    	if(a[pos] == null) //comprobamos que haya un elemento en la posicion dada
			return;
		int p = pos;
		int pAux;
		
		if(numElements == p*2)
			pAux = p*2;
		else
		{
			if(numElements >= p*2+1 && comp.compare(a[p*2].getKey(), a[p*2+1].getKey()) <= 0)
				pAux = p*2;
			else
				pAux = p*2+1;
		}
		while(pAux <= numElements && comp.compare(a[p].getKey(), a[pAux].getKey()) > 0)
		{
			Entry<K,V> aux = a[p];
			a[p] = a[pAux];
			a[pAux] = aux;
			p = pAux;
			if(numElements == p*2)
				pAux = p*2;
			else 
			{
				if(numElements >= p*2+1 && comp.compare(a[p*2].getKey(), a[p*2+1].getKey()) <= 0)
					pAux = p*2;
				else
					pAux = p*2+1;
			}
					
		}
    }

    /**
     * Inserts a new entry in the priority queue.
     * @param key the key to use for the new entry
     * @param value the value to use for the new entry
     * @return the new entry
     */
    public Entry<K,V> insert(K key, V value) {
	Entry<K,V> entry = new MyEntry<K,V>(key,value);

	// Check if we have to increase the size of the array
	if (a == null || numElements >= (a.length-1)) {
	    heapDepth = heapDepth + 1;
	    @SuppressWarnings("unchecked")
	    Entry<K,V> newArr[] =
		(Entry<K,V>[])
		Array.newInstance(entry.getClass(),exp(2,heapDepth));
	    if (a != null) System.arraycopy(a, 0, newArr, 0, a.length);
	    a = newArr;
	}

	// Array has sufficent elements; let's insert the new entry
	numElements = numElements + 1;
	a[numElements] = entry;
	upHeap(numElements);

	return entry;
    }

    /**
     * Is the priority queue empty?
     * @return true if the queue is empty, otherwise false
     */
    public boolean isEmpty() {
	return size()==0;
    }

    /**
     * Returns the minimal element of the queue, but does not remove it.
     * @return the minimal element
     * @throws <code>EmptyPriorityQueueException</code> if the queue is empty.
     */
    public Entry<K,V> min() {
	if (isEmpty())
	    throw new EmptyPriorityQueueException("Priority queue is empty");
	return a[1];
    }

    /**
     * Removes the minimal element from the queue.
     * @return the minimal element that was removed
     * @throws <code>EmptyPriorityQueueException</code> if the queue is empty.
     */
    public Entry<K,V> removeMin() {
	if (isEmpty()) 
	    throw new EmptyPriorityQueueException("Priority queue is empty");

	Entry<K,V> minElement = a[1];
	a[1] =  a[numElements];
	numElements = numElements - 1;
	if (!isEmpty()) downHeap(1);
	
	return minElement;
    }

    /**
     * Returns the size of the queue.
     * @return the size of the queue
     */
    public int size() {
	return numElements;
    }

    protected int exp(int base, int exp) {
	int result=1;

	while (exp > 0) {
	    result *= base;
	    exp -= 1;
	}
	return result;
    }
}


