/**
 * Based on NoMiningCooldownClient.java from the NoMiningCooldown mod by Daxanius, licensed under GPL3.
 * Original source: https://github.com/Daxanius/NoMiningCooldown/blob/edec22aa84ecd775835feb1078c00651a2d4cc0c/src/main/java/me/daxanius/nmc/NoMiningCooldownClient.java
 *
 * Tweaks I made:
 * - Converted to Kotlin
 * - Tweaks to variable names & identifier strings to reference my mod name
 */
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
