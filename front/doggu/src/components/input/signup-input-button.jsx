import React from 'react';

const SignupInputButton = ({ text, id, placeholder, buttonText }) => {
  return (
    <div className="my-4">
      <label htmlFor={id} className="text-white ">
        {text}
      </label>
      <div>
        <input type="text" id={id} className="w-4/5 rounded-lg h-8 text-gray text-sm" placeholder={placeholder} />
        <button className='w-1/5 h-8  rounded-lg bg-brown-800 text-white text-xs hover:bg-white/20'>{buttonText}</button>
      </div>
      
    </div>
  );
};

export default SignupInputButton;
