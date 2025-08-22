/* PCHandlerClass */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;
import java.lang.*;
import java.util.*;

public class PCHandlerClass {

    NSWindow PCWizWindow;

    NSProgressIndicator progress;
    
    NSTextField progressText;

    int tempInt;

    public void OpenPCWizWindow(Object sender) {
        PCWizWindow.makeKeyAndOrderFront(sender);
    }

    public void TestNext(Object sender) {
        if(tempInt < 100){
            tempInt = tempInt + 10;
            String temp = tempInt + " %";
            progress.incrementBy(10);
            progressText.setAttributedStringValue( new NSAttributedString(temp));
            progressText.setNeedsDisplay(true);
        }
    }


}
