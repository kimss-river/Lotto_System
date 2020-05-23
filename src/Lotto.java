import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.json.simple.JSONObject;

public class Lotto extends JFrame implements MouseListener, KeyListener {
	MyButton[] mbt = new MyButton[7]; // 당첨 번호 출력

	JButton checkBtn = new JButton("해당 회차 이동");
	JButton resultBtn = new JButton("결과확인");
	JButton resetBtn = new JButton("초기화");

	JTextField turnTxt = new JTextField();
	JTextField[] ipt = new JTextField[6];

	JLabel titleLbl = new JLabel("로또 번호 조회");
	JLabel turnLbl = new JLabel("");
	JLabel pLbl = new JLabel("+");
	JLabel numCheckLbl = new JLabel("내 번호 당첨 확인");
	JLabel dateLbl = new JLabel("");
	JLabel resultLbl = new JLabel("");

	public void init() {
		for (int i = 0; i < mbt.length; i++) {
			int wm = 70;

			mbt[i] = new MyButton("" + (i + 1));
			mbt[i].setBounds((20 + wm * i), 50, 60, 60);
			getContentPane().add(mbt[i]);

		}

		for (int i = 0; i < ipt.length; i++) {

			int wm = 70;

			ipt[i] = new JTextField();
			ipt[i].setBounds((20 + wm * i), (50 * 3), 60, 60);
			getContentPane().add(ipt[i]);

		}

		getContentPane().setLayout(null);
		titleLbl.setBounds((10 + 15), 10, 150, 30);
		getContentPane().add(titleLbl);

		int w = 60;
		int wm = 70;

		mbt[6].setBounds((20 + wm * 6 + 40), 50, 60, 60);

		turnLbl.setBounds((10 + wm * 2), 10, 200, 30);
		getContentPane().add(turnLbl);

		pLbl.setBounds((20 + wm * 6 + 10), 50, 60, 60);
		getContentPane().add(pLbl);

		dateLbl.setBounds((10 + wm * 3), 10, 150, 30);
		dateLbl.setFont(dateLbl.getFont().deriveFont(18f));
		getContentPane().add(dateLbl);

		resultLbl.setBounds(20, (w * 4), 350, 30);
		getContentPane().add(resultLbl);

		numCheckLbl.setBounds((10 + 15), (w * 2), 150, 30);
		getContentPane().add(numCheckLbl);

		turnTxt.setColumns(10);
		turnTxt.setBounds(150, (w * 5), 100, 50); // 시작포인트+길이+마진
		getContentPane().add(turnTxt);

		resultBtn.setBounds(450, (50 * 3), 100, 35);
		getContentPane().add(resultBtn);
		resetBtn.setBounds(450, (50 * 4), 100, 35);
		getContentPane().add(resetBtn);
		checkBtn.setBounds(280, (w * 5), 150, 50);
		getContentPane().add(checkBtn);

	}

	public void event() {
		checkBtn.addMouseListener(this);
		resultBtn.addMouseListener(this);
		resetBtn.addMouseListener(this);
		turnTxt.addKeyListener(this);
	}

	void clearNumber() {
		for (int i = 0; i < 7; i++) {

			mbt[i].setText("");

		}

	}

	void clearLbl() {
		turnLbl.setText("");
		dateLbl.setText("");
		resultLbl.setText("");
	}

	void clearTurnTxt() {
		turnTxt.setText("");
	}

	void showResult() {
		String turnNum = turnTxt.getText();
		try {
			int a = Integer.parseInt(turnNum);
			System.out.println(a);
			if (a < 0 || a > 10000) {
				System.out.println();
				turnLbl.setText("번호를 다시 입력해주세요");
				clearTurnTxt();
				return;
			}
		} catch (Exception e) {
			turnLbl.setText("번호를 다시 입력해주세요");
			turnTxt.setText("");
			return;
		}

		try {
			JsonReader jr = new JsonReader();
			JSONObject jo = jr.connectionUrlToJSON(turnTxt.getText());
			if (jo == null) {
				turnLbl.setText("접속에 실패하였습니다. 다시 시도해주세요.");
				return;
			}
			if (jo.get("returnValue").equals("fail")) {
				turnLbl.setText("회차정보가 없습니다.");
				clearNumber();
				return;
			}
			for (int i = 0; i < 6; i++) {
				int a1 = Integer.parseInt(String.valueOf(jo.get("drwtNo" + (i + 1))));
				if (a1 < 10) {
					mbt[i].setBgColor(new Color(252, 213, 71));
					mbt[i].setTxtColor(Color.white);
				} else if (a1 < 20) {
					mbt[i].setBgColor(new Color(112, 188, 255));
					mbt[i].setTxtColor(Color.white);
				} else if (a1 < 30) {
					mbt[i].setBgColor(new Color(255, 119, 112));
					mbt[i].setTxtColor(Color.white);
				} else if (a1 < 40) {
					mbt[i].setBgColor(Color.lightGray);
					mbt[i].setTxtColor(Color.white);
				} else {
					mbt[i].setBgColor(new Color(158, 232, 88));
					mbt[i].setTxtColor(Color.white);
				}
			}
			int a1 = Integer.parseInt(String.valueOf(jo.get("bnusNo")));
			if (a1 < 10) {
				mbt[6].setBgColor(new Color(252, 213, 71));
				mbt[6].setTxtColor(Color.white);
			} else if (a1 < 20) {
				mbt[6].setBgColor(new Color(112, 188, 255));
				mbt[6].setTxtColor(Color.white);
			} else if (a1 < 30) {
				mbt[6].setBgColor(new Color(255, 119, 112));
				mbt[6].setTxtColor(Color.white);
			} else if (a1 < 40) {
				mbt[6].setBgColor(Color.lightGray);
				mbt[6].setTxtColor(Color.white);
			} else {
				mbt[6].setBgColor(new Color(158, 232, 88));
				mbt[6].setTxtColor(Color.white);
			}

			for (int i = 0; i < mbt.length; i++) {
				mbt[i].setText(String.valueOf(jo.get("drwtNo" + (i + 1))));

			}

			mbt[6].setText(String.valueOf(jo.get("bnusNo")));

			turnLbl.setText(turnTxt.getText() + "회차"); // 회차
			clearTurnTxt();

			dateLbl.setText(String.valueOf(jo.get("drwNoDate"))); // 날짜 yyyy-mm-dd
			clearTurnTxt();

		} catch (Exception e) {
			e.printStackTrace();
			turnLbl.setText("예기치 못한 오류가 발생했습니다. 다시 시도해주세요.");
			return;
		}

	}

	void checkResult() {

		int cnt = 0;
		int overlapCnt = 0;// 중복값이 있는지 확인 2중 for break break
		boolean num = true;

		for (int i = 0; i < 6; i++) {
			int a2 = 0;
			try {
				a2 = Integer.parseInt(ipt[i].getText());
			} catch (Exception e) {
				resultLbl.setText("문자를 입력할 수 없습니다.");
				num = false;
				break;
			}
			if (a2 >= 1 && a2 <= 45) {
				for (int j = i + 1; j < 6; j++) {
					int a3 = 0;
					try {
						a3 = Integer.parseInt(ipt[j].getText());
					} catch (Exception e) {
						resultLbl.setText("문자를 입력할 수 없습니다.");
						num = false;
						break;
					}
					if (a2 == a3) {
						overlapCnt++;
						break;
					}
				}
				if (overlapCnt >= 1) {
					resultLbl.setText("중복된 값을 입력할 수 없습니다.");
					num = false;
					break;
				}

			} else {
				resultLbl.setText("1에서 45까지의 숫자를 입력해주세요.");
				num = false;
				break;
			}
		}
		if (num == true) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if ((Integer.parseInt(mbt[i].getText()) == (Integer.parseInt(ipt[j].getText())))) {
						resultLbl.setText(mbt[i].getText());
						cnt++;
						break;
					}
				}
			}
			switch (cnt) {
			case 6:
				resultLbl.setForeground(Color.red);
				resultLbl.setText("번호 " + cnt + "개 일치" + " " + "1등입니다!");
				break;
			case 5:
				for (int j = 0; j < 6; j++) {
					if ((Integer.parseInt(mbt[6].getText()) == (Integer.parseInt(ipt[j].getText())))) {
						resultLbl.setText(mbt[6].getText());
						cnt++;
						break;
					}
				}
				if (cnt == 6)
					resultLbl.setText("번호 " + cnt + "개와 보너스 번호 일치" + " " + "2등입니다!");
				else
					resultLbl.setText("번호 " + cnt + "개 일치" + " " + "3등입니다!");
				break;
			case 4:
				resultLbl.setForeground(Color.red);
				resultLbl.setText("번호 " + cnt + "개 일치" + " " + "4등입니다!");
				break;
			case 3:
				resultLbl.setForeground(Color.red);
				resultLbl.setText("번호 " + cnt + "개 일치" + " " + "5등입니다!");
				break;
			default:
				resultLbl.setForeground(Color.red);
				resultLbl.setText("번호 " + cnt + "개 일치" + " " + "낙첨입니다.");
				break;
			}
		}
	}

	public static void setUIFont(FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) { // 화면 전체에 적용
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, f);

		}
	}

	Lotto() {
		super("로또 번호 조회");
		// 화면구성 component 를 초기화
		init();
		// event를 등록
		event();

		setSize(600, 400); // 화면 사이즈 설정
		setVisible(true); // 생성, 화면에 보이게함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로그램 전체 종료
	}

	public static void main(String[] args) throws Exception {
		String fType = "EastSeaDokdo-Regular.ttf";
		// Exception 발생소지가 있으므로 try catch로 묶어주거나 내 메소드에 throws를 추가해 줌.
		Font f1 = Font.createFont(Font.TRUETYPE_FONT, new File(fType));
		setUIFont(new FontUIResource(f1.deriveFont(25f)));
		new Lotto();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			// 회차 결과를 버튼 1-7에 보여주기
			showResult();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			checkResult();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			// 화면을 초기화 한다. textfield, turn lbl, mbt 1~7
			clearNumber();
			clearLbl();
			clearTurnTxt();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
		if (e.getSource() == resultBtn) {
			checkResult();

		}
		if (e.getSource() == resetBtn) {
			clearNumber();
			clearLbl();
			clearTurnTxt();
		}
		if (e.getSource() == checkBtn) {
			showResult();// 회차 결과를 버튼 1-7에 보여주기
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered");
		if (e.getSource() == resultBtn) {

		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Exited");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Released");

	}

}
