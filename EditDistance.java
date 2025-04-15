public class EditDistance {

    public static int editDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        // Preenchendo a tabela base (casos em que uma das strings é vazia)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // remoções
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // inserções
        }

        // Preenchendo a tabela de forma bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Match (custo zero)
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Substituição, inserção, ou remoção
                    int replace = dp[i - 1][j - 1] + 1;
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;

                    dp[i][j] = Math.min(replace, Math.min(insert, delete));
                }
            }
        }

        return dp[m][n]; // Distância de edição final
    }

    public static void main(String[] args) {
        String s1 = "Casablanca";
        String s2 = "Portentoso";

        int distance1 = editDistance(s1, s2);
        System.out.println("Distância de edição (Casablanca vs Portentoso): " + distance1);

        String s3 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to " +
                    "simplify the build processes in the Jakarta Turbine project. There were several" + 
                    " projects, each with their own Ant build files, that were all slightly different." +
                    "JARs were checked into CVS. We wanted a standard way to build the projects, a clear "+ 
                    "definition of what the project consisted of, an easy way to publish project information" +
                    "and a way to share JARs across several projects. The result is a tool that can now be" +
                    "used for building and managing any Java-based project. We hope that we have created " +
                    "something that will make the day-to-day work of Java developers easier and generally help " +
                    "with the comprehension of any Java-based project.";

        String s4 = "This post is not about deep learning. But it could be might as well. This is the power of " +
                    "kernels. They are universally applicable in any machine learning algorithm. Why you might" +
                    "ask? I am going to try to answer this question in this article." + 
                    "Go to the profile of Marin Vlastelica Pogančić" + 
                    "Marin Vlastelica Pogančić Jun";

        int distance2 = editDistance(s3, s4);
        System.out.println("Distância de edição (textos longos): " + distance2);
    }
}
