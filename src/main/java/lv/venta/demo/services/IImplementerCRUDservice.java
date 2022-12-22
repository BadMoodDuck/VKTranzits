package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

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

	Page<Implementer> getPageList(int pageNr);

	Implementer readImplementerById(int id) throws Exception;

}
