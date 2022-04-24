package LockHowl.cards.Neutrals;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Flash extends CustomCard {

    public static final String ID = "Blind";

    public static final String _ID = "LockHowl:Flash";
    public static final String NAME = languagePack.getCardStrings(_ID).NAME;
    public static final String DESC = languagePack.getCardStrings(_ID).DESCRIPTION;
    public static final String[] RESPONSE = languagePack.getCardStrings(_ID).EXTENDED_DESCRIPTION;
    private static final int COST = 0;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    private static final int STUN = 1;

    public Flash() {
        super(ID, NAME, new RegionName("colorless/skill/blind"), COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = STUN;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && m.type != AbstractMonster.EnemyType.BOSS && m.type != AbstractMonster.EnemyType.ELITE && m.getIntentBaseDmg() >= 0) {
            addToBot(new StunMonsterAction(m, p, magicNumber));
        }
        else if (m.type == AbstractMonster.EnemyType.BOSS)
        {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, RESPONSE[0], true));
        }
        else if (m.type == AbstractMonster.EnemyType.ELITE)
        {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, RESPONSE[1], true));
        }
        else
        {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, RESPONSE[2], true));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = languagePack.getCardStrings(_ID).UPGRADE_DESCRIPTION;
            this.exhaust = false;
            initializeDescription();

        }
    }

}
