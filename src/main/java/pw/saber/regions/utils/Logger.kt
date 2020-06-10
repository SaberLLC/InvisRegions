package pw.saber.regions.utils

import org.bukkit.ChatColor
import pw.saber.regions.InvisRegions

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
object Logger {
    @JvmStatic
    fun print(message: String, type: PrefixType) {
        InvisRegions.instance.server.consoleSender.sendMessage(type.prefix + message)
    }

    enum class PrefixType(val prefix: String) {
        WARNING(ChatColor.RED.toString() + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD.toString() + "[InvisRegions] "), FAILED(ChatColor.RED.toString() + "FAILED: ");

    }
}