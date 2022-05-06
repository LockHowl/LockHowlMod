package LockHowl.cards.Neutrals;

import LockHowl.powers.ChaosPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChaosTheory extends CustomCard {

    public static final String ID = "Metamorphosis";

    public static final String _ID = "LockHowl:Chaos";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = 2;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int TURNS = 3;
    private static final int UPGRADED_TURNS = -1;

    public ChaosTheory() {
        super(ID, NAME, new RegionName("colorless/skill/metamorphosis"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = TURNS;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADED_TURNS);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ChaosPower(p, this.magicNumber)));
    }

}
