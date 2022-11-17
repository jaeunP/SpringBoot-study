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
Model은  Redirect된 파라미터를 전달할 수 없기에 RedirectAttributes의 addFlashAttribute를 사용하여 1회성 메세지 전달




