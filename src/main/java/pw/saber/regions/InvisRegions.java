package pw.saber.regions;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pw.saber.regions.struct.impl.LocationTask;
import pw.saber.regions.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */

public class InvisRegions extends JavaPlugin {

    public static InvisRegions instance;
    public List<String> invisRegions = new ArrayList<>();
    public boolean debugging;

    public static InvisRegions getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") == null) {
            this.getServer().getPluginManager().disablePlugin(this);
            Logger.print("DISABLING PLUGIN DUE TO WORLDGUARD NOT BEING VALID", Logger.PrefixType.FAILED);
        }
        debugging = getConfig().getBoolean("InvisRegions.debug");
        invisRegions = getConfig().getStringList("InvisRegions.regions");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new LocationTask(), 100, 60);
    }

    public WorldGuardPlugin getWG() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (!(plugin instanceof WorldGuardPlugin)) return null;
        return (WorldGuardPlugin) plugin;
    }

}
