package raven.ravenarsenal;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;

import raven.ravenarsenal.api.IRAAddon;
import raven.ravenarsenal.api.IRAApi;
import raven.ravenarsenal.api.RAAddon;

import java.util.Objects;
import java.util.Set;
import org.objectweb.asm.Type;

final class Addons {
    private Addons() { throw new AssertionError(); }

    public static void onLoad(IRAApi api) {
        final Type addonAnnotation = Type.getType(RAAddon.class);
        ModList.get().getAllScanData().stream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(Set::stream)
                .filter(annotation -> Objects.equals(annotation.getAnnotationType(), addonAnnotation))
                .map(ModFileScanData.AnnotationData::getMemberName)
                .forEach(className -> {
                    try {
                        final Class<?> clazz = Class.forName(className);
                        final Class<? extends IRAAddon> addonClass = clazz.asSubclass(IRAAddon.class);
                        final IRAAddon addonInstance = addonClass.newInstance();

                        addonInstance.onAvailable(api);
                    } catch (ClassNotFoundException e) {
                        RavenArsenal.getLogger().error("Cannot find addon class. : {}", className, e);
                    } catch (IllegalAccessException | InstantiationException e) {
                        RavenArsenal.getLogger().error("Cannot generate addon instance. : {}", className, e);
                    }
                });
    }
}
