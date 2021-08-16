class Solution {

    public String minWindow(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[] lowerCase = new int[26];
        int[] upperCase = new int[26];

        processInput(b, m, lowerCase, upperCase);

        String st = process(a, n, lowerCase, upperCase);

        return st;
    }

    private String process(String a, int n, int[] lowerCase, int[] upperCase) {
        int[] lowerCaseCurr = new int[26];
        int[] upperCaseCurr = new int[26];
        int shortest = Integer.MAX_VALUE;
        int shortestI = 0;
        int shortestJ = 0;
        int left=0,right=0;
        addChar(lowerCase, upperCase, lowerCaseCurr, upperCaseCurr, a.charAt(0));
        while(left<=n-1 && right <= n-1) {
            boolean isValid = checkValid(lowerCase, upperCase, lowerCaseCurr, upperCaseCurr);
            if (isValid) {
                int temp = right-left+1;
                if (temp < shortest) {
                    shortest = right-left+1;
                    shortestI = left;
                    shortestJ = right;
                }
                removeChar(lowerCase, upperCase, lowerCaseCurr, upperCaseCurr, a.charAt(left));
                left++;
            } else {
                if (right < n-1) {
                    right++;
                    addChar(lowerCase, upperCase, lowerCaseCurr, upperCaseCurr, a.charAt(right));
                } else {
                    removeChar(lowerCase, upperCase, lowerCaseCurr, upperCaseCurr, a.charAt(left));
                    left++;
                }
            }
        }

        print(lowerCase, upperCase, lowerCaseCurr, upperCaseCurr, shortest, shortestI, shortestJ);

        if (shortest != Integer.MAX_VALUE) {
            StringBuilder st = new StringBuilder();
            for (int x = shortestI; x <= shortestJ; x++) {
                st.append(a.charAt(x));
            }
            System.out.println(st);
            return st.toString();
        } else {
            return "";
        }
    }

    private void print(int[] lowerCase, int[] upperCase, int[] lowerCaseCurr, int[] upperCaseCurr, int shortest, int shortestI, int shortestJ) {
        System.out.println();
        System.out.println("lowerCase       -> " + Arrays.toString(lowerCase));
        System.out.println("upperCase       -> " + Arrays.toString(upperCase));
        System.out.println("lowerCaseCurr -> " + Arrays.toString(lowerCaseCurr));
        System.out.println("upperCaseCurr -> " + Arrays.toString(upperCaseCurr));
        System.out.println("shortest - " + shortest);
        System.out.println("shortestI - " + shortestI);
        System.out.println("shortestJ - " + shortestJ);
        System.out.println();
    }

    private static void removeChar(int[] lowerCase, int[] upperCase, int[] lowerCaseActual, int[] upperCaseActual, char d) {
        if (d >= 'a' && d <= 'z') {
            lowerCaseActual[d % 'a']--;
        } else if (d >= 'A' && d <= 'Z') {
            upperCaseActual[d % 'A']--;
        }
    }

    private void addChar(int[] lowerCase, int[] upperCase, int[] lowerCaseActual, int[] upperCaseActual, char d) {
        if (d >= 'a' && d <= 'z') {
            lowerCaseActual[d % 'a']++;
        } else if (d >= 'A' && d <= 'Z') {
            upperCaseActual[d % 'A']++;
        }
    }

    private void processInput(String str, int len, int[] lowerCaseArr, int[] upperCaseArr) {
        for (int i=0;i<len;i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                lowerCaseArr[c%'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                upperCaseArr[c%'A']++;
            } else {
                System.out.println("Given input character " + c + " not valid at position - " + i);
            }
        }
    }

    private boolean checkValid(int[] lowerCase, int[] upperCase, int[] lowerCaseActual, int[] upperCaseActual) {
        for (int i=0;i<26;i++)
            if (lowerCaseActual[i] < lowerCase[i] || upperCaseActual[i] < upperCase[i])
                return false;
        return true;
    }
    
}
