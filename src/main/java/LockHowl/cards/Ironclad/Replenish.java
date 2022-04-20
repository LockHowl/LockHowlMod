package LockHowl.cards.Ironclad;

import LockHowl.cards.AbstractDynamicCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static LockHowl.DefaultMod.makeCardPath;

public class Replenish extends AbstractDynamicCard {

    /* Uncommon, Cost 1, Attack
     * Replenish: Deal 8 damage X times where X is times you lost health this turn. (X times).
     */

    public static final String ID = "LockHowl:Replenish";
    public static final String IMG = makeCardPath("cHunger.png");
    private static final int COST = 1;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = AbstractCard.CardColor.RED;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 11;
    private static final int UPGRADE_DAMAGE = 3;
    private static int LOST_HP = 0;
    private static final int HEALING = 1;
    private static final int UPGRADE_HEALING = 1;

    public Replenish() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = HEALING;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber = LOST_HP;
    }

    public void tookDamage() {
        this.defaultSecondMagicNumber++;
        this.defaultBaseSecondMagicNumber++;
    }

    public void atTurnStart() { this.defaultSecondMagicNumber = 0; this.defaultBaseSecondMagicNumber = 0;}

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE);
            this.upgradeMagicNumber(UPGRADE_HEALING);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.defaultBaseSecondMagicNumber; i++) {
            this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.RED.cpy()), 0.1F));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new HealAction(p, p, this.magicNumber));
        }
    }
}
