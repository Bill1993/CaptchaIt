package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CITFunction;
import net.willhastings.CaptchaIt.CaptchaIt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UserListener implements Listener
{
	public UserListener(CaptchaIt captchaIt)
	{
		captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(!CITFunction.userExists(player))
		{
			CITFunction.addUser(player);
			player.sendMessage(CaptchaIt.messageHandler.getFormatedMessage("user.first.join", true, CITFunction.getUser(player).getCaptcha()));
		}
	}
}
