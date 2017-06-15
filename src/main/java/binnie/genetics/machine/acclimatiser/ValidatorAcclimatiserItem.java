package binnie.genetics.machine.acclimatiser;

import net.minecraft.item.ItemStack;

import binnie.core.machines.inventory.SlotValidator;
import binnie.genetics.Genetics;

public class ValidatorAcclimatiserItem extends SlotValidator {
	public ValidatorAcclimatiserItem() {
		super(null);
	}

	@Override
	public boolean isValid(final ItemStack stack) {
		for (final ToleranceType type : ToleranceType.values()) {
			if (type.hasEffect(stack)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getTooltip() {
		return Genetics.proxy.localise("machine.labMachine.acclimatiser.tooltips.slots.acclimatising");
	}
}
