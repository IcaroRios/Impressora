/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impressora;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 *
 * @author Neida
 */
public class Requisicao {             
        //Consegue fazer o download do arquivo   
    public static void main(String[] args) throws IOException {
    /*
        URL website = new URL("http://localhost:8000/down");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("arquivos"+File.separator+"C_PIC.pdf");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    */
        /*
        URL website = new URL("http://localhost:8000/print/userid");
        
        BufferedReader in = new BufferedReader(
        new InputStreamReader(website.openStream()));
        
        int inputLine =  Integer.valueOf(in.readLine()) ;
        System.out.println(inputLine);

        in.close();
                */
                File arq = new File("C:\\NOOBS.zip");
                System.out.println(arq.getAbsolutePath());
                arq = new File(arq.getAbsolutePath());               
                System.out.println(arq.exists());                
                System.out.println(arq.delete());
    }
    
}
