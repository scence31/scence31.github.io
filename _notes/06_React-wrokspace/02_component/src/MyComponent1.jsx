// 가장 기본적인 함수형 컴포넌트 만들기
// 1. jsx 또는 js 파일 내부에 작성한다
// 2. 이름이 있는 선언적 함수로 만들기(함수명이 곧 컴포넌트명이 됨 - 명명규칙 대문자 시작)
// 3. 파일명과 컴포넌트 함수명이 달라도 상관없지만 일치시키는 것 권장.
// 4. jsx 파일 내 여러 개의 함수형 컴포넌트를 만들어도 되자만 1:1 매칭 권장.
// 5. 리턴 구문 안쪽에는 최종으로 감싸진 태그로 화면 리턴해야 함 ( Virtual DOM 떄문 )


// 파일명과 동일한 MyComponent1 함수를 선언 - 함수형 컴포넌트(레고블럭)
function MyComponent1() {

    // 이 함수가 호출될 때 실행할 코드
    // 1~10 중 짝수가 몇 개인지 카운트
    let count = 0;

    for(let i = 1; i <= 10; i++) {

        if(i % 2 == 0) {

            count++;
        }

    }

    // console.log(count);
    // 헷갈리면 중간에 console.log 디버깅

    // return 구문 - JSX 형식으로 화면 구성 (html 태그가 아니라 xml 태그로)
    return (
        <div>
            <div>나의 첫 번째 컴포넌트</div> <br />
            <h5>1부터 10까지 총 짝수 개수는 {count}개</h5>
        </div>


    )

}

// export 구문 마무리
export default MyComponent1;