/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impressora;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaginasPDFBOX {

    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("erro no sleep");
            }
            try {
                URL website = new URL("http://localhost:8000/print/userid");
                BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
                String linha = in.readLine();
                int idUser = 0;
                in.close();
                if (linha != null) {
                    idUser = Integer.valueOf(linha);
                    if (idUser != 0) {
                        String nomeArquivo = Integer.toString((int) (Math.random() * 999999999 + 1));

                        String caminho = "arquivos" + File.separator + idUser + nomeArquivo + ".pdf";
                        website = new URL("http://localhost:8000/down");
                        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                        FileOutputStream fos = new FileOutputStream(caminho);
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        fos.close();

                        PDDocument document = PDDocument.load(new File(caminho));
                        int paginas = document.getNumberOfPages();
                        System.out.println(paginas); // QUANTIDADE DE PAGINAS          
                        PrintService myPrintService = findPrintService("EPSON L355 Series");
                        PrinterJob job = PrinterJob.getPrinterJob();
                        job.setPageable(new PDFPageable(document));
                        job.setPrintService(myPrintService);
                        //job.print();       

                        document.close();
                        File arq = new File(caminho);
                        System.out.println(arq.getAbsolutePath());
                        arq = new File(arq.getAbsolutePath());

                        arq.delete(); // DELETA O ARQUIVO QUE FOI IMPRESSO. 

                        //enviar para o site as páginas que foram impressas. e quem foi o usuário.
                        website = new URL("http://localhost:8000/print/count/" + idUser + "+" + paginas);
                        Channels.newChannel(website.openStream());

                    }
                }
                //System.out.println(idUser); // ID DO USUÁRIO QUE TERÁ O ARQUIVO IMPRESSO.            

            } catch (IOException | NullPointerException | PrinterException e) {
                e.printStackTrace();
            }
        }

    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());
            if (printService.getName().trim().equals(printerName)) {
                System.out.println(printService.getName());
                return printService;
            }
        }
        return null;
    }
}
