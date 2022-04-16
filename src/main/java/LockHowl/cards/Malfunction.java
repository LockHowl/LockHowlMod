package LockHowl.cards;

import LockHowl.orbs.Acid;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static LockHowl.DefaultMod.makeCardPath;

public class Malfunction extends AbstractDynamicCard{

    /* Uncommon, Cost 2, Skill
     * Malfunction: Evoke all Orbs. Channel 1 Acid for each.
     */

    public static final String ID = "LockHowl:Malfunction";
    public static final String IMG = makeCardPath("cMalfunction.png");
    private static final int COST = 2;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.BLUE;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public Malfunction() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int orbCount = p.filledOrbCount();

        for (int i = 0; i < orbCount; i++) {
            addToBot(new AnimateOrbAction(1));
            addToBot(new EvokeOrbAction(1));
        }

        for (int i = 0; i < orbCount; i++) {
            addToBot(new ChannelAction(new Acid()));
        }
    }
}
