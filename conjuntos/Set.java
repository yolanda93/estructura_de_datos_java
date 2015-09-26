package conjuntos;

/** Interfaz de conjuntos no acotados */
public interface Set<E> {

	/* Cardinalidad (número de elementos) del conjunto */
	public int size();

	/* Indicar si el conjunto es vacío */
	public boolean isEmpty();

	/* Vacía el conjunto */
	public void reset();

	/* Indica si el elemento pertenece a (está en) el conjunto;
	 * si el conjunto es vació devuelve falso, no lanza una excepción. */
	public boolean member(E e);

	/* Inserta el elemento en el conjunto si no está */
	public void add(E e);

	/* Borra el elemento del conjunto si está (no lanza una expeción) */
	public void remove(E e);

	/* Unión con el conjunto argumento */
	public void union(Set<E> s) throws InvalidSetException;

	/* Intersección con el conjunto argumento */
	public void intersect(Set<E> s) throws InvalidSetException;

	/* Diferencia con el conjunto argumento */
	public void difference(Set<E> s) throws InvalidSetException; 
}
