package LockHowl.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

import java.util.Random;

public class VolatilePower extends AbstractPower {

    public static final String POWER_ID = "Volatile";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public VolatilePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("sadistic");
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();

            int RNG = AbstractDungeon.cardRandomRng.random(0, 4);

            switch (RNG) {
                case 0: addToTop(new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, this.amount)));;
                    break;
                case 1: addToTop(new ApplyPowerAction(target, this.owner, new StrengthPower(target, -this.amount)));
                    break;
                case 2: addToTop(new ApplyPowerAction(target, this.owner, new VulnerablePower(target, this.amount, false)));
                    break;
                case 3: addToTop(new ApplyPowerAction(target, this.owner, new WeakPower(target, this.amount, false)));
                    break;
                case 4: addToTop(new ApplyPowerAction(target, this.owner, new ChokePower(target, this.amount)));
                    break;
                default: break;
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("LockHowl:Volatile");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

}
