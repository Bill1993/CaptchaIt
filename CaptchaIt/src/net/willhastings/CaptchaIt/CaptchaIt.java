package net.willhastings.CaptchaIt;

import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;
import net.willhastings.CaptchaIt.Listeners.ChatListener;
import net.willhastings.CaptchaIt.Listeners.CommandListener;
import net.willhastings.CaptchaIt.Listeners.UserListener;
import net.willhastings.CaptchaIt.util.Config;
import net.willhastings.CaptchaIt.util.MessageHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CaptchaIt extends JavaPlugin
{
	private static final int CONFIGURATION_ERROR = 1;
	
	private static CaptchaIt plugin;
	
	public ChatListener chatListener = null;
	public CommandListener commandListener = null;
	public UserListener userListener = null;
	
	private static Logger log = Logger.getLogger("Minecraft");
	
	public static MessageHandler messageHandler;
	public static Permission permission = null;
	
	public void onEnable()
	{
		plugin = this;
		
		log.info("[CaptchaIt] is loading its configuration and message file...");
		Config.loadConfig(this);
		if(!Config.REQUIRED_CHAT && !Config.REQUIRED_CMD)
		{
			log.severe("[CaptchaIt] Config Error: You must require a captcha on commands, chat, or both!");
			this.disablePlugin(CONFIGURATION_ERROR);
		}
		log.info("[CaptchaIt] Configuration file has successfully loaded!");
		messageHandler = new MessageHandler(this);
		log.info("[CaptchaIt] Messages file has succesfully loaded!");
		
		if(this.getServer().getPluginManager().getPlugin("Vault") != null) setupPermissions();
		else log.info("[CaptchaIt] is using Bukkit perm instead of [Vault]");
		
		log.info("[CaptchaIt] Is loading listeners...");
		chatListener = new ChatListener(this);
		log.info("[CaptchaIt] Chat Listener has started!");
		commandListener = new CommandListener(this);
		log.info("[CaptchaIt] Command Listener has started!");
		userListener = new UserListener(this);
		log.info("[CaptchaIt] User Listener has started!");
	}
	
	public void onDisable()
	{
		log.info("[CaptchaIt] is now disabled");
	}
	
	public void disablePlugin(int reason)
	{
		if(reason == CONFIGURATION_ERROR) log.info("[CaptchaIt] Disabling due to Configuration Error!");
		this.getServer().getPluginManager().disablePlugin(this);
	}
	
	public static CaptchaIt getPlugin()
	{
		return plugin;
	}
	
	public static boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) 
        {
            permission = permissionProvider.getProvider();
            log.info("[CaptchaIt] is using [Vault] for permissions!");
        }
        return (permission != null);
    }
}
