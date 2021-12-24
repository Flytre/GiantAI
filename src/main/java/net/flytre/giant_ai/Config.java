package net.flytre.giant_ai;

import com.google.gson.annotations.SerializedName;
import net.flytre.flytre_lib.api.config.annotation.Description;
import net.flytre.flytre_lib.api.config.reference.block.ConfigBlock;
import net.minecraft.block.Blocks;

import java.util.Set;

public class Config {

    @SerializedName("breaks_blocks")
    @Description("Whether giants should be able to break blocks or not")
    public boolean breaksBlocks = true;


    @SerializedName("block_break_blacklist")
    @Description("The blocks the giant is unable to break. Many blocks that can't normally be broken, like bedrock or nether portals, won't be broken regardless of whether they are on this list.")
    public Set<ConfigBlock> blockBreakBlacklist = ConfigBlock.of(Set.of(
            Blocks.OBSIDIAN,
            Blocks.CRYING_OBSIDIAN,
            Blocks.RESPAWN_ANCHOR
    ));


    @Description("Whether giants should spawn in the wild or not. Requires game restart")
    @SerializedName("should_spawn_naturally")
    public boolean shouldSpawnNaturally = true;
}
