/* KnowledgeEnginClass */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;

public class KnowledgeEnginClass {

    String Categorie;

    String Description;

    String Name;

    Knowledge knowledge;

   public KnowledgeEnginClass(Knowledge sender){
        knowledge = sender;
    }
    
    public void Cancel(Object sender) {
    }

    public void Save(Object sender) {
        knowledge.setName(Name).setCategorie(Categorie).setDescription(Description);
    }

    public void Update(Object sender) {
        
    }

}
