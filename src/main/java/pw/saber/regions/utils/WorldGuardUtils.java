package pw.saber.regions.utils;

import com.google.common.collect.Lists;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import pw.saber.regions.InvisRegions;

import java.util.List;

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
public class WorldGuardUtils {

    public static boolean isInRegionsFast(Location location, String... regionNames) {
        return isInRegionsFast(location, Lists.newArrayList(regionNames));
    }

    public static boolean isInRegionsContainsFast(Location location, List<String> regionNames) {
        RegionManager regionMan = InvisRegions.getInstance().getWG().getGlobalRegionManager().get(location.getWorld());
        if (regionMan == null) return false;
        ApplicableRegionSet regionManager = regionMan.getApplicableRegions(location);
        if (regionManager == null) return false;
        for (ProtectedRegion region : regionManager) {
            for (String str : regionNames) {
                if (region.getId().contains(str)) return true;
            }
        }
        return false;
    }

    public static boolean isInRegionsFast(Location location, List<String> regionNames) {
        RegionManager regionMan = InvisRegions.getInstance().getWG().getGlobalRegionManager().get(location.getWorld());
        if (regionMan == null) return false;
        ApplicableRegionSet regionManager = regionMan.getApplicableRegions(location);
        if (regionManager == null) return false;
        for (ProtectedRegion region : regionManager) {
            if (regionNames.contains(region.getId())) return true;
        }
        return false;
    }
}
