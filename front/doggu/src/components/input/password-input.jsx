import React from 'react';

const PasswordInput = ({ text, id, placeholder }) => {
  return (
    <div className="my-4">
      <label htmlFor={id} className="text-white">
        {text}
      </label>
      <input type="password" id={id} className="w-full rounded-lg h-8" placeholder={placeholder} />
    </div>
  );
};

export default PasswordInput;
