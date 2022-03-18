import React from 'react';

const StandardInput = ({ text, id, placeholder }) => {
  return (
    <div className="my-4">
      <label htmlFor={id} className="text-white">
        {text}
      </label>
      <input type="text" id={id} className="w-full rounded-lg h-8" placeholder={placeholder} />
    </div>
  );
};

export default StandardInput;
