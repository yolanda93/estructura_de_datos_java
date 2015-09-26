package conjuntosIterables;

import java.util.Iterator;

public class SetOperations<E extends Enum<E>> {

	/**
	 * Calcula la union de dos conjuntos implementados con vector de booleanos iterables.
	 *  
	 * @return un nuevo conjunto que contiene la union de <code>s1</code> y <code>s2</code>
	 * @throws InvalidSetException cuando alguno de los conjuntos de entrada son nulos
	 */
	public IterableBitVectSet<E> union (IterableBitVectSet<E> s1, IterableBitVectSet<E> s2) throws InvalidSetException {
		if(s1 == null | s2 == null)
			throw new InvalidSetException();
		IterableBitVectSet<E> result = new IterableBitVectSet<E>();
		Iterator<E> i = s1.iterator();
		while(i.hasNext())
			result.add(i.next());
		Iterator<E> j = s2.iterator();
		while(j.hasNext())
			result.add(j.next());
		return result;
	}

	/**
	 * Calcula la interseccion de dos conjuntos implementados con vector de booleanos iterables.
	 *  
	 * @return un nuevo conjunto que contiene la interseccion de <code>s1</code> y <code>s2</code>
	 * @throws InvalidSetException cuando alguno de los conjuntos de entrada son nulos
	 */
	public IterableBitVectSet<E> intersect (IterableBitVectSet<E> s1, IterableBitVectSet<E> s2) throws InvalidSetException {
		if(s1 == null | s2 == null)
			throw new InvalidSetException();
		IterableBitVectSet<E> result = new IterableBitVectSet<E>();
		E aux;
		for(Iterator<E> i = s1.iterator(); i.hasNext();)
		{
			aux = i.next();
			if(s2.member(aux))
				result.add(aux);
		}		
		return result;
	}

	/**
	 * Calcula la diferencia entre dos conjuntos implementados con vector de booleanos iterables.
	 *  
	 * @return un nuevo conjunto que contiene la diferencia de <code>s1</code> y <code>s2</code>
	 * @throws InvalidSetException cuando alguno de los conjuntos de entrada son nulos
	 */
	public IterableBitVectSet<E> difference (IterableBitVectSet<E> s1, IterableBitVectSet<E> s2) throws InvalidSetException {
		if(s1 == null | s2 == null)
			throw new InvalidSetException();
		IterableBitVectSet<E> result = new IterableBitVectSet<E>();
		E aux;
		for(Iterator<E> i = s1.iterator(); i.hasNext();)
		{
			aux = i.next();
			if(!s2.member(aux))
				result.add(aux);
		}		
		return result;
	}


}
