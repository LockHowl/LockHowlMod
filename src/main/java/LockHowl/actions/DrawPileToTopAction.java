package LockHowl.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class DrawPileToTopAction extends AbstractGameAction{
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;

    public DrawPileToTopAction(int numberOfCards, boolean optional) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;// 21
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;// 22
        this.player = AbstractDungeon.player;// 23
        this.numberOfCards = numberOfCards;// 24
        this.optional = optional;// 25
    }// 26

    public DrawPileToTopAction(int numberOfCards) {
        this(numberOfCards, false);// 29
    }// 30

    public void update() {
        if (this.duration == this.startDuration) {// 33
            if (!this.player.drawPile.isEmpty() && this.numberOfCards > 0) {// 34
                AbstractCard c;
                Iterator var6;
                if (this.player.drawPile.size() <= this.numberOfCards && !this.optional) {// 37
                    ArrayList<AbstractCard> cardsToMove = new ArrayList();// 38
                    var6 = this.player.drawPile.group.iterator();// 39

                    while(var6.hasNext()) {
                        c = (AbstractCard)var6.next();
                        cardsToMove.add(c);// 40
                    }

                    var6 = cardsToMove.iterator();// 42

                    while(var6.hasNext()) {
                        c = (AbstractCard)var6.next();
                        //this.player.drawPile.moveToHand(c, this.player.drawPile);// 47
                        this.player.drawPile.moveToDeck(c, false);
                    }

                    this.isDone = true;// 50
                } else {
                    CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);// 53
                    var6 = this.player.drawPile.group.iterator();// 54

                    while(var6.hasNext()) {
                        c = (AbstractCard)var6.next();
                        temp.addToTop(c);// 55
                    }

                    temp.sortAlphabetically(true);// 57
                    temp.sortByRarityPlusStatusCardType(false);// 58
                    if (this.numberOfCards == 1) {// 59
                        if (this.optional) {// 60
                            AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, TEXT[0]);// 61
                        } else {
                            AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[0], false);// 63
                        }
                    } else if (this.optional) {// 66
                        AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);// 67
                    } else {
                        AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);// 73
                    }

                    this.tickDuration();// 80
                }
            } else {
                this.isDone = true;// 35
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {// 84
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();// 85

                while(var1.hasNext()) {
                    AbstractCard c = (AbstractCard)var1.next();
                    //this.player.drawPile.moveToHand(c, this.player.drawPile);// 90
                    this.player.drawPile.moveToDeck(c, false);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();// 93
                AbstractDungeon.player.hand.refreshHandLayout();// 94
            }

            this.tickDuration();// 96
        }
    }// 36 51 81 97

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;// 15
    }

}
