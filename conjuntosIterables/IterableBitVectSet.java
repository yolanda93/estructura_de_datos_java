package conjuntosIterables;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Conjunto acotado implementado mediante vector de bits. 
 *  A diferencia de BitVectSet, esta clase es iterable
 */
public class IterableBitVectSet<E extends Enum<E>> extends BitVectSet<E> implements Set<E>, Iterable<E>{

	protected E[] enumConstants;

	public void add(E e) {
		if (bv == null) { /* Se crea e inicializa el vector de bits */
			enumConstants = e.getDeclaringClass().getEnumConstants();
			bv = new boolean[enumConstants.length];
			for (int i = 0; i < bv.length; i++) bv[i] = false;
			
		}
		if (! bv[e.ordinal()] ) {
			bv[e.ordinal()] = true;
			size++;
		}
	}

	
	public Iterator<E> iterator() {
		return new BitVectSetIterator<E>(this);
	}
	
	protected static class BitVectSetIterator<E extends Enum<E>> implements Iterator<E> {

		protected IterableBitVectSet<E> set;
		protected int porMostrar;
		// posicion donde hemos encontrado el ultimo booleano con valor cierto
		protected int indice = -1;
		
		public BitVectSetIterator(IterableBitVectSet<E> s) {
			set = s;
			porMostrar = s.size();
		}
		
		/**
		 * Avanzamos el atributo <code>indice</code> hasta que encontramos una posicion 
		 * en el array <code>set.bv</code> con el valor <code>cierto</code>.
		 * @return el indice del primer booleano puesto a <code>cierto</code>.
		 */
		protected int findNonEmptyPosition() {
			while (!set.bv[++indice]) {
			}
			return indice;
		}
		
		@Override
		public boolean hasNext() {
			return porMostrar > 0;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			porMostrar--;
			return set.enumConstants[findNonEmptyPosition()];
		}

		@Override
		public void remove() {
		    throw new UnsupportedOperationException("remove");
		}

	}

}
