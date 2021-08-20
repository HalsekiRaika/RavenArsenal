package raven.ravenarsenal.itemgroups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import raven.ravenarsenal.items.RavenItems;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

public class RAItemGroup {
    private RAItemGroup() {
        throw new AssertionError();
    }

    public static final ItemGroup RAVEN_MAIN = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RavenItems.RAVEN_ARSENAL.get());
        }
    };
}
