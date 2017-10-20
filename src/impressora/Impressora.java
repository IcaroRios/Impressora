
package impressora;

import java.io.IOException;
import java.io.FileInputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;


/**
 *
 * @author Neida
 */
public class Impressora {
    
    /**
     
    public static void main(String[] args) throws PrintException {
        try {
            FileInputStream fis = new FileInputStream("C:/Users/Neida/Desktop/Teste.pdf");
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc pdfDoc = new SimpleDoc(fis, flavor, null);            
            DocPrintJob printJob = PrintServiceLookup.lookupDefaultPrintService().createPrintJob();
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
            fis.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    
    //só imprime os criptografados
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("C:/Users/Neida/Desktop/Teste.pdf");
        
        Doc doc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        try {            
            
            service.createPrintJob().print(doc, null);            
        }catch (PrintException e) {
            e.printStackTrace();
        }
    }
    


}
