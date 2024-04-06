package smifflepuss.smifflemod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import smifflepuss.smifflemod.registry.SmiffleModSoundEvents;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Bonified extends Monster implements GeoEntity {
    protected static final RawAnimation BONIFIED_WALK_ANIM = RawAnimation.begin().thenLoop("animation.bonified.walk");

    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

    public Bonified(EntityType<? extends Bonified> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 45.0D).add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.35D).add(Attributes.FOLLOW_RANGE, 64F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, (float) 1.1D));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D, 70));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, WitherSkeleton.class, false));
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return SmiffleModSoundEvents.ENTITY_BONIFIED_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SmiffleModSoundEvents.ENTITY_BONIFIED_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SmiffleModSoundEvents.ENTITY_BONIFIED_DEATH.get();
    }


    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source.is(DamageTypes.DROWN) || source.is(DamageTypes.LAVA) || source.is(DamageTypes.WITHER) || source.is(DamageTypeTags.IS_FIRE) || super.isInvulnerableTo(source);
    }

    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    public float getWalkTargetValue(BlockPos p_27573_, LevelReader p_27574_) {
        return p_27574_.getBlockState(p_27573_.below()).is(Blocks.SOUL_SAND) || p_27574_.getBlockState(p_27573_.below()).is(Blocks.SOUL_SOIL) ? 10.0F : super.getWalkTargetValue(p_27573_, p_27574_);
    }

    public static boolean checkBonifiedSpawnRules(EntityType<? extends Bonified> p_33018_, ServerLevelAccessor p_33019_, MobSpawnType p_33020_, BlockPos p_33021_, RandomSource p_33022_) {
        return (p_33019_.getBlockState(p_33021_.below()).is(Blocks.SOUL_SAND) || p_33019_.getBlockState(p_33021_.below()).is(Blocks.SOUL_SOIL)) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "BonifiedAnimations", 3, this::setAnimation));
    }

    protected <E extends Bonified> PlayState setAnimation(final AnimationState<E> event) {
        if (event.isMoving())
            return event.setAndContinue(BONIFIED_WALK_ANIM);
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.instanceCache;
    }
}
