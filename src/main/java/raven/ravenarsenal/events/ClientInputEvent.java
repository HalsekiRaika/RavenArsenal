package raven.ravenarsenal.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import raven.ravenarsenal.lib.Tuple;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientInputEvent {
    @SubscribeEvent
    public static void OnPressKey(InputEvent.KeyInputEvent event) {
        Tuple.Double<Integer, Integer> bindKeyInput = Tuple.of(event.getKey(), event.getModifiers());
        boolean doubleInput = ((bindKeyInput.isFirstEmpty() || bindKeyInput.isSecondEmpty()) || event.getAction() != 0);
    }
}
