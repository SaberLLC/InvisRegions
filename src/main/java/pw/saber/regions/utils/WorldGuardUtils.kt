package pw.saber.regions.utils

import com.google.common.collect.Lists
import org.bukkit.Location
import pw.saber.regions.InvisRegions

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
object WorldGuardUtils {
    fun isInRegionsFast(location: Location, vararg regionNames: String?): Boolean {
        return isInRegionsFast(location, Lists.newArrayList(*regionNames))
    }

    fun isInRegionsContainsFast(location: Location, regionNames: List<String?>): Boolean {
        val regionMan = InvisRegions.instance.wG.globalRegionManager[location.world] ?: return false
        val regionManager = regionMan.getApplicableRegions(location) ?: return false
        for (region in regionManager) {
            for (str in regionNames) {
                if (region.id.contains(str!!)) return true
            }
        }
        return false
    }

    private fun isInRegionsFast(location: Location, regionNames: List<String?>): Boolean {
        val regionMan = InvisRegions.instance.wG.globalRegionManager[location.world] ?: return false
        val regionManager = regionMan.getApplicableRegions(location) ?: return false
        for (region in regionManager) {
            if (regionNames.contains(region.id)) return true
        }
        return false
    }
}