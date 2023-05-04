package generations.gg.generations.core.generationscore.world.level.block;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.world.level.block.decorations.PokeModDollBlock;
import com.pokemod.pokemod.world.item.PokeModItems;
import com.pokemod.pokemod.world.item.creativetab.PokeModCreativeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PokeModPokeDolls {

	public static final DeferredRegister<Block> POKEDOLLS = DeferredRegister.create(ForgeRegistries.BLOCKS, PokeMod.MOD_ID);
	/**
	 * Pokedolls
	 */
	public static final RegistryObject<Block> ARCEUS_POKEDOLL = registerBlockItem("arceus_doll", () -> new PokeModDollBlock("arceus", false));
	public static final RegistryObject<Block> ARTICUNO_POKEDOLL = registerBlockItem("articuno_doll", () -> new PokeModDollBlock("articuno", false));
	public static final RegistryObject<Block> AZELF_POKEDOLL = registerBlockItem("azelf_doll", () -> new PokeModDollBlock("azelf", false));
	public static final RegistryObject<Block> AZURILL_POKEDOLL = registerBlockItem("azurill_doll", () -> new PokeModDollBlock("azurill", false));
	public static final RegistryObject<Block> BALTOY_POKEDOLL = registerBlockItem("baltoy_doll", () -> new PokeModDollBlock("baltoy", false));
	public static final RegistryObject<Block> BLASTOISE_POKEDOLL = registerBlockItem("blastoise_doll", () -> new PokeModDollBlock("blastoise", false));
	public static final RegistryObject<Block> BLUEGHOST_POKEDOLL = registerBlockItem("blueghost_doll", () -> new PokeModDollBlock("blueghost", false));
	public static final RegistryObject<Block> CELEBI_POKEDOLL = registerBlockItem("celebi_doll", () -> new PokeModDollBlock("celebi", false, 0.15f));
	public static final RegistryObject<Block> CHARIZARD_POKEDOLL = registerBlockItem("charizard_doll", () -> new PokeModDollBlock("charizard", false));
	public static final RegistryObject<Block> CHIKORITA_POKEDOLL = registerBlockItem("chikorita_doll", () -> new PokeModDollBlock("chikorita", false));
	public static final RegistryObject<Block> CLEFAIRY_POKEDOLL = registerBlockItem("clefairy_doll", () -> new PokeModDollBlock("clefairy", false));
	public static final RegistryObject<Block> CRESSELIA_POKEDOLL = registerBlockItem("cresselia_doll", () -> new PokeModDollBlock("cresselia", false));
	public static final RegistryObject<Block> CUBONE_POKEDOLL = registerBlockItem("cubone_doll", () -> new PokeModDollBlock("cubone", false));
	public static final RegistryObject<Block> CYNDAQUIL_POKEDOLL = registerBlockItem("cyndaquil_doll", () -> new PokeModDollBlock("cyndaquil", false));
	public static final RegistryObject<Block> DARKRAI_POKEDOLL = registerBlockItem("darkrai_doll", () -> new PokeModDollBlock("darkrai", false));
	public static final RegistryObject<Block> DEOXYS_POKEDOLL = registerBlockItem("deoxys_doll", () -> new PokeModDollBlock("deoxys", false));
	public static final RegistryObject<Block> DIALGA_POKEDOLL = registerBlockItem("dialga_doll", () -> new PokeModDollBlock("dialga", false));
	public static final RegistryObject<Block> DITTO_POKEDOLL = registerBlockItem("ditto_doll", () -> new PokeModDollBlock("ditto", false));
	public static final RegistryObject<Block> DUSKULL_POKEDOLL = registerBlockItem("duskull_doll", () -> new PokeModDollBlock("duskull", false));
	public static final RegistryObject<Block> EEVEE_POKEDOLL = registerBlockItem("eevee_doll", () -> new PokeModDollBlock("eevee", false));
	public static final RegistryObject<Block> EKANS_POKEDOLL = registerBlockItem("ekans_doll", () -> new PokeModDollBlock("ekans", false));
	public static final RegistryObject<Block> ENTEI_POKEDOLL = registerBlockItem("entei_doll", () -> new PokeModDollBlock("entei", false));
	public static final RegistryObject<Block> GIRATINA_ALTERED_POKEDOLL = registerBlockItem("giratina_altered_doll", () -> new PokeModDollBlock("giratinaaltered", false));
	public static final RegistryObject<Block> GIRATINA_ORIGINAL_POKEDOLL = registerBlockItem("giratina_origin_doll", () -> new PokeModDollBlock("giratinaorigin", false));
	public static final RegistryObject<Block> GROUDON_POKEDOLL = registerBlockItem("groudon_doll", () -> new PokeModDollBlock("groudon", false));
	public static final RegistryObject<Block> GULPIN_POKEDOLL = registerBlockItem("gulpin_doll", () -> new PokeModDollBlock("gulpin", false));
	public static final RegistryObject<Block> HERACROSS_POKEDOLL = registerBlockItem("heracross_doll", () -> new PokeModDollBlock("heracross", false));
	public static final RegistryObject<Block> HOOH_POKEDOLL = registerBlockItem("hooh_doll", () -> new PokeModDollBlock("hooh", false));
	public static final RegistryObject<Block> JIGGLYPUFF_POKEDOLL = registerBlockItem("jigglypuff_doll", () -> new PokeModDollBlock("jigglypuff", false));
	public static final RegistryObject<Block> JIRACHI_POKEDOLL = registerBlockItem("jirachi_doll", () -> new PokeModDollBlock("jirachi", false));
	public static final RegistryObject<Block> KECLEON_POKEDOLL = registerBlockItem("kecleon_doll", () -> new PokeModDollBlock("kecleon", false));
	public static final RegistryObject<Block> KRABBY_POKEDOLL = registerBlockItem("krabby_doll", () -> new PokeModDollBlock("krabby", false, 0.2f));
	public static final RegistryObject<Block> KYOGRE_POKEDOLL = registerBlockItem("kyogre_doll", () -> new PokeModDollBlock("kyogre", false));
	public static final RegistryObject<Block> LAPRAS_POKEDOLL = registerBlockItem("lapras_doll", () -> new PokeModDollBlock("lapras", false));
	public static final RegistryObject<Block> LATIAS_POKEDOLL = registerBlockItem("latias_doll", () -> new PokeModDollBlock("latias", false));
	public static final RegistryObject<Block> LATIOS_POKEDOLL = registerBlockItem("latios_doll", () -> new PokeModDollBlock("latios", false));
	public static final RegistryObject<Block> LITTEN_POKEDOLL = registerBlockItem("litten_doll", () -> new PokeModDollBlock("litten", false));
	public static final RegistryObject<Block> LOTAD_POKEDOLL = registerBlockItem("lotad_doll", () -> new PokeModDollBlock("lotad", false));
	public static final RegistryObject<Block> LUGIA_POKEDOLL = registerBlockItem("lugia_doll", () -> new PokeModDollBlock("lugia", false));
	public static final RegistryObject<Block> MANAPHY_POKEDOLL = registerBlockItem("manaphy_doll", () -> new PokeModDollBlock("manaphy", false));
	public static final RegistryObject<Block> MARILL_POKEDOLL = registerBlockItem("marill_doll", () -> new PokeModDollBlock("marill", false));
	public static final RegistryObject<Block> MEOWTH_POKEDOLL = registerBlockItem("meowth_doll", () -> new PokeModDollBlock("meowth", false));
	public static final RegistryObject<Block> MESPRIT_POKEDOLL = registerBlockItem("mesprit_doll", () -> new PokeModDollBlock("mesprit", false));
	public static final RegistryObject<Block> MEW_POKEDOLL = registerBlockItem("mew_doll", () -> new PokeModDollBlock("mew", false));
	public static final RegistryObject<Block> MEWTWO_POKEDOLL = registerBlockItem("mewtwo_doll", () -> new PokeModDollBlock("mewtwo", false));
	public static final RegistryObject<Block> MOLTRES_POKEDOLL = registerBlockItem("moltres_doll", () -> new PokeModDollBlock("moltres", false));
	public static final RegistryObject<Block> MUDKIP_POKEDOLL = registerBlockItem("mudkip_doll", () -> new PokeModDollBlock("mudkip", false));
	public static final RegistryObject<Block> PALKIA_POKEDOLL = registerBlockItem("palkia_doll", () -> new PokeModDollBlock("palkia", false));
	public static final RegistryObject<Block> PICHU_POKEDOLL = registerBlockItem("pichu_doll", () -> new PokeModDollBlock("pichu", false));
	public static final RegistryObject<Block> PIKACHU_POKEDOLL = registerBlockItem("pikachu_doll", () -> new PokeModDollBlock("pikachu", false));
	public static final RegistryObject<Block> POPPLIO_POKEDOLL = registerBlockItem("popplio_doll", () -> new PokeModDollBlock("popplio", false));
	public static final RegistryObject<Block> POLIWHIRL_POKEDOLL = registerBlockItem("poliwhirl_doll", () -> new PokeModDollBlock("poliwhirl", false));
	public static final RegistryObject<Block> PURPLEGHOST_POKEDOLL = registerBlockItem("purpleghost_doll", () -> new PokeModDollBlock("purpleghost", false));
	public static final RegistryObject<Block> RAIKOU_POKEDOLL = registerBlockItem("raikou_doll", () -> new PokeModDollBlock("raikou", false));
	public static final RegistryObject<Block> RAYQUAZA_POKEDOLL = registerBlockItem("rayquaza_doll", () -> new PokeModDollBlock("rayquaza", false));
	public static final RegistryObject<Block> REGICE_POKEDOLL = registerBlockItem("regice_doll", () -> new PokeModDollBlock("regice", false));
	public static final RegistryObject<Block> REGIGIGAS_POKEDOLL = registerBlockItem("regigigas_doll", () -> new PokeModDollBlock("regigigas", false));
	public static final RegistryObject<Block> REGIROCK_POKEDOLL = registerBlockItem("regirock_doll", () -> new PokeModDollBlock("regirock", false));
	public static final RegistryObject<Block> REGISTEEL_POKEDOLL = registerBlockItem("registeel_doll", () -> new PokeModDollBlock("registeel", false));
	public static final RegistryObject<Block> RHYDON_POKEDOLL = registerBlockItem("rhydon_doll", () -> new PokeModDollBlock("rhydon", false));
	public static final RegistryObject<Block> ROWLET_POKEDOLL = registerBlockItem("rowlet_doll", () -> new PokeModDollBlock("rowlet", false));
	public static final RegistryObject<Block> SABLEYE_POKEDOLL = registerBlockItem("sableye_doll", () -> new PokeModDollBlock("sableye", false));
	public static final RegistryObject<Block> SEEDOT_POKEDOLL = registerBlockItem("seedot_doll", () -> new PokeModDollBlock("seedot", false));
	public static final RegistryObject<Block> SHAYMIN_LAND_POKEDOLL = registerBlockItem("shaymin_land_doll", () -> new PokeModDollBlock("shayminland", false));
	public static final RegistryObject<Block> SHAYMIN_SKY_POKEDOLL = registerBlockItem("shaymin_sky_doll", () -> new PokeModDollBlock("shayminsky", false));
	public static final RegistryObject<Block> SKITTY_POKEDOLL = registerBlockItem("skitty_doll", () -> new PokeModDollBlock("skitty", false));
	public static final RegistryObject<Block> SMOOCHUM_POKEDOLL = registerBlockItem("smoochum_doll", () -> new PokeModDollBlock("smoochum", false));
	public static final RegistryObject<Block> SNORLAX_POKEDOLL = registerBlockItem("snorlax_doll", () -> new PokeModDollBlock("snorlax", false));
	public static final RegistryObject<Block> SUBSTITUTE_POKEDOLL = registerBlockItem("substitute_doll", () -> new PokeModDollBlock("substitute", false));
	public static final RegistryObject<Block> SUICUNE_POKEDOLL = registerBlockItem("suicune_doll", () -> new PokeModDollBlock("suicune", false));
	public static final RegistryObject<Block> SWABLU_POKEDOLL = registerBlockItem("swablu_doll", () -> new PokeModDollBlock("swablu", false));
	public static final RegistryObject<Block> TOGEPI_POKEDOLL = registerBlockItem("togepi_doll", () -> new PokeModDollBlock("togepi", false));
	public static final RegistryObject<Block> TORCHIC_POKEDOLL = registerBlockItem("torchic_doll", () -> new PokeModDollBlock("torchic", false));
	public static final RegistryObject<Block> TOTODILE_POKEDOLL = registerBlockItem("totodile_doll", () -> new PokeModDollBlock("totodile", false));
	public static final RegistryObject<Block> TREECKO_POKEDOLL = registerBlockItem("treecko_doll", () -> new PokeModDollBlock("treecko", false));
	public static final RegistryObject<Block> UXIE_POKEDOLL = registerBlockItem("uxie_doll", () -> new PokeModDollBlock("uxie", false));
	public static final RegistryObject<Block> VENUSAUR_POKEDOLL = registerBlockItem("venusaur_doll", () -> new PokeModDollBlock("venusaur", false));
	public static final RegistryObject<Block> WAILMER_POKEDOLL = registerBlockItem("wailmer_doll", () -> new PokeModDollBlock("wailmer", false));
	public static final RegistryObject<Block> WYNAUT_POKEDOLL = registerBlockItem("wynaut_doll", () -> new PokeModDollBlock("wynaut", false));
	public static final RegistryObject<Block> ZAPDOS_POKEDOLL = registerBlockItem("zapdos_doll", () -> new PokeModDollBlock("zapdos", false));
	public static final RegistryObject<Block> ZERAORA_POKEDOLL = registerBlockItem("zeraora_doll", () -> new PokeModDollBlock("zeraora", false));

	/**
	 * Pokedolls Shiny
	 */
	public static final RegistryObject<Block> SHINY_ARCEUS_POKEDOLL = registerBlockItem("shiny_arceus_doll", () -> new PokeModDollBlock("arceus", true));
	public static final RegistryObject<Block> SHINY_ARTICUNO_POKEDOLL = registerBlockItem("shiny_articuno_doll", () -> new PokeModDollBlock("articuno", true));
	public static final RegistryObject<Block> SHINY_AZELF_POKEDOLL = registerBlockItem("shiny_azelf_doll", () -> new PokeModDollBlock("azelf", true));
	public static final RegistryObject<Block> SHINY_AZURILL_POKEDOLL = registerBlockItem("shiny_azurill_doll", () -> new PokeModDollBlock("azurill", true));
	public static final RegistryObject<Block> SHINY_BALTOY_POKEDOLL = registerBlockItem("shiny_baltoy_doll", () -> new PokeModDollBlock("baltoy", true));
	public static final RegistryObject<Block> SHINY_BLASTOISE_POKEDOLL = registerBlockItem("shiny_blastoise_doll", () -> new PokeModDollBlock("blastoise", true));
	public static final RegistryObject<Block> SHINY_CELEBI_POKEDOLL = registerBlockItem("shiny_celebi_doll", () -> new PokeModDollBlock("celebi", true, 0.15f));
	public static final RegistryObject<Block> SHINY_CHARIZARD_POKEDOLL = registerBlockItem("shiny_charizard_doll", () -> new PokeModDollBlock("charizard", true));
	public static final RegistryObject<Block> SHINY_CHIKORITA_POKEDOLL = registerBlockItem("shiny_chikorita_doll", () -> new PokeModDollBlock("chikorita", true));
	public static final RegistryObject<Block> SHINY_CLEFAIRY_POKEDOLL = registerBlockItem("shiny_clefairy_doll", () -> new PokeModDollBlock("clefairy", true));
	public static final RegistryObject<Block> SHINY_CRESSELIA_POKEDOLL = registerBlockItem("shiny_cresselia_doll", () -> new PokeModDollBlock("cresselia", true));
	public static final RegistryObject<Block> SHINY_CUBONE_POKEDOLL = registerBlockItem("shiny_cubone_doll", () -> new PokeModDollBlock("cubone", true));
	public static final RegistryObject<Block> SHINY_CYNDAQUIL_POKEDOLL = registerBlockItem("shiny_cyndaquil_doll", () -> new PokeModDollBlock("cyndaquil", true));
	public static final RegistryObject<Block> SHINY_DARKRAI_POKEDOLL = registerBlockItem("shiny_darkrai_doll", () -> new PokeModDollBlock("darkrai", true));
	public static final RegistryObject<Block> SHINY_DEOXYS_POKEDOLL = registerBlockItem("shiny_deoxys_doll", () -> new PokeModDollBlock("deoxys", true));
	public static final RegistryObject<Block> SHINY_DIALGA_POKEDOLL = registerBlockItem("shiny_dialga_doll", () -> new PokeModDollBlock("dialga", true));
	public static final RegistryObject<Block> SHINY_DITTO_POKEDOLL = registerBlockItem("shiny_ditto_doll", () -> new PokeModDollBlock("ditto", true));
	public static final RegistryObject<Block> SHINY_DUSKULL_POKEDOLL = registerBlockItem("shiny_duskull_doll", () -> new PokeModDollBlock("duskull", true));
	public static final RegistryObject<Block> SHINY_EEVEE_POKEDOLL = registerBlockItem("shiny_eevee_doll", () -> new PokeModDollBlock("eevee", true));
	public static final RegistryObject<Block> SHINY_EKANS_POKEDOLL = registerBlockItem("shiny_ekans_doll", () -> new PokeModDollBlock("ekans", true));
	public static final RegistryObject<Block> SHINY_ENTEI_POKEDOLL = registerBlockItem("shiny_entei_doll", () -> new PokeModDollBlock("entei", true));
	public static final RegistryObject<Block> SHINY_GIRATINA_ALTERED_POKEDOLL = registerBlockItem("shiny_giratina_altered_doll", () -> new PokeModDollBlock("giratinaaltered", true));
	public static final RegistryObject<Block> SHINY_GIRATINA_ORIGIN_POKEDOLL = registerBlockItem("shiny_giratina_origin_doll", () -> new PokeModDollBlock("giratinaorigin", true));
	public static final RegistryObject<Block> SHINY_GROUDON_POKEDOLL = registerBlockItem("shiny_groudon_doll", () -> new PokeModDollBlock("groudon", true));
	public static final RegistryObject<Block> SHINY_GULPIN_POKEDOLL = registerBlockItem("shiny_gulpin_doll", () -> new PokeModDollBlock("gulpin", true));
	public static final RegistryObject<Block> SHINY_HERACROSS_POKEDOLL = registerBlockItem("shiny_heracross_doll", () -> new PokeModDollBlock("heracross", true));
	public static final RegistryObject<Block> SHINY_HOOH_POKEDOLL = registerBlockItem("shiny_hooh_doll", () -> new PokeModDollBlock("hooh", true));
	public static final RegistryObject<Block> SHINY_JIGGLYPUFF_POKEDOLL = registerBlockItem("shiny_jigglypuff_doll", () -> new PokeModDollBlock("jigglypuff", true));
	public static final RegistryObject<Block> SHINY_JIRACHI_POKEDOLL = registerBlockItem("shiny_jirachi_doll", () -> new PokeModDollBlock("jirachi", true));
	public static final RegistryObject<Block> SHINY_KECLEON_POKEDOLL = registerBlockItem("shiny_kecleon_doll", () -> new PokeModDollBlock("kecleon", true));
	public static final RegistryObject<Block> SHINY_KRABBY_POKEDOLL = registerBlockItem("shiny_krabby_doll", () -> new PokeModDollBlock("krabby", true, 0.2f));
	public static final RegistryObject<Block> SHINY_KYOGRE_POKEDOLL = registerBlockItem("shiny_kyogre_doll", () -> new PokeModDollBlock("kyogre", true));
	public static final RegistryObject<Block> SHINY_LAPRAS_POKEDOLL = registerBlockItem("shiny_lapras_doll", () -> new PokeModDollBlock("lapras", true));
	public static final RegistryObject<Block> SHINY_LATIAS_POKEDOLL = registerBlockItem("shiny_latias_doll", () -> new PokeModDollBlock("latias", true));
	public static final RegistryObject<Block> SHINY_LATIOS_POKEDOLL = registerBlockItem("shiny_latios_doll", () -> new PokeModDollBlock("latios", true));
	public static final RegistryObject<Block> SHINY_LITTEN_POKEDOLL = registerBlockItem("shiny_litten_doll", () -> new PokeModDollBlock("litten", true));
	public static final RegistryObject<Block> SHINY_LOTAD_POKEDOLL = registerBlockItem("shiny_lotad_doll", () -> new PokeModDollBlock("lotad", true));
	public static final RegistryObject<Block> SHINY_LUGIA_POKEDOLL = registerBlockItem("shiny_lugia_doll", () -> new PokeModDollBlock("lugia", true));
	public static final RegistryObject<Block> SHINY_MANAPHY_POKEDOLL = registerBlockItem("shiny_manaphy_doll", () -> new PokeModDollBlock("manaphy", true));
	public static final RegistryObject<Block> SHINY_MARILL_POKEDOLL = registerBlockItem("shiny_marill_doll", () -> new PokeModDollBlock("marill", true));
	public static final RegistryObject<Block> SHINY_MEOWTH_POKEDOLL = registerBlockItem("shiny_meowth_doll", () -> new PokeModDollBlock("meowth", true));
	public static final RegistryObject<Block> SHINY_MESPRIT_POKEDOLL = registerBlockItem("shiny_mesprit_doll", () -> new PokeModDollBlock("mesprit", true));
	public static final RegistryObject<Block> SHINY_MEW_POKEDOLL = registerBlockItem("shiny_mew_doll", () -> new PokeModDollBlock("mew", true));
	public static final RegistryObject<Block> SHINY_MEWTWO_POKEDOLL = registerBlockItem("shiny_mewtwo_doll", () -> new PokeModDollBlock("mewtwo", true));
	public static final RegistryObject<Block> SHINY_MOLTRES_POKEDOLL = registerBlockItem("shiny_moltres_doll", () -> new PokeModDollBlock("moltres", true));
	public static final RegistryObject<Block> SHINY_MUDKIP_POKEDOLL = registerBlockItem("shiny_mudkip_doll", () -> new PokeModDollBlock("mudkip", true));
	public static final RegistryObject<Block> SHINY_PALKIA_POKEDOLL = registerBlockItem("shiny_palkia_doll", () -> new PokeModDollBlock("palkia", true));
	public static final RegistryObject<Block> SHINY_PICHU_POKEDOLL = registerBlockItem("shiny_pichu_doll", () -> new PokeModDollBlock("pichu", true));
	public static final RegistryObject<Block> SHINY_PIKACHU_POKEDOLL = registerBlockItem("shiny_pikachu_doll", () -> new PokeModDollBlock("pikachu", true));
	public static final RegistryObject<Block> SHINY_POPPLIO_POKEDOLL = registerBlockItem("shiny_popplio_doll", () -> new PokeModDollBlock("popplio", true));
	public static final RegistryObject<Block> SHINY_POLIWHIRL_POKEDOLL = registerBlockItem("shiny_poliwhirl_doll", () -> new PokeModDollBlock("poliwhirl", true));
	public static final RegistryObject<Block> SHINY_RAIKOU_POKEDOLL = registerBlockItem("shiny_raikou_doll", () -> new PokeModDollBlock("raikou", true));
	public static final RegistryObject<Block> SHINY_RAYQUAZA_POKEDOLL = registerBlockItem("shiny_rayquaza_doll", () -> new PokeModDollBlock("rayquaza", true));
	public static final RegistryObject<Block> SHINY_REGICE_POKEDOLL = registerBlockItem("shiny_regice_doll", () -> new PokeModDollBlock("regice", true));
	public static final RegistryObject<Block> SHINY_REGIGIGAS_POKEDOLL = registerBlockItem("shiny_regigigas_doll", () -> new PokeModDollBlock("regigigas", true));
	public static final RegistryObject<Block> SHINY_REGIROCK_POKEDOLL = registerBlockItem("shiny_regirock_doll", () -> new PokeModDollBlock("regirock", true));
	public static final RegistryObject<Block> SHINY_REGISTEEL_POKEDOLL = registerBlockItem("shiny_registeel_doll", () -> new PokeModDollBlock("registeel", true));
	public static final RegistryObject<Block> SHINY_ROWLET_POKEDOLL = registerBlockItem("shiny_rowlet_doll", () -> new PokeModDollBlock("rowlet", true));
	public static final RegistryObject<Block> SHINY_SABLEYE_POKEDOLL = registerBlockItem("shiny_sableye_doll", () -> new PokeModDollBlock("sableye", true));
	public static final RegistryObject<Block> SHINY_SEEDOT_POKEDOLL = registerBlockItem("shiny_seedot_doll", () -> new PokeModDollBlock("seedot", true));
	public static final RegistryObject<Block> SHINY_RHYDON_POKEDOLL = registerBlockItem("shiny_rhydon_doll", () -> new PokeModDollBlock("rhydon", true));
	public static final RegistryObject<Block> SHINY_SHAYMIN_LAND_POKEDOLL = registerBlockItem("shiny_shaymin_land_doll", () -> new PokeModDollBlock("shayminland", true));
	public static final RegistryObject<Block> SHINY_SHAYMIN_SKY_POKEDOLL = registerBlockItem("shiny_shaymin_sky_doll", () -> new PokeModDollBlock("shayminsky", true));
	public static final RegistryObject<Block> SHINY_SKITTY_POKEDOLL = registerBlockItem("shiny_skitty_doll", () -> new PokeModDollBlock("skitty", true));
	public static final RegistryObject<Block> SHINY_SMOOCHUM_POKEDOLL = registerBlockItem("shiny_smoochum_doll", () -> new PokeModDollBlock("smoochum", true));
	public static final RegistryObject<Block> SHINY_SNORLAX_POKEDOLL = registerBlockItem("shiny_snorlax_doll", () -> new PokeModDollBlock("snorlax", true));
	public static final RegistryObject<Block> SHINY_SUBSTITUTE_POKEDOLL = registerBlockItem("shiny_substitute_doll", () -> new PokeModDollBlock("substitute", true));
	public static final RegistryObject<Block> SHINY_SUICUNE_POKEDOLL = registerBlockItem("shiny_suicune_doll", () -> new PokeModDollBlock("suicune", true));
	public static final RegistryObject<Block> SHINY_SWABLU_POKEDOLL = registerBlockItem("shiny_swablu_doll", () -> new PokeModDollBlock("swablu", true));
	public static final RegistryObject<Block> SHINY_TOGEPI_POKEDOLL = registerBlockItem("shiny_togepi_doll", () -> new PokeModDollBlock("togepi", true));
	public static final RegistryObject<Block> SHINY_TORCHIC_POKEDOLL = registerBlockItem("shiny_torchic_doll", () -> new PokeModDollBlock("torchic", true));
	public static final RegistryObject<Block> SHINY_TOTODILE_POKEDOLL = registerBlockItem("shiny_totodile_doll", () -> new PokeModDollBlock("totodile", true));
	public static final RegistryObject<Block> SHINY_TREECKO_POKEDOLL = registerBlockItem("shiny_treecko_doll", () -> new PokeModDollBlock("treecko", true));
	public static final RegistryObject<Block> SHINY_UXIE_POKEDOLL = registerBlockItem("shiny_uxie_doll", () -> new PokeModDollBlock("uxie", true));
	public static final RegistryObject<Block> SHINY_VENUSAUR_POKEDOLL = registerBlockItem("shiny_venusaur_doll", () -> new PokeModDollBlock("venusaur", true));
	public static final RegistryObject<Block> SHINY_WAILMER_POKEDOLL = registerBlockItem("shiny_wailmer_doll", () -> new PokeModDollBlock("wailmer", true));
	public static final RegistryObject<Block> SHINY_WYNAUT_POKEDOLL = registerBlockItem("shiny_wynaut_doll", () -> new PokeModDollBlock("wynaut", true));
	public static final RegistryObject<Block> SHINY_ZERAORA_POKEDOLL = registerBlockItem("shiny_zeraora_doll", () -> new PokeModDollBlock("zeraora", true));
	public static final RegistryObject<Block> SHINY_ZAPDOS_POKEDOLL = registerBlockItem("shiny_zapdos_doll", () -> new PokeModDollBlock("zapdos", true));



	private static <T extends Block> RegistryObject<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
		RegistryObject<T> block = POKEDOLLS.register(name, blockSupplier);
		PokeModCreativeTabs.CREATIVE_TAB_ENTRIES.computeIfAbsent("pokedolls", a -> new ArrayList<>()).add(PokeModItems.BLOCKITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())));
		return block;
	}

	public static void onInitialize(IEventBus eventBus) {
		PokeMod.LOGGER.info("Registering PokeMod PokeDolls");
		POKEDOLLS.register(eventBus);
	}
}
