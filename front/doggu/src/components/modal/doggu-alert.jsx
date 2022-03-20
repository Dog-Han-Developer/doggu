import React from 'react';

const DogguAlert = ({ open, handleModalOpen, alertHeader, alertMessage }) => {
  if (open) {
    // 모달 창 오픈시 스크롤 방지
    document.body.style.overflow = 'hidden';
  } else {
    // 스크롤 방지 해제
    document.body.style.overflow = 'unset';
  }

  return (
    <>
      {open && (
        <>
          <div className="fixed bg-black/50 inset-0" onClick={handleModalOpen} />
          <article className="fixed z-50 inset-y-2/4 w-96 h-min bg-brown-100 rounded text-center">
            <header className="bg-brown-800/50">
              <h2 className="p-1 text-center text-white font-semibold text-lg">{alertHeader}</h2>
            </header>
            <div className="h-0.5 bg-brown-800/50" />
            <p role="alert" className="text-lg mt-3">
              {alertMessage}
            </p>
            <footer className="p-4">
              <button
                onClick={handleModalOpen}
                aria-label="알림 닫기"
                className="rounded-lg w-12 outline outline-2 outline-offset-2 text-brown-800"
              >
                &#9587;
              </button>
            </footer>
          </article>
        </>
      )}
    </>
  );
};

export default DogguAlert;
