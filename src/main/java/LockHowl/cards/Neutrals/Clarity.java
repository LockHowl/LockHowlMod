package LockHowl.cards.Neutrals;

import LockHowl.actions.CardCloneAction;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Clarity extends CustomCard {
    public static final String ID = "Enlightenment";

    public static final String _ID = "LockHowl:Clarity";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = -2;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;

    public Clarity() {
        super(ID, NAME, new RegionName("colorless/skill/enlightenment"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        FleetingField.fleeting.set(this, true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (costForTurn < 0) {
                costForTurn = 0;
            }
            upgradeBaseCost(1);
            costForTurn = 1;

            rawDescription = languagePack.getCardStrings(_ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded){
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded) {
            addToBot(new CardCloneAction(1, false, false, false));
        }
    }
}
