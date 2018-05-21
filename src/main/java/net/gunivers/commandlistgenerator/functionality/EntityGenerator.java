package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.core.minecraft.Entity;
import net.gunivers.core.minecraft.EntityType;

/**
 * @author A~Z Generate Entity values
 */
@Deprecated
public class EntityGenerator {
	EntityType et;

	/**
	 * <strong>Constructor</strong>
	 *
	 * @param et
	 *            a EntityType
	 */
	public EntityGenerator(EntityType et) {
		this.et = et;
	}

	/**
	 * Generate Simple Entity
	 *
	 * @param et
	 *            a EntityType
	 * @return ArrayList<String> generated Entity names
	 */
	public static ArrayList<String> generate(EntityType et) {
		ArrayList<String> commands = new ArrayList<>();

		for (Entity entity : Entity.values()) {
			if (EntityType.checkEntity(entity, et))
				commands.add(entity.name());
		}

		return commands;
	}

	/**
	 * @param et
	 *            a EntityType
	 * @return String name of et
	 */
	public static String toString(EntityType et) {
		return et.toString();
	}

	/**
	 * Generate simple Entity
	 *
	 * @return ArrayList<String> generated Entity names
	 */
	public ArrayList<String> generate() {
		ArrayList<String> commands = new ArrayList<>();

		for (Entity entity : Entity.values()) {
			if (EntityType.checkEntity(entity, et))
				commands.add(entity.name());
		}

		return commands;
	}

	public String toString() {
		return "Entity";
	}
}
