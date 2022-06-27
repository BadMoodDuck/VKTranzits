package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Implementer;

public interface IImplementerCRUDservice {

	// izveidot jaunu istenotaju
	boolean insertNewImplementer(Implementer implementer);

	// atjaunot esoso istenotaju
	boolean updateImplementerById(int implementerId, Implementer implementer);

	// izdzest istenotaju
	boolean deleteImplementerById(int implementerId);

	// radit visus istenotajus
	ArrayList<Implementer> selectAllImplementers();

}
