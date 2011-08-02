package de.xcraft.engelier.XcraftGate.Generator;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class PopulatorTaiga extends PopulatorHelper {
	private final static int TREE_CHANCE = 4; //out of 1000
	private final static int GRASS_CHANCE = 2; //out of 1000
	
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = (chunk.getX() * 16) + x;
				int realZ = (chunk.getZ() * 16) + z;
				int y = world.getHighestBlockYAt(realX, realZ);

				Block block = world.getBlockAt(realX, y, realZ);
				if (block.getBiome() != Biome.TAIGA) continue;
				
				Block blockBelow = world.getBlockAt(realX, y - 1, realZ);
				int rnd = random.nextInt(1000);
				
				if (blockBelow.getType() == Material.GRASS && rnd < TREE_CHANCE) {
					world.generateTree(new Location(world, realX, y, realZ), TreeType.REDWOOD);
					continue;
				}
			}
		}
		
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = (chunk.getX() * 16) + x;
				int realZ = (chunk.getZ() * 16) + z;
				int y = world.getHighestBlockYAt(realX, realZ);
				
				Block block = world.getBlockAt(realX, y, realZ);
				if (block.getBiome() != Biome.TAIGA) continue;
						
				Block blockBelow = world.getBlockAt(realX, y - 1, realZ);
				int rnd = random.nextInt(1000);

				if (blockBelow.getType() == Material.GRASS && rnd < GRASS_CHANCE) {
					block.setTypeIdAndData(Material.LONG_GRASS.getId(), (byte) 1, true);
					continue;
				}

				if (block.getType() == Material.AIR && blockBelow.getType() != Material.WATER && blockBelow.getType() != Material.LAVA) {
					block.setType(Material.SNOW);
				}
				
				if (blockBelow.getType() == Material.WATER) {
					blockBelow.setType(Material.ICE);
				}
			}
		}
	}
}