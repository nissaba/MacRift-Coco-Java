/* FileReder */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;
import java.io.*;

public class FileReder {

    BufferedReader inFile;
    FileReader reader;
    String err;

    public boolean OpenFile(String sender) {
        try{
            reader = new FileReader(sender);
            inFile = new BufferedReader(reader);
        }catch(IOException e){
            err = e.getMessage();
            return false;
        }
        return true;
    }

    public int Read() {
        try{
            if(!inFile.ready())
                return -1;
            
            /*
            if(!inFile.markSupported())
                return -1;
            */
            return inFile.read();
        }catch(IOException e){return -1;}
    }

}
