package conjuntos;

import java.lang.management.*;
import java.io.*;

class Tester4 {

	public enum Dias { Lun, Mar, Mie, Jue, Vie, Sab, Dom; }

	private static void compruebaResultado(BitVectSet<Dias> conjunto, BitVectSet<Dias> resultado) {
		if (!conjunto.equals(resultado)) {
			if (conjunto.size() != resultado.size()) {
				System.out.println("conjunto de tamaño incorrecto");
				System.out.print("El contenido de conjunto es: ");
				displaySet(conjunto);
				System.out.print("y deberia ser: ");
				displaySet(resultado);
				
			} else {
				System.out.print("conjunto con contenido incorrecto. Su contenido es: ");
				displaySet(conjunto);
				System.out.print("cuando deberia ser: ");
				displaySet(resultado);
			}
			System.exit(-1);
		}
		else displaySet(conjunto);
	}

	private static void displaySet(Set<Dias> aed) {
		Dias[] constants = Dias.class.getEnumConstants();
		boolean primero = true;
		System.out.print("{ ");
		if (aed != null) {
			for (Dias d : constants)
				if (aed.member(d)) {
					if (primero) {
						primero = false;
					} 
					else { 
						System.out.print(", ");
					}
					System.out.print(d.toString());
				}
		}
		System.out.println(" }");
	}

	private static void checkExcep (boolean error) {
		if (error){
			System.out.println ("Error en lanzamiento de Excepcion InvalidSetException");
			System.exit(1);
		}
	}

	public static void main(String args[]) throws InvalidSetException {
		try
		{
			String[] ids = ManagementFactory.getRuntimeMXBean().getName().split("@");
			BufferedWriter bw = new BufferedWriter(new FileWriter("pid"));
			bw.write(ids[0]);
			bw.close();
		}catch(Exception e)
		{
			System.out.println("Avisa al profesor de fallo sacando el PID");
		}

		BitVectSet<Dias> resultado = new BitVectSet<Dias>();
		BitVectSet<Dias> conjunto = new BitVectSet<Dias>();
		BitVectSet<Dias> aux = new BitVectSet<Dias>();

		System.out.println("Test 1");
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 2");
		conjunto.intersect(resultado);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 3");
		resultado.add(Dias.Lun);
		resultado.remove(Dias.Lun);
		conjunto.intersect(resultado);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 4");
		resultado.add(Dias.Lun);
		conjunto.intersect(resultado);
		resultado.remove(Dias.Lun);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 5");
		conjunto.add(Dias.Lun);
		conjunto.remove(Dias.Lun);
		resultado.remove(Dias.Lun);
		conjunto.intersect(resultado);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 6");
		conjunto.intersect(aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 7");
		resultado.add(Dias.Lun);
		conjunto.intersect(resultado);
		resultado.remove(Dias.Lun);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 8");
		conjunto.add(Dias.Lun);
		conjunto.difference(resultado);
		resultado.add(Dias.Lun);
		compruebaResultado(conjunto,resultado); // {Lun}

		System.out.println("Test 9");
		conjunto.intersect(resultado);
		compruebaResultado(conjunto,resultado); // {Lun}

		System.out.println("Test 10");
		aux.add(Dias.Mie);
		aux.add(Dias.Vie);
		conjunto.union(aux);
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {Lun}

		System.out.println("Test 11");
		conjunto.union(aux);
		conjunto.intersect(aux);
		resultado.remove(Dias.Lun);
		resultado.add(Dias.Mie);
		resultado.add(Dias.Vie);
		compruebaResultado(conjunto,resultado); // {Mie,Vie}

		System.out.println("Test 12");
		aux.reset();
		aux.add(Dias.Mar);
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {Mie,Vie}

		System.out.println("Test 13");
		aux.reset();
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {Mie,Vie}

		System.out.println("Test 14");
		boolean catcherror = true;

		try {
			conjunto.difference(null);
		}
		catch (InvalidSetException excp) { catcherror = false; }
		checkExcep(catcherror);	

		System.out.println("Test 15");
		resultado.remove(Dias.Mie);
		resultado.remove(Dias.Vie);
		conjunto.intersect(aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 16");
		conjunto.add(Dias.Lun);
		conjunto.add(Dias.Sab);

		catcherror = true;
		try {
			conjunto.intersect(null);
		}
		catch (InvalidSetException excp) { catcherror = false; }
		checkExcep(catcherror);	

		System.out.println("Test 17");
		conjunto = new BitVectSet<Dias>();
		aux = new BitVectSet<Dias>();
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 18");
		aux.add(Dias.Dom);
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 19");
		aux.remove(Dias.Dom);
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 20");
		conjunto.add(Dias.Dom);
		aux.add(Dias.Dom);
		conjunto.difference(aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 21");
		conjunto.add(Dias.Dom);
		conjunto.add(Dias.Lun);
		conjunto.add(Dias.Mie);
		aux.add(Dias.Dom);
		conjunto.difference(aux);
		resultado.add(Dias.Lun);
		resultado.add(Dias.Mie);
		compruebaResultado(conjunto,resultado); // { Lun, Mie}

		System.out.println("Test 22");
		aux = new BitVectSet<Dias>();
		conjunto.intersect(aux);
		resultado.remove(Dias.Lun);
		resultado.remove(Dias.Mie);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test finalizado correctamente");
	}
}
