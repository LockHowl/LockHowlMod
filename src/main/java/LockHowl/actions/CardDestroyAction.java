package LockHowl.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static com.evacipated.cardcrawl.mod.stslib.StSLib.getMasterDeckEquivalent;

public class CardDestroyAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    public static int numExhausted;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList();

    public CardDestroyAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public CardDestroyAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber);
        this.target = target;
        this.source = source;
    }

    public CardDestroyAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(amount, isRandom, false, false);
        this.target = target;
        this.source = source;
    }

    public CardDestroyAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.target = target;
        this.source = source;
    }

    public CardDestroyAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(99, isRandom, anyNumber, canPickZero);
    }

    public CardDestroyAction(int amount, boolean canPickZero) {
        this(amount, false, false, canPickZero);
    }

    public CardDestroyAction(int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }

    public CardDestroyAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
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
                    if(getMasterDeckEquivalent(c) != null) {
                        AbstractDungeon.player.masterDeck.removeCard(getMasterDeckEquivalent(c));
                    }
                    AbstractDungeon.player.hand.removeCard(c);

                    //End
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
                if(getMasterDeckEquivalent(c) != null) {
                    AbstractDungeon.player.masterDeck.removeCard(getMasterDeckEquivalent(c));
                }
                AbstractDungeon.player.hand.removeCard(c);
            }

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();

                //Action
                if(getMasterDeckEquivalent(c) != null) {
                    AbstractDungeon.player.masterDeck.removeCard(getMasterDeckEquivalent(c));
                }
                AbstractDungeon.player.hand.removeCard(c);

            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
            return;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DualWieldAction");
        TEXT = uiStrings.TEXT;
    }

}
