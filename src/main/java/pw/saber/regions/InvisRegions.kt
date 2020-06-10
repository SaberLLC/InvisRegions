package pw.saber.regions

import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import pw.saber.regions.struct.impl.LocationTask
import pw.saber.regions.utils.Logger
import pw.saber.regions.utils.Logger.print
import java.util.*

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
class InvisRegions : JavaPlugin() {
    var invisRegions: List<String> = ArrayList()
    var debugging = false
    override fun onEnable() {
        instance = this
        saveDefaultConfig()
        config.options().copyDefaults(true)
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") == null) {
            server.pluginManager.disablePlugin(this)
            print("DISABLING PLUGIN DUE TO WORLDGUARD NOT BEING VALID", Logger.PrefixType.FAILED)
        }
        debugging = config.getBoolean("InvisRegions.debug")
        invisRegions = config.getStringList("InvisRegions.regions")
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, LocationTask(), 100, 60)
    }

    val wG: WorldGuardPlugin
        get() {
            val plugin = server.pluginManager.getPlugin("WorldGuard")
            return (if (plugin !is WorldGuardPlugin) null else plugin) as WorldGuardPlugin
        }

    companion object {
        lateinit var instance: InvisRegions
    }
}