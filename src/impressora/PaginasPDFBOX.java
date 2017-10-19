/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impressora;

import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.text.PDFTextStripper;

public class PaginasPDFBOX {
    public static void main(String[] args) {
        
        
        try {
            String caminho = "arquivos" + File.separator;           
            PDDocument document = PDDocument.load(new File(caminho+"C_PIC.pdf"));
            System.out.println(document.getNumberOfPages()); // printou a quantidade de páginas.
            
            PrintService myPrintService = findPrintService("My Windows printer Name");
            
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.setPrintService(myPrintService);
            job.print();
            
        } catch (Exception e){
            e.printStackTrace();
        }     
        
    }
    
        private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            System.out.println(printService);
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
}
