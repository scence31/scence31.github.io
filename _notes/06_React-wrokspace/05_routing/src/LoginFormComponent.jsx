const LoginFormComponent = () => {

    return(
        <div>
            <h1>로그인</h1>
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
                    </tbody>
                </table>

                <br />

                <button type="submit">로그인</button>
            </form>
        </div>
    )
}

export default LoginFormComponent