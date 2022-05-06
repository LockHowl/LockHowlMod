package LockHowl.cards.Defect;

import LockHowl.cards.AbstractDynamicCard;
import LockHowl.orbs.Acid;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static LockHowl.DefaultMod.makeCardPath;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Wounds extends AbstractDynamicCard {

    /* Common, Cost 0, Skill
     * Open Wounds: Lose 4 (2) health. Channel 1 Acid.
     */

    public static final String ID = "LockHowl:Wounds";
    public static final String IMG = makeCardPath("cWounds.png");
    private static final int COST = 0;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.BLUE;
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int HEALTH_LOSS = 2;
    private static final int UPGRADE_HEALTH_LOSS = -2;

    public Wounds() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEALTH_LOSS;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_HEALTH_LOSS);
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!this.upgraded){
            addToBot(new LoseHPAction(p, p, this.magicNumber));
        }

        addToBot(new ChannelAction(new Acid()));
    }
}
