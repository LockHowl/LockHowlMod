package LockHowl.cards.Ironclad;

import LockHowl.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static LockHowl.DefaultMod.makeCardPath;

public class Bulwark extends AbstractDynamicCard {

    /* Uncommon, Cost 1, Skill
     * Bulwark: Gain 7(9) Block and 1(2) Plated Armour.
     */

    public static final String ID = "LockHowl:Bulwark";
    public static final String IMG = makeCardPath("cEpicBlock.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCard.CardColor.RED;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 7;
    private static final int UPGRADE_BLOCK = 2;

    private static final int PLATED_ARMOUR = 1;
    private static final int UPGRADE_PLUS_PLATED_ARMOUR = 1;

    public Bulwark() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
        magicNumber = baseMagicNumber = PLATED_ARMOUR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_PLATED_ARMOUR);
        }
    }

}
