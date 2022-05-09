package LockHowl.cards.Neutrals;

import LockHowl.actions.AlchemistsCircleAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class AlchemistsCircle extends CustomCard {
    public static final String ID = "Transmutation";

    public static final String _ID = "LockHowl:Alchemy";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = -1;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int GOLD = 6;
    private static final int UPGRADED_GOLD = 3;

    public AlchemistsCircle() {
        super(ID, NAME, new RegionName("colorless/skill/transmutation"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = GOLD;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADED_GOLD);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AlchemistsCircleAction(p, magicNumber, energyOnUse));
    }


}
