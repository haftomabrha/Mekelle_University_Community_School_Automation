public class UsernameFormatter {

    public String replaceRWithSlash(String formattedUsername) {
        if (formattedUsername == null) {
            return null; // Handle null input
        }
        return formattedUsername.replace('r', '/'); // Replace 'r' with '/'
    }

    public static void main(String[] args) {
        UsernameFormatter formatter = new UsernameFormatter();
        String modifiedUsername = "MUrEMP1578rSC24";
        String originalUsername = formatter.replaceRWithSlash(modifiedUsername);

        System.out.println("Modified Username: " + modifiedUsername);
        System.out.println("Original Username: " + originalUsername);
    }
}
