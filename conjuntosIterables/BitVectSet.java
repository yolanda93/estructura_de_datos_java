package conjuntosIterables;

/** Conjunto acotado implementado mediante vector de bits */
public class BitVectSet<E extends Enum<E>> implements Set<E> {

	protected boolean bv[];
	protected int size;

	public BitVectSet() {
		bv   = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void reset() {
		size = 0;
		if (bv != null)
			for (int i = 0; i < bv.length; i++) bv[i] = false;
	}

	public boolean member(E e) {
		if (bv == null)
			return false;
		else
			return bv[e.ordinal()];
	}

	public void add(E e) {
		if (bv == null) { /* Se crea e inicializa el vector de bits */
			bv = new boolean[e.getDeclaringClass().getEnumConstants().length];
			for (int i = 0; i < bv.length; i++) bv[i] = false;
		}
		if (! bv[e.ordinal()] ) {
			bv[e.ordinal()] = true;
			size++;
		}
	}

	public void remove(E e) {
		if (bv != null && bv[e.ordinal()]) {
			bv[e.ordinal()] = false;
			size--;
		}
	}

	protected BitVectSet<E> checkValidSetAndCast(Set<E> s) throws InvalidSetException {
		if (s == null) throw new InvalidSetException();
		if (!(s instanceof BitVectSet<?>)) throw new InvalidSetException();
		return (BitVectSet<E>) s;
	}

	public void union(Set<E> s) throws InvalidSetException {
		throw new UnsupportedOperationException("No debes usar esta operacion en este laboratorio");
	}


	public void intersect(Set<E> s) throws InvalidSetException {
		throw new UnsupportedOperationException("No debes usar esta operacion en este laboratorio");
	}


	public void difference(Set<E> s) throws InvalidSetException {
	     throw new UnsupportedOperationException("No debes usar esta operacion en este laboratorio");
	} 

	public boolean equals(Object s) {
		if (s == null)  return false;
		try {
			@SuppressWarnings("unchecked")
			BitVectSet<E> bvs = (BitVectSet<E>) s; 
			boolean iguales;
			if (size != bvs.size()) {
				iguales = false;
			} else {
				iguales = true;
				if (bv == null) {
					if (bvs.bv != null) {
						for (int i = 0; (i < bvs.bv.length && iguales); i++)
							iguales = !bvs.bv[i];
					}
				} else {
					if (bvs.bv == null) {
						for (int i = 0; (i < bv.length && iguales); i++)
							iguales = !bv[i];
					} else {
						for (int i = 0; (i < bv.length && iguales); i++)
							iguales = (bv[i] == bvs.bv[i]);
					}
				}
			}
			return iguales;
		} catch (ClassCastException e ) {
			return false;		  
		}

	}
}
