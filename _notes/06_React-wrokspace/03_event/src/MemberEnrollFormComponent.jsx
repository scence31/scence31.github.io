const MemberEnrollFormComponent = () => {

    // 실행할 구문
    // id, pwd 유효한지 저장할 목적의 변수
    let checkArr = [false, false, false];
    // [0] - id검사 결과
    // [1] - pwd 검사 결과
    // [2] - pwd 확인 결과

    // 비밀번호 변경될 때마다 비밀번호 == 비밀번호확인 검사

    const insertMember = (e) => {

        e.preventDefault();

        // 회원가입 결과 alert 처리
        if(checkArr[0] && checkArr[1] && checkArr[2] == true) {

            alert('회원가입 성공');

        } else {

            alert('회원가입 실패');
        }

    }

    const checkPwd2 = (e) => {

        let checkPwd = e.target.value;

        // 비밀번호 값은 id 속성 부여 후 골라내기
        let userPwd = document.querySelector("#userPwd").value;

       //  console.log(userPwd, checkPwd);

       // 결과출력할 요소 선택
       const result3 = document.querySelector("#result3");

       if(userPwd == checkPwd) {

            result3.innerText = '일치';

            checkArr[2] = true;

       } else {

            result3.innerText = '일치x';

            checkArr[2] = false;

       }
    }

    const checkPwd1 = (e) => {

        let userPwd = e.target.value;

        // 비밀번호 검사용 정규표현식 세팅 후 검사
        let regExp = /^[a-z0-9!@#$%^]{8,20}$/i;

        // 결과를 출력할 요소 선택
        const result2 = document.querySelector("#result2");

        if(regExp.test(userPwd)) {

            result2.innerText = '유효 pwd';

            checkArr[1] = true;

        } else {

            result2.innerText = '유효하지 않은 pwd';

            checkArr[1] = false;

        }
    }

    // onBlur 이벤트 세팅
    const checkId = (e) => {

        // console.log('?');

        // e.target이 곧 input 요소
        let userId = e.target.value;
        // console.log(userId);

        // 아이디 체크용 정규표현식 세팅 후 검사하기
        let regExp = /^[a-z0-9]{5,20}$/;

        // 결과를 출력할 요소객체 끌고오기
        const result1 = document.getElementById('result1');

        if(regExp.test(userId)) {

            result1.innerText = '아이디 유효';
            result1.style.color = 'green';

            checkArr[0] = true;

        } else {

            result1.innerText = '유효하지 않은 id';

            checkArr[0] = false;
        }

    }

  

  return (
    <div>
        <h4>회원가입</h4>
        <form>
            <table>
                <tbody>
                    <tr>
                        <td width={120}>아이디</td>
                        <td>
                            <input type="text" onBlur={checkId} />
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                            <span id="result1"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <input type="password" onBlur={checkPwd1} id='userPwd' />
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                            <span id='result2'></span>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호 확인</td>
                        <td>
                            <input type="password" onBlur={checkPwd2} />
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                            <span id='result3'></span>
                        </td>
                    </tr>
                    <tr>
                        <td>이름</td>
                        <td>
                            <input type="text" />
                        </td>
                    </tr>
                </tbody>
            </table>

            <br />

            <button type="submit" onClick={insertMember}>회원가입</button>
        </form>
    </div>
  );
};

export default MemberEnrollFormComponent