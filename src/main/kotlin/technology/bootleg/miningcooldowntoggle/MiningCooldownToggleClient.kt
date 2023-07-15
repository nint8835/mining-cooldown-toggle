package technology.bootleg.miningcooldowntoggle

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

@Environment(net.fabricmc.api.EnvType.CLIENT)
class MiningCooldownToggleClient : ClientModInitializer {
    companion object {
        private lateinit var keybind: KeyBinding;
        var cooldownEnabled: Boolean = true;
    }
    
    override fun onInitializeClient() {
        keybind = KeyBindingHelper.registerKeyBinding(KeyBinding(
            "key.miningcooldowntoggle.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.misc"
        ))

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            while (keybind.wasPressed()) {
                client.player?.let {
                    cooldownEnabled = !cooldownEnabled
                    it.sendMessage(
                        Text.literal("Cooldown toggled")
                    )
                }
            }
        })
    }
}
