package animalchess.game;

import animalchess.App;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Objects;

public class GameAssets {

	// a list of AssetIcon that represent the icon used by the animal 
	// ASSET_PATH_PREFIX is part of the directory to get the image 
    private static final String ASSET_PATH_PREFIX = "assets";
    private static final ScaleInstance DEFAULT_ICON_SCALE = new ScaleInstance(68, 68, Image.SCALE_SMOOTH);

    public static final AssetIcon ICON_RED_TIGER = new AssetIcon("red_tiger.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_LION = new AssetIcon("red_lion.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_CAT = new AssetIcon("red_cat.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_DOG = new AssetIcon("red_dog.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_ELEPHANT = new AssetIcon("red_elephant.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_MOUSE = new AssetIcon("red_mouse.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_WOLF = new AssetIcon("red_wolf.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_RED_LEOPARD = new AssetIcon("red_leopard.png", DEFAULT_ICON_SCALE);

    public static final AssetIcon ICON_BLUE_TIGER = new AssetIcon("blue_tiger.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_LION = new AssetIcon("blue_lion.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_CAT = new AssetIcon("blue_cat.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_DOG = new AssetIcon("blue_dog.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_ELEPHANT = new AssetIcon("blue_elephant.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_MOUSE = new AssetIcon("blue_mouse.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_WOLF = new AssetIcon("blue_wolf.png", DEFAULT_ICON_SCALE);
    public static final AssetIcon ICON_BLUE_LEOPARD = new AssetIcon("blue_leopard.png", DEFAULT_ICON_SCALE);

    // constructor for assetIcon 
    public static class AssetIcon {
        @Getter(lazy = true)
        private final ImageIcon imageIcon = getAssetIcon();

        private String path;
        private final ScaleInstance scaleInstance;

        public AssetIcon(String path, ScaleInstance scaleInstance) {
            this.path = path;
            this.scaleInstance = scaleInstance;
        }
        // getting the image for making the icon using the path String 
        private ImageIcon getAssetIcon() {
            Objects.requireNonNull(path, "Asset path cannot be null");
            Objects.requireNonNull(scaleInstance, "Scale instance cannot be null");


            URL pathURL = App.class.getClassLoader().getResource(path);
            // if the first attempt to get the image is failed, change path to use another directory 
            if(pathURL == null)
            	
                path = ASSET_PATH_PREFIX + File.separator + path;
            	pathURL = App.class.getClassLoader().getResource(path);
            	if (pathURL == null) {
            		throw new RuntimeException("Asset not found: " + path);
            	}
            Image scaledImage = new ImageIcon(pathURL)
                    .getImage()
                    .getScaledInstance(scaleInstance.width(), scaleInstance.height(), scaleInstance.scaleAlgorithmType());
            return new ImageIcon(scaledImage);
        }

    }

    public record ScaleInstance(int width, int height, int scaleAlgorithmType) {}

}
