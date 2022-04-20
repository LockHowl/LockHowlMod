package LockHowl.cards.Ironclad;

import LockHowl.actions.ModifyMagicNumberAction;
import LockHowl.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static LockHowl.DefaultMod.makeCardPath;

public class Horn extends AbstractDynamicCard {

    /* Uncommon, Cost 1, Skill
     * Horn: Gain 1 (2) STR. This card's STR increases by 1 this combat.
     */

    public static final String ID = "LockHowl:Horn";
    public static final String IMG = makeCardPath("cHorn.png");
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = AbstractCard.CardColor.RED;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int POWER = 1;
    private static final int UPGRADE_PLUS_POWER = 1;

    public Horn() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POWER;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_POWER);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ModifyMagicNumberAction(this.uuid, 1));
    }
}
