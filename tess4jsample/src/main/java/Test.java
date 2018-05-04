import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * @author wenyz
 */
public class Test {

    public static void main(String[] args) {

        String path = args[0];
        int segMode = 6;
        if(args.length == 2){
            segMode = Integer.parseInt(args[1]);
        }

        File image = new File(path);

        ITesseract tesseract = new Tesseract();
        tesseract.setLanguage("chi_sim");
        tesseract.setPageSegMode(6);
        tesseract.setPageSegMode(segMode);

        try {
           String result =  tesseract.doOCR(image);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
