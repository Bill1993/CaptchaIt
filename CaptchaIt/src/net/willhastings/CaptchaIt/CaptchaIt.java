package net.willhastings.CaptchaIt;

import java.util.logging.Logger;

import net.willhastings.CaptchaIt.Listeners.ChatListener;
import net.willhastings.CaptchaIt.Listeners.CommandListener;
import net.willhastings.CaptchaIt.Listeners.UserListener;
import net.willhastings.CaptchaIt.util.Config;
import net.willhastings.CaptchaIt.util.MessageHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class CaptchaIt extends JavaPlugin
{
	private static final int CONFIGURATION_ERROR = 1;
	
	private static CaptchaIt plugin;
	
	public ChatListener chatListener = null;
	public CommandListener commandListener = null;
	public UserListener userListener = null;
	
	private Logger log = Logger.getLogger("Minecraft");
	
	public static MessageHandler messageHandler;
	
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
}
