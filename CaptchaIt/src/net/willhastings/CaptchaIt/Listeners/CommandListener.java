package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CaptchaIt;

import org.bukkit.event.Listener;

public class CommandListener implements Listener
{
	public CommandListener(CaptchaIt captchaIt)
	{
		captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
	}
}
