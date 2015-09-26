package comparadores;

import java.util.Comparator;

/**
 * Esta clase proporciona un comparador no estandar de objetos 
 * de la clase Integer segun el cual un Integer es mayor que otro
 * cuando la suma de sus digitos es mayor que la suma de los digitos
 * del otro numero. Se ignora el signo de los numeros en esta 
 * comparacion. Por ejempo, -8 es mayor que 10, puesto que la suma de 
 * los digitos del primer numero (8) es mayor que la del segundo (1). 
 *
 */
public class ComparaSumaDigitos implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int abs1 = Math.abs(o1); //variables auxiliares para no modificar los parametros de entrada
		int abs2 = Math.abs(o2);
		int sumaDigitos1 = 0;
		int sumaDigitos2 = 0;
		while(o1 >= 1)
		{
			sumaDigitos1 += abs1 % 10;
			abs1 /= 10;
		}
		while(o2 >= 1)
		{
			sumaDigitos2 += abs2 % 10;
			abs2 /= 10;
		}
		if(sumaDigitos1 > sumaDigitos2)
			return 1;
		else if(sumaDigitos1 < sumaDigitos2)
			return -1;
		return 0;
	}
	
}
