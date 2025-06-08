package main;

public class SkillCheck {

    GamePanel gp;
    boolean correct;

    public SkillCheck(GamePanel gp) {
        this.gp = gp;
        


    }

    public void builderSkillCheck() {

        if (!gp.player.hasBowl) {
            gp.ui.setMessage("You need a bowl to build a salad!");
            return;
        }

        correct = lettuceSkillCheck();
        if (correct) {
            classicSkillCheck();
        }
        
        return;
    }

    public void finisherSkillCheck() {
        if (!gp.player.hasBowl) {
            gp.ui.setMessage("You need a bowl to finish the salad!");
            return;
        }

        correct = premiumSkillCheck();
        if (correct) {
            dressingSkillCheck();
        }

        return;
    }



    public boolean lettuceSkillCheck() {
        boolean correct = false;
        

        return correct;
    }

    public void classicSkillCheck() {
        
    }

    public boolean premiumSkillCheck() {
        boolean correct = false;

        return correct;
    }

    public void dressingSkillCheck() {
        
    }        
}