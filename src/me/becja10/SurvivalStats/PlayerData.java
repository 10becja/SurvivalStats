package me.becja10.SurvivalStats;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerData {
	
	private FileConfiguration data = null;
	private File file = null;
	private String path;
	
	private UUID id;
	private String name;
	
	public PlayerData(Player p){
		id = p.getUniqueId();
		name = p.getName();
		
		path = SurvivalStats.instance.getDataFolder().getAbsolutePath() + File.separator + id.toString() + ".yml";
		loadData();
	}
	
	public UUID getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	
	public void saveData(){
		if(data == null || file == null){
			log("Data file was not loaded", Level.WARNING);
			return;
		}
		try{
			data.save(file);
		} catch(IOException ex){
			log("Unable to save data.", Level.WARNING);
		}
	}
	
	private void loadData(){
		if(file == null)
			file = new File(path);
		data = YamlConfiguration.loadConfiguration(file);
	}
	
	private void log(String msg, Level level){
		SurvivalStats.logger.log(level, "[SurvivalStats " + id + "]" + msg);
	}

}
