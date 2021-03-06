package com.piggest.minecraft.bukkit.nms.biome;

import java.util.Arrays;
import java.util.function.IntFunction;

import org.bukkit.block.Biome;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.piggest.minecraft.bukkit.dropper_shop.Dropper_shop_plugin;
import com.piggest.minecraft.bukkit.nms.NMS_manager;

import net.minecraft.server.v1_15_R1.BiomeBase;
import net.minecraft.server.v1_15_R1.BiomeStorage;

public class Packet_map_chunk_listener_1_15 extends PacketAdapter {
	Biome_modifier_1_15 biome_modifier;
	public Packet_map_chunk_listener_1_15() {
		super(Dropper_shop_plugin.instance, ListenerPriority.NORMAL, PacketType.Play.Server.MAP_CHUNK);
		this.biome_modifier = (Biome_modifier_1_15) NMS_manager.biome_modifier;
	}
	
	@Override
	public void onPacketSending(PacketEvent event) {
		PacketContainer packet = event.getPacket();
		StructureModifier<BiomeStorage> modifier = packet.getSpecificModifier(BiomeStorage.class);
		BiomeStorage biome_storge = modifier.read(0);
		Biome_storage_modifier_1_15 provider = (Biome_storage_modifier_1_15) NMS_manager.biome_modifier
				.get_biome_storge_modifier();
		BiomeBase[] biomes = provider.get_biomes(biome_storge);
		if (biomes == null) {
			return;
		}
		Arrays.parallelSetAll(biomes, new IntFunction<BiomeBase>() {
			@Override
			public BiomeBase apply(int i) {
				Biome biome = biome_modifier.get_biome(biomes[i]);
				Biome pretend = Dropper_shop_plugin.instance.get_biome_modify().get_pretend_biome(biome);
				return biome_modifier.get_biomebase(pretend);
			}
		});
		modifier.write(0, biome_storge);
	}
}
