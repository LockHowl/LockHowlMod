package LockHowl.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

public class SkinPower extends AbstractPower {

    public static final String POWER_ID = "Skin";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public SkinPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("panache");
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {
            this.flash();
            int RNG = AbstractDungeon.cardRandomRng.random(0, 4);

            switch (RNG) {
                case 0: addToTop(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount)));
                    break;
                case 1: addToTop(new ApplyPowerAction(this.owner, this.owner, new EnergizedPower(this.owner, this.amount)));
                    break;
                case 2: addToTop(new ApplyPowerAction(this.owner, this.owner, new DrawCardNextTurnPower(this.owner, this.amount)));
                    break;
                case 3: addToTop(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, this.amount)));
                    break;
                case 4: addToTop(new ApplyPowerAction(this.owner, this.owner, new MantraPower(this.owner, this.amount*2)));
                    break;
                default: break;
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("LockHowl:Skin");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }


}
