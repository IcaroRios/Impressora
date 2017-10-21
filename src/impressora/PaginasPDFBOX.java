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

public class PaginasPDFBOX {

    public static void main(String[] args) {

        try {
            URL website = new URL("http://localhost:8000/print/userid");
            BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
            int idUser = Integer.valueOf(in.readLine());
            in.close();
            System.out.println(idUser); // ID DO USUÁRIO QUE TERÁ O ARQUIVO IMPRESSO.            
            if (idUser != 0) { // ele retorna 0 quando não tem nada para imprimir.
                String nomeArquivo = Integer.toString((int) (Math.random() * 999999999 + 1));

                String caminho = "arquivos" + File.separator;
                website = new URL("http://localhost:8000/down");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(caminho + nomeArquivo + ".pdf");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();

                PDDocument document = PDDocument.load(new File(caminho + nomeArquivo + ".pdf"));
                System.out.println(document.getNumberOfPages()); // QUANTIDADE DE PAGINAS          
                PrintService myPrintService = findPrintService("EPSON L355 Series");
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(document));
                job.setPrintService(myPrintService);
                job.print();

                File arq = new File(caminho + nomeArquivo + ".pdf");
                arq.delete(); // DELETA O ARQUIVO QUE FOI IMPRESSO.            
            }

        } catch (IOException | NullPointerException | PrinterException e) {
            e.printStackTrace();
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
