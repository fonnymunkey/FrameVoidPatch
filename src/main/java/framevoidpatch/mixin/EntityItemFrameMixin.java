package framevoidpatch.mixin;

import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityItemFrame.class)
public abstract class EntityItemFrameMixin {

    /**
     * Don't try to place an item in an item frame and remove the item from the player inventory if the item frame is already dead
     */
    @Inject(
            method = "processInitialInteract",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EntityItemFrame;setDisplayedItem(Lnet/minecraft/item/ItemStack;)V"),
            cancellable = true
    )
    public void fvp_entityItemFrame_processInitialInteract(EntityPlayer player, EnumHand hand, CallbackInfoReturnable<Boolean> cir) {
        if(((EntityItemFrame)(Object)this).isDead) cir.setReturnValue(false);
    }
}