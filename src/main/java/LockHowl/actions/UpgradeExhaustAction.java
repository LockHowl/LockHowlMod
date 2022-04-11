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

import java.util.ArrayList;
import java.util.Iterator;

import static com.evacipated.cardcrawl.mod.stslib.StSLib.getMasterDeckEquivalent;

public class UpgradeExhaustAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    public static int numExhausted;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList();

    public UpgradeExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    public UpgradeExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber);
        this.target = target;
        this.source = source;
    }

    public UpgradeExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(amount, isRandom, false, false);
        this.target = target;
        this.source = source;
    }

    public UpgradeExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.target = target;
        this.source = source;
    }

    public UpgradeExhaustAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(99, isRandom, anyNumber, canPickZero);
    }

    public UpgradeExhaustAction(int amount, boolean canPickZero) {
        this(amount, false, false, canPickZero);
    }

    public UpgradeExhaustAction(int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }

    public UpgradeExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.duration = this.startDuration = duration;
    }

    @Override
    public void update() {
        Iterator var1;
        AbstractCard c;
        int i;

        if (this.duration == this.startDuration) {
            //Zero Hand-size Case
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }

            //sets up hand for selection
            var1 = this.p.hand.group.iterator();
            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (!this.isAttack(c) || c.upgraded == true) {
                    this.cannotUpgrade.add(c);
                }
            }

            //if no cards can be upgraded, terminate process
            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();

                    if (c.canUpgrade() && isAttack(c)) {

                        c.upgrade();
                        if(getMasterDeckEquivalent(c) != null) {
                            getMasterDeckEquivalent(c).upgrade();
                        }
                        this.isDone = true;
                        return;

                    }

                }
            }

            this.p.hand.group.removeAll(this.cannotUpgrade);

            if (!this.isRandom) {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            for(i = 0; i < this.amount; ++i) {
                c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                if (isAttack(c)) {
                    c.upgrade();
                    if(getMasterDeckEquivalent(c) != null) {
                        getMasterDeckEquivalent(c).upgrade();
                    }
                    AbstractDungeon.player.bottledCardUpgradeCheck(c);
                    this.p.hand.moveToExhaustPile(c);
                }
            }

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                c.upgrade();
                if(getMasterDeckEquivalent(c) != null) {
                    getMasterDeckEquivalent(c).upgrade();
                }
                AbstractDungeon.player.bottledCardUpgradeCheck(c);
                this.p.hand.moveToExhaustPile(c);
            }

            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
            return;
        }

        this.tickDuration();
    }

    private void returnCards() {
        Iterator var1 = this.cannotUpgrade.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    private boolean isAttack(AbstractCard card) {
        return card.type.equals(AbstractCard.CardType.ATTACK);
    }


    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }

}