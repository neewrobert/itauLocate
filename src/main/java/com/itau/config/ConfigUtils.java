package com.itau.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Classe para leitura dos arquivos de properties
 * @author newton
 *
 */
public class ConfigUtils {
	
	public static final Logger log = Logger.getLogger(ConfigUtils.class);
	
	/**
	 * Metodo que retorna o arquivo de properties
	 * @return props
	 * @throws IOException
	 */
	public static Properties getProp() {
		Properties props = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream("./src/main/resources/application.properties");
			props.load(file);
		} catch (FileNotFoundException e) {
			log.error("Arquivo de properties nao encontrado");
			
		} catch (IOException e) {
			log.error("Erro na leitura do arquivo de properties");
		}
		return props;
	}

}
