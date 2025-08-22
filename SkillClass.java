/* SkillClass */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;

public class SkillClass extends Knowledge {

    String levelStart;
    String levelBonus;
    String Requirement;
    String levelRules;

    public String getLeveRule() {
        return levelRules;
    }

    public String getRequirement() {
        return Requirement;
    }

    /*public String getRules() {
        return Rules;
    }*/

    public SkillClass setLevelRule(String str) {
       levelRules = str;
        return this;
    }

    public SkillClass setRequirement(String str) {
        Requirement = str;
        return this;
    }

    public SkillClass setRules(String str) {
        int index;
        index = str.indexOf(58);
        levelStart = str.substring(0, index);
        levelBonus = str.substring(index+1, str.length());
        return this;
    }
    
    public String getStartLevel() {
        return levelStart;
    }

    public SkillClass setStartLevel(String str) {
        levelStart = str;
        return this;
    }
    
    public String getBonusLevel() {
        return levelBonus;
    }
    
    public SkillClass setLevelBonus(String str) {
        levelBonus = str;
        return this;
    }
}
    

    
