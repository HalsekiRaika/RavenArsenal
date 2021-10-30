package raven.ravenarsenal.api.material;

import net.minecraft.util.text.Color;

import javax.annotation.Nonnull;

public interface IMaterial extends Comparable<IMaterial> {
    MaterialLocation DEFAULT_LOCATION = new MaterialLocation("raven_arsenal", "default");
    IMaterial DEFAULT = new Material.Builder()
            .setLocation(DEFAULT_LOCATION)
            .setColorFromInt(0xFFFFFF)
            .setTranslation(DEFAULT_LOCATION)
            .setTier(1)
            .build();

    int getTier();

    Color getColor();

    String getTranslation();

    MaterialLocation getLocation();

    @Override
    default int compareTo(@Nonnull IMaterial o) {
        if (this.getTier() != o.getTier()) {
            return Integer.compare(this.getTier(), o.getTier());
        }
        return this.getLocation().compareTo(o.getLocation());
    }
}
