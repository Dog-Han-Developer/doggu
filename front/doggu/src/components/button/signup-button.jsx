import React from 'react';

const SignupButton = ({ title, onClick }) => {
  return (
    <button
      className="p-2 bg-brown-800 w-full rounded-lg text-white text-sm hover:bg-white/20"
      onClick={onClick}
    >
      {title}
    </button>
  );
};

export default SignupButton;
