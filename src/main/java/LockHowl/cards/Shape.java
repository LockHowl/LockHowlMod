package LockHowl.cards;

import LockHowl.actions.UpgradeExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static LockHowl.DefaultMod.makeCardPath;

public class Shape extends AbstractDynamicCard {

    /* Rare, Cost 2 (1), Skill
     * Shape: Permanently Upgrade an Attack and exhaust it. Exhaust.
     */

    public static final String ID = "LockHowl:Shape";
    public static final String IMG = makeCardPath("cHammerSmash.png");
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;


    public Shape() {
        super(ID, IMG, COST, TYPE, AbstractCard.CardColor.RED, RARITY, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UpgradeExhaustAction(1, false));
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    public AbstractCard makeCopy() {
        return new Shape();
    }
}
