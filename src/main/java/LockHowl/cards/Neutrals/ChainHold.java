package LockHowl.cards.Neutrals;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class ChainHold extends CustomCard {

    public static final String ID = "Dark Shackles";

    public static final String _ID = "LockHowl:Chain";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    private static final int CHAIN = 3;
    private static final int UPGRADED_CHAIN = 1;

    public ChainHold() {
        super(ID, NAME, new CustomCard.RegionName("colorless/skill/dark_shackles"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = CHAIN;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADED_CHAIN);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
            addToBot(new ApplyPowerAction(p, p, new GainStrengthPower(p, this.magicNumber)));
            addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(m, this.magicNumber)));
        }
    }

}
