package data;

public enum TileType {

	Grass("grass2", true), Dirt("dust2", false), Water("water2", false), NULL("water2", false), Grass2("grass3", true), Dirt2("path3", false), Water2("water3", false), NULL2("water3", false);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
