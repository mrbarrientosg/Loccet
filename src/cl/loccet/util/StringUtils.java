package cl.loccet.util;

public class StringUtils {

    /**
     * Algoritmo obtenido del siguiente link https://stackoverflow.com/a/25379180
     * @param src String principal
     * @param what String que quiero ver si esta contenido en src
     * @return true si what esta contenido en src, en caso contrario false
     */
    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }
}
