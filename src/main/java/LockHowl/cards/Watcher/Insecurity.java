package LockHowl.cards.Watcher;

import LockHowl.cards.AbstractDynamicCard;
import LockHowl.cards.Interfaces.WhenScriedUse;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static LockHowl.DefaultMod.makeCardPath;

public class Insecurity extends AbstractDynamicCard implements WhenScriedUse {

    public static final String ID = "LockHowl:Insecurity";
    public static final String IMG = makeCardPath("cInsecurity.png");
    private static final int COST = -2;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.PURPLE;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 9;
    private static final int UPGRADE_BLOCK = 3;

    public Insecurity() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
    }

    @Override
    public void whenScried(AbstractPlayer p) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_BLOCK);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Insecurity();
    }

}
