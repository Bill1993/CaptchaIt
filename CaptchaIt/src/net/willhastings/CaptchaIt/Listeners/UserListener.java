package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CaptchaIt;

import org.bukkit.event.Listener;

public class UserListener implements Listener
{
	public UserListener(CaptchaIt captchaIt)
	{
		captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
	}
}
