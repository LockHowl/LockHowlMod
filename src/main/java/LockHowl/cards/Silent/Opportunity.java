package LockHowl.cards.Silent;

import LockHowl.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AddCardToHandNextTurnPower;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

import static LockHowl.DefaultMod.makeCardPath;

public class Opportunity extends AbstractDynamicCard {

    /* Uncommon, Cost 1, Skill
     * Opportunity: Gain 7(9) Block. Add a shiv to your hand next turn.
     */

    public static final String ID = "LockHowl:Opportunity";
    public static final String IMG = makeCardPath("cOpportunity.png");
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = AbstractCard.CardColor.GREEN;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 7;
    private static final int CARDS = 1;


    public Opportunity() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = CARDS;
        this.cardsToPreview = new Shiv(); //upgraded
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.upgradeMagicNumber(1);
            this.cardsToPreview.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY)));
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new AddCardToHandNextTurnPower(p, this.cardsToPreview, this.magicNumber), this.magicNumber));
    }
}
