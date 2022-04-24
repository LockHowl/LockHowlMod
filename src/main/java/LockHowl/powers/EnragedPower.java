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

public class EnragedPower extends AbstractPower {
    public static final String POWER_ID = "Enraged";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public EnragedPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("LockHowlResources/images/powers/Knife84.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("LockHowlResources/images/powers/Knife32.png"), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();

            int RNG = AbstractDungeon.cardRandomRng.random(0, 5);

            switch (RNG) {
                case 0: addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount)));
                    break;
                case 1: addToTop(new ApplyPowerAction(this.owner, this.owner, new DoubleDamagePower(this.owner, this.amount, false)));
                    break;
                case 2: addToTop(new ApplyPowerAction(this.owner, this.owner, new DoubleTapPower(this.owner, this.amount)));
                    break;
                case 3: addToTop(new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, this.amount)));
                    break;
                case 4: addToTop(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, this.amount)));
                    break;
                case 5: addToTop(new ApplyPowerAction(this.owner, this.owner, new FlameBarrierPower(this.owner, this.amount)));
                    break;
                default: break;
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("LockHowl:Enraged");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

}
