package LockHowl.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class OutOfDiscard extends AbstractGameAction {

    private final AbstractCard card;

    public OutOfDiscard(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        AbstractDungeon.player.discardPile.group.remove(this.card);

        this.isDone = true;
    }
}
