package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.core.item.ArchitecturyRecordItem;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RelicSongItem extends ArchitecturyRecordItem implements LangTooltip {
    private final boolean notInsert;

    public RelicSongItem(Item.Properties properties, boolean notInsert) {
        super(0, GenerationsSounds.MELOETTAS_RELIC_SONG, properties, 30);
        this.notInsert = notInsert;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return notInsert;
    }
}
