package com.evileye;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EvilEyeFilterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EvilEyeFilterPlugin.class);
		RuneLite.main(args);
	}
}