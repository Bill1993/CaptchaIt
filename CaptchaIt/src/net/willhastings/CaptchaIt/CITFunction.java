package net.willhastings.CaptchaIt;

import java.util.HashMap;

import net.willhastings.CaptchaIt.util.Config;
import net.willhastings.CaptchaIt.util.User;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.entity.Player;

public class CITFunction 
{
	private static HashMap<String, User> user = new HashMap<String, User>();
	
	public static boolean userExists(Player player)
	{
		return user.containsKey(player.getName());
	}
	
	public static User getUser(Player player)
	{
		return user.get(player.getName());
	}
	
	public static void addUser(Player player)
	{
		if(Config.GENERATE_RANDOM)
		{
			if(Config.ALPHANUMERIC) user.put(player.getName(), new User(RandomStringUtils.randomAlphanumeric(Config.CAPTCHA_LENGTH)));
			else user.put(player.getName(), new User(RandomStringUtils.randomAlphabetic(Config.CAPTCHA_LENGTH)));
		}
		else
		{
			int rand = randomGenINT(0, (Config.WORD_LIST.size() -1));
			Object randWordObj = Config.WORD_LIST.get(rand);
			String randWord = null;
			if(randWordObj instanceof String) randWord = (String) randWordObj;
			else CaptchaIt.getPlugin().getLogger().severe("[CaptchaIt] '" + randWordObj.toString() + "' is not an instance of a string, therefor can not be used!");
			user.put(player.getName(), new User(randWord));
		}
		if(hasPermission(player, "captchait.skip"))
		{
			user.get(player.getName()).setPassed(true);
		}
	}
	
	public static int randomGenINT(int min, int max) 
	{
		return (int) Math.round((min + Math.random() * (max - min)));
	}
	
	public static boolean hasPermission(Player player, String perm)
	{
		if (player.isOp()) return true;	
		else if(CaptchaIt.permission != null) return (CaptchaIt.permission.has(player, perm) || CaptchaIt.permission.has(player, "*"));
		else return (player.hasPermission(perm) || player.hasPermission("*"));
	}
}
