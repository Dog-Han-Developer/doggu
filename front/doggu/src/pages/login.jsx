import React, { useState } from 'react';
import {
  BorderButton,
  DogguAlert,
  MainTitle,
  PasswordInput,
  StandardInput,
  SubTitle,
} from '../components';
import inputValidator from '../utils/input-validator';

const Login = () => {
  const [modalOpen, setModalOpen] = useState(false);

  const validateLoginForm = (id, password) => {
    if (inputValidator.checkEmail(id) && inputValidator.checkPassword(password)) {
      console.log('통과');
    } else {
      handleModalOpen();
    }
  };

  const handleModalOpen = () => {
    setModalOpen(!modalOpen);
  };

  return (
    <main className="flex bg-brown-100 w-screen justify-center lg:px-10">
      <article className="flex w-5/6 h-screen items-center">
        <img
          className="hidden xl:flex container h-5/6 rounded-l-lg"
          src="/images/doggu.jpg"
          alt="로그인페이지 안내 이미지"
        />
        <section className="bg-brown-300 container h-5/6 rounded-r-lg flex justify-center items-center">
          <div className="p-5 lg:w-4/5">
            <MainTitle title="로그인" />
            <form
              onSubmit={(e) => {
                e.preventDefault();
                validateLoginForm(e.target.id.value, e.target.pwd.value);
              }}
            >
              <StandardInput text="이메일" placeholder="example@doggu.com" id="id" />
              <PasswordInput
                text="비밀번호"
                placeholder="영문,숫자 포함 8자 이상 12자 이하"
                id="pwd"
              />
              <BorderButton type="submit" title="로그인" />
            </form>
            <footer className="py-5">
              <div className="h-0.5 bg-brown-800/50" />
              <SubTitle title="아직 회원이 아니멍?🐶" />
              <BorderButton title="회원가입하기" />
            </footer>
          </div>
        </section>
      </article>
      <DogguAlert
        open={modalOpen}
        handleModalOpen={handleModalOpen}
        alertHeader="알림"
        alertMessage="올바른 아이디와 패스워드를 입력해주세요."
      />
    </main>
  );
};

export default Login;
