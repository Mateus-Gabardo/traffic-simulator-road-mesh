
package br.udesc.traffic.simulator.road.mesh.util;


public class ImageUtils {
    
    public static String getImagePath() {
        return "./src/main/java/resources/assets/";
    }
	
    public static String createImagePath(String image) {
        return createImagePath(image, "png");
    }

    public static String createImagePath(String image, String extensao) {
        return getImagePath()+"/"+image+"."+extensao;
    }
}
