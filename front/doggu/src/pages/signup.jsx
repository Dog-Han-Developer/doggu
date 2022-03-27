import React, { useState } from 'react';
import {
  BorderButton,
  DogguAlert,
  MainTitle,
  PasswordInput,
  StandardInput,
  SubTitle,
  SignupInputButton,
  SignupButton,
} from '../components';
import inputValidator from '../utils/input-validator';

const Signup = () => {

  return (
    <main className="flex bg-brown-100 w-screen justify-center lg:px-10">
      <article className="flex w-5/6 h-screen items-center justify-center">
        <section className="bg-brown-300 container w-1/3 h-5/6 rounded-lg flex justify-center items-center">
        
        <div className='p-5 lg:w-4/5'>
        <MainTitle title="회원가입" />
        <SignupInputButton text="이메일" placeholder="이메일을 입력해주세요." id="email" buttonText="인증요청" />
        <SignupInputButton text="인증번호" placeholder="인증번호를 입력해주세요." id="verificationCode" buttonText="확인" />
        <SignupInputButton text="사용자 이름" placeholder="사용자 이름을 입력해주세요." id="id" buttonText="중복체크" />
        <StandardInput text="비밀번호" placeholder="비밀번호를 입력해주세요." id="pw"/>
        <StandardInput text="비밀번호 확인" placeholder="비밀번호를 다시 입력해주세요." id="pw"/>
        <SignupButton title="회원가입" />
        </div>
        
        </section>
      </article>
      <DogguAlert
      />
    </main>
  );
};

export default Signup;
