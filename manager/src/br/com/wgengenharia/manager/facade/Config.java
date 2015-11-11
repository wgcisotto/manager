package br.com.wgengenharia.manager.facade;

import java.util.ResourceBundle;

public class Config {
  
  private final static Config INSTANCE = new Config();
  private ResourceBundle resource;
  
  private Config() {
    try{
      resource = ResourceBundle.getBundle("manager-config");
    }catch(Throwable e){
      throw new IllegalStateException("Arquivo de configuracao [manager-config.properties] nao encontrado no classpath.");
    }
  }
  
  public final static String get(String chave) {
    return INSTANCE.resource.getString(chave);
  }
  
}
