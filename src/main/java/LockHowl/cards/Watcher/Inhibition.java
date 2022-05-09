package LockHowl.cards.Watcher;

import LockHowl.cards.AbstractDynamicCard;
import LockHowl.cards.Interfaces.WhenScriedUse;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static LockHowl.DefaultMod.makeCardPath;

public class Inhibition extends AbstractDynamicCard implements WhenScriedUse {

    public static final String ID = "LockHowl:Inhibition";
    public static final String IMG = makeCardPath("cInhibition.png");
    private static final int COST = -2;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.PURPLE;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int ENERGY = 1;
    private static final int UPGRADE_ENERGY = 1;

    public Inhibition() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = ENERGY;
    }

    @Override
    public void whenScried(AbstractPlayer p) {
        addToBot(new GainEnergyAction(this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_ENERGY);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Inhibition();
    }

}
