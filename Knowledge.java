/* Knowledge */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;

public class Knowledge {

    String Categorie;

    String Description;

    String Name;

    public String getCategorie() {
        return Categorie;
    }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public Knowledge setCategorie(String str) {
        Categorie = str;
        return this;
    }

    public Knowledge setDescription(String str) {
        Description = str;
        return this;
    }

    public Knowledge setName(String str) {
        Name = str;
        return this;
    }

}
