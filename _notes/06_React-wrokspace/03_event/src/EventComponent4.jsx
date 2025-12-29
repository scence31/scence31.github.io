const EventComponent4 = () => {

    const submitTest = (e) => {

        // 기본이벤트 있는 요소에 클릭이벤트 걸면
        // 내가 부여한 클릭이벤트 먼저 실행된 후 기본이벤트 발생

        // 기본이벤트 막는 구문 적으면 됨
        e.preventDefault();

        // 기본이벤트 제거하고, ajax로 기능 부활

    }

    return (
        <div>
            <form>
                {/*
                    form 태그 내부는 input, button, textarea 요소 등을 조합해서 입력양식을 구성함

                    마지막엔 submit 버튼 클릭하면 기본이벤트로 인해 controller로 자동으로 요청 넘어감

                    입력양식을 구현했다 치고 submit 버튼만 구성해보자 => 화면 깜빡거림
                    => 기본이벤트 제거해야 함
                */}

                <button type="submit" onClick={submitTest}>
                    제출
                </button>
            </form>
        </div>


    )
}

export default EventComponent4