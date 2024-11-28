// 모달 표시/숨기기 토글 함수
function toggleModal() {
    const modal = document.getElementById('logout-modal');
    if (modal.style.display === 'none' || modal.style.display === '') {
        modal.style.display = 'block';
    } else {
        modal.style.display = 'none';
    }
}

// 로그아웃 함수
function logout() {
    // 로컬 스토리지에서 토큰 제거
    localStorage.removeItem('accessToken');
    localStorage.removeItem('accessTokenExpiration');
    alert('로그아웃 되었습니다.');
    location.reload(); // 페이지 새로고침
}

// 페이지 어디를 클릭해도 모달이 닫히도록
document.addEventListener('click', (event) => {
    const modal = document.getElementById('logout-modal');
    const userName = document.getElementById('member-name');
    if (modal.style.display === 'block' && !modal.contains(event.target) && event.target !== userName) {
        modal.style.display = 'none';
    }
});