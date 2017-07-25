import java.util.Scanner;

/**
 * Created by Administrator on 2017/4/7.
 */
public class NKtest {

    /**
     * 给定一个字符串，请你将字符串重新编码，将连续的字符替换成“连续出现的个数+字符”。比如字符串AAAABCCDAA会被编码成4A1B2C1D2A。
     * 输入 AAAABCCDAA
     * 输出 4A1B2C1D2A
     */
    public void test01() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[] inputArr = input.toCharArray();
        String[] strings = new String[inputArr.length];
        Integer index = 0;
        for (char arr : inputArr) {
            strings[index] = String.valueOf(arr);
            index++;
        }
        String result = "";
        Integer count = 0;
        Integer length = 0;
        for (int i = 0; i < strings.length; i++) {
            count++;
            if (i == strings.length - 1) {
                result += (i + 1 - length) + strings[i];
                break;
            }
            if (!strings[i + 1].equals(strings[i])) {
                result += count + strings[i];
                count = 0;
                length = i + 1;
            }
        }
        System.out.println(result);
    }
}
