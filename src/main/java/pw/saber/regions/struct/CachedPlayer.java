package pw.saber.regions.struct;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * SaberInvisRegions - Developed by Driftay.
 * All rights reserved 2020.
 * Creation Date: 5/26/2020
 */
public class CachedPlayer {

    private Player player;

    private Location location;

    private String worldName;

    private World world;

    public CachedPlayer(Player player, Location location, World world, String worldName) {
        this.player = player;
        this.location = location;
        this.worldName = worldName;
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public String getWorldName() {
        return worldName;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location;
    }
}
