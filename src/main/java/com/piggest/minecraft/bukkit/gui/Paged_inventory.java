package com.piggest.minecraft.bukkit.gui;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Paged_inventory implements Inventory {
	private Inventory internal;
	private int last_slot;
	private int next_slot;

	public Paged_inventory(Paged_inventory_holder holder, int size, String title, int last_slot, int next_slot) {
		this.internal = Bukkit.createInventory(holder, size, title);
		this.last_slot = last_slot;
		this.next_slot = next_slot;
		ItemStack last_item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemStack next_item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta last_meta = last_item.getItemMeta();
		ItemMeta next_meta = next_item.getItemMeta();
		last_meta.setDisplayName("§r上一页");
		next_meta.setDisplayName("§r下一页");
		last_item.setItemMeta(last_meta);
		next_item.setItemMeta(next_meta);
		this.internal.setItem(last_slot, last_item);
		this.internal.setItem(next_slot, next_item);
	}

	public int getSize() {
		return internal.getSize();
	}

	public int getMaxStackSize() {
		return internal.getMaxStackSize();
	}

	public void setMaxStackSize(int size) {
		internal.setMaxStackSize(size);
	}

	public ItemStack getItem(int index) {
		return internal.getItem(index);
	}

	public void setItem(int index, ItemStack item) {
		internal.setItem(index, item);
	}

	public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
		return internal.addItem(items);
	}

	public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
		return internal.removeItem(items);
	}

	public ItemStack[] getContents() {
		return internal.getContents();
	}

	public void setContents(ItemStack[] items) throws IllegalArgumentException {
		internal.setContents(items);
	}

	public ItemStack[] getStorageContents() {
		return internal.getStorageContents();
	}

	public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
		internal.setStorageContents(items);
	}

	public boolean contains(Material material) throws IllegalArgumentException {
		return internal.contains(material);
	}

	public boolean contains(ItemStack item) {
		return internal.contains(item);
	}

	public boolean contains(Material material, int amount) throws IllegalArgumentException {
		return internal.contains(material, amount);
	}

	public boolean contains(ItemStack item, int amount) {
		return internal.contains(item, amount);
	}

	public boolean containsAtLeast(ItemStack item, int amount) {
		return internal.containsAtLeast(item, amount);
	}

	public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
		return internal.all(material);
	}

	public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
		return internal.all(item);
	}

	public int first(Material material) throws IllegalArgumentException {
		return internal.first(material);
	}

	public int first(ItemStack item) {
		return internal.first(item);
	}

	public int firstEmpty() {
		return internal.firstEmpty();
	}

	public void remove(Material material) throws IllegalArgumentException {
		internal.remove(material);
	}

	public void remove(ItemStack item) {
		internal.remove(item);
	}

	public void clear(int index) {
		internal.clear(index);
	}

	public void clear() {
		internal.clear();
	}

	public List<HumanEntity> getViewers() {
		return internal.getViewers();
	}

	public InventoryType getType() {
		return internal.getType();
	}

	public Paged_inventory_holder getHolder() {
		return (Paged_inventory_holder) internal.getHolder();
	}

	public ListIterator<ItemStack> iterator() {
		return internal.iterator();
	}

	public ListIterator<ItemStack> iterator(int index) {
		return internal.iterator(index);
	}

	public Location getLocation() {
		return internal.getLocation();
	}

	public int get_current_page() {
		return this.getHolder().get_gui_page(this);
	}

	public void set_current_page(int page) {
		this.getHolder().set_gui_page(this, page);
	}

	public int get_page_size() {
		return this.getHolder().get_page_size();
	}

	public int get_last_slot() {
		return this.last_slot;
	}

	public int get_next_slot() {
		return this.next_slot;
	}
}
