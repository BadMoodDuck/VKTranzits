package lv.venta.demo.repos;

import lv.venta.demo.models.MyUser;

public interface IMyUserRepo {

	MyUser findByUsername(String username);

}
