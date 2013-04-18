package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CaptchaIt;
import net.willhastings.CaptchaIt.util.User;

import org.apache.commons.lang.RandomStringUtils;
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
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if(!CaptchaIt.userMap.containsKey(player))
		{
			CaptchaIt.userMap.put(player, new User(RandomStringUtils.randomAlphanumeric(5)));
		}
	}
}
