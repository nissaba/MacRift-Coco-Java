/* SkillWindowHandler */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;
import java.util.*;

public class SkillWindowHandler {

   //atrribute bonus tab
    NSButton 		IQBtn;
    NSButton 		MABtn;
    NSButton 		MEBtn;
    NSButton 		PBBtn;
    NSButton 		PEBtn;
    NSButton 		PPBtn;
    NSButton 		PSBtn;
    NSButton 		SPDBtn;
    NSTextField 	diceBonusField;
    NSTextField 	diceMultiplierField;
    NSTextField	 	nbrDiceFaceField;
    NSTextField 	nbrDiceField;
    NSTextField 	StaticBonusField;
    NSTextField 	Lable1Stat;
    NSTextField 	Lable2Stat;
    NSButton 		FormulatedBonusRadioBtn;
    NSButton 		StaticBonusRadiodBtn;
    
    //
    NSTextField 	Categorie;
    NSTextField 	levelBonusField;
    NSTextField 	levelStartField;
    NSTableView 	SkillList;
    NSTableView 	requiredList;
    NSTableView 	Categories;
    NSTableView		requiresCategoriesList;
    NSTableView		requiresSkillList;
    SkillClass 		CurSkill;
    NSTextView 		Description;
    KnowledgeHandler 	KnowledAccess;
    NSTextField 	Name;
    NSButton 		addRequiredSkill;
    NSButton		removeRequiredSkill;
    
    NSMutableArray 	SkillKeys;
    NSMutableArray 	CategoriesKeys;
    NSMutableArray	requiredKeys;
    NSMutableArray	requiresSkillKeys;
    
    public SkillWindowHandler(){
        requiredKeys = new NSMutableArray();
        CategoriesKeys = new NSMutableArray();
        requiresSkillKeys = new NSMutableArray();
    }
    
    public void NewSkill(Object sender) {
    }

    public void OKButton(Object sender) {
    }

    public void populateCategories() {
        Enumeration e;
        e = KnowledAccess.getSkillCategories();
    
        while(e.hasMoreElements())
        {
            CategoriesKeys.addObject(e.nextElement());
        }
       // NSSelector mySelector = new NSSelector("caseInsensitiveCompare", new Class[] {String.class} );
       //CategoriesKeys.sortUsingSelector(mySelector);            
       Categories.reloadData();
    }

    public void populateSkillInfo(Object sender) {
        String str;
        int skillIndex = SkillList.selectedRow();
        int categoriesIndex = Categories.selectedRow();
        
        CurSkill = KnowledAccess.getSkill((String) CategoriesKeys.objectAtIndex(categoriesIndex), 
            (String) SkillKeys.objectAtIndex(skillIndex));
        Name.setStringValue(CurSkill.getName());
        Categorie.setStringValue(CurSkill.getCategorie());
        Description.setString(CurSkill.getDescription());
        levelStartField.setStringValue(CurSkill.getStartLevel());
        levelBonusField.setStringValue(CurSkill.getBonusLevel());
        
        requiredKeys.removeAllObjects();
        str = CurSkill.getRequirement();
        int index1 = 0, index2 = str.indexOf(44);
        boolean stop = false;
        
        while(!stop)
        {
            if (index2 == -1)
            {
                requiredKeys.addObject(str.substring(index1, str.length()));
                stop = true;
            }else
            {
                requiredKeys.addObject(str.substring(index1, index2));
                index1 = index2 +1;
                index2 = str.indexOf(44, index1 +1);
            }
        }
        requiresCategoriesList.reloadData();
        requiresSkillList.reloadData();
        requiredList.reloadData();
    }

    public void populateSkillList(Object sender) {
        Enumeration e;
        //get list of skill from the knowlegeController 
        int indexRow = Categories.selectedRow();
        e =  KnowledAccess.getSkillList((String) CategoriesKeys.objectAtIndex(indexRow));
        SkillKeys = new NSMutableArray();
        //send the list of keys in the coresponding categorie list.
        while(e.hasMoreElements())
        {
            SkillKeys.addObject(e.nextElement());
        }
        //NSSelector mySelector = new NSSelector("compareToIgnoreCase()", new Class[] {String.class} );
        //SkillKeys.sortUsingSelector(mySelector);
        SkillList.reloadData();
    }

    public void Revert(Object sender) {
    }

    public void saveChange(Object sender) {
    }
    
    /*public int browserNumberOfRowsInColumn(NSBrowser Sender, int column) {
         if(Categories.indexOfSelectedItem() == -1)
            return 0;
        //get the number of item
        String cat = (String) Categories.objectValueOfSelectedItem();
        return KnowledAccess.numberOfSkills(cat);
    }*/
    
    public int numberOfRowsInTableView(NSTableView theTable)
    {
        
        
        String str = (String)theTable.autosaveName();
        if(str.compareTo("categories") == 0) {
            if (CategoriesKeys == null) return 0;
            return CategoriesKeys.count();
        }else if (str.compareTo("skills") == 0)
        {
            if (SkillKeys == null) return 0; 
            return SkillKeys.count();
        }else if (str.compareTo("requiredList") == 0)
        {
            if (requiredKeys == null) return 0;
            return requiredKeys.count();
        }if (str.compareTo("requiresSkillList") == 0)
        {
            if (requiresSkillKeys == null) return 0;
            return requiresSkillKeys.count();
        }
        return 0;
    }
    
    public Object tableViewObjectValueForLocation(NSTableView aTable, NSTableColumn aTableColumn, int rowIndex)
    {
    
        String str = (String) aTableColumn.identifier();
        if (str.compareTo("categories") == 0) 
        { 
            if (CategoriesKeys == null) return null;
            return CategoriesKeys.objectAtIndex(rowIndex);
        }else if (str.compareTo("skills") == 0)
        {
            if (SkillKeys == null) return null;
            return SkillKeys.objectAtIndex(rowIndex);
        }else if (str.compareTo("requiredList") == 0)
        {
            if (requiredKeys == null) return null;
            return requiredKeys.objectAtIndex(rowIndex); 
        }else if (str.compareTo("requiresSkillList") == 0)
        {
            if (requiresSkillKeys == null) return null;
            return requiresSkillKeys.objectAtIndex(rowIndex); 
        }return null;
    }
    
    public void awakeFromNib(){
        Categories.setAutosaveName("categories");
        SkillList.setAutosaveName("skills");
        requiresCategoriesList.setAutosaveName("categories");
        requiresSkillList.setAutosaveName("requiresSkillList");
        requiredList.setAutosaveName("requiredList");
        
        requiredList.setDataSource(this);
        requiredList.sizeLastColumnToFit();
        requiresSkillList.setDataSource(this);
        requiresSkillList.sizeLastColumnToFit();
        requiresCategoriesList.setDataSource(this);
        requiresCategoriesList.sizeLastColumnToFit();
        Categories.setDataSource(this);
        Categories.sizeLastColumnToFit();
        SkillList.setDataSource(this);
        SkillList.sizeLastColumnToFit();
    }
    
    public void addRequired(Object sender)
    {
        int index = requiresSkillList.selectedRow();
        if (index  == -1) return;
        String str = (String) requiresSkillKeys.objectAtIndex(index);
        requiredKeys.addObject(str);
        requiresSkillKeys.removeObjectAtIndex(index);
        requiresSkillList.reloadData();
        requiredList.reloadData();
    }
    
    public void removeRequired(Object sender)
    {
        if (requiredList.selectedRow()  == -1) return;
        requiredKeys.removeObjectAtIndex(requiredList.selectedRow());
        populateRequiredSkillList(null);
        requiresSkillList.reloadData();
        requiredList.reloadData();
    }
    
    public void populateRequiredSkillList(Object sender)
    {
        Enumeration e;
        String str;
        
        //get list of skill from the knowlegeController 
        int indexRow = requiresCategoriesList.selectedRow();
        e =  KnowledAccess.getSkillList((String) CategoriesKeys.objectAtIndex(indexRow));
        requiresSkillKeys.removeAllObjects();
        
        //send the list of keys in the coresponding categorie list.
        boolean found;
        while(e.hasMoreElements())
        {
            found = false;
            str = (String) e.nextElement();
            int test = requiredKeys.count();
            for (int i = 0; i < test ; i++){
                if (str.compareTo((String) requiredKeys.objectAtIndex(i)) == 0){
                    found = true;
                    break;
                }
            }
            
            if (!found){
                requiresSkillKeys.addObject(str);
            }
        }
        
        //NSSelector mySelector = new NSSelector("compareToIgnoreCase()", new Class[] {String.class} );
        //SkillKeys.sortUsingSelector(mySelector);
        requiresSkillList.reloadData();
    }
    
    public void attribBonusSelection(Object sender)
    {
    
    }
}
