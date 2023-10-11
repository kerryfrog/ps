import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] magnets = new int[4][8];
	static int[] tops = new int[4];
	static int T;

	public static int findIdx(int a, int b) {
		int next = a + b;
		if (next < 0)
			next = 8 + next;
		next = next % 8;
		return next;
	}

	// 1: �ð� -1: �ݽð�
	public static void move(int target, int direction) {
		// ���� ȸ�� ��
		int beforeTop = tops[target];
		int next = findIdx(tops[target], direction);

		// ȸ��
		tops[target] = next;

		int frontType = magnets[target][findIdx(beforeTop, 6)];
		int frontRotate = direction;
		// ���� ���� ����
		for (int i = target - 1; i >= 0; i--) {
			if (magnets[i][findIdx(tops[i], 2)] == frontType)
				break;
			else {
				frontType = magnets[i][findIdx(tops[i], 6)];
				frontRotate = -1 * frontRotate;
				tops[i] = findIdx(tops[i], frontRotate);

			}
		}

		frontType = magnets[target][findIdx(beforeTop, 2)];
		frontRotate = direction;
		// ���� ���� ������
		for (int i = target + 1; i < 4; i++) {
			if (magnets[i][findIdx(tops[i], 6)] == frontType)
				break;
			else {
				frontType = magnets[i][findIdx(tops[i], 2)];
				frontRotate = -1 * frontRotate;
				tops[i] = findIdx(tops[i], frontRotate);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tops = new int[4];

		for (int i = 0; i < 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				magnets[i][j] = line.charAt(j) -'0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			move(target - 1, dir * (-1));
		}
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			if (magnets[i][tops[i]] == 1) {
				answer += Math.pow(2, i);
			}
		}

		System.out.println(answer);
	}
}