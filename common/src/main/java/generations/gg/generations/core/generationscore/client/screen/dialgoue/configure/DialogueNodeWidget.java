package generations.gg.generations.core.generationscore.client.screen.dialgoue.configure;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.client.screen.AbstractHierarchicalWidget;
import generations.gg.generations.core.generationscore.client.screen.HierarchicalWidget;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNodeTypes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class DialogueNodeWidget extends AbstractHierarchicalWidget {

    private final AbstractNode node;
    public final Field[] fields;
    private boolean mouseDown;
    private double moveOffsetX;
    private double moveOffsetY;

    public DialogueNodeWidget(int relativeX, int relativeY, int height, HierarchicalWidget parent, AbstractNode node, Field[] fields) {
        super(relativeX, relativeY, 80, height, parent);
        this.node = node;
        this.fields = Arrays.stream(fields)
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .toArray(Field[]::new);

        for (var param : this.fields) {
            if (!param.getName().equals("type"))
                children.add(new ConfigurableJsonEntry(0, (children.size() + 1) * 12, width, 10, this, param, node));
        }

        this.height = 8 + getChildren().size() * 10;
    }

    @Override
    public void render(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTick) {
        super.render(stack, mouseX, mouseY, partialTick);

        if (this.visible) {
            if (mouseDown) {
                getRootParent().setX((int) (mouseX - moveOffsetX));
                getRootParent().setY((int) (mouseY - moveOffsetY));
            }

            stack.pose().pushPose();

            // Tab
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 0.337f, 0.286f, 1.0f);
            ScreenUtils.drawRect(stack, getX(), getY(), 80, getHeight(), 0xFFFFFFFF);
            var nodeId = AbstractNodeTypes.ABSTRACT_NODES.inverse().get(node.type());
            stack.drawString(client.font, nodeId.toString(), getX() + 2, getY(), 0xFFFFFFFF);

            // Settings
            RenderSystem.setShaderColor(0.2f, 0.2f, 0.2f, 1.0f);
            ScreenUtils.drawRect(stack, getX(), getY() + 8, 80, getHeight(), 0xFFFFFFFF);
            RenderSystem.disableBlend();
            stack.pose().popPose();

            // Children Connections
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            var lineStart = new Vector2f(getX() + getWidth() / 2f, getY() + getHeight() + 9);
            for (var child : getChildren()) {
                if (child instanceof DialogueNodeWidget) {
                    ScreenUtils.drawLine(stack.pose().last().pose(), 0, lineStart, new Vector2f(child.getX() + getWidth() / 2f, child.getY() + 2), 0xFFFFFFFF);
                }
            }
        }

        finishRendering(stack, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double) this.getX() && mouseY >= (double) this.getY() && mouseX < (double) (this.getX() + this.width) && mouseY < (double) (this.getY() + 8);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && isMouseOver(mouseX, mouseY)) {
            this.moveOffsetX = mouseX - getRootParent().getX();
            this.moveOffsetY = mouseY - getRootParent().getY();
            this.mouseDown = true;
        } else {
            for (var child : getChildren()) {
                if (child instanceof GuiEventListener guiEventListener) {
                    guiEventListener.mouseClicked(mouseX, mouseY, button);
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean onScreenMouseRelease(double mouseX, double mouseY, int button) {
        boolean childNotHandled = super.onScreenMouseRelease(mouseX, mouseY, button);

        if (childNotHandled) {
            if (isMouseOver(mouseX, mouseY)) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                    this.mouseDown = false;
                }

                return false;
            }
        }

        return childNotHandled;
    }

    private static class ConfigurableJsonEntry extends AbstractHierarchicalWidget {

        private final String fieldName;
        private final Class<?> fieldType;
        private final AbstractNode node;

        public ConfigurableJsonEntry(int relativeX, int relativeY, int width, int height, HierarchicalWidget parent, Field entry, AbstractNode node) {
            super(relativeX, relativeY, width, height, parent);
            this.fieldName = entry.getName();
            this.fieldType = entry.getType();
            this.node = node;
        }

        @Override
        public void onClick(double mouseX, double mouseY) {
            if (active && isHovered) {
                try {
                    var field = node.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);

                    switch (fieldType.getName()) {
                        case "java.util.List" -> {
                            List<?> list = (List<?>) field.get(node);
                            client.setScreen(new ConfigureStringListEntryScreen(client.screen, (List<String>) list, fieldName, node));
                        }

                        case "int", "java.lang.Integer" -> {

                        }

                        default -> System.out.println("unknown type :(");
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public void render(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTick) {
            super.render(stack, mouseX, mouseY, partialTick);
            stack.drawString(client.font, fieldName, getX() + 2, getY(), isHovered ? 0xFFFFFFFF : 0xFF999999);
        }
    }
}