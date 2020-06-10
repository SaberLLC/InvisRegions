package pw.saber.regions.struct

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
class CachedPlayer(val player: Player, val location: Location, val world: World, val worldName: String)