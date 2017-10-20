/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impressora;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
            URL website = new URL("http://localhost:8000/down");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());            
            FileOutputStream fos = new FileOutputStream("arquivos"+File.separator+"C_PIC.pdf");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }
    
}
