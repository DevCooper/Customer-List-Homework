public class StringUtils {

    /**
     * Adds spaces to an existing string to meet a specified length
     *
     * @param string the existing string to be padded
     *
     * @param size the desired end length of the string after padding
     *
     * @return the padded string
     */
    public static String rightPad(String string, int size){
        int sizeOfPad = size - string.length();
        StringBuilder builder = new StringBuilder();
        builder.append(string);
        for (int index = 0; index < sizeOfPad; index++){
            builder.append(' ');
        }
        return builder.toString();
    }
}
