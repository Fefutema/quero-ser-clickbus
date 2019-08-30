package br.com.futema.utils;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseLocalH2IT {

	private static Logger LOGGER = LoggerFactory.getLogger(BaseLocalH2IT.class);
	
	@Autowired
	private DataSource dataSource;
	
	private static Boolean dataBaseConsoleInitialized = false;
	private static Boolean dataBaseLoaded = false;
	
	@Before
	public void initBase() {
		setupInMemoryH2DataBaseConsole();
		loadDatabase();
	}
	
	private void loadDatabase() {
		if (dataBaseLoaded) return;
		
		try {
			if (getClass().isAnnotationPresent(LoadTestData.class)) {
				LoadTestData dataToLoadAnnotation = getClass().getAnnotation(LoadTestData.class);
				ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource(dataToLoadAnnotation.script()));
			}
		} catch (SQLException e) {
			LOGGER.error("Error on run script in H2", e);
		}
		
		dataBaseLoaded = true;
	}
	
	private void setupInMemoryH2DataBaseConsole() {
		if (dataBaseConsoleInitialized) return;
		
		try {
			if (getClass().isAnnotationPresent(SetupH2Console.class)) {
				Server server;
				server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8080");
				server.start();
			}
		} catch (SQLException e) {
			LOGGER.error("Start up error", e);
		}
		
		dataBaseConsoleInitialized = true;
	}
}
