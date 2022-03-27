import React from 'react';

const SignupButton = ({ title }) => {
  return (
    <button className="p-2 bg-brown-800 w-full rounded-lg text-white text-sm hover:bg-white/20">
      {title}
    </button>
  );
};

export default SignupButton;
