package generations.gg.generations.core.generationscore.client.screen.npc.tabs;

import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomizationTab {

    protected final Minecraft minecraft;
    protected final Font font;
    protected final PlayerNpcEntity npcEntity;
    private final List<GuiEventListener> children;
    protected int x, y;

    public CustomizationTab(PlayerNpcEntity npcEntity) {
        this.npcEntity = npcEntity;
        this.minecraft = Minecraft.getInstance();
        this.font = minecraft.font;
        this.children = new ArrayList<>();
    }

    public void init(int x, int y) {
        this.x = x;
        this.y = y;
        this.children.clear();
    }

    public abstract void tick();

    public abstract void preRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    public abstract void postRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    protected <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T widget) {
        this.children.add(widget);
        return widget;
    }

    public List<GuiEventListener> getChildren() {
        return children;
    }
}