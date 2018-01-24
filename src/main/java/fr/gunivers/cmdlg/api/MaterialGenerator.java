package fr.gunivers.cmdlg.api;

import fr.gunivers.cmdlg.util.Material;

public abstract class MaterialGenerator extends BasicGenerator {
	
	
	/**Get the generated material list
	 * 
	 * @return
	 */
	public abstract Iterable<? extends Material> getMaterialList();

}
