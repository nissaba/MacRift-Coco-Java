/* KnowledgeHandler */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;
import java.util.*;
import java.lang.*;

public class KnowledgeHandler {

    Enumeration SkillKeys;
    Enumeration CategoriesKeys;
    Hashtable SkillList;
    Hashtable SkillCategorieTable;
    SkillFileReader SkillReader;
    //SkillFileReader SkillWriter;
    
    public KnowledgeHandler() {
        SkillReader = new SkillFileReader();
        
        SkillCategorieTable = SkillReader.makeSkillList();
        //if(SkillCategorieTable != null)
            //CategoriesKeys = SkillCategorieTable.keys();
        
        SkillReader = null;
    }
    
    public Enumeration getSkillCategories() {
        return SkillCategorieTable.keys();
    }
    
    public int numberOfSkills(String cat){
        SkillList = (Hashtable) SkillCategorieTable.get(cat);
        return SkillList.size();
    }
    
    public Enumeration getSkillList(String cat){
        SkillList = (Hashtable) SkillCategorieTable.get(cat);
        return SkillList.keys();
    }
    
    public SkillClass getSkill(String cat, String Skill){
        SkillList = (Hashtable) SkillCategorieTable.get(cat);
        return (SkillClass) SkillList.get(Skill);
    }

}
