package LockHowl.cards.Neutrals;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Tapestry extends CustomCard {
    public static final String ID = "Jack Of All Trades";

    public static final String _ID = "LockHowl:Tapestry";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;

    private static final int NUM = 2;
    private static final int UPGRADED_NUM = 1;

    public Tapestry() {
        super(ID, NAME, new RegionName("colorless/skill/jack_of_all_trades"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
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
        AbstractCard c;
        for (int i = 0; i < magicNumber; i++) {
            c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
            c.setCostForTurn(0);
            this.addToBot(new MakeTempCardInHandAction(c, true));
        }
    }

}
