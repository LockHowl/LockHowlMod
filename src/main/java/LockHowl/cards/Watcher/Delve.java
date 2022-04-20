package LockHowl.cards.Watcher;

import LockHowl.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static LockHowl.DefaultMod.makeCardPath;

public class Delve extends AbstractDynamicCard {

    public static final String ID = "LockHowl:Delve";
    public static final String IMG = makeCardPath("cDestiny.png");
    private static final int COST = 0;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.PURPLE;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int SCRY = 1;
    private static final int UPGRADE_SCRY = 1;

    public Delve() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = SCRY;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_SCRY);
        }
    }

    public void onRetained() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScryAction(this.magicNumber));

        if(this.upgraded){
            magicNumber = 2; baseMagicNumber = 2;
        }
        else{
            magicNumber = 1; baseMagicNumber = 1;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Delve();
    }

}
