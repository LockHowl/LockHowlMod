package LockHowl.cards.Silent;

import LockHowl.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;


import static LockHowl.DefaultMod.makeCardPath;

public class Singe extends AbstractDynamicCard {

    /*
     * Singe: Apply 3 (5) Poison. Deal damage equal to poison.
     */

    public static final String ID = "LockHowl:Singe";
    public static final String IMG = makeCardPath("Attack.png");
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    private static final int POISON = 3;
    private static final int UPGRADE_PLUS_POISON = 2;

    public Singe() {
        super(ID, IMG, COST, TYPE, AbstractCard.CardColor.GREEN, RARITY, TARGET);

        magicNumber = baseMagicNumber = POISON;
    }

    //Action
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber));

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPower po = m.getPower(PoisonPower.POWER_ID);
                if (po != null) {
                    int poisonAmount = po.amount;
                    this.addToTop(new LoseHPAction(m, p, poisonAmount));
                }
                isDone = true;
            }
        });

    }
        @Override
        public AbstractCard makeCopy() {
            return new Singe();
        }

        public void upgrade() {
            if (!this.upgraded) {
                this.upgradeName();
                this.upgradeMagicNumber(UPGRADE_PLUS_POISON);
            }
        }

}
