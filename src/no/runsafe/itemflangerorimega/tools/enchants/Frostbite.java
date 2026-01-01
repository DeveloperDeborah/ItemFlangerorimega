package no.runsafe.itemflangerorimega.tools.enchants;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.itemflangerorimega.Config;
import no.runsafe.itemflangerorimega.tools.CustomToolEnchant;

public class Frostbite extends CustomToolEnchant
{
	@Override
	public String getEnchantText()
	{
		return "Frostbite";
	}

	@Override
	public String getSimpleName()
	{
		return "frostbite";
	}

	@Override
	public boolean onBlockBreak(IPlayer player, IBlock block)
	{
		if (block == null || player == null)
			return false;

		if (block.getMaterial() != Item.BuildingBlock.Stone.Stone)
			return false;

		ILocation origin = block.getLocation();
		int radius = Config.frostbiteRadius;
		int radiusSquared = radius * radius;

		for (double x = -radius; x <= radius; x++)
			for (double y = -radius; y <= radius; y++)
				for (double z = -radius; z <= radius; z++)
				{
					if (x * x + y * y + z * z > radiusSquared)
						continue;

					ILocation targetLoc = origin.clone();
					targetLoc.offset(x, y, z);
					IBlock targetBlock = targetLoc.getBlock();

					if (targetBlock != null && targetBlock.getMaterial() == Item.BuildingBlock.Stone.Stone)
						targetBlock.set(Item.BuildingBlock.PackedIce);
				}
		return true;
	}
}
