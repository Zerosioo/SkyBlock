package me.zerosio.mechanics.mining;

import me.zerosio.mechanics.mining.blocks.*;

public class RegisterBlocks {
	public static void reg() {
		MiningBlock.getBlocks().add(new StoneBlock());
        MiningBlock.getBlocks().add(new IronBlock());
        MiningBlock.getBlocks().add(new GoldBlock());
        MiningBlock.getBlocks().add(new CobblestoneBlock());
	}
}