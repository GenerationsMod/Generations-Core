package generations.gg.generations.core.generationscore.client;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class NpcSkinUtils {
    private static final Gson GSON = new GsonBuilder().create();
    public static final ResourceLocation DEFAULT_SKIN = new ResourceLocation("textures/entity/player/wide/steve.png");
    private static final Map<String, ResourceLocation> USERNAME_SKIN_CACHE = new HashMap<>();
    private static final File skinCacheDir = getOrCreateDirectory("assets/pixelmon_npc_skins/");
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void loadSkin(PlayerNpcEntity npcEntity) {
        if (npcEntity.isTextureUsername()) {
            String username = npcEntity.getTexture();
            if (USERNAME_SKIN_CACHE.containsKey(username)) {
                npcEntity.setTexture(USERNAME_SKIN_CACHE.get(username));
                return;
            }

            USERNAME_SKIN_CACHE.put(username, DEFAULT_SKIN);
            npcEntity.setTexture(DEFAULT_SKIN);
            Thread thread = new Thread(() -> {
                ResourceLocation texture = loadSkinFromUsername(username);
                npcEntity.setTexture(texture);
                USERNAME_SKIN_CACHE.put(username, texture);
            });
            thread.start();
        } else {
            npcEntity.setTexture(loadSkinFromResourceLocation(npcEntity.getTexture()));
        }
    }

    private static ResourceLocation loadSkinFromResourceLocation(String key) {
        ResourceLocation out = ResourceLocation.tryParse(key);
        return out == null ? DEFAULT_SKIN : out;
    }

    private static ResourceLocation loadSkinFromUsername(String username) {
        String uuidRequest = httpGetRequest("https://api.mojang.com/users/profiles/minecraft/" + username);
        if (uuidRequest.isBlank()) {
            return DEFAULT_SKIN;
        }
        JsonObject uuidRequestJson = GSON.fromJson(uuidRequest, JsonObject.class);
        String uuid = uuidRequestJson.get("id").getAsString();
        String profileRequest = httpGetRequest("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
        JsonArray properties = GSON.fromJson(profileRequest, JsonObject.class).getAsJsonArray("properties");
        String textureValue = null;
        for (int i = 0; i < properties.size(); i++) {
            JsonObject prop = properties.get(i).getAsJsonObject();
            if (prop.get("name").getAsString().equals("textures")) {
                textureValue = prop.get("value").getAsString();
                break;
            }
        }
        String textureString = new String(Base64.getDecoder().decode(textureValue));
        JsonObject textureObject = GSON.fromJson(textureString, JsonObject.class);
        String textureUrl = textureObject.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();

        String s = Hashing.sha256().hashUnencodedChars(textureUrl).toString();
        ResourceLocation resourcelocation = new ResourceLocation("loaded_skins/" + s);
        AbstractTexture abstracttexture = minecraft.getTextureManager().getTexture(resourcelocation, MissingTextureAtlasSprite.getTexture());
        if (abstracttexture == MissingTextureAtlasSprite.getTexture()) {
            File file1 = new File(skinCacheDir, s.length() > 2 ? s.substring(0, 2) : "xx");
            File file2 = new File(file1, s);
            HttpTexture httptexture = new HttpTexture(file2, textureUrl, DEFAULT_SKIN, true, () -> {});
            minecraft.getTextureManager().register(resourcelocation, httptexture);
        }
        return resourcelocation;
    }

    private static File getOrCreateDirectory(String path) {
        File directory = new File(".", path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    private static String httpGetRequest(String url) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection con = null;
        try {
            URL uuidRequestUrl = new URL(url);
            con = (HttpURLConnection) uuidRequestUrl.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null)
                con.disconnect();
        }
        return responseContent.toString();
    }
}