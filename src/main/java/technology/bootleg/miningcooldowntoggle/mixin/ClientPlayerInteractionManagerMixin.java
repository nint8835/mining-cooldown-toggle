/**
 * Based on ClientPlayerInteractionManagerMixin.java from the NoMiningCooldown mod by Daxanius, licensed under GPL3.
 * Original source: https://github.com/Daxanius/NoMiningCooldown/blob/edec22aa84ecd775835feb1078c00651a2d4cc0c/src/main/java/me/daxanius/nmc/mixin/ClientPlayerInteractionManagerMixin.java
 *
 * Tweaks I made:
 * - Tweak to function name
 * - Modified function body to interact with the Kotlin side of the mod
 */

package technology.bootleg.miningcooldowntoggle.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import technology.bootleg.miningcooldowntoggle.MiningCooldownToggleClient;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
	@ModifyConstant(method = "updateBlockBreakingProgress", constant = @Constant(intValue = 5))
	private int MiningCooldownToggle(int value) {
		return MiningCooldownToggleClient.Companion.getCooldownEnabled() ? value : 0;
	}
}
