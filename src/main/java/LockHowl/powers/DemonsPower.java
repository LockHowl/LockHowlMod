package LockHowl.powers;

import LockHowl.cards.Interfaces.ReleaseDemons;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class DemonsPower extends AbstractPower implements ReleaseDemons {

    public static final String POWER_ID = "Demons";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public DemonsPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("LockHowlResources/images/powers/Knife84.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("LockHowlResources/images/powers/Knife32.png"), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = this.amount + DESCRIPTIONS[0];
    }

    @Override
    public void releaseDemons() {
        AbstractMonster randMons = AbstractDungeon.getRandomMonster();
        AbstractPlayer p = AbstractDungeon.player;

        addToBot(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
        addToBot(new DamageAction(randMons, new DamageInfo(p, this.amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("LockHowl:Demons");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
