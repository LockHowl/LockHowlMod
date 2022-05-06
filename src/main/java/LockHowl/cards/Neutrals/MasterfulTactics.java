package LockHowl.cards.Neutrals;

import LockHowl.actions.DrawPileToTopAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class MasterfulTactics extends CustomCard {
    public static final String ID = "Master of Strategy";

    public static final String _ID = "LockHowl:MasterfulTactics";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = 0;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;

    private static final int NUM = 2;
    private static final int UPGRADED_NUM = 1;

    public MasterfulTactics() {
        super(ID, NAME, new RegionName("colorless/skill/master_of_strategy"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = NUM;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADED_NUM);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawPileToTopAction(magicNumber, true));
    }

}
