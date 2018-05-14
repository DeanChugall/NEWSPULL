package club.androidx.mvc.model.utilities;

public class FixUrlToImageExtension {

    public static String fixUrl(String path) {

        String fixed = "";

       // String extensionString = path.substring(path.length()-4, path.length());

        if(!path.isEmpty()  &&  !path.equals(".jpg")){
            int lenght = path.length();
            return path.substring(0, lenght - 4) + ".jpg";

        }

        return path;
    }



}
