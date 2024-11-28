document.addEventListener("DOMContentLoaded", () => {
    fetch('/html/header.html') // header.html 경로에 맞게 수정
        .then(response => {
            if (!response.ok) {
                throw new Error('헤더를 로드하는 데 실패했습니다.');
            }
            return response.text();
        })
        .then(data => {
            document.querySelector(".header-container").innerHTML = data;
            initializeHeader(); // 헤더 로드 후 초기화 함수 호출
        })
        .catch(error => console.error('헤더 로드 오류:', error));
});

function initializeHeader() {
    const accessToken = localStorage.getItem('accessToken');
    const loginBtn = document.getElementById('login-btn');
    const signupBtn = document.getElementById('signup-btn');
    const userInfo = document.getElementById('user-info');
    const memberName = document.getElementById('member-name');

    // 토큰 확인 및 사용자 정보 로드
    if (accessToken) {
        fetch('/api/v1/member/me', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${accessToken}`
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
                console.error('사용자 정보 로드 오류:', error);
                alert('사용자 정보를 불러오는 데 실패했습니다.');
            });
    } else {
        loginBtn.style.display = 'block';
        signupBtn.style.display = 'block';
        userInfo.style.display = 'none';
    }
}

function toggleModal() {
    const modal = document.getElementById('logout-modal');
    modal.style.display = modal.style.display === 'block' ? 'none' : 'block';
}

function logout() {
    localStorage.removeItem('accessToken');
    alert('로그아웃 되었습니다.');
    location.reload();
}

// 아무데나 클릭하면 모달 닫힘
document.addEventListener('click', (event) => {
    const modal = document.getElementById('logout-modal');
    const userName = document.getElementById('member-name');
    if (modal.style.display === 'block' && !modal.contains(event.target) && event.target !== userName) {
        modal.style.display = 'none';
    }
});

// CSS 파일을 동적으로 추가하는 함수
function loadCSS(cssFilePath) {
    // <link> 요소 생성
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = cssFilePath;
    link.type = 'text/css';
    // <head>에 추가
    document.head.appendChild(link);
}

// CSS 파일 로드
loadCSS('/css/index.css'); // 원하는 경로의 CSS 파일을 입력하세요