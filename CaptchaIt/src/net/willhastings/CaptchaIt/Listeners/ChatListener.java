package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CaptchaIt;
import net.willhastings.CaptchaIt.util.User;

import org.bukkit.ChatColor;
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
		if(event.isCancelled()) return;
		
		Player player = event.getPlayer();
		User user = CaptchaIt.userMap.get(player);
		
		if(user.hasPassed()) return;
		
		String pMessage = event.getMessage();
		
		if(pMessage.equals(user.getCaptcha()))
		{
			player.sendMessage(ChatColor.GREEN + "Thank you for prooving your a humanoid!");
			user.setPassed(true);
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "Please pass the human test. Enter the Captcha: " + ChatColor.GOLD + user.getCaptcha());
			event.setCancelled(true);
		}
	}
}
