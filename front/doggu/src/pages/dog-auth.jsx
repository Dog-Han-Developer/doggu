import React from 'react';
import { MainTitle, SignupButton, StandardInput } from '../components';

const DogAuth = () => {
  return (
    <main className="flex bg-brown-100 w-screen justify-center lg:px-10">
      <article className="flex w-5/6 h-screen items-center">
        <section className="bg-brown-300 container h-5/6 rounded-lg flex justify-center items-center">
          <div>
            <MainTitle title="반려견 조회" />
            <StandardInput
              text="🐾 동물등록번호"
              placeholder="등록번호를 입력해주세요."
              id="code"
            />
            <StandardInput text="😊 소유자 이름" placeholder="이름을 입력해주세요." id="owner" />
            <SignupButton title="반려견 등록" />
          </div>
        </section>
      </article>
    </main>
  );
};

export default DogAuth;
