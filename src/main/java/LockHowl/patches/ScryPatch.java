package LockHowl.patches;

import LockHowl.cards.Interfaces.WhenScriedUse;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

@SpirePatch(
        clz = ScryAction.class,
        method = "update"
)
public class ScryPatch {

    @SpireInsertPatch(locator = LocatorClear.class, localvars={"c"})
    public static void InsertCallback(AbstractCard c) {

        /*
        if ( c.hasTag(CustomCardTags.Burden)) {

            AbstractDungeon.player.drawPile.group.remove(c);
            AbstractDungeon.player.limbo.group.add(c);

            c.applyPowers();

            AbstractDungeon.actionManager.addToTop(new OutOfDiscard(c));
            AbstractDungeon.actionManager.addToTop(new NewQueueCardAction(c, AbstractDungeon.getRandomMonster(), false, true));
            AbstractDungeon.actionManager.addToTop(new UnlimboAction(c));
        }
        */

        if(c instanceof WhenScriedUse)
            ((WhenScriedUse) c).whenScried(AbstractDungeon.player);
    }
    private static class LocatorClear extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDiscardPile");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }


}
