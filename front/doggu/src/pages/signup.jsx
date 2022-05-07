import React, { useEffect, useState } from 'react';
import {
  BorderButton,
  DogguAlert,
  MainTitle,
  PasswordInput,
  StandardInput,
  SubTitle,
  SignupInputButton,
  SignupButton,
  Switch,
} from '../components';
import inputValidator from '../utils/input-validator';

const Signup = () => {
  const [values,setValue] = useState({email:"",verificationCode:"",id:"", pwd:"", pwdCheck:""});

  const handleChange = (event) => {
      const {name,value} = event.target;
      setValue({...values, [name]:value}); 
  }

  useEffect(() => {
    console.log(values);
  }, [values]);

  const validateLoginForm = (id, password) => {
    console.log(id,password)
    if (inputValidator.checkEmail(id) && inputValidator.checkPassword(password)) {
      console.log('통과');
    } else {
      console.log('!!!')
    }
  };

  return (
    <main className="flex bg-brown-100 w-screen justify-center lg:px-10">
      <article className="flex w-5/6 h-screen items-center justify-center ">
        <section className="bg-brown-300 container w-2/3 h-5/6 rounded-lg flex justify-center items-center xl:w-1/3 transform duration-300 ease-in-out">
        
        <div className='p-5 lg:w-4/5'>
        <MainTitle title="회원가입" />
        <SignupInputButton text="이메일" placeholder="이메일을 입력해주세요." id="email" buttonText="인증요청" handleChange={handleChange} />
        <SignupInputButton text="인증번호" placeholder="인증번호를 입력해주세요." id="verificationCode" buttonText="확인" handleChange={handleChange} />
        <SignupInputButton text="사용자 이름" placeholder="사용자 이름을 입력해주세요." id="id" buttonText="중복체크" handleChange={handleChange} />
        <StandardInput text="비밀번호" placeholder="비밀번호를 입력해주세요." id="pwd" handleChange={handleChange}/>
        <StandardInput text="비밀번호 확인" placeholder="비밀번호를 다시 입력해주세요." id="pwdCheck" handleChange={handleChange}/>
        <div>
        <Switch/>
        </div>
        

        <SignupButton title="회원가입" onClick={(e) => {
                e.preventDefault();
                validateLoginForm(values.id, values.pwd);
              }} />
        </div>
        
        </section>
      </article>
      <DogguAlert
      />
    </main>
  );
};

export default Signup;
