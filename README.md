## Lotto System

### 로또 번호 조회 / 사용자의 등수 확인 프로그램

＊ 입력한 회차의 로또 당첨 번호를 조회한다.   
＊ 응모한 번호를 입력하고 ‘결과확인’ 버튼을 누르면 당첨 여부를 알 수 있다.   
＊ 동행복권 ( https://nlotto.co.kr/ ) 당첨번호 페이지에서 JSON 데이터를 파싱하여 출력한다.   
＊ ‘초기화’ 버튼을 누르면 화면이 초기화 된다.   
   
   
![1](https://user-images.githubusercontent.com/61627637/85242726-c6a60400-b47a-11ea-9039-b1bdd80d7267.PNG)
   
```c
	MyButton[] mbt = new MyButton[7];

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
  ```
     
![2](https://user-images.githubusercontent.com/61627637/85242818-053bbe80-b47b-11ea-950e-bee457300400.PNG)
   

![3](https://user-images.githubusercontent.com/61627637/85242822-079e1880-b47b-11ea-97ba-02c3d9582ecd.PNG)
   

![4](https://user-images.githubusercontent.com/61627637/85242831-0967dc00-b47b-11ea-9c62-bb30df21e5d2.PNG)
   
