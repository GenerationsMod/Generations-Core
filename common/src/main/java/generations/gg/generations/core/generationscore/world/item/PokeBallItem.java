package generations.gg.generations.core.generationscore.world.item;

//import com.pokemod.pokemod.registries.types.PokeBall;
//import com.pokemod.pokemod.world.entity.PokeBallEntity;
//import com.pokemod.pokemod.world.sound.PokeModSounds;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResultHolder;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.registries.RegistryObject;
//import org.jetbrains.annotations.NotNull;
//
//public class PokeBallItem extends Item {
//
//    private final RegistryObject<PokeBall> pokeBall;
//
//    public PokeBallItem(RegistryObject<PokeBall> pokeBall) {
//        super(new Properties());
//        this.pokeBall = pokeBall;
//    }
//
//    @Override
//    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
//        var itemStack = player.getItemInHand(usedHand);
//        level.playSound(null, player.getX(), player.getY(), player.getZ(), PokeModSounds.SEND_OUT.get(), SoundSource.NEUTRAL, 0.5f, 1f);
//
//        if (!level.isClientSide()) {
//            var pokeballEntity = new PokeBallEntity(itemStack.getItem(), player, new PokeBallEntity.CatchingPixelmon());
//            pokeballEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
//            level.addFreshEntity(pokeballEntity);
//        }
//
//        player.awardStat(Stats.ITEM_USED.get(this));
//        if (!player.getAbilities().instabuild) {
//            itemStack.shrink(1);
//        }
//
//        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
//    }
//
//    public RegistryObject<PokeBall> getPokeBall() {
//        return pokeBall;
//    }
//}
