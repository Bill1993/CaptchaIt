package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CITFunction;
import net.willhastings.CaptchaIt.CaptchaIt;
import net.willhastings.CaptchaIt.util.Config;
import net.willhastings.CaptchaIt.util.User;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener
{
	public CommandListener(CaptchaIt captchaIt)
	{
		captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) 
	{
		System.out.println("1");
		if(event.isCancelled() || !Config.REQUIRED_CMD) return;
		
		Player player = event.getPlayer();
		if(!CITFunction.userExists(player)) CITFunction.addUser(player);
		
		User user = CITFunction.getUser(player);
		System.out.println("2");
		
		if(user.hasPassed()) return;
		else
		{
			System.out.println("3");
			player.sendMessage(CaptchaIt.messageHandler.getFormatedMessage("user.captcha.fail.command", true, user.getCaptcha()));
			event.setCancelled(true);
		}
	}
}
