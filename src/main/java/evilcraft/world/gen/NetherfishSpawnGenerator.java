package evilcraft.world.gen;

import evilcraft.block.NetherfishSpawn;
import evilcraft.block.NetherfishSpawnConfig;
import evilcraft.core.config.configurable.ConfigurableBlockWithInnerBlocks;
import evilcraft.core.world.gen.WorldGenMinableExtended;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.cyclops.cyclopscore.helper.MinecraftHelpers;

import java.util.Random;

/**
 * WorldGenerator for netherfish spawn blocks.
 * @author rubensworks
 *
 */
public class NetherfishSpawnGenerator extends WorldGenMinableExtended{
    
    /**
     * Make a new instance.
     */
    public NetherfishSpawnGenerator() {
        super(NetherfishSpawn.getInstance().getDefaultState(), 1, NetherfishSpawnConfig.veinsPerChunk, 1, 127, Blocks.netherrack);
    }
    
    @Override
    public void loopGenerate(World world, Random rand, int chunkX, int chunkZ) {
        for(int k = 0; k < veinsPerChunk; k++){
            BlockPos blockPos = new BlockPos(
                    chunkX + rand.nextInt(16),
                    startY + rand.nextInt(endY - startY),
                    chunkZ + rand.nextInt(16));
            
            Block block = world.getBlockState(blockPos).getBlock();
            int meta = NetherfishSpawn.getInstance().getMetadataFromBlock(block);
            if(meta != -1) {
                world.setBlockState(
                        blockPos,
                        block.getDefaultState().withProperty(ConfigurableBlockWithInnerBlocks.FAKEMETA, meta),
                        MinecraftHelpers.BLOCK_NOTIFY_CLIENT);
            }
        }
    }
}
