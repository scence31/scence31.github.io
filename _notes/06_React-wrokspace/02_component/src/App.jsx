import './App.css'
import MyComponent1 from "./MyComponent1.jsx";
import MyComponent2 from "./MyComponent2.jsx";
import MyComponent3 from "./MyComponent3.jsx";
// MyComponent.jsx 파일로부터 MyComponent1, 2 함수를 갖다 쓰겟다 import

// App 자바스크립트 함수
function App() {

  // 실행할 구문
  // DB 조회할 때 ajax 필수. 5명 세팅
  // [{} {} {} {} {}] 형식으로 받아올 것 JSON 형식
  const memberList = [];

  memberList.push({name : '홍길동', gender : "남자", age : '20'}); // [0]
  memberList.push({name : '박말순', gender : "여자", age : '33'}); // [1]
  memberList.push({name : '이순신', gender : "남자", age : '43'}); // [2]
  memberList.push({name : '고영희', gender : "여자", age : '29'}); // [3]
  memberList.push({name : '김말똥', gender : "남자", age : '37'}); // [4]

  // 반복문으로 정보들을 MyComponent3 호출구문으로 옮기기
  // 1. 반복문
  /*
  let myComponent3List = [];

  for(let i = 0; i < memberList.length; i++) {
    // 조회한 회원 수만큼 반복 진행
    // i번째 회원 한 명의 정보를 MyComponent3으로 옮기기
    
    myComponent3List.push(<MyComponent3 key={i} name={memberList[i].name} gender={memberList[i].gender} age={memberList[i].age} />);
    // key 속성으로 겹치지 않게 unique 붙여주는 것 권장. 추후 버추얼 DOM이 요소 탐색에 효율적으로 할 수 있도록 유도
  }
  */

  // 이 시점 기준으로 myComponent3List 배열에는
  // [<MyComponent3 />, ...]

  // 2. map 함수
  /*
  let myComponent3List = memberList.map((item, index) => {

    
    // return (
    //   <MyComponent3 key={index} name={item.name} gender={item.gender} age={item.age} />
    // );
    

    // 짝수는 남자 홀수는 여자
    if(index % 2 == 1) {

      return (

        <MyComponent3 key={index} name={item.name} age={item.age} gender='여자' />
      )

    } else {

      return (

        <MyComponent3 key={index} name={item.name} age={item.age} gender='남자' />
      )

    }


  });
  */

  // 3. myComponent3List 변수를 사용하지 않고 바로 로직에 적용



  // return 구문 - JSX 형식으로 화면 구현
  return (

    <div>
      <h1>리액트로 화면 구현하기</h1>

      <h3>* Component</h3>

      <p>
        - 리액트 화면을 이루는 최소단위. 하나의 item(항목)을 출력할 수 있는 템플릿 <br />

        - 화면 구현의 재사용성을 높여서 협업을 효율적으로 할 수 있고, 유지보수 용이함 <br />

        예) 썸네일 게시판 목록에서 게시글 하나에 대한 요소, 메뉴바, 푸터, 헤더 등 <br />

        - 데이터를 각각 담아서 반복적으로 출력 가능한 경우 <br />
        - 한 번 만들어 두면 모든 페이지에서 사용 가능한 경우 <br />
      </p>
      
      <br /><hr />

      <h3>* 컴포넌트 작성방법</h3>

      <p>
        1. 자바스크립트의 function 형식으로 작성할 수 있다.(함수용 컴포넌트) <br />
        2. 자바스크립트의 class 형식으로 작성할 수 있다.(클래스형 컴포넌트) <br />

        <br />

        - 즉, 함수 또는 클래스 자바스크립트 코드 형식으로 자주 쓰일 컴포넌트(화면요소) 만들고, 필요할 때마다 호출해서 조립함 <br />
        - App도 함수형 컴포넌트임. main.jsx 파일에서 가져다 조립한 것임. <br />

        - 클래스형보다는 함수형 컴포넌트 사용을 권장  <br /><br />

        - 주의사항 <br />
        컴포넌트 이름은 대문자로 시작 <br />
        최대한 재사용할 수 있게 신중히 작성 
      </p>

      <br /><hr />

      <h3>1. 기본적인 함수형 컴포넌트 만들기</h3>

      {/*
        명심할 사항: App도 함수형 컴포넌트임.
      */}

      <MyComponent1 />
      {/* 함수형 컴포넌트를 갖다 쓸 경우 태그형식으로 호출 */}

      <br /><hr />

      <h3 style={{color: 'blue', backgroundColor: 'beige'}}>2. 스타일을 적용한 컴포넌트 만들기</h3>

      <p id="styleComponent">
        - 리액트에서도 마찬가지로 CSS를 적용해서 스타일 가능 <br />

        1. 외부스타일 방식 <br />
        - 별도의 css 파일 작성 후 import 구문으로 갖다 쓰기 <br />

        2. 인라인스타일 방식 <br />
        - 요소 시작태그에 스타일 속성 직접 기술 <br />
        - 근데 html 태그가 아닌 xml 태그기 때문에 <br />

        3. 내부스타일 방식 <br />
        - index.html 문서의 head 영역에 스타일 태그 작성

      </p>

      <MyComponent2 />

      <br /><hr />

      <h3>3. 데이터를 전달받는 컴포넌트 만들기</h3>

      <p>
          - 컴포넌트 사이에 부모, 자식 관계가 성립됨 <br />
          예) App 컴포넌트에서 MyComponent1, 2 컴포넌트를 갖다 쓰는중임. 부모/자식으로 불림 <br /> <br />
          
          - 부모컴포넌트에서 import 하는 css 파일 있으면 자식한테도 해당 css 적용됨 <br />
          마찬가지로 부모컴포넌트에서 자식으로 필요한 데이터가 있으면 전달할 수 있다. <br /> <br />
          
          - 함수형 컴포넌트를 기준으로, 이걸 갖다 쓰고싶으면 태그로 호출했음 <br />
          - 함수는 호출할 때 인자값 넘길 수 있고, 매개변수로 받을 수 있음(이걸 이용) <br /> <br />

          - 하위 자식컴포넌트로 데이터를 전달하고 싶다면 태그형식으로 호출할 때 속성명='속성값' 형태로 나열해서 전달 가능함 <br />
          - 그러면 자식은 전달받은 값을 함수형 컴포넌트 기준으로 매개변수로 받으면 됨
      </p>

      {/*
      <ul>
        <MyComponent3 name='홍길동' gender='남자' age='20' />
        <MyComponent3 name='박말순' gender='여자' age='33' />
        <MyComponent3 name='이순신' gender='남자' age='43' />
        <MyComponent3 name='고영희' gender='여자' age='29' />
        <MyComponent3 name='김말똥' gender='남자' age='37' />
      </ul>
      
      위 하드코딩 값은 DB에서 가져와야 함
      */}

      <h4>회원목록조회</h4>

      {/*
      <ul>
        {myComponent3List}
      </ul>
      */}

      <ul>
        {
          memberList.map((item, index) => {

            return ( <MyComponent3 key={index} name={item.name} gender={item.gender} age={item.age} />)
          })
          // map 변수 없이 바로 출력
        }
      </ul>


    </div>

  )
}

// 내보내기
export default App
