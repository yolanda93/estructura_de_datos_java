package conjuntosIterables;

import java.lang.management.*;
import java.io.*;

class Tester5 {

	public enum Dias { Lun, Mar, Mie, Jue, Vie, Sab, Dom; }

	private static void compruebaResultado(IterableBitVectSet<Dias> conjunto, IterableBitVectSet<Dias> resultado) {
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

		IterableBitVectSet<Dias> resultado = new IterableBitVectSet<Dias>();
		IterableBitVectSet<Dias> conjunto = new IterableBitVectSet<Dias>();
		IterableBitVectSet<Dias> aux = new IterableBitVectSet<Dias>();

		SetOperations<Dias> op = new SetOperations<Dias>();
		
		System.out.println("Test 1");
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 2");
		conjunto = op.intersect(conjunto, resultado);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 3");
		resultado.add(Dias.Lun);
		resultado.remove(Dias.Lun);
		conjunto = op.intersect(conjunto, resultado);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 4");
		resultado.add(Dias.Lun);
		conjunto = op.intersect(conjunto, resultado);
		resultado.remove(Dias.Lun);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 5");
		conjunto.add(Dias.Lun);
		conjunto.remove(Dias.Lun);
		resultado.remove(Dias.Lun);
		conjunto = op.intersect(conjunto, resultado);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 6");
		conjunto = op.intersect(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 7");
		resultado.add(Dias.Lun);
		conjunto = op.intersect(conjunto, resultado);
		resultado.remove(Dias.Lun);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 8");
		conjunto.add(Dias.Lun);
		conjunto = op.difference(conjunto, resultado);
		resultado.add(Dias.Lun);
		compruebaResultado(conjunto,resultado); // {Lun}

		System.out.println("Test 9");
		conjunto = op.intersect(conjunto, resultado);
		compruebaResultado(conjunto,resultado); // {Lun}

		System.out.println("Test 10");
		aux.add(Dias.Mie);
		aux.add(Dias.Vie);
		conjunto = op.union(conjunto, aux);
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {Lun}

		System.out.println("Test 11");
		conjunto = op.union(conjunto, aux);
		conjunto = op.intersect(conjunto, aux);
		resultado.remove(Dias.Lun);
		resultado.add(Dias.Mie);
		resultado.add(Dias.Vie);
		compruebaResultado(conjunto,resultado); // {Mie,Vie}

		System.out.println("Test 12");
		aux.reset();
		aux.add(Dias.Mar);
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {Mie,Vie}

		System.out.println("Test 13");
		aux.reset();
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {Mie,Vie}

		System.out.println("Test 14");
		boolean catcherror = true;

		try {
			conjunto = op.difference(conjunto, null);
		}
		catch (InvalidSetException excp) { catcherror = false; }
		checkExcep(catcherror);	

		System.out.println("Test 15");
		resultado.remove(Dias.Mie);
		resultado.remove(Dias.Vie);
		conjunto = op.intersect(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 16");
		conjunto.add(Dias.Lun);
		conjunto.add(Dias.Sab);

		catcherror = true;
		try {
			conjunto = op.intersect(conjunto, null);
		}
		catch (InvalidSetException excp) { catcherror = false; }
		checkExcep(catcherror);	

		System.out.println("Test 17");
		conjunto = new IterableBitVectSet<Dias>();
		aux = new IterableBitVectSet<Dias>();
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 18");
		aux.add(Dias.Dom);
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 19");
		aux.remove(Dias.Dom);
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 20");
		conjunto.add(Dias.Dom);
		aux.add(Dias.Dom);
		conjunto = op.difference(conjunto, aux);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test 21");
		conjunto.add(Dias.Dom);
		conjunto.add(Dias.Lun);
		conjunto.add(Dias.Mie);
		aux.add(Dias.Dom);
		conjunto = op.difference(conjunto, aux);
		resultado.add(Dias.Lun);
		resultado.add(Dias.Mie);
		compruebaResultado(conjunto,resultado); // { Lun, Mie}

		System.out.println("Test 22");
		aux = new IterableBitVectSet<Dias>();
		conjunto = op.intersect(conjunto, aux);
		resultado.remove(Dias.Lun);
		resultado.remove(Dias.Mie);
		compruebaResultado(conjunto,resultado); // {}

		System.out.println("Test finalizado correctamente");
	}
}
