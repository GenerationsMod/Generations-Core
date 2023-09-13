package generations.gg.generations.core.generationscore.client.screen.dialgoue.configure;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.HierarchicalWidget;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.network.packets.dialogue.C2SSaveDatapackEntryPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNodeTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConfigureDialogueScreen extends Screen {
    private final DialogueGraph graph;
    public final List<AbstractNode> orphanNodes = new ArrayList<>();
    public boolean selectingNewNode;
    private DialogueNodeWidget rootNode;
    private int preReinitPosX = -1;
    private int preReinitPosY = -1;

    public ConfigureDialogueScreen(DialogueGraph graph) {
        super(Component.literal("Dialogue Graph Editor"));
        this.graph = graph;
    }

    @Override
    protected void clearWidgets() {
        if (rootNode != null) {
            this.preReinitPosX = rootNode.getX();
            this.preReinitPosY = rootNode.getY();
        }
        super.clearWidgets();
    }

    @Override
    protected void init() {
        int offset = -120;

        for (var orphanNode : orphanNodes) {
            var nodeWidget = new DialogueNodeWidget(10, height / 2 + offset, 8, null, orphanNode, orphanNode.getClass().getDeclaredFields());
            addRenderableWidget(nodeWidget);
        }

        this.rootNode = addNodeWidget(graph.root(), null, 40, 0);
        int graphWidth = Math.min(rootNode.getMaxX(), width);
        int graphHeight = Math.min(rootNode.getMaxY(), height);

        if (preReinitPosX == -1) {
            preReinitPosX = width - graphWidth;
            preReinitPosY = height - graphHeight;
        }

        rootNode.setX(preReinitPosX);
        rootNode.setY(preReinitPosY);

        addRenderableWidget(Button
                .builder(Component.literal("Save to Datapack"), this::saveDataPackEntry)
                .pos(width / 2 - 74, 20)
                .tooltip(Tooltip.create(Component.literal("Make sure to run /reload afterwards")))
                .build()
        );

        if (selectingNewNode)
            addRenderableWidget(new NodeListWidget(width - 2, height - 2, 120, 20 + AbstractNodeTypes.getSize() * 10));
        else addRenderableWidget(Button
                .builder(Component.literal("+"), this::openNewNodeMenu)
                .pos(width - 22, height - 22)
                .size(20, 20)
                .tooltip(Tooltip.create(Component.literal("Create a new dialogue node")))
                .build()
        );
    }

    private DialogueNodeWidget addNodeWidget(AbstractNode node, DialogueNodeWidget parent, int xOffset, int yOffset) {
        var children = node.getAllPossibleNexts();
        var nodeWidget = new DialogueNodeWidget(xOffset, (parent != null ? parent.getHeight() : 0) + 20 + yOffset, 8, parent, node, node.getClass().getDeclaredFields());
        if (parent == null) addRenderableWidget(nodeWidget);
        else parent.getChildren().add(nodeWidget);

        for (int i = 0; i < children.size(); i++) {
            int childXOffset = 0;

            if (children.size() > 1) {
                var j = children.size() / 2; // not sure what to call this. basically, split half on left and half on right to make it look cleaner and easier to view
                childXOffset = (-50 + (i - j) * (nodeWidget.getWidth() + 10));
            }

            addNodeWidget(children.get(i), nodeWidget, childXOffset, (int) (children.size() * 2f));
        }

        return nodeWidget;
    }

    private <T> JsonObject getJson(Codec<T> codec, T obj) {
        return JsonOps.INSTANCE.withEncoder(codec).apply(obj).getOrThrow(false, s -> {
            throw new RuntimeException(s);
        }).getAsJsonObject();
    }

    private void saveDataPackEntry(Button button) {
        var data = Dialogues.instance().getGson().toJsonTree(graph);
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2SSaveDatapackEntryPacket(GenerationsCore.id("dialogue/test.json"), data));
    }

    private void warnCouldntSave(String s) {
        minecraft.player.sendSystemMessage(Component.literal("Failed to save dialogue graph. Error: " + s).withStyle(ChatFormatting.RED));
        minecraft.setScreen(null);
    }

    private void openNewNodeMenu(Button button) {
        this.selectingNewNode = true;
        init(minecraft, width, height);
    }

    public void save(AbstractNode objNode, String fieldName, Object value) {
        try {
            var field = objNode.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(objNode, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to save node \"" + objNode.getClass().getSimpleName() + "\" :( maybe node was written poorly?", e);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (GuiEventListener child : children()) {
            if (child instanceof HierarchicalWidget hierarchicalWidget) {
                hierarchicalWidget.onScreenMouseRelease(mouseX, mouseY, button);
            }
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void render(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTick);
        ScreenUtils.drawCenteredString(stack, minecraft.font, "Press Left Click to Edit a Property", width / 2, 8, 0xFFCCCCCC, true);
    }

    @Override
    public void onClose() {
        children().stream()
                .filter(guiEventListener -> guiEventListener instanceof HierarchicalWidget)
                .map(guiEventListener -> (HierarchicalWidget) guiEventListener)
                .forEach(hierarchicalWidget -> hierarchicalWidget.onScreenClose(this));

        super.onClose();
    }
}