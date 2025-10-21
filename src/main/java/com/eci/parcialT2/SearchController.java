package com.eci.parcialT2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @RequestMapping("/linearsearch")
    public String  LinearSearch ( String[] listaValores,  String valorBuscado) {

        int salida = -1;

		for (int i = 0; i < listaValores.length; i++) {
			if (listaValores[i].equals(valorBuscado)) {
				salida =  i;
			}
		}
        return "{" + "operation: 'Linear Search', " +
                "\nlistaValores: '" + String.join(",", listaValores) + "', " +
                "\nvalorBuscado: '" + valorBuscado + "', " +
                "\nresultado: " + salida +
                "}";
    }

    @RequestMapping("/BinariSearch")
    public String BinariSearch (String[] listaValores,String valorBuscado) {
        int inicio = 0;
        int finalLista = listaValores.length - 1;
        
        String resultado= BinariSearch2(listaValores, valorBuscado, inicio, finalLista);


        return "{" + "operation: 'Busqueda Binaria', " +
                "\nlistaValores: '" + String.join(",", listaValores) + "', " +
                "\nvalorBuscado: '" + valorBuscado + "', " +
                "\nresultado: " + resultado +
                "}";
    }

    public String BinariSearch2 (String[] listaValores, String valorBuscado, int inicio, int finalLista) {
        int valor = Integer.parseInt(valorBuscado);
        int medio = (inicio + finalLista) / 2;
        int medioValor = Integer.parseInt(listaValores[medio]);
        if (medioValor > valor) {
            return BinariSearch2(listaValores, valorBuscado, inicio, medio - 1);

        } else if (medioValor < valor) {
            return BinariSearch2(listaValores, valorBuscado, medio + 1, finalLista);
        } else if (medioValor == valor) {
            return " " + medio;
        }else {
            return "-1";
        }
    }


}
