package MockFormula.lib.Method;

import MockFormula.Database.DataResource;
import MockFormula.lib.MFLauncher;

import java.io.*;
import java.nio.charset.Charset;

public class Filereader {
    BufferedReader buffreader;

    public Filereader() throws Exception {
        InputStream inputStream = DataResource.class.getResourceAsStream("Defaultcountries.list");
        buffreader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String tempinputString;
        while ((tempinputString = buffreader.readLine()) != null) {
            MFLauncher.orderLists.listVector.elementAt(0).addorder(tempinputString);
        }
    }

    public void setreaderfile(String filename) {

    }
}

