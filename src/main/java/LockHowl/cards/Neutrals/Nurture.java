package LockHowl.cards.Neutrals;

import LockHowl.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static LockHowl.DefaultMod.makeCardPath;

public class Nurture extends AbstractDynamicCard {

    /* Uncommon, Cost 1, Skill
     * Replenish: Discard 1 card. Next turn, Draw 2 Cards and gain 1(2) E.
     */

    public static final String ID = "LockHowl:Nurture";
    public static final String IMG = makeCardPath("cFlower.png");
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;

    private static final int DRAW = 2;
    private static final int ENERGY = 1;
    private static final int UPGRADE_ENERGY = 1;

    public Nurture() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = ENERGY;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber = DRAW;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_ENERGY);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, 1, false));
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.defaultBaseSecondMagicNumber), this.defaultBaseSecondMagicNumber));
    }
}
