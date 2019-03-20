package de.greenman1805.spawnextra;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class SpawnAPI {

	private static YamlConfiguration getConfig() {
		File file = new File("plugins//SpawnExtra//config.yml");
		return YamlConfiguration.loadConfiguration(file);
	}

	private static void setConfig(String path, Object value) throws IOException {
		File file = new File("plugins//SpawnExtra//config.yml");
		YamlConfiguration cfg = getConfig();
		cfg.set(path, value);
		cfg.save(file);
	}



	public static void setSpawn(Location loc)  {
		try {
			setLocation(loc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static Location getLocation()  {
		World world = Bukkit.getServer().getWorld(getConfig().getString("spawn.location.world"));
		double x = getConfig().getDouble("spawn.location.x");
		double y = getConfig().getDouble("spawn.location.y");
		double z = getConfig().getDouble("spawn.location.z");
		float yaw = (float) getConfig().getDouble("spawn.location.yaw");
		float pitch = (float) getConfig().getDouble("spawn.location.pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}

	private static void setLocation(Location loc) throws IOException {
		setConfig("spawn.location.world", loc.getWorld().getName());
		setConfig("spawn.location.x", loc.getX());
		setConfig("spawn.location.y", loc.getY());
		setConfig("spawn.location.z", loc.getZ());
		setConfig("spawn.location.yaw", (double) loc.getYaw());
		setConfig("spawn.location.pitch", (double) loc.getPitch());
	}

	public static void spawn(Player p)  {
		p.teleport(getLocation());
	}
	
	
	public static void lobby(Player p) {
		 ByteArrayDataOutput out = ByteStreams.newDataOutput();
		 out.writeUTF("Connect");
		 out.writeUTF("Lobby");   
		 p.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}

}
