package de.greenman1805.spawnextra;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommands implements CommandExecutor {
	public Main plugin;

	public SpawnCommands(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("setspawn")) {
				if (args.length == 0) {
					if (p.hasPermission("spawnextra.admin")) {
						SpawnAPI.setSpawn(p.getLocation());
						p.sendMessage(Main.prefix + "§aDer Spawn wurde gesetzt.");
					} else {
						p.sendMessage(Main.prefix + "§cKeine Rechte.");
					}
				} else {
					p.sendMessage(Main.prefix + "/setspawn");
				}
			}

			if (cmd.getName().equalsIgnoreCase("lobby")) {
				if (args.length == 0) {
					if (p.hasPermission("spawnextra.teleportdelay")) {
						final Location loc = p.getLocation();
						p.sendMessage("§7Teleportiervorgang startet in §a" + Main.teleportdelay + " §7Sekunden. Nicht bewegen!");
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								if (loc.getBlockX() == p.getLocation().getBlockX() && loc.getBlockY() == p.getLocation().getBlockY()) {
									SpawnAPI.lobby(p);
								} else {
									p.sendMessage(Main.prefix + "§cTeleportiervorgang abgebrochen!");
								}
							}
						}, 20 * Main.teleportdelay);

					} else {
						SpawnAPI.lobby(p);
					}
				}
			}

			if (cmd.getName().equalsIgnoreCase("spawn")) {
				if (args.length == 0) {
					if (p.hasPermission("spawnextra.teleportdelay")) {
						final Location loc = p.getLocation();
						p.sendMessage("§7Teleportiervorgang startet in §a" + Main.teleportdelay + " §7Sekunden. Nicht bewegen!");
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								if (loc.getBlockX() == p.getLocation().getBlockX() && loc.getBlockY() == p.getLocation().getBlockY()) {
									SpawnAPI.spawn(p);
								} else {
									p.sendMessage(Main.prefix + "§cTeleportiervorgang abgebrochen!");
								}
							}
						}, 20 * Main.teleportdelay);

					} else {
						SpawnAPI.spawn(p);
					}
				}
			}

		}
		return false;
	}

}
