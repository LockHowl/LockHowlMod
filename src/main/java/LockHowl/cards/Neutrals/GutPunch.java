package LockHowl.cards.Neutrals;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class GutPunch extends CustomCard {

    public static final String ID = "Swift Strike";

    public static final String _ID = "LockHowl:GutPunch";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 6;
    private static final int UPGRADED_DAMAGE = 2;

    private static final int STRSAP = 1;
    private static final int UPGRADED_STRSAP = 1;

    public GutPunch() {
        super(ID, NAME, new RegionName("colorless/attack/swift_strike"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = STRSAP;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPGRADED_STRSAP);
            upgradeDamage(UPGRADED_DAMAGE);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage)));

        if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() > 0) {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber)));
        }
    }

}
