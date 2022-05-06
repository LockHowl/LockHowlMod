package LockHowl.cards.Neutrals;

import LockHowl.powers.VolatilePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class VolatileWeapon extends CustomCard {

    public static final String ID = "Sadistic Nature";

    public static final String _ID = "LockHowl:Volatile";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = 3;
    private static final AbstractCard.CardType TYPE = CardType.POWER;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    public VolatileWeapon() {
        super(ID, NAME, new RegionName("colorless/power/sadistic_nature"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = languagePack.getCardStrings(_ID).UPGRADE_DESCRIPTION;
            this.isInnate = true;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VolatilePower(p, 1), 1));
    }
}
