package com.piggest.minecraft.bukkit.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.io.Files;
import com.piggest.minecraft.bukkit.dropper_shop.Dropper_shop_plugin;

public abstract class Ext_config {
	protected File config_file;
	protected YamlConfiguration config;
	protected String file_name;

	public Ext_config(String file_name) {
		this.file_name = file_name;
	}

	public YamlConfiguration get_config() {
		return this.config;
	}

	public void load() {
		this.config_file = new File(Dropper_shop_plugin.instance.getDataFolder(), file_name);
		if (!config_file.exists()) {
			try {
				config_file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(config_file);
	}

	public boolean save() {
		try {
			config.save(this.config_file);
		} catch (IOException e) {
			Dropper_shop_plugin.instance.getLogger().severe(file_name + "文件保存错误!");
			return false;
		}
		return true;
	}

	public boolean backup() {
		File config_file_backup = new File(config_file.getAbsolutePath() + ".bak");
		try {
			if (config_file_backup.exists()) {
				config_file_backup.delete();
				config_file_backup.createNewFile();
			}
			Files.copy(config_file, config_file_backup);
		} catch (IOException e) {
			Dropper_shop_plugin.instance.getLogger().severe("原" + file_name + "备份失败");
			return false;
		}
		Dropper_shop_plugin.instance.getLogger().info("原" + file_name + "已保存至" + config_file_backup.getAbsolutePath());
		return true;
	}
}
