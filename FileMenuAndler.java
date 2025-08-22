/* FileMenuAndler */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;

public class FileMenuAndler {

    NSWindow PCWindow;

    NSWindow PsyWindow;

    NSWindow SkillWindow;

    NSWindow SpellWindow;
    
    SkillWindowHandler SkillWindowH;

    public void OpenPCWindow(Object sender) {
        PCWindow.makeKeyAndOrderFront(sender);
    }

    public void OpenPsyWindow(Object sender) {
        PsyWindow.makeKeyAndOrderFront(sender);
    }

    public void OpenSkillWindow(Object sender) {
        SkillWindow.makeKeyAndOrderFront(sender);
        SkillWindowH.populateCategories();
    }

    public void OpenSpellWindow(Object sender) {
        SpellWindow.makeKeyAndOrderFront(sender);
    }

}
