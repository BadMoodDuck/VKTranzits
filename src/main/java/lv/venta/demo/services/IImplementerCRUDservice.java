package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Implementer;

public interface IImplementerCRUDservice {

	// izveidot jaunu istenotaju
	public abstract Implementer insertNewImplementer(String name);

	// atjaunot esoso istenotaju
	public abstract Implementer updateImplementerById(int implementerId);

	// izdzest istenotaju
	public abstract ArrayList<Implementer> deleteImplementerById(int implementerId);

	// radit visus istenotajus
	public abstract ArrayList<Implementer> selectAllImplementers();

}
