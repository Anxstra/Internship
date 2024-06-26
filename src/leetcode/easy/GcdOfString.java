package leetcode.easy;

public class GcdOfString {

    public String gcdOfStringsFastest(String str1, String str2) {
        if(str2.length()>str1.length()){
            return gcdOfStringsFastest(str2, str1);
        }
        if(str2.equals(str1)){
            return str1;
        }
        if(str1.startsWith(str2)){
            return gcdOfStringsFastest( str1.substring(str2.length()), str2);
        }
        return "";
    }

    public String gcdOfStringsMine(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        for(int i = l2; i >= 1; i--) {
            if (l1 % i == 0 && l2 % i == 0) {
                String seq = str2.substring(0, i);
                if (seq.repeat(l1 / i).equals(str1) && seq.repeat(l2 / i).equals(str2)) {
                    return seq;
                }
            }
        }

        return "";
    }

    public static void main(String[] args) {
        GcdOfString gcdOfString = new GcdOfString();

        System.out.println("AB".equals(gcdOfString.gcdOfStringsFastest("ABABAB", "ABAB")));
        System.out.println("".equals(gcdOfString.gcdOfStringsFastest("LEET", "CODE")));
        System.out.println("AB".equals(gcdOfString.gcdOfStringsMine("ABABAB", "ABAB")));
        System.out.println("".equals(gcdOfString.gcdOfStringsMine("LEET", "CODE")));
    }
}
