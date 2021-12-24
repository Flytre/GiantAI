package net.flytre.giant_ai;

import net.flytre.flytre_lib.api.config.reference.block.ConfigBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BreakBlockGoal extends Goal {

    private final GiantEntity giant;
    private BlockPos pos;

    public BreakBlockGoal(GiantEntity giant) {
        this.giant = giant;
        this.pos = new BlockPos(0, 0, 0);
    }

    private boolean getMobGriefing() {
        return giant.world.getGameRules().get(GameRules.DO_MOB_GRIEFING).get();
    }

    @Override
    public boolean canStart() {
        return GiantAI.CONFIG_HANDLER.getConfig().breaksBlocks && Math.random() < 0.04 && this.pos != giant.getBlockPos() && getMobGriefing();
    }

    public boolean shouldContinue() {
        return false;
    }


    public void tick() {
        World world = giant.getEntityWorld();
        pos = giant.getBlockPos();
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                for (int y = 0; y <= 12; y++) {
                    BlockPos pos = giant.getBlockPos().add(x, y, z);
                    if (!world.canSetBlock(pos)) {
                        continue;
                    }
                    BlockState state = world.getBlockState(pos);


                    if (state.getHardness(world, pos) < 0 || ConfigBlock.contains(GiantAI.CONFIG_HANDLER.getConfig().blockBreakBlacklist,state.getBlock(),world))
                        continue;


                    world.breakBlock(pos, Math.random() < 0.5);
                }
            }
        }
    }
}
