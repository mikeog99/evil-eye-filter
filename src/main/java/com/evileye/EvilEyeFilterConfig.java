package com.evileye;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("evileye")
public interface EvilEyeFilterConfig extends Config
{
    @ConfigSection(
        name = "Unlocked Regions",
        description = "Toggle the regions you have unlocked in Leagues",
        position = 0
    )
    String regionsSection = "regions";

    @ConfigItem(
        keyName = "misthalin",
        name = "Misthalin",
        description = "Show Misthalin in the teleport menu",
        section = regionsSection,
        position = 1
    )
    default boolean misthalin() { return false; }

    @ConfigItem(
        keyName = "asgarnia",
        name = "Asgarnia",
        description = "Show Asgarnia in the teleport menu",
        section = regionsSection,
        position = 2
    )
    default boolean asgarnia() { return false; }

    @ConfigItem(
        keyName = "karamja",
        name = "Karamja",
        description = "Show Karamja in the teleport menu",
        section = regionsSection,
        position = 3
    )
    default boolean karamja() { return false; }

    @ConfigItem(
        keyName = "kandarin",
        name = "Kandarin",
        description = "Show Kandarin in the teleport menu",
        section = regionsSection,
        position = 4
    )
    default boolean kandarin() { return false; }

    @ConfigItem(
        keyName = "desert",
        name = "Desert",
        description = "Show Desert in the teleport menu",
        section = regionsSection,
        position = 5
    )
    default boolean desert() { return false; }

    @ConfigItem(
        keyName = "morytania",
        name = "Morytania",
        description = "Show Morytania in the teleport menu",
        section = regionsSection,
        position = 6
    )
    default boolean morytania() { return false; }

    @ConfigItem(
        keyName = "fremennik",
        name = "Fremennik",
        description = "Show Fremennik in the teleport menu",
        section = regionsSection,
        position = 7
    )
    default boolean fremennik() { return false; }

    @ConfigItem(
        keyName = "tirannwn",
        name = "Tirannwn",
        description = "Show Tirannwn in the teleport menu",
        section = regionsSection,
        position = 8
    )
    default boolean tirannwn() { return false; }

    @ConfigItem(
        keyName = "wilderness",
        name = "Wilderness",
        description = "Show Wilderness in the teleport menu",
        section = regionsSection,
        position = 9
    )
    default boolean wilderness() { return false; }

    @ConfigItem(
        keyName = "kourend",
        name = "Great Kourend",
        description = "Show Great Kourend in the teleport menu",
        section = regionsSection,
        position = 10
    )
    default boolean kourend() { return false; }

    @ConfigItem(
        keyName = "varlamore",
        name = "Varlamore",
        description = "Show Varlamore in the teleport menu",
        section = regionsSection,
        position = 11
    )
    default boolean varlamore() { return false; }
}
