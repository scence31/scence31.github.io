const MemberEnrollFormComponent = () => {

    return (
        <div>
            <h1>회원가입 페이지</h1>
            <form>
                <table>
                    <tbody>
                        <tr>
                            <th>아이디</th>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호</th>
                            <td>
                                <input type="password" />
                            </td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td>
                                <input type="email" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <br />

                <button type="submit">회원가입</button>
            </form>
        </div>
    )
}

export default MemberEnrollFormComponent