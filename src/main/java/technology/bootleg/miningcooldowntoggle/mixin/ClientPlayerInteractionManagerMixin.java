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
		return MiningCooldownToggleClient.Companion.getCooldownEnabled() ? 0 : value;
	}
}
