package LockHowl.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class MagneticPower extends AbstractPower {

    public static final String POWER_ID = "Magnetic";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MagneticPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("magnet");
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            //Steal Gold VFX
            AbstractCreature p = AbstractDungeon.player;
/*
            int num = this.amount;
            AbstractCreature tar = target;
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    for (int i = 0; i < num; ++i) {
                        AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb_x, p.hb_y, tar.hb_x, tar.hb_y, true));
                    }
                    isDone = true;
                }
            });
*/


            for (int i = 0; i < amount; i++) {
                addToBot(new VFXAction(p, new GainPennyEffect(target.hb.cX, target.hb.cY), 0.2F));
            }
            AbstractDungeon.player.gainGold(this.amount);
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("LockHowl:Magnetic");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

}
