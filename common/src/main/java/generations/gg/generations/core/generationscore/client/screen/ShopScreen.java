package generations.gg.generations.core.generationscore.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.ClientPlayerMoney;
import generations.gg.generations.core.generationscore.client.screen.widget.RadioButton;
import generations.gg.generations.core.generationscore.client.screen.widget.ScrollableMultiLineTextBox;
import generations.gg.generations.core.generationscore.client.screen.widget.ShopListWidget;
import generations.gg.generations.core.generationscore.network.packets.shop.C2SCloseShopPacket;
import generations.gg.generations.core.generationscore.util.ShopUtils;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import generations.gg.generations.core.generationscore.world.shop.SimpleShopEntry;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Comparator;

public class ShopScreen extends Screen {

    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/shop/shop.png");
    private static final ResourceLocation POKEDOLLAR_ICON = GenerationsCore.id("textures/gui/pokedollar.png");

    private final ShopOfferProvider shopOfferProvider;

    private ImageButton buyButton;
    private ShopListWidget shopListWidget;
    private ScrollableMultiLineTextBox descriptionTextBox;

    private boolean isBuyPage;
    private int x, y;
    private int selected, amount;
    private SimpleShopEntry[] entries;

    public ShopScreen(ShopOfferProvider shopOfferProvider) {
        super(Component.empty());
        this.amount = 1;
        this.selected = -1;
        this.isBuyPage = true;
        this.shopOfferProvider = shopOfferProvider;
    }

    @Override
    protected void init() {
        if (shopOfferProvider.getOffers() == null) {
            this.onClose();
        }

        entries = isBuyPage ? buyEntries() : sellEntries();

        this.x = width / 2 - scaledCoord(716) / 2;
        this.y = height / 2 - scaledCoord(743) / 2;

        this.addRenderableWidget(new ImageButton(x + scaledCoord(653), y + scaledCoord(619), scaledCoord(19), scaledCoord(21),
                735, 414, 19, TEXTURE/*, 21*/, 921, 805, btn -> {
            int i = 1;
            if (hasControlDown()) {
                i = 100;
            } else if (hasShiftDown()) {
                i = 10;
            }
            this.amount += i;
            if (this.amount > 1000)
                this.amount = 1000;

            updateBuyButtonActive();
        }));

        this.addRenderableWidget(new ImageButton(x + scaledCoord(523), y + scaledCoord(619), scaledCoord(19), scaledCoord(21),
                716, 414, 19, TEXTURE/*, 21*/, 921, 805, btn -> {
            int i = 1;
            if (hasControlDown()) {
                i = 100;
            } else if (hasShiftDown()) {
                i = 10;
            }
            this.amount -= i;
            if (this.amount < 1)
                this.amount = 1;

            updateBuyButtonActive();
        }));

        this.addRenderableWidget(new RadioButton(x + scaledCoord(33), y, scaledCoord(120), scaledCoord(72),
                TEXTURE, 716, 72, 120, 72, 921, 805,
                RadioButton.Orientation.HORIZONTAL, 3, 0,
                new RadioButton.RadioOption("Buy", null, () -> updatePage(true)),
                new RadioButton.RadioOption("Sell", null, () -> updatePage(false))));
        this.buyButton = this.addRenderableWidget(new ImageButton(x + scaledCoord(513), y + scaledCoord(664),
                scaledCoord(170), scaledCoord(40),
                716, 313, 170, TEXTURE,/*40,*/ 921, 805, btn -> shopItem()));
        this.buyButton.active = selected != -1;
        this.descriptionTextBox = this.addRenderableWidget(new ScrollableMultiLineTextBox(x + scaledCoord(40), y + scaledCoord(570),
                scaledCoord(453), scaledCoord(130), false, false, font, 0x000000, description()));
        this.shopListWidget = this.addRenderableWidget(new ShopListWidget(this, x + scaledCoord(34), y + scaledCoord(122),
                scaledCoord(646), scaledCoord(366), scaledCoord(62),
                isBuyPage, entries, index -> {
            this.selected = index;
            descriptionTextBox.setParagraphs(description());
            updateBuyButtonActive();
        }));
        this.shopListWidget.select(selected);
    }

    private void updateBuyButtonActive() {
        if (isBuyPage && ClientPlayerMoney.balance < getItemPrice()) {
            this.buyButton.active = false;
        } else {
            if (!isBuyPage) {
                this.buyButton.active = selected != -1
                        && ShopUtils.getAmountInInventory(minecraft.player, entries[selected].getItem()) >= entries[selected].getItem().getCount() * amount;
            } else {
                this.buyButton.active = selected != -1;
            }
        }
    }

    private void updatePage(boolean isBuyPage) {
        this.isBuyPage = isBuyPage;
        this.entries = isBuyPage ? buyEntries() : sellEntries();
        this.selected = Math.min(selected, entries.length - 1);
        this.descriptionTextBox.setParagraphs(description());
        this.shopListWidget.setEntries(entries, isBuyPage);
        updateBuyButtonActive();
    }

    private String[] description() {
        return selected == -1
                ? new String[0]
                : entries[selected].getDescription().split("\\n");
    }

    private SimpleShopEntry[] buyEntries() {
        return Arrays.stream(shopOfferProvider.getOffers().getEntries())
                .filter(entry -> entry.getBuyPrice() > 0)
                .sorted(Comparator.comparingInt(SimpleShopEntry::getOrder).reversed())
                .toArray(SimpleShopEntry[]::new);
    }

    private SimpleShopEntry[] sellEntries() {
        return Arrays.stream(shopOfferProvider.getOffers().getEntries())
                .filter(entry -> entry.getSellPrice() > 0)
                .sorted(Comparator.comparingInt(SimpleShopEntry::getOrder).reversed())
                .toArray(SimpleShopEntry[]::new);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(TEXTURE, x, y, scaledCoord(716), scaledCoord(743),
                0, 0, 716, 743, 921, 805);

        String moneyText = String.valueOf(ClientPlayerMoney.balance);
        Component yourMoneyText = Component.literal("Your Money");
        int moneyTextX = x + scaledCoord(362) + font.width(yourMoneyText) + 6;
        int moneyTextXEnd = x + scaledCoord(685);
        if (font.width(moneyText) > moneyTextXEnd - moneyTextX) {
            moneyText = font.plainSubstrByWidth(moneyText, moneyTextXEnd - moneyTextX - font.width("...")) + "...";
        }
        int moneyTextY = y + scaledCoord(30) - font.lineHeight / 2;
        guiGraphics.drawString(font, yourMoneyText, x + scaledCoord(362), moneyTextY, 0x000000);

        guiGraphics.blit(POKEDOLLAR_ICON, moneyTextX, moneyTextY, 0, 0, 7, 9, 7, 9);
        guiGraphics.drawString(font, moneyText, moneyTextX + 7, moneyTextY, 0x000000);
        if (mouseX >= moneyTextX && mouseX <= moneyTextXEnd && mouseY >= moneyTextY && mouseY <= moneyTextY + font.lineHeight) {
            guiGraphics.renderTooltip(font, Component.literal(String.valueOf(ClientPlayerMoney.balance)), mouseX, mouseY);
        }

        String amountText = String.valueOf(amount);
        guiGraphics.drawString(font, amountText, (int) (x + scaledCoord(598) - font.width(amountText) / 2F), (int) (y + scaledCoord(630) - font.lineHeight / 2F), 0x000000);

        if (selected != -1) {
            SimpleShopEntry entry = entries[selected];
            int selectedX = x + scaledCoord(40);
            int selectedY = y + scaledCoord(550);
            drawItem(guiGraphics, entry.getItem(), selectedX, selectedY - 10, mouseX, mouseY);
            guiGraphics.drawString(font, entry.getItem().getHoverName().getString(), selectedX + 18, (int) (selectedY - font.lineHeight / 2F), 0xFCF9EA);

            int itemAmount = ShopUtils.getAmountInInventory(minecraft.player, entry.getItem());
            String amountInInventory = "x" + itemAmount;
            int inventoryX = x + scaledCoord(597) - font.width(amountInInventory) / 2 - 9;
            int inventoryY = y + scaledCoord(555);
            int color = 0x000000;
            if (!isBuyPage && itemAmount < entry.getItem().getCount() * amount) {
                color = 0xFF5555;
            }
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 1050);
            drawItem(guiGraphics, entry.getItem(), inventoryX, inventoryY - 8, mouseX, mouseY);
            guiGraphics.pose().popPose();
            guiGraphics.drawString(font, amountInInventory, inventoryX + 17, inventoryY + 8 - font.lineHeight, color);
        }
        guiGraphics.pose().pushPose();
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.pose().popPose();
        if (selected != -1) {
            String price = String.valueOf(getItemPrice());
            if (font.width(price) + 7 > scaledCoord(162)) {
                price = font.plainSubstrByWidth(price, scaledCoord(162) - 7 - font.width("...")) + "...";
            }
            int color = 0x000000;
            if (isBuyPage && !buyButton.active) {
                color = 0xFF5555;
            }
            int priceX = (int) (buyButton.getX() + buyButton.getWidth() / 2F - font.width(price) / 2F) - 4;
            int priceY = (int) (buyButton.getY() + buyButton.getHeight() / 2F - font.lineHeight / 2F);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 1000);
            guiGraphics.drawString(font, price, priceX + 7, priceY, color);
            guiGraphics.blit(POKEDOLLAR_ICON, priceX, priceY, 0, 0, 7, 9, 7, 9);
            guiGraphics.pose().popPose();
        }
    }

    private int getItemPrice() {
        if (selected == -1) {
            return 0;
        } else {
            return (isBuyPage ? entries[selected].getBuyPrice() : entries[selected].getSellPrice()) * amount;
        }
    }

    private void drawItem(GuiGraphics guiGraphics, ItemStack item, int x, int y, int mouseX, int mouseY) {
        guiGraphics.renderFakeItem(item, x, y);
        if (mouseX >= x && mouseX <= x + 16 && mouseY >= y && mouseY <= y + 16) {
            guiGraphics.renderTooltip(font, item, mouseX, mouseY);
        }
    }

    @Override
    public void onClose() {
        GenerationsCore.implementation.getNetworkManager().sendPacketToServer(new C2SCloseShopPacket());
    }

    private void shopItem() {
        if (selected == -1) {
            return;
        }

        SimpleShopEntry entry = entries[selected];
        ItemStack itemStack = entry.getItem();
        int price = isBuyPage ? entry.getBuyPrice() : entry.getSellPrice();
        GenerationsCore.implementation.getNetworkManager().sendPacketToServer(shopOfferProvider.createItemPacket(itemStack, price, amount, isBuyPage));

        updateBuyButtonActive();
    }

    private int scaledCoord(int i) {
        return Math.round(i * 0.33F);
    }
}