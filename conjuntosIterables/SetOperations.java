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
		// TODO implementar. Debe hacer un recorrido de s1 usando las operaciones 
		// de iteradores y despues recorrer s2 usando tambien las operaciones de iteradores.
		return null;
	}

	/**
	 * Calcula la interseccion de dos conjuntos implementados con vector de booleanos iterables.
	 *  
	 * @return un nuevo conjunto que contiene la interseccion de <code>s1</code> y <code>s2</code>
	 * @throws InvalidSetException cuando alguno de los conjuntos de entrada son nulos
	 */
	public IterableBitVectSet<E> intersect (IterableBitVectSet<E> s1, IterableBitVectSet<E> s2) throws InvalidSetException {
		// TODO implementar. Debe implementarse con bucles for-each. La solucion optima solo necesita un bucle.
		return null;
	}

	/**
	 * Calcula la diferencia entre dos conjuntos implementados con vector de booleanos iterables.
	 *  
	 * @return un nuevo conjunto que contiene la diferencia de <code>s1</code> y <code>s2</code>
	 * @throws InvalidSetException cuando alguno de los conjuntos de entrada son nulos
	 */
	public IterableBitVectSet<E> difference (IterableBitVectSet<E> s1, IterableBitVectSet<E> s2) throws InvalidSetException {
		// TODO implementar. Debe implementarse con bucles for-each. La solucion optima solo necesita un bucle.
		return null;
	}


}
