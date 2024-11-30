function loadFooter() {
    fetch('/html/footer/footer.html')
        .then(response => {
            if (!response.ok) {
                throw new Error('푸터 파일 로드 실패');
            }
            return response.text();
        })
        .then(html => {
            const footerContainer = document.createElement('div');
            footerContainer.innerHTML = html;
            document.body.appendChild(footerContainer);
            loadFooterCSS(); // CSS 로드
        })
        .catch(error => console.error('푸터 로드 오류:', error));
}

function loadFooterCSS() {
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = '/css/footer/footer.css';
    document.head.appendChild(link);
}

// DOMContentLoaded 이벤트에서 Footer 로드
document.addEventListener('DOMContentLoaded', loadFooter);