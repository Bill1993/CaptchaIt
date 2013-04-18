package net.willhastings.CaptchaIt;

import java.util.logging.Logger;

import net.willhastings.CaptchaIt.Listeners.ChatListener;
import net.willhastings.CaptchaIt.Listeners.CommandListener;

import org.bukkit.plugin.java.JavaPlugin;

public class CaptchaIt extends JavaPlugin
{
	private static CaptchaIt plugin;
	
	public ChatListener chatListener = null;
	public CommandListener commandListener = null;
	public UserListener userListener = null;
	
	private Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable()
	{
		plugin = this;
		
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
		
	}
	
	public static CaptchaIt getPlugin()
	{
		return plugin;
	}
}
