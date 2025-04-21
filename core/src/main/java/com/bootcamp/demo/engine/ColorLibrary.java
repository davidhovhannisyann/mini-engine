package com.bootcamp.demo.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ObjectMap;
import com.bootcamp.demo.data.game.Rarity;
import lombok.Getter;

public class ColorLibrary {
    public static ObjectMap<String, com.badlogic.gdx.graphics.Color> colors = new ObjectMap<>();
    @Getter
    public static ObjectMap<Rarity, com.badlogic.gdx.graphics.Color> rarityColors = new ObjectMap<>();

    public static com.badlogic.gdx.graphics.Color get (String hashtag) {
        if (!colors.containsKey(hashtag)) {
            colors.put(hashtag, com.badlogic.gdx.graphics.Color.valueOf(hashtag));
        }
        return colors.get(hashtag);
    }

    static {
        rarityColors.put(Rarity.COMMON, Color.valueOf("BAA390"));
        rarityColors.put(Rarity.RARE, Color.valueOf("#5AB1F4"));
        rarityColors.put(Rarity.EPIC, Color.valueOf("#CE7DF0"));
        rarityColors.put(Rarity.LEGENDARY, Color.valueOf("#E5AB48"));
        rarityColors.put(Rarity.EXOTIC, Color.valueOf("#D24C41"));
    }

    public enum COLORS {
        WHITE("#F5EAE2"),
        BROWN("B7A79C"),
        DARK_BROWN("#A28D7A"),
        GRAY("#D1CFCC"),
        BORDER("#8C8379"),
        GRAY_BROWN("#A9A29B"),
        COMMON("#BAA390"),
        LIGHT_GRAY("#C9C0B8");
        @Getter
        private Color color;

        COLORS (String hex) {
            this.color = Color.valueOf(hex);
        }

    }

}
