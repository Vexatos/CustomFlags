package mods.custom_flags.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import mods.custom_flags.CommonProxy;
import mods.custom_flags.blocks.TileEntityFlagPole;
import mods.custom_flags.client.renderer.FlagPoleTileRenderer;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 4:28 PM
 * TODO: Add discription
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerLanguages() {
        LanguageHelper.loadAllLanguages("Custom Flags", "custom_flags", "custom_flags");
    }

    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlagPole.class, new FlagPoleTileRenderer());
    }
}
