package LockHowl.actions;

import LockHowl.cards.Interfaces.CardCheck;
import basemod.patches.com.megacrit.cardcrawl.helpers.PotionLibrary.PotionHelperGetPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AttackPotion;
import com.megacrit.cardcrawl.potions.ColorlessPotion;
import com.megacrit.cardcrawl.potions.PowerPotion;
import com.megacrit.cardcrawl.potions.SkillPotion;

import java.util.ArrayList;
import java.util.Iterator;

import static com.evacipated.cardcrawl.mod.stslib.StSLib.getMasterDeckEquivalent;

public class PotionMakerAction extends AbstractGameAction implements CardCheck {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    public static int numExhausted;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList();

    public PotionMakerAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    public PotionMakerAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber);
        this.target = target;
        this.source = source;
    }

    public PotionMakerAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(amount, isRandom, false, false);
        this.target = target;
        this.source = source;
    }

    public PotionMakerAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.target = target;
        this.source = source;
    }

    public PotionMakerAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(99, isRandom, anyNumber, canPickZero);
    }

    public PotionMakerAction(int amount, boolean canPickZero) {
        this(amount, false, false, canPickZero);
    }

    public PotionMakerAction(int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }

    public PotionMakerAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.duration = this.startDuration = duration;
    }

    @Override
    public void update() {
        Iterator var1;
        AbstractCard c;
        int i;

        if (this.duration == this.startDuration) {
            //sets up hand for selection
            var1 = this.p.hand.group.iterator();

            //Zero Hand Size Case
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }

            //One Hand Size Case
            if (this.p.hand.group.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();

                    //Action
                    cardToPot(c);

                    //Exhaust end
                    this.p.hand.moveToExhaustPile(c);
                    this.isDone = true;
                    return;
                }
            }


            if (!this.isRandom) {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            for(i = 0; i < this.amount; ++i) {
                c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);

                //Action
                cardToPot(c);

                //Exhaust
                this.p.hand.moveToExhaustPile(c);
            }

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();

                //Action
                cardToPot(c);

                //Exhaust
                this.p.hand.moveToExhaustPile(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
            return;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }

    @Override
    public void cardToPot(AbstractCard c) {
        AbstractCard.CardType TYPE = c.type;

        switch (TYPE) {
            case POWER: addToBot(new ObtainPotionAction(new PowerPotion()));
                break;
            case SKILL: addToBot(new ObtainPotionAction(new SkillPotion()));
                break;
            case ATTACK: addToBot(new ObtainPotionAction(new AttackPotion()));
                break;
            default: break;
        }
    }
}
