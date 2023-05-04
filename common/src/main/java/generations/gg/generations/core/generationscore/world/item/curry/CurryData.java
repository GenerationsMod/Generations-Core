package generations.gg.generations.core.generationscore.world.item.curry;

import generations.gg.generations.core.generationscore.api.data.curry.Flavor;
import generations.gg.generations.core.generationscore.world.item.berry.BerryType;
import generations.gg.generations.core.generationscore.world.item.berry.ICurryRarity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CurryData {
    private Flavor flavor = Flavor.NONE;
    private CurryType curryType = CurryType.None;
    private int experience;
    private double healthPercentage;
    private boolean canHealStatus;
    private boolean canRestorePP;
    private int friendship;
    private CurryTasteRating rating;

    public CurryData() {
        this(CurryTasteRating.Unknown);
    }

    public CurryData(CurryTasteRating rating) {
        this.rating = rating;
        rating.configureData(this);
    }

    public CurryData(CurryType mainIngredient, List<BerryType> berries, CurryTasteRating rating) {
        this(rating);
        var flavor = BerryType.getDominantFlavor(berries.toArray(BerryType[]::new));
        int friendship = Stream.concat(berries.stream(), Stream.of(mainIngredient)).filter(Objects::nonNull).map(ICurryRarity.class::cast).mapToInt(ICurryRarity::getRarity).sum();
        if (mainIngredient == CurryType.Gigantamax) flavor = Flavor.NONE;

        this.curryType = mainIngredient;
        this.flavor = flavor;
        this.friendship = friendship;
    }

    public CurryData(CurryType mainIngredient, List<BerryType> berries) {
        this(mainIngredient, berries, CurryTasteRating.Unknown);
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public CurryType getCurryType() {
        return curryType;
    }

    public CurryTasteRating getRating() {
        return rating;
    }

    public int getExperience() {
        return experience;
    }

    public double getHealthPercentage() {
        return healthPercentage;
    }

    public boolean canRestorePP() {
        return canRestorePP;
    }

    public boolean canHealStatus() {
        return canHealStatus;
    }

    public int getFriendship() {
        return friendship;
    }

    public CurryData setFlavor(Flavor flavor) {
        this.flavor = flavor;
        return this;
    }

    public CurryData setCurryType(CurryType curryType) {
        this.curryType = curryType;
        return this;
    }

    public CurryData setRating(CurryTasteRating rating) {
        this.rating = rating;
        return this;
    }

    public CurryData setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public CurryData setHealthPercentage(double healthPercentage) {
        this.healthPercentage = healthPercentage;
        return this;
    }

    public CurryData setCanHealStatus(boolean canHealStatus) {
        this.canHealStatus = canHealStatus;
        return this;
    }

    public CurryData setCanRestorePP(boolean canRestorePP) {
        this.canRestorePP = canRestorePP;
        return this;
    }

    public CurryData setFriendship(int friendship) {
        this.friendship = friendship;
        return this;
    }

    public ResourceLocation getResourceLocation() {
        return getCurryType().getResourceLocation();
    }

    public Tag toNbt() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("flavor", flavor.ordinal());
        nbt.putInt("type", curryType.ordinal());
        nbt.putInt("rating", rating.ordinal());
        nbt.putInt("experience", experience);
        nbt.putDouble("healthPercentage", healthPercentage);
        nbt.putBoolean("canHealStatus", canHealStatus);
        nbt.putBoolean("canRestorePP", canRestorePP);
        nbt.putInt("friendship", friendship);

        return nbt;
    }

    public static CurryData fromNbt(CompoundTag nbt) {
        CurryData curry = new CurryData();
        if (nbt.isEmpty()) {
            return curry;
        }

        return curry.setFlavor(Flavor.getFlavorFromIndex(nbt.getInt("flavor")))
                .setCurryType(CurryType.getCurryTypeFromIndex(nbt.getInt("type")))
                .setRating(CurryTasteRating.fromId(nbt.getInt("rating")))
                .setExperience(nbt.getInt("experience"))
                .setHealthPercentage(nbt.getDouble("healthPercentage"))
                .setCanHealStatus(nbt.getBoolean("canHealStatus"))
                .setCanRestorePP(nbt.getBoolean("canRestorePP"))
                .setFriendship(nbt.getInt("friendship"));
    }
}
