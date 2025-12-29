// 자바스크립트의 선언적함수드 작성하기
// 안녕, 잘가, 변수 버튼 클릭시 실행할 함수
/*
export function sayHello(name) {
    alert(`Hello! ${name}`);
}
export function sayBye(name) {
    alert(`Bye! ${name}`);
}
export let num = 1;
*/

// 한번에 export 하기
function sayHello(name) {

    alert(`Hello ${name}`);
}

function sayBye(name) {

    alert(`Bye ${name}`);
}

let num = 22;

// export {sayHello, sayBye, num};
// 한번에 export 해도 import 각각할 수 있음

// 내보내야 하는 것들이 많을 경우
// 별칭으로 축약 가능
export {sayHello as hi, sayBye as bye, num as n};