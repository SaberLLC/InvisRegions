package pw.saber.regions.struct.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.saber.regions.InvisRegions;
import pw.saber.regions.struct.CachedPlayer;
import pw.saber.regions.utils.Logger;
import pw.saber.regions.utils.WorldGuardUtils;

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */


public class LocationTask implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            CachedPlayer cp = new CachedPlayer(player, player.getLocation(), player.getWorld(), player.getWorld().getName());
            //Players Persistence is an Operator We Skip Them.
            if (cp.getPlayer().isOp()) continue;

            if (WorldGuardUtils.isInRegionsContainsFast(cp.getLocation(), InvisRegions.getInstance().invisRegions)) {
                if (!cp.getPlayer().hasMetadata("invisRegion")) {
                    cp.getPlayer().setMetadata("invisRegion", new FixedMetadataValue(InvisRegions.getInstance(), true));
                }
                cp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));
                if (InvisRegions.getInstance().debugging) {
                    Logger.print(cp.getPlayer().getName() + " has entered an InvisRegion!", Logger.PrefixType.DEFAULT);
                }
            } else if (cp.getPlayer().hasMetadata("invisRegion")) {
                cp.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                cp.getPlayer().removeMetadata("invisRegion", InvisRegions.getInstance());
                if (InvisRegions.getInstance().debugging) {
                    Logger.print(cp.getPlayer().getName() + " has left an InvisRegion!", Logger.PrefixType.DEFAULT);
                }
            }
        }
    }
}
