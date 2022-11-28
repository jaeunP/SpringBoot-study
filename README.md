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
입력한 데이터에 id값이 들어갈 경우 수정해버리는 경우가 발생해서<br>
`if(article.getId() != null){return null;}`로 id값이 입력될경우에도 잘못된 요청으로 처리

<b>service를 통해서 CRUD 구현</b>
ResponseEntity로 상태코드를 반환하도록 구현

----
2022-11-22<br>
<b>댓글 Entity 작성</b><br>
@ManyToOne을 통해 여러 댓글 Entity가 하나의 Article로 관련되게 설정<br>
@JoinColumn을 통해 article의 id를 받아옴

<b>댓글 Repository</b><br>
JpaRepository를 사용<br>
JpaRepository: 페이지처리와 정렬기능을 제공<br>
query문을 통해서 직접 메소드 작성(SQL로 작성)<br>
SQL을 orm.xml로 작성<br>

----
2022-11-23<br>
**댓글 DTO, Service, Controller 생성**

**특정 게시물의 댓글 목록 조회 구현**<br>
목록을 조회하기에 List 사용<br>
stream을 사용해서 Entity를 Dto로 변환(람다식 사용) -> collect로 List로 반환<br>

**댓글 생성 기능 구현**<br>
Repository에서 articleId를 가져와 게시글 조회 및 예외발생<br>
Entity를 생성, 저장하고 DTO로 변환하여 저장<br>

**댓글 수정 기능 구현**<br>
Repository에서 댓글을 불러오고 patch 메소드를 통하여 수정<br>
patch 메소드는 nickname과 body가 null이 아니라면 새로운 값으로 변경하는 메소드<br>
다시 Repository에 저장<br>
Entity를 Dto로 변환하여 리턴<br>

**댓글 삭제**
Repository에서 댓글을 불러와서 DB에서 데이터를 삭제 후 DTO 반환<br>
댓글을 불러올 때 ID가 없다면 예외 발생<br>

----
2022-11-24<br>
**댓글 목록 view**<br>
기존의 게시글 show 페이지에 댓글 페이지를 연결<br>
DTO를 view로 띄우기 위해 DTO를 model로 등록<br>
commentDtos를 불러와 댓글 목록을 가져옴<br>

----
2022-11-25<br>
**댓글 생성**<br>
댓글 폼에서 전송버튼을 클릭하였을 때 REST API 요청이 보내지도록 작성<br>
document.querySelector를 사용하여 버튼 변수화<br>
.addEventListener을 통해서 클릭 이벤트를 감지<br>
클릭시 input에 있는 데이터를 객체로 생성<br>
fetch()를 사용하여 POST 형식으로 JSON 데이터로 전송<br>
alert메세지를 통하여 전송 결과에 따른 메시지 출력<br>
메세지 전송 후 자동으로 새로고침 되도록 window.location.reload()사용

----
2022-11-26<br>
**댓글 수정**<br>
**view부분**<br>
BootStrap의 모달 사용<br>
모달 변수화<br>
모달이 보여질 때 데이터를 가져와서 모달 안에 출력되도록 작성<br>
수정 버튼을 변수화<br>
클릭시 수정된 댓글을 객체로 생성
객체를 fetch()를 통해서 REST API 호출<br>
alert메세지를 통하여 전송 결과에 따른 메시지 출력 후 새로고침<br>

----
2022-11-27<br>
**댓글 삭제**<br>
삭제 버튼 추가하고 삭제버튼 변수화<br>
document.querySelector이 첫번째 버튼만 인식<br>
수정버튼의 경우 bootstrap의 modal에 들어있는 JS 때문에 Selector만으로 모든 버튼이 변수화 되었다<br>
따라서 querySelectorAll을 사용<br>
배열형태로 받아오기 때문에 forEach()를 사용하여 각 요소에 개별적으로 함수 실행<br>
삭제 댓글의 id를 가져오고 REST API 호출<br>
remove()를 사용하여 새로고침하지 않고 화면에서 삭제된 댓글을 지움<br>

----
20220-11-28
**My SQL 연동**
My SQL 의존성 추가<br>
properties에 계정 추가<br>
`spring.datasource.initialization-mode=never`로 초기화 설정을 끄고<br>
`spring.jpa.hibernate.ddl-auto=update`로 데이터 변경시 변경사항만 저장하도록 설정<br>




    














 








