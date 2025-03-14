import java.util.*;

public class LinkShortener {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private Map<String, String> urlMap = new HashMap<>();
    private Map<String, String> shortToLongMap = new HashMap<>();
    private Random random = new Random();
    
    public String shortenURL(String longUrl) {
        if (urlMap.containsKey(longUrl)) {
            return urlMap.get(longUrl);
        }

        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (shortToLongMap.containsKey(shortUrl));

        urlMap.put(longUrl, shortUrl);
        shortToLongMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String expandURL(String shortUrl) {
        return shortToLongMap.getOrDefault(shortUrl, "URL not found");
    }

    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkShortener shortener = new LinkShortener();

        while (true) {
            System.out.println("\n1. Shorten a URL\n2. Expand a URL\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter long URL: ");
                String longUrl = scanner.nextLine();
                String shortUrl = shortener.shortenURL(longUrl);
                System.out.println("Shortened URL: " + shortUrl);
            } 
            else if (choice == 2) {
                System.out.print("Enter short URL: ");
                String shortUrl = scanner.nextLine();
                System.out.println("Original URL: " + shortener.expandURL(shortUrl));
            } 
            else {
                System.out.println("Exiting...");
                break;
            }
        }
        scanner.close();
    }
}
