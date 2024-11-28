document.addEventListener('DOMContentLoaded', () => {
    const accessToken = localStorage.getItem('accessToken');
    const expirationTime = localStorage.getItem('accessTokenExpiration');
    const loginBtn = document.getElementById('login-btn');
    const signupBtn = document.getElementById('signup-btn');
    const userInfo = document.getElementById('user-info');
    const memberName = document.getElementById('member-name');

    // 토큰 만료 시간 확인
    function checkTokenExpiration() {
        const now = Date.now(); // 현재 시간
        if (expirationTime && now > parseInt(expirationTime, 10)) {
            // 토큰 만료 시 제거
            logout();
            alert('로그인 세션이 만료되었습니다. 다시 로그인해주세요.');
            return false; // 만료됨
        }
        return true; // 유효함
    }

    // 사용자 정보 요청
    function fetchUserInfo(token) {
        fetch('/api/v1/member/me', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                if (!response.ok) throw new Error('사용자 정보 요청 실패');
                return response.json();
            })
            .then(data => {
                loginBtn.style.display = 'none';
                signupBtn.style.display = 'none';
                memberName.textContent = `안녕하세요, ${data.data.memberName}님!`;
                userInfo.style.display = 'flex';
            })
            .catch(error => {
                console.error('오류:', error);
                alert('사용자 정보를 불러오는 데 실패했습니다.');
            });
    }

    if (accessToken) {
        // 토큰 만료 확인 후에만 사용자 정보 요청
        if (checkTokenExpiration()) {
            fetchUserInfo(accessToken);
        } else {
            location.reload(); // 페이지 새로고침
        }
    } else {
        // accessToken이 없으면 로그인/회원가입 버튼 표시
        loginBtn.style.display = 'block';
        signupBtn.style.display = 'block';
        userInfo.style.display = 'none';
    }
});

function logout() {
    // 로그아웃 로직: 토큰 제거 및 페이지 새로고침
    localStorage.removeItem('accessToken');
    localStorage.removeItem('accessTokenExpiration');
    alert('로그아웃 되었습니다.');
    location.reload();
}