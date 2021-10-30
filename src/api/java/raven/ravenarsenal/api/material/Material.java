package raven.ravenarsenal.api.material;

import net.minecraft.util.Util;
import net.minecraft.util.text.Color;

public class Material implements IMaterial {
    private final MaterialLocation location;
    private final Color materialColor;
    private final String translation;
    private final int tier;

    private Material() { throw new AssertionError(); }

    public Material(Builder builder) {
        this.location = builder.location;
        this.materialColor = builder.materialColor;
        this.translation = builder.translation;
        this.tier = builder.tier;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public Color getColor() {
        return this.materialColor;
    }

    @Override
    public String getTranslation() {
        return this.translation;
    }

    @Override
    public MaterialLocation getLocation() {
        return this.location;
    }


    public static class Builder {
        private MaterialLocation location;
        private Color materialColor;
        private String translation;
        private int tier;

        public Builder() {  }

        public Builder setLocation(MaterialLocation location) {
            this.location = location;
            return this;
        }

        public Builder setColorFromInt(int colorCode) {
            this.materialColor = Color.fromInt(colorCode);
            return this;
        }

        public Builder setColor(Color materialColor) {
            this.materialColor = materialColor;
            return this;
        }

        public Builder setTranslation(MaterialLocation location) {
            this.translation = Util.makeTranslationKey("mat_", location);
            return this;
        }

        public Builder setTier(int tier) {
            this.tier = tier;
            return this;
        }

        public Material build() {
            return new Material(this);
        }
    }
}
