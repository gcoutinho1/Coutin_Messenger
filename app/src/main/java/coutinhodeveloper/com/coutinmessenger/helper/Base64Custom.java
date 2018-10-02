package coutinhodeveloper.com.coutinmessenger.helper;

import java.util.Base64;

/** Created by Guilherme Coutinho
 *  on 01/10/2018
 */


public class Base64Custom {

    public static String converterBase64(String texto){
        String textoConvertido = android.util.Base64.encodeToString(texto.getBytes(), android.util.Base64.DEFAULT);
        return textoConvertido.trim();

    }

    public static String decodificarBase64(String textoCodificado){
        byte[] byteDecodificado = android.util.Base64.decode(textoCodificado, android.util.Base64.DEFAULT);


        return new String(byteDecodificado);
    }
}
