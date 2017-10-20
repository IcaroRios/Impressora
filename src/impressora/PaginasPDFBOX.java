/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impressora;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
public class PaginasPDFBOX {
    public static void main(String[] args) {
        
        
        try {
            String caminho = "arquivos" + File.separator;  
            URL website = new URL("http://localhost:8000/down");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());        
            FileOutputStream fos = new FileOutputStream(caminho+"Teste.pdf");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
                                 
            PDDocument document = PDDocument.load(new File(caminho+"Teste.pdf"));
            System.out.println(document.getNumberOfPages()); // printou a quantidade de páginas.            
            PrintService myPrintService = findPrintService("EPSON L355 Series");            
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.setPrintService(myPrintService);
            job.print();
            
        } catch (IOException | NullPointerException | PrinterException e){
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
