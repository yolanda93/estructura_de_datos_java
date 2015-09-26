package positionLists;

import net.datastructures.IndexList;
import net.datastructures.NodePositionList;
import net.datastructures.Position;
import net.datastructures.PositionList;

public class IndexListWithPositionList<E> implements IndexList<E> {

	protected PositionList<E> list;
	
	public IndexListWithPositionList() {
		list = new NodePositionList<E>();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	protected void checkIndex(int r, int n) throws IndexOutOfBoundsException {	 
		if (r < 0 || r >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + r);
	}
	
	/**
	 * Recibe como entrada un indice de la lista y devuelve la posicion 
	 * del nodo correspondiente a dicho indice. 
	 * Asumimos que se llama siempre con un indice valido dentro de la lista.
	 * 
	 * Por eficiencia, recorre la lista secuencialmente, bien desde el primer nodo o 
	 * o bien desde el ultimo, segun el que este mas cerca del nodo buscado.
	 * 
	 * @param i    indice en la lista
	 * @return     posicion correspondiente a dicho indice.
	 */
	protected Position<E> getPosition(int i) {
		Position<E> pos;
		if (i <= size()/2) { // recorremos desde el principio
			pos = list.first();
			for(int a = 0; a < i; a++)
				pos = list.next(pos);
		} 
		else { // recorremos desde el final
			pos = list.last();
			for(int a = size()-1; a > i; a--) 
				pos = list.prev(pos);
		}
		return pos;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, list.size() + 1); // se lanzaran las posibles excepciones
		if(i == 0)
			list.addFirst(e);
		else
		{
			Position<E> pos = getPosition(i-1);
			list.addAfter(pos, e);
		}
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, list.size()); // se lanzaran las posibles excepciones
		if(i == 0)
			return list.first().element();
		else if(i == list.size() -1)
			return list.last().element();
		else
			return getPosition(i).element();
	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, list.size()); // se lanzaran las posibles excepciones
		if(i == 0)
			return list.remove(list.first());
		else if(i == list.size() -1)
			return list.remove(list.last());
		else
			return list.remove(getPosition(i));
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, list.size()); // se lanzaran las posibles excepciones
		if(i == 0)
			return list.set(list.first(), e);
		else if(i == list.size() -1)
			return list.set(list.last(), e);
		else
			return list.set(getPosition(i), e);
	}

}
