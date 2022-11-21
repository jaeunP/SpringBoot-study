# SpringBoot-study
스프링 공부

------
2022-11-15<br>
<b>controller 구현</b>

----

2022-11-16<br>
<b>form 태그를 이용하여 데이터를 받고 이를 컨트롤러에서 확인</b><br>
form데이터가 전송이 되고 controller 이를 객체에 담아 받는데 그 객체를 dto라고 한다

<b>JPA를 통하여 DB에 데이터 저장</b><br>
데이터 저장과정<br>
DTo를 Controller를 통해 Entity로 변환 -> Repository를 통해 Entity를 DB에 저장

<b>Lombok을 이용해 리펙토링</b><br>
@AllArgsConstructor,@NoArgsConstructor, @ToString 사용<br>
@AllArgsConstructor => 모든 필드 값을 파라미터로 받는 생성자를 만듦<br>
@NoArgsConstructor => 파라미터가 없는 기본 생성자를 생성<br>
@ToString => toString() 메서드를 자동생성

<b>JPA를 통하여 데이터 조회</b><br>
url요청을 파라미터로 받아올 때는 @PathVariable 사용<br>
데이터 조회과정<br>
ID를 통해 데이터를 가져옴<br>
Repository에서 findById 메서드를 통하여 데이터를 조회<br>
데이터를 모델에 등록<br>
등록된 데이터를 뷰페이지로 연결<br>

<b>전체 페이지 조회</b><br>
Repository에서 Article을 가져와 리스트로 저장(타입일치 필요)<br>
데이터를 모델에 등록<br>
등록된 데이터를 뷰페이지로 연결<br>

----
2022-11-17<br>
<b>페이지 간의 연결</b><br>
링크와 리다이렉트를 사용

<b>데이터 수정, 삭제 기능 구현</b><br>
<b>UPDATE 처리</b><br>
기존 데이터를 불러오고 if문 사용하여 데이터가 null이 아니라면 DB갱신<br>

<b>삭제과정</b><br>
기존 데이터를 불러오고 if문을 사용하여 데이터 삭제<br>
삭제 메세지를 출력할때 Model을 사용하려 했으나 <br>
Model은  Redirect된 파라미터를 전달할 수 없기에 RedirectAttributes의 addFlashAttribute를 사용하여 
1회성 메세지 전달

<b>DB에서 ID값 자동생성하도록 변경</b><br>
데이터를 새로 생성할 때 ID가 중복되는 오류가 발생하여
@GeneratedValue(strategy = GenerationType.IDENTITY)를 사용하여 DB를 통해 ID를 자동 생성하도록 설정<br>
이후에도 오류가 발생하여 찾아보니 h2의 버전문제로 properties url 설정 부분에 ;MODE=MySQL을 추가햐여 해결

<b>로깅 기능 추가</b>

----
2022-11-18<br>
<b>REST API로 CREATE, READ 기능</b><br>
데이터 요청,처리 부분만 구현<br>
REST API: 웹 서버의 자원을 클라이언트에 구애받지 않고 사용할수 있게 하는 설계 방식<br>
@RestController는 데이터를 JSON으로 반환하기 때문에 데이터를 던질때 @RequestBody를 사용함

----
2022-11-19<br>
<b>REST API로 UPDATE 기능</b><br>
dto를 requestBody로 받아 Entity로 저장<br>
Entity를 조회<br>
ResponseEntity에 Article데이터가 담겨서 json으로 반환<br>

수정 시 수정되지 않는 데이터는 값으로 전송되지 않는데 전송 되지않는 값이 null값으로 반환됨
Artice Entity에 patch메소드 추가<br>
patch메소드는 기존에 데이터가 있다면 그 값을 그대로 불러오도록 작성<br>
target.patch(article)로 patch메소드를 통해 데이터가 수정되지 않았으면 기존 값을 불러오도록 설정<br>

----
2022-11-20<br>
<b>REST API로 DELETE 기능</b><br>
repository에서 대상을 찾아 target에 저장<br>
target == null일 경우 오류메세지 출력<br>
delete로 삭제<br>
성공 상태메시지로 리턴, body에 담겨야할 json데이터가 없음으로 build로 바로 실행<br>

----
2022-11-21<br>
<b>Service 추가</b><br>
service를 통해서 전체목록, 상세페이지 영역 분할<br>




 








