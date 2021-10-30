package raven.ravenarsenal.api.material;

import net.minecraft.util.ResourceLocation;

public class MaterialLocation extends ResourceLocation {
    public MaterialLocation(String modId, String path) {
        super(modId, path);
    }

    public MaterialLocation(ResourceLocation location) {
        super(location.getNamespace(), location.getPath());
    }
}
