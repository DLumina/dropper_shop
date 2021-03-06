package com.piggest.minecraft.bukkit.nms;

import com.piggest.minecraft.bukkit.dropper_shop.Dropper_shop_plugin;
import com.piggest.minecraft.bukkit.nms.biome.Biome_modifier;
import com.piggest.minecraft.bukkit.nms.biome.Biome_modifier_1_15;
import com.piggest.minecraft.bukkit.nms.biome.Packet_map_chunk_listener_1_15;
import com.piggest.minecraft.bukkit.nms.enchant.Enchant_manager;
import com.piggest.minecraft.bukkit.nms.enchant.Enchant_manager_1_15;
import com.piggest.minecraft.bukkit.nms.nbt.Ext_id;
import com.piggest.minecraft.bukkit.nms.nbt.Ext_id_1_15;

public class NMS_manager {
	public static Ext_id ext_id_provider = null;
	public static Raid_trigger raid_provider = null;
	public static Biome_modifier biome_modifier = null;
	public static Packet_map_chunk_listener_1_15 packet_map_chunk_listener = null;
	public static Enchant_manager enchant_manager = null;
	public NMS_manager(String version) {
		Dropper_shop_plugin.instance.getLogger().info("当前NMS:" + version);
		NMS_version nms_version = NMS_version.parse_version(version);
		switch (nms_version) {
		case v1_15:
			Dropper_shop_plugin.instance.getLogger().info("已适配NMS:" + version);
			ext_id_provider = new Ext_id_1_15();
			raid_provider = new Raid_trigger_1_15();
			biome_modifier = new Biome_modifier_1_15();
			packet_map_chunk_listener = new Packet_map_chunk_listener_1_15();
			enchant_manager = new Enchant_manager_1_15();
			break;
		default:
			Dropper_shop_plugin.instance.getLogger().warning("NMS未能适配!");
			ext_id_provider = new Ext_id_1_15();
			raid_provider = new Raid_trigger_1_15();
			biome_modifier = new Biome_modifier_1_15();
			packet_map_chunk_listener = new Packet_map_chunk_listener_1_15();
			enchant_manager = new Enchant_manager_1_15();
			break;
		}
	}
}
