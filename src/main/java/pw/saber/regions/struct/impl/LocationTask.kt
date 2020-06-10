package pw.saber.regions.struct.impl

import org.bukkit.Bukkit
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import pw.saber.regions.InvisRegions
import pw.saber.regions.struct.CachedPlayer
import pw.saber.regions.utils.Logger
import pw.saber.regions.utils.WorldGuardUtils

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
class LocationTask : Runnable {
    override fun run() {
        for (player in Bukkit.getServer().onlinePlayers) {
            val cp = CachedPlayer(player, player.location, player.world, player.world.name)
            //Players Persistence is an Operator We Skip Them.
            if (cp.player.isOp) continue
            if (WorldGuardUtils.isInRegionsContainsFast(cp.location, InvisRegions.instance.invisRegions)) {
                if (!cp.player.hasMetadata("invisRegion")) {
                    cp.player.setMetadata("invisRegion", FixedMetadataValue(InvisRegions.instance, true))
                }
                cp.player.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 2))
                if (InvisRegions.instance.debugging) {
                    Logger.print(cp.player.name + " has entered an InvisRegion!", Logger.PrefixType.DEFAULT)
                }
            } else if (cp.player.hasMetadata("invisRegion")) {
                cp.player.removePotionEffect(PotionEffectType.INVISIBILITY)
                cp.player.removeMetadata("invisRegion", InvisRegions.instance)
                if (InvisRegions.instance.debugging) {
                    Logger.print(cp.player.name + " has left an InvisRegion!", Logger.PrefixType.DEFAULT)
                }
            }
        }
    }
}