package co.yabx.admin.portal.app.connect.service;

import java.util.Iterator;

public interface EntityManagerService {

	Iterator<?> executeQuery(String filename);
}
