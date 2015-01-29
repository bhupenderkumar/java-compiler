import java.util.*;
public class Klasa {
    public static void main(String[] args) {
	ArrayList<String> lista = new ArrayList<String>();
	lista.add("Mama");
	lista.add("Tata");
	lista.add("Ola");
	for (String s: lista) {
		System.out.print(String.format("%s ", s));
	}	
    }
}
