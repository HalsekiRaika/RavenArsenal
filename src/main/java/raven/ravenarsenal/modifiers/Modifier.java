package raven.ravenarsenal.modifiers;

import net.minecraft.util.text.ITextComponent;
import raven.ravenarsenal.api.modifier.IModifier;

import javax.annotation.Nullable;

public abstract class Modifier implements IModifier<Modifier> {
    private final int colorCode;
    private final String modifierName;

    @Nullable
    private static final ITextComponent description = null;

    public Modifier(int colorHexCode, String name) {
        this.colorCode = colorHexCode;
        this.modifierName = name;
    }

    public abstract void onApply();


}
