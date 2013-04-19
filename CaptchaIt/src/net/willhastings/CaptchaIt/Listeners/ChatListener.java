package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CITFunction;
import net.willhastings.CaptchaIt.CaptchaIt;
import net.willhastings.CaptchaIt.util.Config;
import net.willhastings.CaptchaIt.util.User;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener
{	
	public ChatListener(CaptchaIt captchaIt)
	{
		captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSendChat(AsyncPlayerChatEvent event)
	{
		if(event.isCancelled() || !Config.REQUIRED_CHAT) return;
		
		Player player = event.getPlayer();
		if(!CITFunction.userExists(player)) CITFunction.addUser(player);
		
		User user = CITFunction.getUser(player);
		
		if(user.hasPassed()) return;
		else
		{
			String pMessage = event.getMessage();
			if(user.getCaptcha().equals(pMessage))
			{
				user.setPassed(true);
				player.sendMessage(CaptchaIt.messageHandler.getMessage("user.captcha.pass", true));
				event.setCancelled(true);
			}
			else 
			{
				player.sendMessage(CaptchaIt.messageHandler.getFormatedMessage("user.captcha.fail.chat", true, user.getCaptcha()));
				event.setCancelled(true);
			}
		}
	}
}
