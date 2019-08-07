package com.piggest.minecraft.bukkit.utils;

import org.bukkit.Location;

public class Radio {
	public static final int light_speed = 299792458;
	public static final double antenna_bandwidth = 0.2; // 天线半带宽
	public static final int max_channel_bandwidth = 5; // 频道带宽最大占用天线带宽的几分之一

	/*
	 * 香农公式，带宽(kHz)和信噪比得到比特率(kbit/s)
	 */
	public static int Shannon_equation(int channel_bandwidth, int ston) {
		return (int) (channel_bandwidth * Math.log(1 + ston) / Math.log(2));
	}

	/*
	 * 获得阻抗，单位为欧姆
	 */
	public static int z(int n, int freq) {
		int central_freq = get_central_freq(n);
		int d = Math.abs(central_freq - freq);
		return (int) (73.0 + 0.414 * 73.0 / antenna_bandwidth / central_freq * d);
	}

	/*
	 * 获得单位频率的发射功率
	 */
	public static int get_power(int u, int n, int channel_bandwidth, int freq) {
		int z = z(n, freq);
		return u * u * freq / z * freq / z * n * n / channel_bandwidth;
	}

	/*
	 * 获得不同天线的中心频率，单位kHz
	 */
	public static int get_central_freq(int n) {
		return 75000 / n;
	}

	/*
	 * 获得一定距离处的单位频率的信号功率
	 */
	public static double get_power_at(Location source_location, int radiant_power, int central_freq, Location loc) {
		double distance = source_location.distance(loc);
		return radiant_power / distance / distance;
	}

}