package com.evileye;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuOpened;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@PluginDescriptor(
    name = "Evil Eye Filter",
    description = "Filters Evil Eye necklace teleport menu to only show your unlocked regions",
    tags = {"leagues", "teleport", "evil eye", "filter"}
)
public class EvilEyeFilterPlugin extends Plugin
{
    private static final int EVIL_EYE_ITEM_ID = 33227;

    private static final Set<String> ALWAYS_KEEP = Set.of(
        "Cancel",
        "Examine",
        "Use",
        "Drop",
        "Wear",
        "Teleport",         // covers "Teleport Evil eye >"
        "Last-destination"  // covers last teleport shortcut
    );

    @Inject
    private Client client;

    @Inject
    private EvilEyeFilterConfig config;

    @Provides
    EvilEyeFilterConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(EvilEyeFilterConfig.class);
    }

    /**
     * Builds the allowed region set dynamically from the user's config toggles.
     */
    private Set<String> getAllowedRegions()
    {
        Set<String> allowed = new HashSet<>();
        if (config.misthalin())  allowed.add("Misthalin");
        if (config.asgarnia())   allowed.add("Asgarnia");
        if (config.karamja())    allowed.add("Karamja");
        if (config.kandarin())   allowed.add("Kandarin");
        if (config.desert())     allowed.add("Desert");
        if (config.morytania())  allowed.add("Morytania");
        if (config.fremennik())  allowed.add("Fremennik");
        if (config.tirannwn())   allowed.add("Tirannwn");
        if (config.wilderness()) allowed.add("Wilderness");
        if (config.kourend())    allowed.add("Great Kourend");
        if (config.varlamore())  allowed.add("Varlamore");
        return allowed;
    }

    @Subscribe
    public void onMenuOpened(MenuOpened event)
    {
        MenuEntry[] entries = event.getMenuEntries();

        // Check if any entry involves the Evil Eye necklace
        boolean isEvilEyeMenu = Arrays.stream(entries).anyMatch(e ->
            e.getItemId() == EVIL_EYE_ITEM_ID
        );

        if (!isEvilEyeMenu)
        {
            return;
        }

        Set<String> allowedRegions = getAllowedRegions();

        MenuEntry[] filtered = Arrays.stream(entries)
            .filter(e -> {
                String option = stripColorTags(e.getOption());
                String target = stripColorTags(e.getTarget());

                // Always keep standard item options
                if (ALWAYS_KEEP.contains(option))
                {
                    return true;
                }

                // Keep entries whose option or target matches an allowed region
                for (String region : allowedRegions)
                {
                    if (option.contains(region) || target.contains(region))
                    {
                        return true;
                    }
                }

                return false;
            })
            .toArray(MenuEntry[]::new);

        client.setMenuEntries(filtered);
    }

    /**
     * Strips RuneLite color tags (e.g. <col=ff9040>) from menu text
     * so region name matching works reliably.
     */
    private String stripColorTags(String text)
    {
        if (text == null) return "";
        return text.replaceAll("<[^>]*>", "");
    }
}
