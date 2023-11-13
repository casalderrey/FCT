package com.example.gestiondemusica.utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Escribir {
    String texto;

    public Escribir(){

    }

    public void setTexto(String nuevo){
        this.texto = nuevo;
    }

    public void escribirArchivo(String datos, File direccion) {
        System.out.println(direccion);
        if (direccion.exists()) {
            System.out.println("Existe");
        }else{
            System.out.println("No Existe");
        }




        try {
            if(!direccion.exists()){ // Si no existe, crea el archivo.
                direccion.createNewFile();
            }

            FileWriter fw;
            fw = new FileWriter(direccion);
            fw.write(datos);
            fw.close();
            System.out.println("INFO: Archivo escrito.");
        } catch (FileNotFoundException ex) {
            System.out.println("+------------------------------------------------------");
            System.out.println("|ERROR: Al escribir el archivo (Archivo no encontrado).");
            System.out.println("|" + ex.getMessage());
            System.out.println("+------------------------------------------------------");
        } catch (IOException ex) {
            System.out.println("+---------------------------------------------");
            System.out.println("|ERROR: Al escribir el archivo (En la salida).");
            System.out.println("|" + ex.getMessage());
            System.out.println("+---------------------------------------------");
        }
    }
    public String leerArchivo(String direccion) {
        String texto = "";
        String linea = "";
        try {
            FileReader fr;
            fr = new FileReader(direccion);
            BufferedReader buf = new BufferedReader(fr);
            while (linea != null) {
                linea = buf.readLine();
                if (linea != null) {
                    texto += linea + "\n";
                }
            }
            fr.close();
            System.out.println("INFO: Archivo leido.");
            System.out.println("INFO: " + texto);
            return texto;
        } catch (FileNotFoundException ex) {
            System.out.println("+--------------------------------------------------");
            System.out.println("|ERROR: Al leer el archivo (Archivo no encontrado).");
            System.out.println("|" + ex.getMessage());
            System.out.println("+--------------------------------------------------");
        } catch (IOException ex) {
            System.out.println("+------------------------------------------");
            System.out.println("|ERROR: Al leer el archivo (En la entrada).");
            System.out.println("|" + ex.getMessage());
            System.out.println("+------------------------------------------");
        }
        return texto;
    }

}
