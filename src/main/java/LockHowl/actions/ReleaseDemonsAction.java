package LockHowl.actions;

import LockHowl.powers.DemonsPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReleaseDemonsAction extends AbstractGameAction {

    public ReleaseDemonsAction(AbstractPlayer p) {
        this.source = p;
    }

    public void update() {
        for (AbstractPower pow : source.powers) {
            if (pow instanceof DemonsPower) {
                ((DemonsPower) pow).releaseDemons();
            }
        }

        this.isDone = true;
    }
}