package LockHowl.actions;

import LockHowl.powers.RiggedPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TriggerExplosionAction extends AbstractGameAction  {

    public TriggerExplosionAction() {}

    public void update() {
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            for (AbstractPower pow : mon.powers) {
                if (pow instanceof RiggedPower) {
                    ((RiggedPower) pow).triggerExplosion();
                }
            }
        }
        this.isDone = true;
    }

}
