package de.greenman1805.spawnextra;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;


public class Main extends JavaPlugin implements PluginMessageListener {
	public static String prefix = "§f[§9Spawn§f] §f";
	public static int teleportdelay;
	public static boolean teleport_on_join;
	public static Main instance;

	@Override
	public void onEnable() {
		try {
			checkFiles();
			setupConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		getValues();

		registerCommands("spawn", new SpawnCommands(this));
		registerCommands("lobby", new SpawnCommands(this));
		registerCommands("setspawn", new SpawnCommands(this));

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new SpawnListener(this), this);
		instance = this;
	    this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	


		  @Override
		  public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		    if (!channel.equals("BungeeCord")) {
		      return;
		    }
		  }
		

	private void registerCommands(String cmd, CommandExecutor exe) {
		getCommand(cmd).setExecutor(exe);
	}

	private void checkFiles() throws IOException {
		File file1 = new File("plugins//SpawnExtra");
		File file2 = new File("plugins//SpawnExtra//config.yml");

		if (!file1.isDirectory()) {
			file1.mkdir();
		}
		if (!file2.exists()) {
			file2.createNewFile();
		}
	}

	private void getValues() {
		File file = new File("plugins//SpawnExtra//config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		teleportdelay = cfg.getInt("Teleportdelay");
		teleport_on_join = cfg.getBoolean("Teleport on join");
	}

	private void setupConfig() throws IOException {
		File file = new File("plugins//SpawnExtra//config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		cfg.addDefault("Teleportdelay", 3);
		cfg.addDefault("Teleport on join", false);
		cfg.options().copyDefaults(true);
		cfg.save(file);
	}

}
