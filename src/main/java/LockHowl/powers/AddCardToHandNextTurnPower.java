//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package LockHowl.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AddCardToHandNextTurnPower extends AbstractPower {
    public static final String POWER_ID = "Add Shiv";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private final AbstractCard card;

    public AddCardToHandNextTurnPower(AbstractCreature owner, AbstractCard card, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.card = card;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("LockHowlResources/images/powers/Knife84.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("LockHowlResources/images/powers/Knife32.png"), 0, 0, 32, 32);
        this.priority = 20;
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }

    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new MakeTempCardInHandAction(this.card.makeStatEquivalentCopy(), this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Add Shiv"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("LockHowl:AddShivNextTurn");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
