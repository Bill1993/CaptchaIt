package net.willhastings.CaptchaIt.Listeners;

import net.willhastings.CaptchaIt.CaptchaIt;

import org.bukkit.event.Listener;

public class ChatListener implements Listener
{
	public ChatListener(CaptchaIt captchaIt)
	{
		captchaIt.getServer().getPluginManager().registerEvents(this, captchaIt);
	}
}
