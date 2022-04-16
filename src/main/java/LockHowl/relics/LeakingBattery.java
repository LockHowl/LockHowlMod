package LockHowl.relics;

import LockHowl.DefaultMod;
import LockHowl.orbs.Acid;
import LockHowl.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static LockHowl.DefaultMod.makeRelicOutlinePath;
import static LockHowl.DefaultMod.makeRelicPath;

public class LeakingBattery extends CustomRelic {

    public static final String ID = DefaultMod.makeID("LeakingBattery");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Battery.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Battery.png"));

    public LeakingBattery() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LeakingBattery();
    }

    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Acid());
    }

}
