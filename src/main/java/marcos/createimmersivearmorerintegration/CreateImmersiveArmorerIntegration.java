package marcos.createimmersivearmorerintegration;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(CreateImmersiveArmorerIntegration.MODID)
public class CreateImmersiveArmorerIntegration {
    public static final String MODID = "createimmersivearmorerintegration";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final DeferredItem<Item> NINEMM_CASING = ITEMS.registerSimpleItem("9mm_casing", new Item.Properties());
    public static final DeferredItem<Item> TEWNTYMM_CASING = ITEMS.registerSimpleItem("20mm_cased_telescoped_ammunition_casing", new Item.Properties());
    public static final DeferredItem<Item> FOURFIVEMM_CASING = ITEMS.registerSimpleItem("454_casul_casing", new Item.Properties());
    public static final DeferredItem<Item> AP_CASING = ITEMS.registerSimpleItem("ap_casing", new Item.Properties());
    public static final DeferredItem<Item> EMPTY_FLAMMABLE_CHEMICAL = ITEMS.registerSimpleItem("empty_flammable_chemical", new Item.Properties());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.createimmersivearmorerintegration"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EMPTY_FLAMMABLE_CHEMICAL.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(NINEMM_CASING.get());
                output.accept(TEWNTYMM_CASING.get());
                output.accept(FOURFIVEMM_CASING.get());
                output.accept(AP_CASING.get());
                output.accept(EMPTY_FLAMMABLE_CHEMICAL.get());
            }).build());

    public CreateImmersiveArmorerIntegration(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
