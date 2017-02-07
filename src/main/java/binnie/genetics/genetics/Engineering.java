package binnie.genetics.genetics;

import binnie.genetics.Genetics;
import binnie.genetics.api.IGene;
import binnie.genetics.api.IItemChargeable;
import binnie.genetics.api.IItemSerum;
import binnie.genetics.item.GeneticsItems;
import binnie.genetics.item.ItemSerum;
import binnie.genetics.item.ItemSerumArray;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class Engineering {
	public static boolean isGeneAcceptor(@Nullable final ItemStack stack) {
		if (stack == null) {
			return false;
		}
		if (stack.getItem() instanceof IItemSerum) {
			return ((IItemSerum) stack.getItem()).getCharges(stack) == 0;
		}
		return stack.getItem() == Genetics.getItemGenetics() && (stack.getItemDamage() == GeneticsItems.EmptySerum.ordinal() || stack.getItemDamage() == GeneticsItems.EmptyGenome.ordinal());
	}

	public static boolean canAcceptGene(final ItemStack stack, final IGene gene) {
		if (stack.getItem() instanceof ItemSerum) {
			return true;
		}
		if (stack.getItem() instanceof IItemSerum) {
			return ((IItemSerum) stack.getItem()).getSpeciesRoot(stack) == gene.getSpeciesRoot();
		}
		return isGeneAcceptor(stack);
	}

	@Nullable
	public static IGene getGene(final ItemStack stack, final int chromosome) {
		if (stack.getItem() instanceof IItemSerum) {
			return ((IItemSerum) stack.getItem()).getGene(stack, chromosome);
		}
		return null;
	}

	public static ItemStack addGene(final ItemStack stack, final IGene gene) {
		if (stack.getItem() instanceof IItemSerum) {
			((IItemSerum) stack.getItem()).addGene(stack, gene);
		}
		if (stack.getItem() == Genetics.getItemGenetics() && stack.getItemDamage() == GeneticsItems.EmptySerum.ordinal()) {
			return ItemSerum.create(gene);
		}
		if (stack.getItem() == Genetics.getItemGenetics() && stack.getItemDamage() == GeneticsItems.EmptyGenome.ordinal()) {
			return ItemSerumArray.create(gene);
		}
		return stack;
	}

	public static IGene[] getGenes(@Nullable final ItemStack serum) {
		if (serum != null) {
			if (serum.getItem() instanceof IItemSerum) {
				return ((IItemSerum) serum.getItem()).getGenes(serum);
			}
			if (serum.getItem() == Genetics.itemSequencer) {
				SequencerItem sequencerItem = SequencerItem.create(serum);
				if (sequencerItem != null) {
					return new IGene[]{sequencerItem.getGene()};
				}
			}
		}
		return new IGene[0];
	}

	public static int getCharges(final ItemStack serum) {
		return ((IItemChargeable) serum.getItem()).getCharges(serum);
	}
}
