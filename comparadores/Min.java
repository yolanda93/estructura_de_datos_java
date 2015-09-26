package comparadores;

import java.util.Comparator;

/**
 * 
 * Clase que implementa una serie de metodos para el calculo del minimo
 * de los elementos que recibe como entrada.
 * 
 * El minimo se puede obtener utilizando la comparacion natural de los
 * elementos del tipo en cuestion o mediante la utilizacion de objetos
 * comparadores.
 * 
 * Ademas, hay metodos para obtener el minimo de dos elementos (minNatural 
 * y minComparador) y el minimo de 3 elementos (min3Natural y min3Comparador).
 *
 */
public class Min<E extends Comparable<E>> {

	/**
	 * 
	 * @param one
	 * @param other
	 * @return El objeto <code>one</code> o el objeto <code>other</code>, 
	 *         dependiendo de cual sea menor segun el orden natural de 
	 *         la clase E. 
	 */
	public E minNatural(E one, E other) {
		int r = one.compareTo(other);
		if (r < 0)
		    return one;
		 else if (r == 0)
		    return other;
		return null;
	}
	/**
	 * 
	 * @param one
	 * @param other
	 * @param c
	 * @return El objeto <code>one</code> o el objeto <code>other</code>, 
	 *         dependiendo de cual sea menor segun el objeto comparador 
	 *         <code>c</code>. 
	 */
	public E minComparador(E one, E other, Comparator<E> c) {
		int r = c.compare(one, other);
		if (r < 0)
		    return one;
		 else if (r == 0)
		    return other;
		return null;
	}
	
	/**
	 * 
	 * @param first
	 * @param second
	 * @param third
	 * @param c
	 * @return El objeto de entre <code>first</code>, <code>second</code> y 
	 *         <code>third</code> que tenga valor minimo segun el orden 
	 *         natural de la clase E. 
	 */
	public E min3Natural(E first, E second, E third) {
		return minNatural(minNatural(first, second), third);
	}
	/**
	 * 
	 * @param first
	 * @param second
	 * @param third
	 * @param c
	 * @return El objeto de entre <code>first</code>, <code>second</code> y 
	 *         <code>third</code> que tenga valor minimo segun el objeto 
	 *         comparador c.
	 */
	public E min3Comparador(E first, E second, E third, Comparator<E> c) {
		return minComparador(minComparador(first, second, c), third, c);
	}

}
